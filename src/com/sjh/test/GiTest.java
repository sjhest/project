package com.sjh.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sjh.project.model.Gi;

public class GiTest {

	ApplicationContext ctx = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void testSave() {
		
		Configuration conf = new Configuration().configure();
		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		SessionFactory sf = conf.buildSessionFactory(sr);
		
		Gi country = new Gi();
		country.setText("中国");
		country.setType("country");
		
		Gi province = new Gi();
		province.setText("北京");
		province.setType("province");
		province.setParentGi(country);
		Set<Gi> provinceSet = new HashSet<>();
		provinceSet.add(province);
		country.setChildrenGi(provinceSet);
		
		Gi city = new Gi();
		city.setText("北京");
		city.setType("city");
		city.setParentGi(province);
		Set<Gi> CitySet = new HashSet<>();
		CitySet.add(city);
		province.setChildrenGi(CitySet);
		
	       try {
	           Session session=sf.openSession();
	           session.beginTransaction();
	            //注解中加了cascade={CascadeType.ALL}，只存跟节点就把整颗树
	            //存到了数据库。
	           session.save(country);

	           session.getTransaction().commit();
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	}
	
	@Test
	public void testLoad(){
		Configuration conf = new Configuration().configure();
		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		SessionFactory sf = conf.buildSessionFactory(sr);
		Session session=sf.openSession();
		Gi x = (Gi) session.get(Gi.class,1);
		Print(x,0);
		session.getTransaction().commit();
	}

	private void Print(Gi o,int level) {
	   String preLevel="";
	   for (int i = 0; i < level; i++) {
	       preLevel+="----";
	   }
	   System.out.println(preLevel+o.getText());
	   for (Gi org:o.getChildrenGi()) {
	       Print(org,level+1);
	   }
	}
}
