package demo.gui.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Layout extends JFrame {
	// 发行版本
	private static final long serialVersionUID = 4690429895448460337L;

	public Layout() {
		this.setTitle("");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口
	}
	
	@Override
	public void setSize(int width, int height) {
		// 设置窗口位置： 居中
		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();//调用Toolkit接口
	    Dimension screenSize = defaultToolkit.getScreenSize();//获取screen的大小
	    //先定义宽高，用当前屏幕大小减去自定义的宽高，再除以2就是居中的位置
	    this.setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
	    super.setSize(width, height);
	}
}
