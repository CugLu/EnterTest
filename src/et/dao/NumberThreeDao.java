package et.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.jdbc.TxQueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import et.domain.Plan;

/**
 * 
 * @author Yulong Yan
 * 
 */
public class NumberThreeDao {
	// 池类
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	// 声明PreparedStatement对象，用来向数据库发送SQL语句
	private static PreparedStatement pre;
	// 声明ResultSet对象,这是结果集
	private static ResultSet res;

	// 连接
	private static Connection conn;

	// 查询器
	private static QueryRunner qr = new TxQueryRunner();

	//年份
	private static Year yearTime=new Year();
	private static List<Year> yearList;
	
	public static int GetYear() throws SQLException{
		yearList=yearTime.GetYearList();
		return Integer.parseInt(yearList.get(0).getYear());
	}
	
	/**
	 * 输入查询专业
	 * @return
	 */
	/*public  String GiveMajor(){
		System.out.println("请输入你要查询的专业");
		Scanner in=new Scanner(System.in);
		String major=in.nextLine();
		in.close();
		return major;
	}*/
	
    /**
     * 根据输入的专业查询包含此专业的学校，并给出该学校的招生计划信息
     * 包括院校名称，专业名称，科类，备注
     * 输出样式   (学校，专业名，科类，备注)
     * @throws SQLException
     */
	public  Vector<Vector<String>>GetPlan(String str) throws SQLException{
		String sql="SELECT DISTINCT yxmc,zymc,kl,bz FROM yxjh_3 WHERE zymc=?";
		//查询
		pre = conn.prepareStatement(sql);
		pre.setString(1, str);
		//获得结果集
		res = pre.executeQuery();
		//将结果存入到Vector容器中
		Vector<Vector<String>> arraylist = new Vector<Vector<String>>();
		while(res.next()){
			Vector<String> array =new Vector<String>();
			array.add(res.getString("yxmc"));
			array.add(res.getString("zymc"));
			array.add(res.getString("kl"));
			array.add(res.getString("bz"));
			arraylist.add(array);
		}
		//将查询结果显示出来
		/*
		System.out.println("查询结果如下");
		for(int i=0;i<arraylist.size();++i){
			for(int j=0;j<arraylist.get(i).size();++j){
				System.out.print(arraylist.get(i).get(j)+"\t\t");
			}
			System.out.println();
		}
		*/
		return arraylist;
	}
	/**
	 * 根据输入的专业，求出去年该专业的录取平均分，该专业最低投档成绩....由于yxzs表中没有科类这一列，所以目前无法分别求出理工类和文史类各自的平均分
	 * 输出样式    (学校，平均分)
	 */
	public  Vector<Vector<String>> GetLastYearAve(String year, String major) throws SQLException{
		int Year = GetYear();
		String sql;
		if(Integer.parseInt(year)==Year-1)
			sql = "SELECT yxmc,AVG(tdcj) avg_grade,MIN(tdcj) min_grade  FROM yxzs_2 WHERE zymc=? GROUP BY yxmc";
		else
			sql = "SELECT yxmc,AVG(tdcj) avg_grade,MIN(tdcj) min_grade  FROM yxzs_1 WHERE zymc=? GROUP BY yxmc";		
		//查询
	    pre = conn.prepareStatement(sql);
		pre.setString(1, major);
		//获得结果集
		res = pre.executeQuery();
		//将结果存入Vector容器中
		Vector<Vector<String>> arraylist = new Vector<Vector<String>>();
		while(res.next()){
			Vector<String> array =new Vector<String>();
			array.add(res.getString("yxmc"));
			//平均成绩小数点后保留九位小数，语数外各三位
			if(res.getString("avg_grade").length()>13){
			array.add(res.getString("avg_grade").substring(0,13)); 
			}
			else 
				array.add(res.getString("avg_grade"));
			array.add(res.getString("min_grade"));
			arraylist.add(array);
		}
		return arraylist;
	}
	
