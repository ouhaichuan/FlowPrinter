package com.wewin.flowprinter.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 本类是专门解析XML文件的，主要用于为系统读取自己的配置文件时提供最方便的解析操作
 * 
 * @author HCOU
 * @date 2013-6-17
 */
public class XmlManager {

	/**
	 * 得到某节点下某个属性的值
	 * 
	 * @param element
	 *            要获取属性的节点
	 * @param attributeName
	 *            要取值的属性名称
	 * @return 要获取的属性的值
	 * @author HCOU
	 * @date 2013-6-17
	 */
	public static String getAttribute(Element element, String attributeName) {
		return element.getAttribute(attributeName);
	}

	/**
	 * 获取指定节点下的文本
	 * 
	 * @param element
	 *            要获取文本的节点
	 * @return 指定节点下的文本
	 * @author HCOU
	 * @date 2013-6-17
	 */
	public static String getText(Element element) {
		return element.getFirstChild().getNodeValue();
	}

	/**
	 * 解析某个xml文件，并在内存中创建DOM树
	 * 
	 * @param xmlFile
	 *            要解析的XML文件
	 * @return 解析某个配置文件后的Document
	 * @author HCOU
	 * @date 2013-6-17
	 * @throws Exception
	 *             xml文件不存在
	 */
	public static Document parse(String xmlFile) throws Exception {
		// 绑定XML文件，建造DOM树
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document domTree = db.parse(xmlFile);
		return domTree;
	}

	/**
	 * 获得某节点下的某个子节点（指定子节点名称，和某个属性的值）
	 * 即获取parentElement下名字叫childName，并且属性attributeName的值为attributeValue的子结点
	 * 
	 * @param parentElement
	 *            要获取子节点的那个父节点
	 * @param childName
	 *            要获取的子节点名称
	 * @param attributeName
	 *            要指定的属性名称
	 * @param attributeValue
	 *            要指定的属性的值
	 * @return 符合条件的子节点
	 * @throws Exception
	 *             子结点不存在或有多个符合条件的子节点
	 * @author HCOU
	 * @date 2013-6-17
	 */
	public static Element getChildElement(Element parentElement,
			String childName, String attributeName, String attributeValue)
			throws Exception {
		NodeList list = parentElement.getElementsByTagName(childName);
		int count = 0;
		Element curElement = null;
		for (int i = 0; i < list.getLength(); i++) {
			Element child = (Element) list.item(i);
			String value = child.getAttribute(attributeName);
			if (true == value.equals(attributeValue)) {
				curElement = child;
				count++;
			}
		}
		if (0 == count) {
			throw new Exception("找不到个符合条件的子节点！");
		} else if (1 < count) {
			throw new Exception("找到多个符合条件的子节点！");
		}

		return curElement;
	}

	/**
	 * 得到某节点下的某个子节点（通过指定子节点名称） 即获取parentElement下名字叫childName的子节点
	 * 
	 * @param parentElement
	 *            要获取子节点的父节点
	 * @param childName
	 *            要获取的子节点名称
	 * @return 符合条件的子节点
	 * @throws Exception
	 *             找不到符合条件的子结点或找到多个符合条件的子节点
	 */
	public static Element getChildElement(Element parentElement,
			String childName) throws Exception {
		NodeList list = parentElement.getElementsByTagName(childName);
		Element curElement = null;
		if (1 == list.getLength()) {
			curElement = (Element) list.item(0);
		} else if (0 == list.getLength()) {
			throw new Exception("找不到个符合条件的子节点！");
		} else {
			throw new Exception("找到多个符合条件的子节点！");
		}
		return curElement;
	}
}
