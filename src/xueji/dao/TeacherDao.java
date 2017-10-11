package xueji.dao;

import java.util.List;

import xueji.model.Teacher;




public interface TeacherDao  {
	
	
	
	public void insertBean(Teacher bean);
	
	public void deleteBean(Teacher bean);
	
	public void updateBean(Teacher bean);

	public Teacher selectBean(String where);
	
	public List<Teacher> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
