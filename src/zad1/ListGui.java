package zad1;

import java.awt.GridLayout;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class ListGui extends JFrame
{
	private static final long serialVersionUID = 1L;

	JList<Object> list = new JList<Object>();
	private JFrame f = new JFrame();
	private JScrollPane sp = new JScrollPane(list);

	private AbstractListModel<Object> degree;

	public ListGui()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				createListGui();
			}
		});
	}

	private void createListGui()
	{
		degree = new AbstractListModel<Object>()
		{
			private static final long serialVersionUID = -1529596623412086081L;

			@Override
			public Object getElementAt(int i)
			{
				return (-70 + i++) + " stopni C = "
						+ CelsiusToFahrenheit((-71 + i++)) + " stopni F";
			}

			private double CelsiusToFahrenheit(int i)
			{
				double tempC = i;
				return Math.round(32.0 + (9.0 / 5) * tempC);
			}

			@Override
			public int getSize()
			{
				return 131;
			}

		};

		f.setTitle("Celsius to Fahrenheit");
		f.setLayout(new GridLayout());
		f.setSize(210, 400);
		list.setModel(degree);
		f.add(sp);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
