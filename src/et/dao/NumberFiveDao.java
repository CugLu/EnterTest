package et.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.jdbc.TxQueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import et.domain.SchoolPlan;

/**
 * 
 * @author cailu
 * 
 */
public class NumberFiveDao {
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

	// 年份
	private static Year yearTime = new Year();
	private static List<Year> yearList;

	public static int GetYear() throws SQLException {
		yearList = yearTime.GetYearList();
		return Integer.parseInt(yearList.get(0).getYear());
	}

	/*
	 * 查询学校计划
	 */
	public static Vector<Vector<String>> CX(String sql, String name)
			throws SQLException {
		pre = conn.prepareStatement(sql);
		pre.setString(1, name);
		res = pre.executeQuery();
		Vector<Vector<String>> arraylist = new Vector<Vector<String>>();
		while (res.next()) {
			Vector<String> array = new Vector<String>();
			array.add(res.getString(1));
			array.add(res.getString(2));
			array.add(res.getString(3));
			array.add(res.getString(4));
			array.add(res.getString(5));
			array.add(res.getString(6));
			arraylist.add(array);

		}
		return arraylist;
	}

	/**
	 * InfoMajor()函数，第五部分的主要函数
	 * 
	 * @param major
	 * @param year
	 * @return
	 * @throws SQLException
	 */
	public List<SchoolPlan> InfoMajor(String major, int year)
			throws SQLException {
		conn = ds.getConnection();

		int Year = GetYear();
		String sql = null;
		Vector<Vector<String>> arraylist = new Vector<Vector<String>>();
		if (year == Year - 2)
			sql = "select distinct zydm,zymc,yxdh,yxmc,kl,jhs from yxjh_1 where zymc=?";
		else if (year == Year - 1)
			sql = "select distinct zydm,zymc,yxdh,yxmc,kl,jhs from yxjh_2 where zymc=?";
		else
			sql = "select distinct zydm,zymc,yxdh,yxmc,kl,jhs from yxjh_3 where zymc=?";

		arraylist = CX(sql, major);

		List<SchoolPlan> schoolPlanList = new ArrayList<SchoolPlan>();

		for (int i = 0; i < arraylist.size(); i++) {
			SchoolPlan schoolPlan = new SchoolPlan();

			Vector<String> vc = arraylist.get(i);
			schoolPlan.setZydm(vc.get(0));
			schoolPlan.setZymc(vc.get(1));
			schoolPlan.setYxdh(vc.get(2));
			schoolPlan.setYxmc(vc.get(3));
			schoolPlan.setKl(vc.get(4));
			schoolPlan.setJhs(vc.get(5));

			schoolPlanList.add(schoolPlan);
		}
		conn.close();
		return schoolPlanList;
	}

}
