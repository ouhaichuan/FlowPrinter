package com.wewin.flowprinter.xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * 
 * @author HCOU
 * @date 2013-6-17
 */
public class Sax4Xml {
	/**
	 * 加载并解析XML输入流
	 * 
	 * @date 2013-6-17
	 * @param stream
	 */
	public void doLoadAndParseXml(String xml_data, XmlHandler handler) {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = spf.newSAXParser();
			saxParser.parse(xml_data, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
