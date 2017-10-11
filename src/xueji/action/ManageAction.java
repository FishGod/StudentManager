package xueji.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import xueji.dao.ChengjiDao;
import xueji.dao.StudentDao;
import xueji.dao.TeacherDao;
import xueji.dao.UserDao;
import xueji.model.Chengji;
import xueji.model.Student;
import xueji.model.Teacher;
import xueji.model.User;
import xueji.util.Pager;
import xueji.util.Util;

import com.opensymphony.xwork2.ActionSupport;

public class ManageAction extends ActionSupport {

	private static final long serialVersionUID = -4304509122548259589L;

	private UserDao userDao;

	private String url = "./";

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	
	
	
//登入请求
	public String login() throws IOException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String  role = request.getParameter("role");
		User user = userDao.selectBean(" where username = '" + username
				+ "' and password= '" + password + "' and role= "+role +" and deletestatus=0 ");
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			this.setUrl("index.jsp");//设置跳转路径
			return "redirect";
		} else {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");
			response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者密码错误');window.location.href='login.jsp';</script>");
		}
		return null;
	}
//用户退出
	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		this.setUrl("login.jsp");
		return SUCCESS;
	}
//跳转到修改密码页面
	public String changepwd() {
		this.setUrl("user/user.jsp");
		return SUCCESS;
	}
//修改密码操作
	public void changepwd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"' and deletestatus=0");
		if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('success!');window.location.href='method!changepwd';</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('原密码错误');window.location.href='method!changepwd';</script>");
		}
	}
	
	private TeacherDao  teacherDao;

	public TeacherDao getTeacherDao() {
		return teacherDao;
	}

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}
	
	
	//班主任列表
	public String teacherlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String tname = request.getParameter("tname");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (tname != null && !"".equals(tname)) {

			sb.append("tname like '%" + tname + "%'");
			sb.append(" and ");
			request.setAttribute("tname", tname);
		}

		
		
		
		sb.append("  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = teacherDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", teacherDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!teacherlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!teacherlist");
		request.setAttribute("url2", "method!teacher");
		request.setAttribute("title", "班主任管理");
		this.setUrl("teacher/teacherlist.jsp");
		return SUCCESS;

	}
//跳转到添加班主任页面
	public String teacheradd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "method!teacheradd2");
		request.setAttribute("title", "班主任添加");
		this.setUrl("teacher/teacheradd.jsp");
		return SUCCESS;
	}
//添加班主任操作
	public void teacheradd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String bianhao = request.getParameter("bianhao");
		String sfz = request.getParameter("sfz");
		String tname = request.getParameter("tname");
		String xingbie = request.getParameter("xingbie");
		String yuanxi = request.getParameter("yuanxi");
		Teacher bean = teacherDao.selectBean(" where bianhao='"+bianhao+"' and deletestatus=0 ");
		User user = userDao.selectBean(" where username='"+bianhao+"' and deletestatus=0 and role=2 ");
		if(bean==null&&user==null ){
			bean = new Teacher();
			bean.setBianhao(bianhao);
			bean.setSfz(sfz);
			bean.setTname(tname);
			bean.setXingbie(xingbie);
			bean.setYuanxi(yuanxi);
			teacherDao.insertBean(bean);
			
			User u = new User();
			u.setCreatetime(new Date());
			u.setPassword("111111");
			u.setRole(2);
			u.setTruename(tname);
			u.setUsername(bianhao);
			userDao.insertBean(u);
			
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('success!');window.location.href='method!teacherlist';</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('操作失败，该班主任编号已经存在');window.location.href='method!teacherlist';</script>");
		}
	}
//跳转到更新班主任页面
	public String teacherupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Teacher bean = teacherDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!teacherupdate2?id="+bean.getId());
		request.setAttribute("title", "班主任更新");
		this.setUrl("teacher/teacherupdate.jsp");
		return SUCCESS;
	}
//更新班主任操作
	public void teacherupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sfz = request.getParameter("sfz");
		String tname = request.getParameter("tname");
		String xingbie = request.getParameter("xingbie");
		String yuanxi = request.getParameter("yuanxi");
		Teacher bean = teacherDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setSfz(sfz);
		bean.setTname(tname);
		bean.setXingbie(xingbie);
		bean.setYuanxi(yuanxi);
		
		teacherDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!teacherlist';</script>");
	}
