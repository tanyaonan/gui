package demo.gui.views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class TextView extends JLabel {
	// 版本号
	private static final long serialVersionUID = -3426532977251044673L;
	
	public TextView(String text) {
		super(text);
		
		setFont(new Font("微软雅黑", 0, 16));
		setForeground(new Color(45, 45, 45));
	}
}
