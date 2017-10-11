package xueji.dao;

import java.util.List;

import xueji.model.Chengji;




public interface ChengjiDao  {
	
	
	
	public void insertBean(Chengji bean);
	
	public void deleteBean(Chengji bean);
	
	public void updateBean(Chengji bean);

	public Chengji selectBean(String where);
	
	public List<Chengji> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