//删除班主任操作
	public void teacherdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Teacher bean = teacherDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		teacherDao.updateBean(bean);
		User user = userDao.selectBean(" where username='"+bean.getBianhao()+"' ");
		user.setDeletestatus(1);
		userDao.updateBean(user);
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!teacherlist';</script>");
	}
	
	//跳转到查看班主任页面
	public String teacherupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Teacher bean = teacherDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "班主任查看");
		this.setUrl("teacher/teacherupdate3.jsp");
		return SUCCESS;
	}
	
	private StudentDao studentDao;

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	
	//学籍基本信息列表
	public String studentlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sno = request.getParameter("sno");
		String sname = request.getParameter("sname");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (sno != null && !"".equals(sno)) {

			sb.append("sno like '%" + sno + "%'");
			sb.append(" and ");
			request.setAttribute("sno", sno);
		}
		
		if (sname != null && !"".equals(sname)) {

			sb.append("sname like '%" + sname + "%'");
			sb.append(" and ");
			request.setAttribute("sname", sname);
		}

		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		
		
		sb.append(" teacher.bianhao='"+user.getUsername()+"' and  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = studentDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", studentDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!studentlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!studentlist");
		request.setAttribute("url2", "method!student");
		request.setAttribute("title", "学籍基本信息管理");
		this.setUrl("student/studentlist.jsp");
		return SUCCESS;

	}
//跳转到添加学籍基本信息页面
	public String studentadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "method!studentadd2");
		request.setAttribute("title", "学籍基本信息添加");
		this.setUrl("student/studentadd.jsp");
		return SUCCESS;
	}
	
	private File uploadfile;
	
	
	public File getUploadfile() {
		return uploadfile;
	}


	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}
	
//添加学籍基本信息操作
	public void studentadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sno = request.getParameter("sno");
		String address = request.getParameter("address");
		String banjimingchen = request.getParameter("banjimingchen");
		String beizhu = request.getParameter("beizhu");
		String lianxidianhua = request.getParameter("lianxidianhua");
		String sex = request.getParameter("sex");
		String sfz = request.getParameter("sfz");
		String sname = request.getParameter("sname");
		Student bean = studentDao.selectBean(" where sno='"+sno+"' and deletestatus=0 ");
		User user = userDao.selectBean(" where username='"+sno+"' and deletestatus=0  and role=3");
		if(bean==null&&user==null ){

			HttpSession session = request.getSession();
			User tuser = (User)session.getAttribute("user");
			Teacher t = teacherDao.selectBean(" where bianhao='"+tuser.getUsername()+"' ");
			String savapath = ServletActionContext.getServletContext().getRealPath("/")+"/uploadfile/";
			String time = Util.getTime2();
			String imgpath = time+".jpg";
			File file = new File(savapath+imgpath);
			Util.copyFile(uploadfile, file);
			
			
			bean = new Student();
			bean.setAddress(address);
			bean.setBanjimingchen(banjimingchen);
			bean.setBeizhu(beizhu);
			bean.setLianxidianhua(lianxidianhua);
			bean.setSex(sex);
			bean.setSfz(sfz);
			bean.setSname(sname);
			bean.setSno(sno);
			bean.setTeacher(t);
			bean.setTouxiang(imgpath);
			studentDao.insertBean(bean);
			
			User u = new User();
			u.setCreatetime(new Date());
			u.setPassword("111111");
			u.setRole(3);
			u.setTruename(sname);
			u.setUsername(sno);
			userDao.insertBean(u);
			
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('success!');window.location.href='method!studentlist';</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('操作失败，该学号已经存在');window.location.href='method!studentlist';</script>");
		}
	}
//跳转到更新学籍基本信息页面
	public String studentupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Student bean = studentDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!studentupdate2?id="+bean.getId());
		request.setAttribute("title", "学籍基本信息更新");
		this.setUrl("student/studentupdate.jsp");
		return SUCCESS;
	}
//更新学籍基本信息操作
	public void studentupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String address = request.getParameter("address");
		String banjimingchen = request.getParameter("banjimingchen");
		String beizhu = request.getParameter("beizhu");
		String lianxidianhua = request.getParameter("lianxidianhua");
		String sex = request.getParameter("sex");
		String sfz = request.getParameter("sfz");
		String sname = request.getParameter("sname");
		Student bean = studentDao.selectBean(" where id= "
				+ request.getParameter("id"));
		if(uploadfile!=null){
			String savapath = ServletActionContext.getServletContext().getRealPath("/")+"/uploadfile/";
			String time = Util.getTime2();
			String imgpath = time+".jpg";
			File file = new File(savapath+imgpath);
			Util.copyFile(uploadfile, file);
			bean.setTouxiang(imgpath);
		}
		
		bean.setAddress(address);
		bean.setBanjimingchen(banjimingchen);
		bean.setBeizhu(beizhu);
		bean.setLianxidianhua(lianxidianhua);
		bean.setSex(sex);
		bean.setSfz(sfz);
		bean.setSname(sname);
		
		studentDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!studentlist';</script>");
	}
