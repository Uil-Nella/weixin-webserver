
package com.yonyou.weixin.core.mp.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.yonyou.weixin.core.mp.client package. 
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

    private final static QName _Exception_QNAME = new QName("http://itf.mp.uap.yonyou.com/", "Exception");
    private final static QName _GetMessageVOsByUserAndSystemAndTypeResponse_QNAME = new QName("http://itf.mp.uap.yonyou.com/", "getMessageVOsByUserAndSystemAndTypeResponse");
    private final static QName _GetMessageVOsByUserAndSystemAndType_QNAME = new QName("http://itf.mp.uap.yonyou.com/", "getMessageVOsByUserAndSystemAndType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.yonyou.weixin.core.mp.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link GetMessageVOsByUserAndSystemAndType }
     * 
     */
    public GetMessageVOsByUserAndSystemAndType createGetMessageVOsByUserAndSystemAndType() {
        return new GetMessageVOsByUserAndSystemAndType();
    }

    /**
     * Create an instance of {@link GetMessageVOsByUserAndSystemAndTypeResponse }
     * 
     */
    public GetMessageVOsByUserAndSystemAndTypeResponse createGetMessageVOsByUserAndSystemAndTypeResponse() {
        return new GetMessageVOsByUserAndSystemAndTypeResponse();
    }

    /**
     * Create an instance of {@link MpMessageUrlVO }
     * 
     */
    public MpMessageUrlVO createMpMessageUrlVO() {
        return new MpMessageUrlVO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://itf.mp.uap.yonyou.com/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMessageVOsByUserAndSystemAndTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://itf.mp.uap.yonyou.com/", name = "getMessageVOsByUserAndSystemAndTypeResponse")
    public JAXBElement<GetMessageVOsByUserAndSystemAndTypeResponse> createGetMessageVOsByUserAndSystemAndTypeResponse(GetMessageVOsByUserAndSystemAndTypeResponse value) {
        return new JAXBElement<GetMessageVOsByUserAndSystemAndTypeResponse>(_GetMessageVOsByUserAndSystemAndTypeResponse_QNAME, GetMessageVOsByUserAndSystemAndTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMessageVOsByUserAndSystemAndType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://itf.mp.uap.yonyou.com/", name = "getMessageVOsByUserAndSystemAndType")
    public JAXBElement<GetMessageVOsByUserAndSystemAndType> createGetMessageVOsByUserAndSystemAndType(GetMessageVOsByUserAndSystemAndType value) {
        return new JAXBElement<GetMessageVOsByUserAndSystemAndType>(_GetMessageVOsByUserAndSystemAndType_QNAME, GetMessageVOsByUserAndSystemAndType.class, null, value);
    }

}
