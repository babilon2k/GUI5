package zad2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class ListGui2 extends JFrame implements ActionListener, MouseListener
{
	private static final long serialVersionUID = 1L;
	JList<String> list;
	private JFrame f = new JFrame();
	private JPanel p = new JPanel();
	private JScrollPane sp;
	private JTextField tf = new JTextField();
	private DefaultListModel<String> listModel;

	public ListGui2()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				createListGui2();
			}
		});

	}

	private void createListGui2()
	{
		listModel = new DefaultListModel<String>();
		list = new JList<String>();
		list.setModel(listModel);
		sp = new JScrollPane(list);
		tf.setActionCommand("enter");
		tf.addActionListener(e -> {
			listModel.addElement(tf.getText());
			String txt = tf.getText();
			txt = "";
			tf.setText(txt);
		});
		list.addMouseListener(this);
		list.setToolTipText(
				"Press alt + left mouse button to remove item from list");
		p.setLayout(new GridLayout());
		p.add(tf);
		p.add(sp);
		f.setTitle("Dodaje elemnty na liste");
		f.add(p);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (e.isAltDown())
		{
			if (list.getSelectedIndex() >= 0)
				listModel.remove(list.getSelectedIndex());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{

	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{

	}
}
