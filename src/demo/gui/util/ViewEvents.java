package demo.gui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import demo.gui.R.R;

public class ViewEvents {
	public static void addClickListener(String id, Click event) {
		R.getButtonById(id).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				event.main();
			}
		});
	}
	
	public interface Click{
		void main();
	}
}
