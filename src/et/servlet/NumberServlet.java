package et.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
import et.dao.Year;
import et.domain.Plan;
import et.domain.School;
import et.domain.SchoolPlan;
import et.service.NumberService;

/**
 * Servlet用于处理响应事件
 * @author cailu
 *
 */
public class NumberServlet extends BaseServlet {
	
	private NumberService numberService=new NumberService();
	private Year yearTime=new Year();
	/**
	 * GraToSch
	 * @param request
	 * @param response
	 * @return
	 */
	public String GraToSch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		/*
		 * 得到表单项
		 */
		String major=(String) request.getParameter("major");
		float grade=Float.parseFloat((String)request.getParameter("grade"));
		
		List<Year> yearList=yearTime.GetYearList();
		/*
		 * 查到结果
		 */
		List<School> schoollist1=numberService.GraToSch(yearList.get(1).getYear(), major, grade);
		
		//保存到域
		request.setAttribute("schoollist_1_1", schoollist1);
		request.setAttribute("year1", yearList.get(1).getYear());
		request.setAttribute("major", major);
		request.setAttribute("grade", grade);
		request.setAttribute("lastgrade_1", numberService.getLastgrade());
		request.setAttribute("location", numberService.getLocation());
		request.setAttribute("lastlocation_1", numberService.getLastlocation());
		
		/*
		 * 查到结果
		 */
		List<School> schoollist2=numberService.GraToSch(yearList.get(2).getYear(), major, grade);
		
		//保存到域
		request.setAttribute("schoollist_1_2", schoollist2);
		request.setAttribute("year2", yearList.get(2).getYear());
		request.setAttribute("major", major);
		request.setAttribute("grade", grade);
		request.setAttribute("lastgrade_2", numberService.getLastgrade());
		request.setAttribute("location", numberService.getLocation());
		request.setAttribute("lastlocation_2", numberService.getLastlocation());
		
		//转发
		return "f:/list_1.jsp";
	}
	
	/**
	 * GraToMaj
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String GraToMaj(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		/*
		 * 得到表单项
		 */
		String school = new String(request.getParameter("school").getBytes("ISO-8859-1"),"utf-8");
		String major=(String) request.getParameter("major");
		float grade=Float.parseFloat((String)request.getParameter("grade"));
		String year=(String) request.getParameter("year");
		/*
		 * 查到结果
		 */
		List<School> schoollist=numberService.gettarget(year, grade, major,school);
		
		//保存到域
		request.setAttribute("school", school);
		request.setAttribute("schoollist_2", schoollist);
		//转发
		return "f:/list_2.jsp";
	}
	
	/**
	 * MajToSch
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String MajToSch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		/*
		 * 得到表单项
		 */
		String major=new String(request.getParameter("major").getBytes("ISO-8859-1"),"utf-8");
		
		List<Year> yearList=yearTime.GetYearList();
		/*
		 * 查到结果
		 */
		List<Plan> schoollist1=numberService.MajToSch(yearList.get(1).getYear(), major);
		//保存到域
		request.setAttribute("major", major);
		request.setAttribute("year1", yearList.get(1).getYear());
		request.setAttribute("schoollist_3_3", schoollist1);
		
		/*
		 * 查到结果
		 */
		List<Plan> schoollist2=numberService.MajToSch(yearList.get(1).getYear(), major);
		//保存到域
		request.setAttribute("major", major);
		request.setAttribute("year2", yearList.get(2).getYear());
		request.setAttribute("schoollist_3_4", schoollist2);
		
		//转发
		return "f:/list_3.jsp";
	}
	
	public String GetMaj(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		/*
		 * 得到表单项
		 */
		String major=(String) request.getParameter("major");
		
		/*
		 * 查到结果
		 */
		List<String> schoollist1=numberService.GetMaj(major);
		//保存到域
		request.setAttribute("major", major);
		request.setAttribute("schoollist_3_1", schoollist1);
		
		//转发
		return "f:/list_3_1.jsp";
	}
	
	/**
	 * InfoSchool
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String InfoSchool(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		/*
		 * 得到表单项
		 */
		String school=(String) request.getParameter("school");
		List<Year> yearList=yearTime.GetYearList();
		/*
		 * 查到结果
		 */
		//得到今年年份
		int Year = Integer.parseInt(yearList.get(0).getYear());
		
		List<SchoolPlan> schoollist_1=numberService.InfoSchool(school,Year);
		List<SchoolPlan> schoollist_2=numberService.InfoSchool(school,Year-1);
		List<SchoolPlan> schoollist_3=numberService.InfoSchool(school,Year-2);
		//保存到域
		request.setAttribute("school_4", school);
		request.setAttribute("year_4", Year);
		request.setAttribute("schoollist_4_1", schoollist_1);
		request.setAttribute("schoollist_4_2", schoollist_2);
		request.setAttribute("schoollist_4_3", schoollist_3);
		//转发
		return "f:/list_4.jsp";
	}
	
	public String InfoMajor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		/*
		 * 得到表单项        
		 */
		String major=(String) request.getParameter("major");
		List<Year> yearList=yearTime.GetYearList();
		/*
		 * 查到结果
		 */
		//得到今年年份
		int Year = Integer.parseInt(yearList.get(0).getYear());
		
		List<SchoolPlan> majorlist_1=numberService.InfoMajor(major,Year);
		List<SchoolPlan> majorlist_2=numberService.InfoMajor(major,Year-1);
		List<SchoolPlan> majorlist_3=numberService.InfoMajor(major,Year-2);
		//保存到域
		request.setAttribute("major_5", major);
		request.setAttribute("year_5", Year);
		request.setAttribute("majorlist_5_1", majorlist_1);
		request.setAttribute("majorlist_5_2", majorlist_2);
		request.setAttribute("majorlist_5_3", majorlist_3);
		//转发
		return "f:/list_5.jsp";
	}
}
