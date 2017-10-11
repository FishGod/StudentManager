package xueji.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import xueji.dao.TeacherDao;
import xueji.model.Teacher;











public class TeacherDaoImpl extends HibernateDaoSupport implements  TeacherDao{


	public void deleteBean(Teacher bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Teacher bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Teacher selectBean(String where) {
		List<Teacher> list = this.getHibernateTemplate().find("from Teacher " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Teacher "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Teacher> selectBeanList(final int start,final int limit,final String where) {
		return (List<Teacher>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Teacher> list = session.createQuery("from Teacher "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Teacher bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
