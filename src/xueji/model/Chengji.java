package xueji.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//成绩
@Entity
@Table(name="t_Chengji")
public class Chengji {

	@Id
	@GeneratedValue
	private int id;
	
	private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除
	
	
	private String kecheng;//课程
	
	private String chengji;//成绩
	
	private  String xuefen;//学分
	
	private String laoshi;//任课老师
	
	private Date createtime;//添加时间
	
	@ManyToOne
	@JoinColumn(name="studentid")
	private Student student;//关联学生

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

	public String getKecheng() {
		return kecheng;
	}

	public void setKecheng(String kecheng) {
		this.kecheng = kecheng;
	}

	public String getChengji() {
		return chengji;
	}

	public void setChengji(String chengji) {
		this.chengji = chengji;
	}

	public String getXuefen() {
		return xuefen;
	}

	public void setXuefen(String xuefen) {
		this.xuefen = xuefen;
	}

	public String getLaoshi() {
		return laoshi;
	}

	public void setLaoshi(String laoshi) {
		this.laoshi = laoshi;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
	
	
}
