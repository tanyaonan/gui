package demo.gui.util;

import java.awt.Component;
import java.awt.Container;

public class LayoutManager extends LayoutAdapter {

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void layoutContainer(Container parent) {
		// 获取布局宽高
		int width = parent.getWidth();
//		int height = parent.getHeight();
		
		// 获取全部子控件
		Component[] child = parent.getComponents();
		
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < child.length; i++) {
			Component component = child[i];
			component.setBounds(x, y, 100, 100);
			
			x += 100;
			if (x > width) {
				x = 0;
				y += 100;
			}
		}
	}
}
