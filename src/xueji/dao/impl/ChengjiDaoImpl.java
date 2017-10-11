package xueji.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import xueji.dao.ChengjiDao;
import xueji.model.Chengji;











public class ChengjiDaoImpl extends HibernateDaoSupport implements  ChengjiDao{


	public void deleteBean(Chengji bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Chengji bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Chengji selectBean(String where) {
		List<Chengji> list = this.getHibernateTemplate().find("from Chengji " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Chengji "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Chengji> selectBeanList(final int start,final int limit,final String where) {
		return (List<Chengji>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Chengji> list = session.createQuery("from Chengji "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Chengji bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
