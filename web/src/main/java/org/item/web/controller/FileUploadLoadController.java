package org.item.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.item.common.Result;
import org.item.util.poi.ExcelPOIUtil;
import org.item.util.poi.entity.ExcelEntity;
import org.springframework.stereotype.Controller;
/**
 * 文件上传
 * @author ZhaoZhilong
 * @date 2018年1月11日
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/upload")
public class FileUploadLoadController {

	@RequestMapping("/uploadFile")
	@ResponseBody
	public Result uploadFile(@RequestParam MultipartFile[] file, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println(request.getParameter("upload"));
		Result result=new Result();
		// 如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
		// 如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
		// 上传多个文件时,前台表单中的所有<input
		// type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
		String originalFilename = "";
		for (MultipartFile myfile : file) {
			if (myfile.isEmpty()) {
				result.addError("请选择文件");
				return result;
			} else {
				originalFilename = myfile.getOriginalFilename();
				System.out.println("文件原名: " + originalFilename);
				System.out.println("文件名称: " + myfile.getName());
				System.out.println("文件长度: " + myfile.getSize());
				System.out.println("文件类型: " + myfile.getContentType());
				System.out.println("========================================");
				try {
					// 这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
					// 此处也可以使用Spring提供的MultipartFile.transferTo(File
					// dest)方法实现文件的上传
//					ExcelEntity readExcel2Line = ExcelPOIUtil.readExcel2Line(myfile.getInputStream(), originalFilename);
//					System.out.println(JSON.toJSONString(readExcel2Line));
					FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(request.getSession().getServletContext().getRealPath("/")+"upload/", originalFilename));
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		result.addOK("上传完成");
		result.setData(request.getContextPath()+"/upload/"+originalFilename);
		return result;
	}
	@RequestMapping("gopage")
	public void gopage(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		System.out.println(request.getRealPath(request.getRequestURI()));
		System.out.println(request.getRequestURI());
		System.out.println(this.getClass().getClassLoader().getResource("").getPath());
		request.getRequestDispatcher("/index.html").forward(request, response);
	}
}
