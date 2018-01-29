package org.item.test;

import javax.xml.namespace.QName;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.alibaba.fastjson.JSON;

public class TestCxf {
	public static void main(String[] args) {
		//动态调用cxf接口
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        org.apache.cxf.endpoint.Client client = dcf
                .createClient("http://localhost:8081/web/webservice/userService?wsdl");
        // url为调用webService的wsdl地址
        //QName name = new QName("http://webservice.web.item.org/", "queryUser");
        // namespace是命名空间，methodName是方法名
        String xmlStr = "aaaaaaaa";
        // paramvalue为参数值
        Object[] objects;
        try {
        	WxUser user=new WxUser();
        	user.setNickname("admin");
            objects = client.invoke("save",JSON.toJSONString(user));
            System.out.println(JSON.toJSONString(objects));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
