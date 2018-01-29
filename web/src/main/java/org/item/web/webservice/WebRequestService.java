package org.item.web.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.item.entity.WxUser;

@WebService
public interface WebRequestService {
	@WebMethod
	public List<WxUser> queryUser();
	@WebMethod
	public int save(String user);
}
