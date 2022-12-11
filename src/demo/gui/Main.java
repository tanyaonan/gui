package demo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import demo.gui.R.R;
import demo.gui.util.Reader;
import demo.gui.util.ViewEvents;
import demo.gui.util.ViewEvents.Click;

public class Main {

	public static void main(String[] args) {
		new Reader("main.xml"); // 添加xml布局到本页面
		
		initEvents();
	}

	private static void initEvents() {
		// 通过id设置按钮事件
		ViewEvents.addClickListener("btn", new Click() {
			
			@Override
			public void main() {
				System.out.println("点击了id为btn的按钮");
			}
		});
		
		// 登录实现
		ViewEvents.addClickListener("submit", new Click() {
			
			@Override
			public void main() {
				String user = R.getInputById("user").getText();
				String pass = R.getInputById("pass").getText();
				
				System.out.println("user:" + user + "\npass:" + pass);
				System.out.println("------------");
			}
		});
		
		// 监听多选框
		R.getCheckBoxById("cbox").addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("agree:" + R.getCheckBoxById("cbox").isSelected());
			}
		});
		
		// 设置多选框选中
		R.getButtonById("setCbox").addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean boo = R.getCheckBoxById("cbox").isSelected();
				R.getCheckBoxById("cbox").setSelected(!boo);;
			}
		});
		
		// 设置多选框禁用/启用
		R.getButtonById("setCboxEnb").addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean boo = R.getCheckBoxById("cbox").isEnabled();
				R.getCheckBoxById("cbox").setEnabled(!boo);;
			}
		});
	}

}