//删除学籍基本信息操作
	public void studentdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Student bean = studentDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		studentDao.updateBean(bean);
		User user = userDao.selectBean(" where username='"+bean.getSno()+"' ");
		user.setDeletestatus(1);
		userDao.updateBean(user);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!studentlist';</script>");
	}
	
	//跳转到查看学籍基本信息页面
	public String studentupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Student bean = studentDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "学籍基本信息查看");
		this.setUrl("student/studentupdate3.jsp");
		return SUCCESS;
	}
	
	
	private ChengjiDao  chengjiDao;

	public ChengjiDao getChengjiDao() {
		return chengjiDao;
	}

	public void setChengjiDao(ChengjiDao chengjiDao) {
		this.chengjiDao = chengjiDao;
	}
	
	
	//学生成绩列表
	public String studentlist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sno = request.getParameter("sno");
		String sname = request.getParameter("sname");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (sno != null && !"".equals(sno)) {

			sb.append("sno like '%" + sno + "%'");
			sb.append(" and ");
			request.setAttribute("sno", sno);
		}
		
		if (sname != null && !"".equals(sname)) {

			sb.append("sname like '%" + sname + "%'");
			sb.append(" and ");
			request.setAttribute("sname", sname);
		}

		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		
		
		sb.append(" teacher.bianhao='"+user.getUsername()+"' and  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = studentDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", studentDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!studentlist2", "共有" + total + "条记录"));
		request.setAttribute("url", "method!studentlist2");
		request.setAttribute("url2", "method!student");
		request.setAttribute("title", "学生成绩管理");
		this.setUrl("student/studentlist2.jsp");
		return SUCCESS;

	}
	
	
	//学生成绩列表
	public String chengjilist() {
		HttpServletRequest request = ServletActionContext.getRequest();

		String sid = request.getParameter("sid");
		
		request.setAttribute("sid", sid);
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		
		
		
		
		sb.append(" student.id="+sid+" and  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = chengjiDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", chengjiDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!chengjilist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!chengjilist");
		request.setAttribute("url2", "method!chengji");
		request.setAttribute("title", "学生成绩管理");
		this.setUrl("chengji/chengjilist.jsp");
		return SUCCESS;

	}
//跳转到添加学生成绩页面
	public String chengjiadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "method!chengjiadd2");
		String sid = request.getParameter("sid");
		request.setAttribute("sid", sid);
		request.setAttribute("title", "学生成绩添加");
		this.setUrl("chengji/chengjiadd.jsp");
		return SUCCESS;
	}
//添加学生成绩操作
	public void chengjiadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String chengji = request.getParameter("chengji");
		String kecheng = request.getParameter("kecheng");
		String laoshi = request.getParameter("laoshi");
		String sid = request.getParameter("sid");
		String xuefen = request.getParameter("xuefen");

		Chengji bean = new Chengji();
		bean.setChengji(chengji);
		bean.setCreatetime(new Date());
		bean.setKecheng(kecheng);
		bean.setLaoshi(laoshi);
		bean.setStudent(studentDao.selectBean(" where id= "+sid));
		bean.setXuefen(xuefen);
		chengjiDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!chengjilist?sid="+sid+"';</script>");
		
		
		
	}
//跳转到更新学生成绩页面
	public String chengjiupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Chengji bean = chengjiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!chengjiupdate2?id="+bean.getId());
		request.setAttribute("title", "学生成绩更新");
		this.setUrl("chengji/chengjiupdate.jsp");
		return SUCCESS;
	}
//更新学生成绩操作
	public void chengjiupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String chengji = request.getParameter("chengji");
		String kecheng = request.getParameter("kecheng");
		String laoshi = request.getParameter("laoshi");
		String xuefen = request.getParameter("xuefen");
		Chengji bean = chengjiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setChengji(chengji);
		bean.setKecheng(kecheng);
		bean.setLaoshi(laoshi);
		bean.setXuefen(xuefen);
		
		chengjiDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!chengjilist?sid="+bean.getStudent().getId()+"';</script>");
	}
//删除学生成绩操作
	public void chengjidelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Chengji bean = chengjiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		chengjiDao.updateBean(bean);

		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!chengjilist?sid="+bean.getStudent().getId()+"';</script>");
	}
	
	//跳转到查看学生成绩页面
	public String chengjiupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Chengji bean = chengjiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "学生成绩查看");
		this.setUrl("chengji/chengjiupdate3.jsp");
		return SUCCESS;
	}
	
	

	
