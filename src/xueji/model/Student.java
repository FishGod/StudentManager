package xueji.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//学生
@Entity
@Table(name="t_Student")
public class Student {

	@Id
	@GeneratedValue
	private int id;
	
	private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除
	
	private String sno;//学号
	
	private String touxiang;//头像
	
	private String sname;//学生姓名
	
	private String sex;//性别
	
	private String sfz;//身份证
	
	private String address;//地址
	
	private String lianxidianhua;//联系电话
	
	private String banjimingchen;//班级名称
	
	private String lixiaoriqi;//离校日期
	
	private String lixiaoyuanyin;//离校原因
	
	private String beizhu;//备注
	
	private String pingyu;//
	

	@ManyToOne
	@JoinColumn(name="teacherid")
	private Teacher teacher;//关联的班主任

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(int deletestatus) {
		this.deletestatus = deletestatus;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSfz() {
		return sfz;
	}

	public void setSfz(String sfz) {
		this.sfz = sfz;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLianxidianhua() {
		return lianxidianhua;
	}

	public void setLianxidianhua(String lianxidianhua) {
		this.lianxidianhua = lianxidianhua;
	}

	public String getBanjimingchen() {
		return banjimingchen;
	}

	public void setBanjimingchen(String banjimingchen) {
		this.banjimingchen = banjimingchen;
	}

	public String getLixiaoriqi() {
		return lixiaoriqi;
	}

	public void setLixiaoriqi(String lixiaoriqi) {
		this.lixiaoriqi = lixiaoriqi;
	}

	public String getLixiaoyuanyin() {
		return lixiaoyuanyin;
	}

	public void setLixiaoyuanyin(String lixiaoyuanyin) {
		this.lixiaoyuanyin = lixiaoyuanyin;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getPingyu() {
		return pingyu;
	}

	public void setPingyu(String pingyu) {
		this.pingyu = pingyu;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getTouxiang() {
		return touxiang;
	}

	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}
	
	
	

	
	
	
	
}
