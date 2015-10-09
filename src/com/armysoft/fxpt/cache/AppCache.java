package com.armysoft.fxpt.cache;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.armysoft.fxpt.model.CdCategories;
import com.armysoft.fxpt.model.CdSmallclass;
import com.armysoft.fxpt.service.member.CdCategoriesService;
import com.armysoft.fxpt.service.member.CdSmallclassService;



public class AppCache {
	private static Hashtable db_store=new Hashtable();
	@SuppressWarnings("unchecked")
	public static <T> List<T> getDbList(String key)
	{
		return (List<T>)db_store.get(key);
	}
	public static <T> T getDb(String key)
	{
		return (T)db_store.get(key);
	}
	@Resource
	private CdCategoriesService dlservice;
	public CdCategoriesService getDlservice() {
		return dlservice;
	}
	public void setDlservice(CdCategoriesService dlservice) {
		this.dlservice = dlservice;
	}
	public CdSmallclassService getXlservice() {
		return xlservice;
	}
	public void setXlservice(CdSmallclassService xlservice) {
		this.xlservice = xlservice;
	}
	@Resource
	private CdSmallclassService xlservice;
	
	public void init() {
		
          System.out.println("*******************************");
          System.out.println("初始化定义");
          System.out.println("*******************************");
          initGllb();
	}
	
	
	
	private void initGllb()
	{
		
	    System.out.println("--- 管理类别 初始化---");		
	    Map<String, Object> params = new HashMap<String, Object>();
		List<CdCategories> cdCategoriesList=dlservice.getCdCategories(params);
		db_store.put("cd_categories", cdCategoriesList);
		
		for(int i=0;i<cdCategoriesList.size();i++)
		{
			CdCategories db=cdCategoriesList.get(i);
			List<CdSmallclass> cdSmallclassList=xlservice.getCdCategories(db.getCategoriesdm());
			
			db_store.put("cd_smallclass_"+db.getCategoriesdm(), cdSmallclassList);
		}
		cdCategoriesList=null;
	}
	/***
	 * 缓存区域省地市
	 */
	
}