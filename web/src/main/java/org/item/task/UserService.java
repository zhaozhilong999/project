package org.item.task;

import org.item.dao.WxUserMapper;
import org.item.entity.WxUser;
import org.item.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

public class UserService implements Runnable{

	private WxUserService wxUserService;
	public UserService(WxUserService wxUserService) {
		this.wxUserService=wxUserService;
	}
	public UserService() {
	}
	@Override
	public void run() {
		System.out.println("添加开始==================");
		WxUser r=new WxUser();
		r.setId("admin"+System.currentTimeMillis());
		r.setNickname("测试");
		int i = wxUserService.insert(r);
		System.out.println("添加完成=========================="+i);
	}

}