	/**
	 * 输入专业，求出有该专业的学校该专业的最低投档线
	 * 输出样式    (学校,该专业最低投档线)
	 */
	/*
	public Vector<Vector<String>>GetLow(String str)throws SQLException{
		//求出有该专业的学校
		String sql="SELECT DISTINCT yxmc FROM yxzs WHERE zymc=?";
		//查询
		 pre = conn.prepareStatement(sql);
		pre.setString(1, str);
		//获得结果集
		res = pre.executeQuery();
		Vector<String> vec=new Vector<String>();   //学校名结果集
		while(res.next()){
			vec.add(res.getString("yxmc"));
		}
		Vector<Vector<String>>arraylist=new Vector<Vector<String>>(); 
		//找出每个学校该专业对应的最低投档线
		for(int ix=0;ix!=vec.size();++ix){
			sql="SELECT MIN(tdcj) min_grade  FROM yxzs WHERE yxmc=? and zymc=?";
			 pre = conn.prepareStatement(sql);
			pre.setString(1, vec.get(ix));
			pre.setString(2,str);
			res = pre.executeQuery();
			while(res.next()){
			Vector<String> array=new Vector<String>();
			array.add(vec.get(ix));
			if(res.getString("min_grade").length()>13){
				array.add(res.getString("min_grade").substring(0,13)); 
			}
				else 	array.add(res.getString("min_grade"));
			arraylist.add(array);
			}
		}
		return arraylist;
}
*/

/**
 * 理工类投档成绩预测
 */
public String Get_LiGong(float grades,float year) throws SQLException{
	 int Year = GetYear();
	 float adpeo=0,max_adpeo=0,new_addpeo=0;
	 float grade1=0,grade2=0,ave_grade=0;
	  //根据年份和分数得到位置值
	  String sql_ligong1="Select addpeo from ligong where year=? and grades=?";
	 //根据年份得到当年总人数
	 String sql_ligong2="Select MAX(addpeo) max_addpeo from ligong where year=?";
	 //根据新位置值和当年年份预测今年的分数
	 String sql_ligong3="Select MAX(grades) new_grades from ligong  where addpeo>=? and year=?"; 
			
	 String sql_ligong4="Select MIN(grades)  new_grades from ligong where addpeo<=? and year=?";
	 
	 pre = conn.prepareStatement(sql_ligong1);
		pre.setFloat(1,year);
		pre.setFloat(2,grades);
		//获得结果集
		res = pre.executeQuery();
		while(res.next()){
		adpeo=res.getFloat("addpeo");
		}
		pre = conn.prepareStatement(sql_ligong2);
		pre.setFloat(1,year);
		res = pre.executeQuery();
		while(res.next()){
		max_adpeo=res.getFloat("max_addpeo");
		}
		pre = conn.prepareStatement(sql_ligong2);
		pre.setFloat(1,Year);
		res = pre.executeQuery();
		while(res.next()){
		new_addpeo=res.getFloat("max_addpeo");
		}
		new_addpeo=adpeo*new_addpeo/max_adpeo;    //今年的位置值
		pre = conn.prepareStatement(sql_ligong3);
		pre.setFloat(1,(int)(new_addpeo+0.5));
		pre.setFloat(2,Year);
		res = pre.executeQuery();
		while(res.next()){
		grade1=res.getFloat("new_grades");
		}
		pre = conn.prepareStatement(sql_ligong4);
		pre.setFloat(1,(int)(new_addpeo+0.5));
		pre.setFloat(2,Year);
		res = pre.executeQuery();
		while(res.next()){
		grade2=res.getFloat("new_grades");
		}
		ave_grade=(grade1+grade2)/2;
		return String.valueOf(ave_grade);
}

/**
 * 文史类投档成绩预测
 */
public String Get_WenShi(float grades,float year) throws SQLException{
	int Year = GetYear();
	float adpeo=0,max_adpeo=0,new_addpeo=0;
	float grade1=0,grade2=0,ave_grade=0;
	 
	String sql_wenshi1="Select addpeo from wenshi where year=? and grades=?";
	
	String sql_wenshi2="Select MAX(addpeo) max_addpeo from wenshi where year=?";
	
	String sql_wenshi3="Select MAX(grades) new_grades from wenshi where addpeo>=? and year=?"; 
	
	String sql_wenshi4="Select MIN(grades)  new_grades from wenshi where addpeo<=? and year=?";
	
	 pre = conn.prepareStatement(sql_wenshi1);
		pre.setFloat(1,year);
		pre.setFloat(2,grades);
		//获得结果集
		res = pre.executeQuery();
		while(res.next()){
		adpeo=res.getFloat("addpeo");
		}
		pre = conn.prepareStatement(sql_wenshi2);
		pre.setFloat(1,year);
		res = pre.executeQuery();
		while(res.next()){
		max_adpeo=res.getFloat("max_addpeo");
		}
		pre = conn.prepareStatement(sql_wenshi2);
		pre.setFloat(1,Year);
		res = pre.executeQuery();
		while(res.next()){
		new_addpeo=res.getFloat("max_addpeo");
		}
		new_addpeo=adpeo*new_addpeo/max_adpeo;   
		pre = conn.prepareStatement(sql_wenshi3);
		pre.setFloat(1,(int)(new_addpeo+0.5));
		pre.setFloat(2,Year);
		res = pre.executeQuery();
		while(res.next()){
		grade1=res.getFloat("new_grades");
		}
		pre = conn.prepareStatement(sql_wenshi4);
		pre.setFloat(1,(int)(new_addpeo+0.5));
		pre.setFloat(2,Year);
		res = pre.executeQuery();
		while(res.next()){
		grade2=res.getFloat("new_grades");
		}
		ave_grade=(grade1+grade2)/2;
		return String.valueOf(ave_grade);
}
	
