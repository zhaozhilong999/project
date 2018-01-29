
package org.item.web.webclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.item.web.webclient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _QueryUser_QNAME = new QName("http://webservice.web.item.org/", "queryUser");
    private final static QName _QueryUserResponse_QNAME = new QName("http://webservice.web.item.org/", "queryUserResponse");
    private final static QName _Save_QNAME = new QName("http://webservice.web.item.org/", "save");
    private final static QName _SaveResponse_QNAME = new QName("http://webservice.web.item.org/", "saveResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.item.web.webclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryUser }
     * 
     */
    public QueryUser createQueryUser() {
        return new QueryUser();
    }

    /**
     * Create an instance of {@link QueryUserResponse }
     * 
     */
    public QueryUserResponse createQueryUserResponse() {
        return new QueryUserResponse();
    }

    /**
     * Create an instance of {@link Save }
     * 
     */
    public Save createSave() {
        return new Save();
    }

    /**
     * Create an instance of {@link SaveResponse }
     * 
     */
    public SaveResponse createSaveResponse() {
        return new SaveResponse();
    }

    /**
     * Create an instance of {@link WxUser }
     * 
     */
    public WxUser createWxUser() {
        return new WxUser();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.web.item.org/", name = "queryUser")
    public JAXBElement<QueryUser> createQueryUser(QueryUser value) {
        return new JAXBElement<QueryUser>(_QueryUser_QNAME, QueryUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.web.item.org/", name = "queryUserResponse")
    public JAXBElement<QueryUserResponse> createQueryUserResponse(QueryUserResponse value) {
        return new JAXBElement<QueryUserResponse>(_QueryUserResponse_QNAME, QueryUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Save }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.web.item.org/", name = "save")
    public JAXBElement<Save> createSave(Save value) {
        return new JAXBElement<Save>(_Save_QNAME, Save.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.web.item.org/", name = "saveResponse")
    public JAXBElement<SaveResponse> createSaveResponse(SaveResponse value) {
        return new JAXBElement<SaveResponse>(_SaveResponse_QNAME, SaveResponse.class, null, value);
    }

}
