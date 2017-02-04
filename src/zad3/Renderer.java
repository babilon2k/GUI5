package zad3;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Renderer extends DefaultTableCellRenderer
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lbl = new JLabel();
	ImageIcon[] icon = {new ImageIcon("o.jpg"), new ImageIcon("g.png")};

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column)
	{
		
		
		lbl.setText((String) value);
		
		for(int i=0; i < icon.length; i++){
		lbl.setIcon(icon[i]);
		}
		
		return lbl;

	}

}
