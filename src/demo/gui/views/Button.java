package demo.gui.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import demo.gui.style.RoundBorder;

public class Button extends JButton implements MouseListener {
	// 发行版本
	private static final long serialVersionUID = -7919938448593176712L;
	private RoundBorder roundBorder;
	
	public Button() {}
	
	public Button(String text) {
		super(text);
		
		// 设置按钮样式
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
		setFont(new Font("微软雅黑", 0, 16));
		
		roundBorder = new RoundBorder(new Color(220, 223, 230), new Color(255, 255, 255), new Color(96, 98, 102), text);
		setBorder(roundBorder);
		
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		// 鼠标上移效果
		roundBorder.setFontColor(new Color(79, 166, 255));
		roundBorder.setBorderColor(new Color(198, 226, 255));
		roundBorder.setBackgroundColor(new Color(236, 245, 255));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// 鼠标移出
		roundBorder.setFontColor(new Color(96, 98, 102));
		roundBorder.setBorderColor(new Color(220, 223, 230));
		roundBorder.setBackgroundColor(new Color(255, 255, 255));
	}
}
