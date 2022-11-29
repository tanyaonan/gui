package demo.gui.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import demo.gui.views.ViewsManager;

import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.net.URL;
/**
 * 布局阅读器
 * @author tanyaonan
 *
 */
public class Reader {
	
	private Layout layout = new Layout(); // 布局控件
	private ViewsManager viewsManager = new ViewsManager(); // 控件管理器
	private JPanel panel = new JPanel();
	
	public Reader(String xmlPath) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			URL path = Reader.class.getClassLoader().getResource("demo/gui/layout/" + xmlPath);
			Document document = db.parse(path.toString());
			
			// 绑定容器
			layout.setContentPane(panel);
			// 设置布局管理器
//			layout.setLayout(new BorderLayout());
			
			// 构建head节点
			buildHead(document.getElementsByTagName("head"));
			
			// 构建body节点
			buildBody(document.getElementsByTagName("body"));
			
			// 构建div节点
			buildNode(document.getElementsByTagName("div"), "div");
			
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
	
	// 构建公共节点以及子节点
	private void buildNode(NodeList list, String name) {
		if (list.getLength() > 0) {
			for (int i = 0; i < list.getLength(); i++) {
				// 判断是否节点，是则读取
				if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element ele = (Element) list.item(i);
					NodeList childNodes = ((Node) ele).getChildNodes();

					// 遍历子节点
					for (int j = 0; j < childNodes.getLength(); j++) {
						Node n = childNodes.item(j);
						if (n.getNodeName() != "#text") {
							switch (name) {
							case "div":
								// 构建节点为控件
								buildView(n);
								break;
							default:
								break;
							}
						}
					}
				}
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
	private void buildView(Node node) {
		String text = node.getTextContent();
		NamedNodeMap arributes = node.getAttributes();
		
		switch (node.getNodeName()) {
		case "div":
			// 构建子项
			buildNode(node.getChildNodes(), "div");
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
