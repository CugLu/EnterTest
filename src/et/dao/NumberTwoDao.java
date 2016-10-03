package et.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import et.domain.School;

/**
 * @author Haoqiang Li
 */
public class NumberTwoDao {
	// 池类
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	// 声明PreparedStatement对象，用来向数据库发送SQL语句
	private static PreparedStatement pre;
	// 声明ResultSet对象,这是结果集
	private static ResultSet res;

	// 连接
	private static Connection con;

	// 查询器
	private static QueryRunner qr = new TxQueryRunner();
	
	//年份
	private static Year yearTime=new Year();
	private static List<Year> yearList;
		
	/**
	 * gettarget()函数，第二部分的主要函数
	 * @param year 
	 * 
	 * @param major
	 * @param grade
	 * @param school 
	 * @return
	 */
	public List<School> gettarget(String year, float grade, String major, String school) throws SQLException {
		// 开启
		con = ds.getConnection();
		
		int Year = GetYear();
		String Str;
		if(Integer.parseInt(year)==Year-1)
			Str = "SELECT DISTINCT zymc,tdcj FROM yxzs_2 WHERE tdcj<=? AND tdcj>? and yxmc=?";
		else
			Str = "SELECT DISTINCT zymc,tdcj FROM yxzs_1 WHERE tdcj<=? AND tdcj>? and yxmc=?";
		float Tdcj = getlgrade(year, grade, major);
		// 封装
		List<School> SchoolList = qr.query(Str, new BeanListHandler<School>(
				School.class), Tdcj, 150, school);

		con.close();// 关闭
		return SchoolList;
	}

	// 得到当前年份
	public static int GetYear() throws SQLException{
		yearList=yearTime.GetYearList();
		return Integer.parseInt(yearList.get(0).getYear());
	}

	// 得到位置值
	public int getpoint(float grade, String str) throws SQLException {
		int point;
		if (str.equalsIgnoreCase("ligong"))
			pre = con.prepareStatement("select * from ligong WHERE year=?");
		else
			pre = con.prepareStatement("select * from wenshi WHERE year=?");
		pre.setFloat(1, GetYear());
		res = pre.executeQuery();
		res.next();
		while (grade < res.getInt("grades")) {
			res.next();
		}
		point = res.getInt("addpeo");
		return point;
	}

	// 得到今年总人数
	public int getall(String str) throws SQLException {
		if (str.equalsIgnoreCase("ligong"))
			pre = con.prepareStatement("select * from ligong WHERE year=?");
		else
			pre = con.prepareStatement("select * from wenshi WHERE year=?");
		pre.setFloat(1, GetYear());
		res = pre.executeQuery();
		while (!res.isLast()) {
			res.next();
		}
		int all = res.getInt("addpeo");
		return all;
	}

	// 得到去年总人数
	public int getallp(String year,String str) throws SQLException {
		if (str.equalsIgnoreCase("ligong"))
			pre = con.prepareStatement("select * from ligong WHERE year=?");
		else
			pre = con.prepareStatement("select * from wenshi WHERE year=?");
		pre.setFloat(1, Integer.parseInt(year));
		res = pre.executeQuery();
		while (!res.isLast()) {
			res.next();
		}
		int all1 = res.getInt("addpeo");
		return all1;
	}

	// 得到位置值
	public float getlpoint(String year, float grade, String str) throws SQLException {
		float pointl;
		int a = getpoint(grade, str);
		int b = getall(str);
		int c = getallp(year, str);
		pointl = (float) a * (float) c / (float) b;
		return pointl;
	}

	// 得到上年分数
	public float getlgrade(String year, float grade, String str) throws SQLException {
		float glrade=0;
		float alllp = getlpoint(year, grade, str);
		if (str.equalsIgnoreCase("ligong"))
			pre = con.prepareStatement("select * from ligong WHERE year=?");
		else
			pre = con.prepareStatement("select * from wenshi WHERE year=?");
		pre.setFloat(1, Integer.parseInt(year));
		res = pre.executeQuery();
		res.next();
		while (alllp >= res.getInt("addpeo")) {
			glrade=res.getFloat("grades");
			res.next();
		}
		return glrade;
	}

}
