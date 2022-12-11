package demo.gui.R;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;

import demo.gui.views.Button;
import demo.gui.views.InputView;

/**
 * id 管理器
 * @author tanyaonan
 *
 */
public class R {
	private static Map<String, Object> ids = new HashMap<String, Object>();
	
	public static Object getViewById(String id) {
		return ids.get(id);
	}
	
	public static void setViewById(String id, Object obj) {
		ids.put(id, obj);
	}
	
	public static Button getButtonById(String id) {
		if(ids.get(id) != null) {
			return (Button) ids.get(id);
		}
		System.err.println("[id not found] no button name '" + id + "'.");
		return new Button();
	}
	
	public static InputView getInputById(String id) {
		if(ids.get(id) != null) {
			return (InputView) ids.get(id);
		}
		System.err.println("[id not found] no inputView name '" + id + "'.");
		return new InputView();
	}
	
	public static JCheckBox getCheckBoxById(String id) {
		if(ids.get(id) != null) {
			return (JCheckBox) ids.get(id);
		}
		System.err.println("[id not found] no checkBox name '" + id + "'.");
		return new JCheckBox();
	}
}
