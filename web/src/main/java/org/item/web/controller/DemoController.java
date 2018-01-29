package org.item.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("demo")
public class DemoController {

	@RequestMapping("test")
	public String test(){
		System.out.println("测试");
		return "success";
	}
	@RequestMapping("page")
	public ModelAndView index(){
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
	@RequestMapping("page3")
	public String index3(HttpServletRequest request){
		System.out.println("进入页面");
		request.setAttribute("message", "测试页面");
		return "html/index";
	}
	@RequestMapping("page4")
	public String index4(HttpServletRequest request){
		System.out.println("进入页面");
		request.setAttribute("message", "测试页面");
		return "jsp/index";
	}
}
