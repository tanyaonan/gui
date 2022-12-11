package demo.gui.views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import demo.gui.R.R;

/**
 * 控件管理器
 * 
 * @author tanyaonan
 *
 */
public class ViewsManager {

	/**
	 * 文本控件
	 * 
	 * @param text
	 *            文本内容
	 * @param arributes
	 *            节点属性
	 * @param panel
	 *            节点容器
	 */
	public void setTextView(String text, NamedNodeMap arributes, JPanel panel) {
		JLabel label = new JLabel(text);
		panel.add(label);
		label.setForeground(new Color(45, 45, 45));
	}

	/**
	 * 标题文本控件
	 * 
	 * @param text
	 *            文本内容
	 * @param arributes
	 *            节点属性
	 * @param panel
	 *            节点容器
	 */
	public void setTitleTextView(String text, NamedNodeMap arributes, JPanel panel, int size) {
		JLabel label = new JLabel(text);
		panel.add(label);
		label.setForeground(new Color(45, 45, 45)); // 设置前景色 字体颜色
		// label.setOpaque(true); // 背景不透明：此情况下的背景色才会生效
		// label.setBackground(new Color(255, 255, 255)); // 设置背景色

		// label.setPreferredSize(new Dimension(50, 50)); // 设置宽高 超出显示省略号
		// label.setHorizontalAlignment(SwingConstants.CENTER); // 水平对其方式
		// label.setVerticalAlignment(SwingConstants.CENTER); // 垂直对其方式

		switch (size) {
		case 1:
			label.setFont(new Font("微软雅黑", Font.BOLD, 32));
			break;
		case 2:
			label.setFont(new Font("微软雅黑", Font.BOLD, 24));
			break;
		case 3:
			label.setFont(new Font("微软雅黑", Font.BOLD, 18));
			break;
		case 4:
			label.setFont(new Font("微软雅黑", Font.BOLD, 16));
			break;
		case 5:
			label.setFont(new Font("微软雅黑", Font.BOLD, 13));
			break;
		case 6:
			label.setFont(new Font("微软雅黑", Font.BOLD, 12));
			break;
		default:
			break;
		}
	}

	/**
	 * 按钮控件
	 * 
	 * @param text
	 * @param arributes
	 * @param panel
	 */
	public void setButtonView(String text, NamedNodeMap arributes, JPanel panel) {
		Button button = new Button(text);
		panel.add(button);
		if (arributes.getNamedItem("id") != null) {
			String id = arributes.getNamedItem("id").getTextContent();
			R.setViewById(id, button);
		}
	}

	/**
	 * 输入框控件
	 * 
	 * @param text
	 * @param arributes
	 * @param panel
	 */
	public void setInputView(NamedNodeMap arributes, JPanel panel) {

		// 输入框集合
		if (arributes.getNamedItem("type") != null) {
			String type = arributes.getNamedItem("type").getTextContent();
			switch (type) {
			case "checkbox":
				setCheckBoxView(arributes, panel);
				break;
			case "text":
			default:
				setNormalInputView(arributes, panel);
				break;
			}
		} else {
			setNormalInputView(arributes, panel);
		}
	}

	/**
	 * 默认输入框控件
	 * 
	 * @param text
	 * @param arributes
	 * @param panel
	 */
	public void setNormalInputView(NamedNodeMap arributes, JPanel panel) {
		InputView input = new InputView(15);
		panel.add(input);

		if (arributes.getNamedItem("value") != null) {
			String value = arributes.getNamedItem("value").getTextContent();
			input.setText(value);
		} else {
			input.setText("");
		}
		
		if (arributes.getNamedItem("placeholder") != null) {
			String placeholder = arributes.getNamedItem("placeholder").getTextContent();
			input.setPlaceholder(placeholder);
		}

		if (arributes.getNamedItem("id") != null) {
			String id = arributes.getNamedItem("id").getTextContent();
			R.setViewById(id, input);
		}
	}

	/**
	 * 多选框控件
	 * 
	 * @param text
	 * @param arributes
	 * @param panel
	 */
	public void setCheckBoxView(NamedNodeMap arributes, JPanel panel) {
		JCheckBox checkbox = new JCheckBox();
		panel.add(checkbox);

		if (arributes.getNamedItem("name") != null) {
			String name = arributes.getNamedItem("name").getTextContent();
			checkbox.setText(name);
		}

		if (arributes.getNamedItem("id") != null) {
			String id = arributes.getNamedItem("id").getTextContent();
			R.setViewById(id, checkbox);
		}
	}

	/**
	 * 下拉框控件
	 * 
	 * @param text
	 * @param arributes
	 * @param panel
	 */
	public void setSelectView(NamedNodeMap arributes, JPanel panel, NodeList childNodes) {
		JComboBox<String> select = new JComboBox<>();
		panel.add(select);
		if (childNodes.getLength() > 0) {
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node node = childNodes.item(i);
				if (node.getNodeName().equals("option")) {
					select.addItem(node.getTextContent());
				}
			}
		}

		/**
		 * addItem(String) 添加option addItemCount() 获取子项数量 getItemAt(Integer) 根据下标获取子项
		 * 
		 * ---下标索引--- ------------ getSelectedIndex() 获取选中项的下标 setSelectedIndex(Integer)
		 * 设置选中项 remove(Integer) 移除指定下标项
		 * 
		 * ---内容索引--- ------------ getSelectedItem() 获取选中项内容 setSelectedItem(String)
		 * 根据内容设置选中项 remove(String) 移除指定内容的项
		 */
	}
}
