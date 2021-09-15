
package com.example.demo.calculator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6b21 
 * Generated source version: 2.0
 *
 */

@PropertySource("classpath:application.yml")
@WebServiceClient(name = "Calculator", targetNamespace = "http://tempuri.org/",  wsdlLocation = "file:/C:/Projects/simple-spring-crud-app/src/main/resources/wsdl/calcul.wsdl")
public class Calculator
    extends Service
{
    private static final URL CALCULATOR_WSDL_LOCATION;
    private static final WebServiceException CALCULATOR_EXCEPTION;
    private final static QName CALCULATOR_QNAME = new QName("http://tempuri.org/", "Calculator");

    @Value("${spring.wsdl.location}")
    private static String wsdlLocation;

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            assert false;
            url = new URL("file:/C:/Projects/simple-spring-crud-app/src/main/resources/wsdl/calcul.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CALCULATOR_WSDL_LOCATION = url;
        CALCULATOR_EXCEPTION = e;
    }

    public Calculator() {
        super(__getWsdlLocation(), CALCULATOR_QNAME);
    }

    public Calculator(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * 
     * @return
     *     returns CalculatorSoap
     */
    @WebEndpoint(name = "CalculatorSoap")
    public CalculatorSoap getCalculatorSoap() {
        return super.getPort(new QName("http://tempuri.org/", "CalculatorSoap"), CalculatorSoap.class);
    }

    private static URL __getWsdlLocation() {
        if (CALCULATOR_EXCEPTION!= null) {
            throw CALCULATOR_EXCEPTION;
        }
        return CALCULATOR_WSDL_LOCATION;
    }
}
