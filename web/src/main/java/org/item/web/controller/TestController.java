package org.item.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.item.annotation.Login;
import org.item.entity.WxUser;
import org.item.service.WxUserService;
import org.item.task.UserService;
import org.item.web.controller.base.BaseController;
import org.item.web.webclient.WebRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

@RestController
public class TestController extends BaseController{
	
	@Autowired
	WxUserService wxUserService;
	@Autowired
	WebRequestService userClient;
	@Autowired
	private ThreadPoolTaskExecutor threadPool;
	
	@RequestMapping("cxf")
	public List<org.item.web.webclient.WxUser> testCxf(){
		List<org.item.web.webclient.WxUser> list = userClient.queryUser();
		System.out.println(JSON.toJSONString(list));
		return list;
	}
	@SuppressWarnings("null")
	@RequestMapping("res")
	public String response(){
		
		return "成功";
	}
	@RequestMapping("add")
	public String test(){
		System.out.println("测试");
		WxUser user=new WxUser();
		user.setId("100"+System.currentTimeMillis());
		user.setNickname("赵测试");
		user.setCreatetime(new Date());
		wxUserService.insert(user);
		threadPool.execute(new UserService(wxUserService));
		return "success";
	}
	@RequestMapping("page")
	@Login
	public ModelAndView index(){
		logger.info("日志");
		System.out.println("进入页面");
		ModelAndView mv=new ModelAndView("html/index");
		mv.addObject("message", "进入页面了");
		return mv;
	}
	@RequestMapping("page2")
	public ModelAndView index2(){
		System.out.println("进入页面");
		ModelAndView mv=new ModelAndView("jsp/index");
		mv.addObject("message", "进入页面了");
		return mv;
	}
	@RequestMapping("json")
	public Map<String, Object> json(@RequestParam(value="array") Map<String, Object> array){
		Map<String, Object> json=new HashMap<>();
		System.out.println(array.size());
		json.put("code", "200");
		json.put("message", "成功");
		json.put("data", array);
		return json;
	}
}
