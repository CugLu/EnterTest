package et.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.itcast.jdbc.TxQueryRunner;

import et.domain.School;

/**
 * 
 * @author cailu
 * 
 */
public class NumberDao {

	// 查询器
	private QueryRunner qr = new TxQueryRunner();

	// 年份
	private static Year yearTime = new Year();
	private static List<Year> yearList;

	/*
	 * 常用变量
	 */
	private float lastgrade;// 去年分数
	private float lastlocation;// 去年位置
	private float location;// 今年位置

	private int nowAllPeo;// 今年人数
	private int preAllPeo;// 去年人数

	public float getLastgrade() {
		return lastgrade;
	}

	public float getLastlocation() {
		return lastlocation;
	}

	public float getLocation() {
		return location;
	}

	public static int GetYear() throws SQLException {
		yearList = yearTime.GetYearList();
		return Integer.parseInt(yearList.get(0).getYear());
	}

	/**
	 * GraToSch()函数,第一部分的主要函数
	 * 
	 * @param major
	 * @param major2
	 * @param grade
	 * @return
	 * @throws SQLException
	 *             作者：cailu
	 */
	@Test
	public List<School> GraToSch(String year, String major, float grade)
			throws SQLException {
		int Year = GetYear();
		String sql;
		if (Integer.parseInt(year) == Year - 1)
			sql = "SELECT DISTINCT yxmc,tdcj FROM yxzs_2 WHERE tdcj<=? AND tdcj>? ORDER BY yxmc";
		else
			sql = "SELECT DISTINCT yxmc,tdcj FROM yxzs_1 WHERE tdcj<=? AND tdcj>? ORDER BY yxmc";
		float Tdcj = GetLastGrades(year, major, grade);
		// 如果分数为0的话全设为0
		if (Tdcj == 0) {
			lastgrade = 0;
			lastlocation = 0;
			location = 0;
		}
		List<School> SchoolList = qr.query(sql, new BeanListHandler<School>(
				School.class), Tdcj, 150);
		
		/*
		 * 删减，找到录取线
		 */
		List<School> schoollist_1 = new ArrayList<School>();
		School school = new School();
		int i, j;
		// 遍历
		for (i = 0; i < SchoolList.size();) {
			
			school = SchoolList.get(i);
			String str = school.getYxmc();
			float flo = school.getTdcj();
			
			j = i + 1;
			if(j==SchoolList.size())
				break;
			School school1 = SchoolList.get(j);
			String str1 = school1.getYxmc();
			float flo1 = school1.getTdcj();
			
			while (str.equals(str1)) {
				
				int count=((String.valueOf(flo1)).split("\\.")[1]).length();
				char point=((String.valueOf(flo1)).split("\\.")[1]).charAt(0);
				if (flo1 < flo && (count > 1 || point!='0'))
					school = school1;
				
				j++;
				
				if (j < SchoolList.size()) {
					school1 = SchoolList.get(j);
					str1 = school1.getYxmc();
					flo1 = school1.getTdcj();
				} else
					break;
				
			}
			i = j;
			//添加
			schoollist_1.add(school);
		}

		// 排序
		Collections.sort(schoollist_1);

		// 返回最终结果
		return schoollist_1;
	}

	public float GetNowPosition(String year, String major, float grade)
			throws SQLException {
		/**
		 * 1.使用一分一栏表："ligong"或者"wenshi"，要做一个判断 2.通过分数找到当年的位置值
		 */
		float gra = grade;

		float Total_peo = 0;

		// 数据库查询语句
		/**
		 * 这里是要进行判断！！！
		 */
		String sql = null;

		if (gra < 150) {
			return 1000000;
		} else {
			if (major.equalsIgnoreCase("ligong")) {
				sql = "SELECT * FROM ligong WHERE year=?";
			} else {
				sql = "SELECT * FROM wenshi WHERE year=?";
			}

			// 查询
			int Year = GetYear();

			List<Map<String, Object>> list = qr.query(sql,
					new MapListHandler(), Year);
			float temp = Float.valueOf(list.get(list.size() - 1).get("addpeo")
					.toString());// 取得今年总人数
			nowAllPeo = (int) temp;
			// 当分数太高时，自己设置为第一名
			if (gra >= Float.parseFloat(list.get(0).get("grades").toString())) {
				Total_peo = 1;
				location = Total_peo;
				return Total_peo;
			}

			for (Map<String, Object> map : list) {
				float Gra = Float.parseFloat(map.get("grades").toString());
				if (Gra >= gra) {
					Total_peo = Float.parseFloat(map.get("addpeo").toString());
				} else
					break;
			}
		}
		location = Total_peo;
		return Total_peo;
	}

	public float GetLastGrades(String year, String major, float grade)
			throws SQLException {
		/**
		 * 1.使用一分一栏表 2.通过位置值找到上年的分数
		 */
		float position = GetNowPosition(year, major, grade);
		float Last_gra = 0;
		// 数据库查询语句
		if (position == 1000000) {
			return 0;
		} else {
			/**
			 * 判断
			 */
			String sql = null;
			if (major.equalsIgnoreCase("ligong")) {
				sql = "SELECT * FROM ligong WHERE year=?";
			} else {
				sql = "SELECT * FROM wenshi WHERE year=?";
			}
			// 查询
			List<Map<String, Object>> list = qr.query(sql,
					new MapListHandler(), year);

			float temp = Float.valueOf(list.get(list.size() - 1).get("addpeo")
					.toString());// 取得去年或前年总人数
			preAllPeo = (int) temp;
			position = preAllPeo * position / nowAllPeo;

			lastlocation = position;

			for (Map<String, Object> map : list) {
				float Position = Float.parseFloat(map.get("addpeo").toString());
				if (Position <= position) {
					Last_gra = Float.parseFloat(map.get("grades").toString());
				} else
					break;
			}
		}
		lastgrade = Last_gra;
		return Last_gra;
	}

}
