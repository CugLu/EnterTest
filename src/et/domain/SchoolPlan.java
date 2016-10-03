package et.domain;

public class SchoolPlan {
	private String yxdh;
	private String yxmc;
	private String zydm;
	private String zymc;
	private String kl;
	private String jhs;
	public String getYxdh() {
		return yxdh;
	}
	public void setYxdh(String yxdh) {
		this.yxdh = yxdh;
	}
	public String getYxmc() {
		return yxmc;
	}
	public void setYxmc(String yxmc) {
		this.yxmc = yxmc;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getKl() {
		return kl;
	}
	public void setKl(String kl) {
		this.kl = kl;
	}
	public String getJhs() {
		return jhs;
	}
	public void setJhs(String jhs) {
		this.jhs = jhs;
	}
	@Override
	public String toString() {
		return "SchoolPlan [yxdh=" + yxdh + ", yxmc=" + yxmc + ", zydm=" + zydm
				+ ", zymc=" + zymc + ", kl=" + kl + ", jhs=" + jhs + "]";
	}
}
