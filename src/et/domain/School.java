package et.domain;

public class School implements Comparable<School> {
	
	private String yxmc;
	private String zymc;
	private float tdcj;
	
	public String getYxmc() {
		return yxmc;
	}
	public void setYxmc(String yxmc) {
		this.yxmc = yxmc;
	}
	public String getZymc() {
		return zymc; 
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	
	//注意这里Float,用作排序
	public Float getTdcj1() {
		return tdcj;
	}
	public float getTdcj() {
		return tdcj;
	}
	public void setTdcj(float tdcj) {
		this.tdcj = tdcj;
	}
	
	@Override
	public String toString() {
		return "School [yxmc=" + yxmc + ", zymc=" + zymc + ", tdcj=" + tdcj
				+ "]";
	}
	
	//排序函数，降序
	@Override
	public int compareTo(School o) {
		return o.getTdcj1().compareTo(this.getTdcj1());
	}
	
}
