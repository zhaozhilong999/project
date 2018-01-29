package org.item.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {

	public static void main(String[] args) {
		List<String> list=new ArrayList<>();
		Iterator i=list.iterator();
		Map<String, Object> map=new TreeMap<>();
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/test/ioc/applicationContext.xml");  
	}
}
