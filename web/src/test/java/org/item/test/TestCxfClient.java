package org.item.test;

import java.util.List;

import org.item.web.webclient.WebRequestService;
import org.item.web.webclient.WebRequestServiceImplService;
import org.item.web.webclient.WxUser;

import com.alibaba.fastjson.JSON;

public class TestCxfClient {

	public static void main(String[] args) {
		WebRequestServiceImplService ws=new WebRequestServiceImplService();
		WebRequestService w = ws.getWebRequestServiceImplPort();
		List<WxUser> queryUser = w.queryUser();
		System.out.println(JSON.toJSONString(queryUser));
	}
}
