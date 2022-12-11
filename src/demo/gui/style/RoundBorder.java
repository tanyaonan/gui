package demo.gui.style;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

/**
 * 圆角边框
 * @author tanyaonan
 *
 */
public class RoundBorder  implements Border {
	private Color borderColor;
	private Color fontColor;
	private Color bgColor;
	private String text;

	// 有参数的构造方法
	public RoundBorder(Color borderColor, Color bgColor, Color fontColor, String text) {
		this.borderColor = borderColor;
		this.fontColor = fontColor;
		this.bgColor = bgColor;
		this.text = text;
	}

	public RoundBorder() {// 无参构造方法
		this.borderColor = new Color(220, 223, 230);
	}

	public Insets getBorderInsets(Component c) {
		return new Insets(10, 15, 10, 15);
	}

	public boolean isBorderOpaque() {
		return false;
	}

	// 实现Border（父类）方法
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {
		if (borderColor != null) {
			g.setColor(borderColor);
			g.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 10, 10);
		}
		if (bgColor != null) {
			g.setColor(bgColor);
			g.fillRoundRect(1, 1, c.getWidth() - 2, c.getHeight() - 2, 10, 10);
		}
		if (fontColor != null) {
			g.setColor(fontColor);
			g.drawString(text, 15, 26);
		}
		
	}
	
	public void setFontColor(Color color) {
		this.fontColor = color;
	}
	
	public void setBackgroundColor(Color color) {
		this.bgColor = color;
	}
	
	public void setBorderColor(Color color) {
		this.borderColor = color;
	}
}
