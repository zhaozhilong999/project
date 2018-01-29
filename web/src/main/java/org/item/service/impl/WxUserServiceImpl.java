package org.item.service.impl;

import org.item.dao.WxUserMapper;
import org.item.entity.WxUser;
import org.item.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class WxUserServiceImpl implements WxUserService {

	@Autowired
	WxUserMapper wxUserMapper;
	@Override
	public int insert(WxUser user) {
		System.out.println("====添加用户===");
		return wxUserMapper.insert(user);
	}

}