	/**
	 * 根据每个学校该专业的最低投档线预测今年的投档线
	 * @param year 
	 */
	 public  void GetNow_Td_Grade(Vector<Vector<String>> vec, String year) throws SQLException{
		float grades=0;
		String ligong,wenshi;
		//预测投档线
		for(int ix=0;ix!=vec.size();ix++){
			grades=(int)(Float.parseFloat(vec.get(ix).get(2))+0.5);  //获得该学校该专业的投档成绩
			ligong=Get_LiGong(grades,Integer.parseInt(year));
			wenshi=Get_WenShi(grades,Integer.parseInt(year));
			vec.get(ix).remove(2);
			vec.get(ix).add(ligong);
			vec.get(ix).add(wenshi);
		}
		}
	
	/**
	 * 整合数据
	 */
	public Vector<Vector<String>> Complex(String year, String major)throws SQLException{
		conn=ds.getConnection();
		//根据输入的专业进行不同数据的查询
		//查询招生计划
		Vector<Vector<String>> vec1=new Vector<Vector<String>>();
		vec1=GetPlan(major);
		//查询该专业去年录取平均分,最低投档成绩
		Vector<Vector<String>> vec2=new Vector<Vector<String>>();
		vec2=GetLastYearAve(year,major);
		//预测今年投档成绩
		GetNow_Td_Grade(vec2,year);
		//进行数据整合
		for(int i=0;i<vec1.size();++i){
			for(int j=0;j<vec2.size();++j){
				//若院校名称一样，则将数据连接到一起
				if(vec1.get(i).get(0).equals(vec2.get(j).get(0))){
					for(int k=1;k<vec2.get(j).size();++k){
						vec1.get(i).add(vec2.get(j).get(k));
					}
					break;
				}
			}
		}
		conn.close();
		return vec1;
	}
	
	public List<Plan> Get_List(String year, String major) throws SQLException{  
		Vector<Vector<String>> vec=new Vector<Vector<String>>();
		vec=Complex(year, major);
		List<Plan> list=new ArrayList<Plan>();
		for(int ix=0;ix!=vec.size();++ix){
		Plan plan=new Plan();
	    switch(vec.get(ix).size()){
	    case 7: plan.setPRE_GRADE_OF_WenShi(vec.get(ix).get(6));
	    case 6: plan.setPRE_GRADE_OF_LiGong(vec.get(ix).get(5));
	    case 5: plan.setAVE_GRADE(vec.get(ix).get(4));
	    case 4: plan.setBZ(vec.get(ix).get(3));
	    case 3: plan.setKL(vec.get(ix).get(2));
	    case 2: plan.setZYMC(vec.get(ix).get(1));
	    case 1: plan.setYXMC(vec.get(ix).get(0));
	    }
		list.add(plan);
		}
		return list;
	}

	/**
	 * GraToMaj()函数，第三部分的主要函数
	 * 
	 * @param major
	 * @param year
	 * @return
	 */
	public List<Plan> GraToMaj(String year, String major) throws SQLException {
		return Get_List(year, major);
	}
	
	public List<String> GetMaj(String major) throws SQLException {
		conn=ds.getConnection();

		List<String> arraylist = new ArrayList<String>();
		
		//查询
		pre = conn.prepareStatement("SELECT DISTINCT zymc FROM yxjh_3 WHERE zymc LIKE concat('%',?,'%')");
		pre.setString(1, major);
		//获得结果集
		res = pre.executeQuery();
		while (res.next()) {
			arraylist.add(res.getString("zymc"));
		}
		conn.close();
		return arraylist;
	}

}