//离校信息列表
	public String studentlist4() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sno = request.getParameter("sno");
		String sname = request.getParameter("sname");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (sno != null && !"".equals(sno)) {

			sb.append("sno like '%" + sno + "%'");
			sb.append(" and ");
			request.setAttribute("sno", sno);
		}
		
		if (sname != null && !"".equals(sname)) {

			sb.append("sname like '%" + sname + "%'");
			sb.append(" and ");
			request.setAttribute("sname", sname);
		}

		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		
		
		sb.append(" teacher.bianhao='"+user.getUsername()+"' and  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = studentDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", studentDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!studentlist4", "共有" + total + "条记录"));
		request.setAttribute("url", "method!studentlist4");
		request.setAttribute("url2", "method!student");
		request.setAttribute("title", "离校信息管理");
		this.setUrl("student/studentlist4.jsp");
		return SUCCESS;

	}
	
	//跳转到更新离校信息页面
	public String studentupdate5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Student bean = studentDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!studentupdate6?id="+bean.getId());
		request.setAttribute("title", "离校信息更新");
		this.setUrl("student/studentupdate5.jsp");
		return SUCCESS;
	}
//更新离校信息操作
	public void studentupdate6() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String lixiaoyuanyin = request.getParameter("lixiaoyuanyin");
		String lixiaoriqi = request.getParameter("lixiaoriqi");
		
		Student bean = studentDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setLixiaoyuanyin(lixiaoyuanyin);
		bean.setLixiaoriqi(lixiaoriqi);
		
		studentDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!studentlist4';</script>");
	}
	
	

	

	
	//学籍查询列表
	public String studentlist6() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sno = request.getParameter("sno");
		String sname = request.getParameter("sname");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (sno != null && !"".equals(sno)) {

			sb.append("sno like '%" + sno + "%'");
			sb.append(" and ");
			request.setAttribute("sno", sno);
		}
		
		if (sname != null && !"".equals(sname)) {

			sb.append("sname like '%" + sname + "%'");
			sb.append(" and ");
			request.setAttribute("sname", sname);
		}

	
		
		
		sb.append("  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = studentDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", studentDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!studentlist6", "共有" + total + "条记录"));
		request.setAttribute("url", "method!studentlist6");
		request.setAttribute("url2", "method!student");
		request.setAttribute("title", "学籍查询管理");
		this.setUrl("student/studentlist6.jsp");
		return SUCCESS;

	}
	
	
	//跳转到查看查看详细信息页面
	public String studentupdate9() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Student bean = studentDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "详细信息查看");
		
		
		request.setAttribute("chengjilist", chengjiDao.selectBeanList(0, 9999, " where  student.id="+bean.getId()));
		
		this.setUrl("student/studentupdate9.jsp");
		return SUCCESS;
	}
	
	
	//班主任账户列表
	public String userlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");


		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {

			sb.append("username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}
		
		

		
		
		
		
		sb.append(" role=2 and  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!userlist");
		request.setAttribute("url2", "method!user");
		request.setAttribute("title", "班主任账户查询");
		this.setUrl("user/userlist.jsp");
		return SUCCESS;

	}
	
	
	//学生账户列表
	public String userlist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");


		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {

			sb.append("username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}
		
		

		
		
		
		
		sb.append(" role=3 and  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist2", "共有" + total + "条记录"));
		request.setAttribute("url", "method!userlist2");
		request.setAttribute("url2", "method!user");
		request.setAttribute("title", "学生账户查询");
		this.setUrl("user/userlist2.jsp");
		return SUCCESS;

	}
	
	
	//个人学籍查询列表
	public String studentlist7() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sno = request.getParameter("sno");
		String sname = request.getParameter("sname");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (sno != null && !"".equals(sno)) {

			sb.append("sno like '%" + sno + "%'");
			sb.append(" and ");
			request.setAttribute("sno", sno);
		}
		
		if (sname != null && !"".equals(sname)) {

			sb.append("sname like '%" + sname + "%'");
			sb.append(" and ");
			request.setAttribute("sname", sname);
		}


		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		
		sb.append(" sno='"+user.getUsername()+"' and  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = studentDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", studentDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!studentlist7", "共有" + total + "条记录"));
		request.setAttribute("url", "method!studentlist7");
		request.setAttribute("url2", "method!student");
		request.setAttribute("title", "个人学籍查询管理");
		this.setUrl("student/studentlist7.jsp");
		return SUCCESS;

	}
	
}
