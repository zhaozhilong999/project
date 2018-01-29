package org.item.test;

import org.item.entity.WxUser;
import org.item.service.AopInterface;
import org.item.service.WxUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AopTest extends BaseJunit4Test {

	@Autowired
	AopInterface aopInterface;
	@Autowired
	WxUserService wxUserService;
	@Test
	public void test(){
		WxUser user=new WxUser();
		user.setId(System.currentTimeMillis()+"");
		user.setNickname("admin");
		int i = wxUserService.insert(user);
		System.out.println(i+"============================================================");
		
	}
}
