package com.henghao.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * String转XML
 * 短信服务专用方法noteserve(String sendSms)
 * @author 周承耀
 *
 */
public class StringToXmlUtil {

	public static String noteserve(String sendSms) throws ParserConfigurationException, SAXException, IOException {
		//将返回String转化为xml形式
		StringReader sr = new StringReader(sendSms);   
		InputSource is = new InputSource(sr);   
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
		DocumentBuilder builder=factory.newDocumentBuilder();    
		Document doc = builder.parse(is);
		//获取成功标识
		String nodeValue = doc.getElementsByTagName("returnstatus").item(0).getFirstChild().getNodeValue();  
		//("nodeValue:   "+nodeValue);
		return nodeValue;
	}
}
