package demo.gui.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import demo.gui.style.RoundBorder;

public class InputView extends JTextField implements FocusListener {
	// 版本号
	private static final long serialVersionUID = -60900476048166967L;
	
	private RoundBorder roundBorder;
	private String placeholder;
	
	public InputView(int size) {
		super(size);
	}
	
	public InputView() {}

	public void setText(String text) {
		// TODO 自动生成的方法存根
		super.setText(text);
		
		setFont(new Font("微软雅黑", 0, 16));
		setMargin(new Insets(10, 15, 10, 15));
		
		roundBorder = new RoundBorder();
		setBorder(roundBorder);
	}
	
	public String getText() {
		
		// 防止将提示语作为文本输出
		if (super.getText().equals(this.placeholder)) {
			return "";
		}
		return super.getText();
	}
	
	public String getSuperText() {
		// 用于防止过滤提示文本导致误判
		return super.getText();
	}
	
	// 设置提示文字
	public void setPlaceholder(String tip) {
		
		if (!tip.isEmpty()) {
			// 加个tab防止用户输入提示文字导致清空
			// 普通输入框控件不允许输入tab
			this.placeholder = tip + "	";
			
		}
		
		if(getText().isEmpty() && !tip.isEmpty()) {
			setForeground(Color.gray);
			setText(this.placeholder);
		}
		
		addFocusListener(this);
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		
		// 获得焦点
		if (getSuperText().equals(placeholder)){
            setText("");     //将提示文字清空
            setForeground(Color.black);  //设置用户输入的字体颜色为黑色
        }
	}

	@Override
	public void focusLost(FocusEvent e) {
	
		// 失去焦点
		if (getSuperText().isEmpty()){
            setForeground(Color.gray); //将提示文字设置为灰色
            setText(placeholder);     //显示提示文字
        }
	}

}
