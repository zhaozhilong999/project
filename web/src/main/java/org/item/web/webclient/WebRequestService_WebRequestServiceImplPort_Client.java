
package org.item.web.webclient;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.14
 * 2018-01-05T11:16:29.938+08:00
 * Generated source version: 3.1.14
 * 
 */
public final class WebRequestService_WebRequestServiceImplPort_Client {

    private static final QName SERVICE_NAME = new QName("http://webservice.web.item.org/", "WebRequestServiceImplService");

    private WebRequestService_WebRequestServiceImplPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = WebRequestServiceImplService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        WebRequestServiceImplService ss = new WebRequestServiceImplService(wsdlURL, SERVICE_NAME);
        WebRequestService port = ss.getWebRequestServiceImplPort();  
        
        {
        System.out.println("Invoking save...");
        java.lang.String _save_arg0 = "";
        int _save__return = port.save(_save_arg0);
        System.out.println("save.result=" + _save__return);


        }
        {
        System.out.println("Invoking queryUser...");
        java.util.List<org.item.web.webclient.WxUser> _queryUser__return = port.queryUser();
        System.out.println("queryUser.result=" + _queryUser__return);


        }

        System.exit(0);
    }

}
