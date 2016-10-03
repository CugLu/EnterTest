package et.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class Year {
	private String year;

	// 查询器
	private QueryRunner qr = new TxQueryRunner();
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public List<Year> GetYearList() throws SQLException{
		String sql="SELECT DISTINCT year FROM ligong";
		List<Year> yearList=qr.query(sql, new BeanListHandler<Year>(Year.class));
		return yearList;
	}
}
