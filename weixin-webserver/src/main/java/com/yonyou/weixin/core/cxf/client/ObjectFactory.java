
package com.yonyou.weixin.core.cxf.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.yonyou.weixin.core.cxf.client package. 
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

    private final static QName _BindUserResponse_QNAME = new QName("http://service.yyuser.cxf.note.dd.org/", "bindUserResponse");
    private final static QName _FindUserResponse_QNAME = new QName("http://service.yyuser.cxf.note.dd.org/", "findUserResponse");
    private final static QName _UpdateUser_QNAME = new QName("http://service.yyuser.cxf.note.dd.org/", "updateUser");
    private final static QName _FindUser_QNAME = new QName("http://service.yyuser.cxf.note.dd.org/", "findUser");
    private final static QName _BindUser_QNAME = new QName("http://service.yyuser.cxf.note.dd.org/", "bindUser");
    private final static QName _UpdateUserResponse_QNAME = new QName("http://service.yyuser.cxf.note.dd.org/", "updateUserResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.yonyou.weixin.core.cxf.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateUserResponse }
     * 
     */
    public UpdateUserResponse createUpdateUserResponse() {
        return new UpdateUserResponse();
    }

    /**
     * Create an instance of {@link BindUser }
     * 
     */
    public BindUser createBindUser() {
        return new BindUser();
    }

    /**
     * Create an instance of {@link FindUser }
     * 
     */
    public FindUser createFindUser() {
        return new FindUser();
    }

    /**
     * Create an instance of {@link BindUserResponse }
     * 
     */
    public BindUserResponse createBindUserResponse() {
        return new BindUserResponse();
    }

    /**
     * Create an instance of {@link UpdateUser }
     * 
     */
    public UpdateUser createUpdateUser() {
        return new UpdateUser();
    }

    /**
     * Create an instance of {@link FindUserResponse }
     * 
     */
    public FindUserResponse createFindUserResponse() {
        return new FindUserResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BindUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.yyuser.cxf.note.dd.org/", name = "bindUserResponse")
    public JAXBElement<BindUserResponse> createBindUserResponse(BindUserResponse value) {
        return new JAXBElement<BindUserResponse>(_BindUserResponse_QNAME, BindUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.yyuser.cxf.note.dd.org/", name = "findUserResponse")
    public JAXBElement<FindUserResponse> createFindUserResponse(FindUserResponse value) {
        return new JAXBElement<FindUserResponse>(_FindUserResponse_QNAME, FindUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.yyuser.cxf.note.dd.org/", name = "updateUser")
    public JAXBElement<UpdateUser> createUpdateUser(UpdateUser value) {
        return new JAXBElement<UpdateUser>(_UpdateUser_QNAME, UpdateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.yyuser.cxf.note.dd.org/", name = "findUser")
    public JAXBElement<FindUser> createFindUser(FindUser value) {
        return new JAXBElement<FindUser>(_FindUser_QNAME, FindUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BindUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.yyuser.cxf.note.dd.org/", name = "bindUser")
    public JAXBElement<BindUser> createBindUser(BindUser value) {
        return new JAXBElement<BindUser>(_BindUser_QNAME, BindUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.yyuser.cxf.note.dd.org/", name = "updateUserResponse")
    public JAXBElement<UpdateUserResponse> createUpdateUserResponse(UpdateUserResponse value) {
        return new JAXBElement<UpdateUserResponse>(_UpdateUserResponse_QNAME, UpdateUserResponse.class, null, value);
    }

}
