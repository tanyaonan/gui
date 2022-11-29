package demo.gui.util;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import demo.gui.views.ViewsManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.awt.Color;
import java.io.IOException;

/**
 * 布局阅读器
 * @author tanyaonan
 *
 */
public class Reader {
	
	private Layout layout = new Layout(); // 布局控件
	private ViewsManager viewsManager = new ViewsManager(); // 控件管理器
	private JPanel Panel = new JPanel(); // 主容器
	
	public Reader(String xmlPath) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			String path = this.getClass().getClassLoader().getResource("res/layout/" + xmlPath).getPath();
			Document document = db.parse(path);
			
			// 绑定容器
			layout.setContentPane(Panel);
			// 设置布局管理器
			layout.setLayout(new LayoutManager());
			
			// 构建head节点
			buildHead(document.getElementsByTagName("head"));
			
			// 构建body节点
			buildBody(document.getElementsByTagName("body"));
			
			// 构建div节点
			buildDiv(getDom(document.getElementsByTagName("body")));
			
			// 显示布局
			layout.setVisible(true);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	private NodeList getDom(NodeList nodeList) {
		if (nodeList.getLength() > 0) {
			// 默认最后一个节点生效
			return nodeList.item(nodeList.getLength() - 1).getChildNodes();
			/**
			 * 为什么不使用 getElementsByTagName -》 div ？
			 * 因为这样会遍历全部div节点，包括子节点，
			 * 而我们的需求是一级一级地处理，并不是放到同一节点下
			 */
		}
		return null;
	}

	// 构建div
	private void buildDiv(NodeList list) {
		if (list != null && list.getLength() > 0) {
			for (int i = 0; i < list.getLength(); i++) {
				// 判断是否节点，是则读取
				if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
					NodeList childNodes = list.item(i).getChildNodes();
					
					buildDivChild(childNodes, null);
				}
			}
		}
	}
	
	// 构建div全部子节点
	private void buildDivChild(NodeList childNodes, JPanel panel2) {
		JPanel panel = new JPanel(); // 子容器
		panel.setBorder(BorderFactory.createLineBorder(new Color(205, 205, 205), 1));
		
		if (panel2 != null) {
			panel2.add(panel);
		} else {
			Panel.add(panel);
		}

		// 遍历子节点
		for (int j = 0; j < childNodes.getLength(); j++) {
			Node n = childNodes.item(j);
			if (n.getNodeName() != "#text") {
				// 构建节点为控件
				buildView(n, panel);
			}
		}
	}

	/**
	 * 构建子节点内容
	 * @param childNodes 子节点列表
	 * @param id 子节点绑定的唯一id
	 */
	private void buildOptions(NodeList childNodes) {
		
		if (childNodes.getLength() > 0) {
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node node = childNodes.item(i);
				if (node.getNodeName() != "#text") {
					switch (node.getNodeName()) {
					case "title":
						layout.setTitle(node.getTextContent());
						break;
					default:
						break;
					}
				}
			}
		}
	}
	
	// 构建head
	private void buildHead(NodeList head){
		if (head.getLength() > 0) {
			// 默认最后一个节点生效
			Node node = head.item(head.getLength() - 1);
			// 获取子节点参数
			buildOptions(node.getChildNodes());
		}
	}

	// 构建body
	private void buildBody(NodeList body) {
		if (body.getLength() > 0) {
			// 默认最后一个节点生效
			NamedNodeMap map = body.item(body.getLength() - 1).getAttributes();
			if (map.getNamedItem("width") != null && map.getNamedItem("height") != null) {
				int width = Integer.parseInt(map.getNamedItem("width").getTextContent());
				int height = Integer.parseInt(map.getNamedItem("height").getTextContent());
				layout.setSize(width, height);
			}
		}
	}
	
	// 构建控件
	private void buildView(Node node, JPanel panel) {
		String text = node.getTextContent();
		NamedNodeMap arributes = node.getAttributes();
		
		switch (node.getNodeName()) {
		case "div":
			buildDivChild(node.getChildNodes(), panel);
			break;
		case "span":
			viewsManager.setTextView(text, arributes , panel);
			break;
		case "button":
			viewsManager.setButtonView(text, arributes, panel);
			break;
		case "input":
			viewsManager.setInputView(arributes, panel);
			break;
		case "select":
			// 构建view
			viewsManager.setSelectView(arributes, panel, node.getChildNodes());
			break;
		case "h1":
			viewsManager.setTitleTextView(text, arributes, panel, 1);
			break;
		case "h2":
			viewsManager.setTitleTextView(text, arributes, panel, 2);
			break;
		case "h3":
			viewsManager.setTitleTextView(text, arributes, panel, 3);
			break;
		case "h4":
			viewsManager.setTitleTextView(text, arributes, panel, 4);
			break;
		case "h5":
			viewsManager.setTitleTextView(text, arributes, panel, 5);
			break;
		case "h6":
			viewsManager.setTitleTextView(text, arributes, panel, 6);
			break;
		default:
			break;
		}
	}
}
