package org.item.test;

import org.item.entity.WxUser;
import org.item.service.WxUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class TestAdd extends BaseJunit4Test{
	@Autowired
	//@Qualifier("userInfo")
	WxUserService wxUserServiceImpl;//多态时可以在类和方法上加上@Qualifier注解指定实现类。也可以用‘变量名’指定实现类（变量名必须是实现类的第一个字母小写的全称），
	@Autowired
	WxUserService userServiceImpl;
	@Test
	public void testAdd(){
		WxUser user=new WxUser();
		user.setId(System.currentTimeMillis()+"");
		wxUserServiceImpl.insert(user);
		userServiceImpl.insert(null);
	}
}
