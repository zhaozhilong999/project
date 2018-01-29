package org.item.task;

import org.item.entity.WxUser;
import org.item.service.impl.WxUserServiceImpl;
import org.item.test.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ThreadPool extends BaseJunit4Test{
//	@Autowired
//	 private ThreadPoolTaskExecutor threadPool;
	@Autowired
	WxUserServiceImpl wxUserService;
	@Test
	public void test(){
		WxUser r=new WxUser();
		r.setId("admin"+System.currentTimeMillis());
		r.setNickname("测试");
		//threadPool.execute(new UserService(wxUserService));
		wxUserService.insert(r);
		System.out.println("-----");
	}
}
