package org.item.web.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.item.entity.WxUser;

import com.alibaba.fastjson.JSON;
@WebService
public class WebRequestServiceImpl implements WebRequestService {

	@Override
	public List<WxUser> queryUser() {
		List<WxUser> list=new ArrayList<>();
		WxUser user1=new WxUser();
		WxUser user2=new WxUser();
		WxUser user3=new WxUser();
		WxUser user4=new WxUser();
		user1.setNickname("张三");
		user1.setId("1");
		user2.setNickname("李四");
		user2.setId("2");
		user3.setNickname("王五");
		user3.setId("3");
		user4.setNickname("赵六");
		user4.setId("4");
		list.add(user1);
		list.add(user2);
		list.add(user3);
		list.add(user4);
		System.out.println(JSON.toJSONString(list));
		return list;
	}

	@Override
	public int save(String user) {
		System.out.println("用户："+user);
		return 1;
	}

}
