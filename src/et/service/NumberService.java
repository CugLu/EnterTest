package et.service;

import java.sql.SQLException;
import java.util.List;

import et.dao.NumberDao;
import et.dao.NumberFiveDao;
import et.dao.NumberFourDao;
import et.dao.NumberThreeDao;
import et.dao.NumberTwoDao;
import et.domain.Plan;
import et.domain.School;
import et.domain.SchoolPlan;

/**
 * 服务
 * @author cailu
 *
 */
public class NumberService {
	
	private NumberDao numberDao=new NumberDao();
	private NumberTwoDao numberTwoDao=new NumberTwoDao();
	private NumberThreeDao numberThreeDao=new NumberThreeDao();
	private NumberFourDao numberFourDao=new NumberFourDao();
	private NumberFiveDao numberFiveDao=new NumberFiveDao();
	/*
	 * 1.由numberDao来完成
	 * @return
	 * @throws SQLException 
	 */
	public List<School> GraToSch(String year,String major, float grade) throws SQLException{
		return numberDao.GraToSch(year,major,grade);
	}
	
	public float getLastgrade() {
		return numberDao.getLastgrade();
	}

	public float getLocation() {
		return numberDao.getLocation();
	}

	public float getLastlocation() {
		return numberDao.getLastlocation() ;
	}
	
	/*
	 * 2.由numberTwoDao来完成
	 * 
	 */
	public List<School> gettarget(String year, float grade, String major, String school) throws SQLException {
		return numberTwoDao.gettarget(year,grade,major,school);
	}
	
	/*
	 * 3.由numberThreeDao来完成
	 */
	public List<Plan> MajToSch(String year, String major) throws SQLException {
		return numberThreeDao.GraToMaj(year, major);
	}
	
	public List<String> GetMaj(String major) throws SQLException {
		return numberThreeDao.GetMaj(major);
	}
	
	/*
	 * 4.由numberFourDao来完成
	 */
	public List<SchoolPlan> InfoSchool(String school, int year) throws SQLException {
		return numberFourDao.InfoSchool(school,year);
	}
	
	/*
	 * 5.由numberFiveDao来完成
	 */
	public List<SchoolPlan> InfoMajor(String major, int year) throws SQLException {
		return numberFiveDao.InfoMajor(major,year);
	}

}
