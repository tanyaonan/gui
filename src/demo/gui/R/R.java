package demo.gui.R;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 * id 管理器
 * @author tanyaonan
 *
 */
public class R {
	private static Map<String, Object> ids = new HashMap<String, Object>();
	
	public static Object getId(String id) {
		return ids.get(id);
	}
	
	public static void setId(String id, Object obj) {
		ids.put(id, obj);
	}
	
	public static JButton getButtonById(String id) {
		return (JButton) ids.get(id);
	}
	
	public static JTextField getInputById(String id) {
		return (JTextField) ids.get(id);
	}
	
	public static JCheckBox getCheckBoxById(String id) {
		return (JCheckBox) ids.get(id);
	}
	
	public static class data {
		private static Map<String, Object> dataId = new HashMap<String, Object>();
		
		public static Object getId(String id) {
			return dataId.get(id);
		}
		
		public static void setId(String id, Object obj) {
			dataId.put(id, obj);
		}
	}
}
