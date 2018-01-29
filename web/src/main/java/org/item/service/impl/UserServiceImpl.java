package org.item.service.impl;

import org.item.entity.WxUser;
import org.item.service.WxUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
//@Qualifier("userInfo")
public class UserServiceImpl implements WxUserService {

	@Override
	public int insert(WxUser user) {
		System.out.println("+++添加新用户+++");
		return 0;
	}

}
