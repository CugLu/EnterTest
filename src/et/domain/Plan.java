package et.domain;

/**
 * 
 * @author Yulong Yan
 * 
 */
public class Plan {
	private String YXMC; // 院校名称
	private String ZYMC; // 专业名称
	private String KL; // 科类
	private String BZ; // 备注
	private String AVE_GRADE; // 录取平均分
	private String PRE_GRADE_OF_LiGong; // 理工类预测分数
	private String PRE_GRADE_OF_WenShi; // 文史类预测分数

	// 构造函数
	public Plan() {
	}

	public Plan(String yxmc, String zymc, String kl, String bz,
			String ave_grade, String pre_grade1, String pre_grade2) {
		YXMC = yxmc;
		ZYMC = zymc;
		KL = kl;
		BZ = bz;
		AVE_GRADE = ave_grade;
		PRE_GRADE_OF_LiGong = pre_grade1;
		PRE_GRADE_OF_WenShi = pre_grade2;
	}

	public String getYXMC() {
		return YXMC;
	}

	public void setYXMC(String yXMC) {
		YXMC = yXMC;
	}

	public String getZYMC() {
		return ZYMC;
	}

	public void setZYMC(String zYMC) {
		ZYMC = zYMC;
	}

	public String getKL() {
		return KL;
	}

	public void setKL(String kL) {
		KL = kL;
	}

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String bZ) {
		BZ = bZ;
	}

	public String getAVE_GRADE() {
		return AVE_GRADE;
	}

	public void setAVE_GRADE(String aVE_GRADE) {
		AVE_GRADE = aVE_GRADE;
	}

	public String getPRE_GRADE_OF_LiGong() {
		return PRE_GRADE_OF_LiGong;
	}

	public void setPRE_GRADE_OF_LiGong(String pRE_GRADE_OF_LiGong) {
		PRE_GRADE_OF_LiGong = pRE_GRADE_OF_LiGong;
	}

	public String getPRE_GRADE_OF_WenShi() {
		return PRE_GRADE_OF_WenShi;
	}

	public void setPRE_GRADE_OF_WenShi(String pRE_GRADE_OF_WenShi) {
		PRE_GRADE_OF_WenShi = pRE_GRADE_OF_WenShi;
	}

	@Override
	public String toString() {
		return "Plan [YXMC=" + YXMC + ", ZYMC=" + ZYMC + ", KL=" + KL + ", BZ="
				+ BZ + ", AVE_GRADE=" + AVE_GRADE + ", PRE_GRADE_OF_LiGong="
				+ PRE_GRADE_OF_LiGong + ", PRE_GRADE_OF_WenShi="
				+ PRE_GRADE_OF_WenShi + "]";
	}

}
