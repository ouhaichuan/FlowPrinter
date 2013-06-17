package com.wewin.flowprinter.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlHandler extends DefaultHandler {

	private String tagValue;
	String label;

	private String user_inputNum, guanglu_Name, user_addr;

	public String getUser_inputNum() {
		return user_inputNum;
	}

	public String getGuanglu_Name() {
		return guanglu_Name;
	}

	public String getUser_addr() {
		return user_addr;
	}

	/**
	 * 在解释到一个开始元素时会调用此方法.但是当元素有重复时可以自己写算法来区分
	 * 
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		label = qName;
		if (attributes != null && attributes.getLength() != 0) {
			for (int i = 0; i < attributes.getLength(); i++) {
				System.out.print(attributes.getQName(i) + "=");
				System.out.print(attributes.getValue(i) + " ");
			}
		}
	}

	/**
	 * 在遇到结束标签时调用此方法
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("user_inputNum".equals(qName)) {
			user_inputNum = tagValue;
			return;
		}
		if ("guanglu_Name".equals(qName)) {
			guanglu_Name = tagValue;
			return;
		}
		if ("user_addr".equals(qName)) {
			user_addr = tagValue;
			return;
		}
	}

	/**
	 * 所有的XML文件中的字符会放到tagValue中
	 */
	@Override
	public void characters(char ch[], int start, int length)
			throws SAXException {
		tagValue = new String(ch, start, length).trim();
	}

}
