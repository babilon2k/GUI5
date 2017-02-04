package zad3;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class BooksGui
{

	JFrame f = new JFrame();
	JTable table = new JTable();
	JButton bRemove, bAdd, bAddImage;
	JLabel lAuthor, lTitle, lPrice;
	JMenu menuFile;
	JMenuItem mOpen, mSave, mExit;
	JMenuBar menuBar;
	List<Books> bookList = new ArrayList<Books>();
	String readLine = null;
	BooksTableModel tableModel = new BooksTableModel();
	String author, title;
	Double price;

	public BooksGui()
	{

		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				createGui();
			}
		});
	}
	private void createGui()
	{

		// Add Button
		bAdd = new JButton("Dodaj");
		bAdd.setToolTipText(
				"Aby dodać pozycję uzupełnij pola Autor, Tytuł i Cena wybierz wiersz w tabeli i kliknij przycisk Dodaj.");
		bAdd.addActionListener(e -> addRow());

		// Remove Button
		bRemove = new JButton("Usuń wybrany wiersz");
		bRemove.addActionListener(e -> {
			if (table.getSelectedRow() >= 0)
				tableModel.deleteRow(table.getSelectedRow());
		});

		// Add Image icon
		bAddImage = new JButton("Dodaj okładkę");
		bAddImage.addActionListener(e -> {

			table.setRowHeight(100);
			table.getColumnModel().getColumn(3).setCellRenderer(new Renderer());
		});

		// Layout for table and buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPanel.add(Box.createHorizontalGlue());

		buttonPanel.add(bAdd, Box.LEFT_ALIGNMENT);
		buttonPanel.add(bRemove, Box.RIGHT_ALIGNMENT);
		buttonPanel.add(bAddImage, Box.RIGHT_ALIGNMENT);

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.PAGE_AXIS));
		tablePanel.add(Box.createRigidArea(new Dimension(0, 5)));
		tablePanel.add(new JScrollPane(table));

		// Menu
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		mOpen = new JMenuItem("Open");
		mOpen.addActionListener(new ActionListener()
		{
			private BufferedReader bufReader;
			@Override
			public void actionPerformed(ActionEvent e)
			{

				JFileChooser fc = new JFileChooser(
						System.getProperty("user.dir"));

				int returnVal = fc.showOpenDialog((Component) e.getSource());
				File file = null;
				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					file = fc.getSelectedFile();

					tableModel.deleteAllRows();

					try
					{
						{

							FileReader reader = new FileReader(file);
							bufReader = new BufferedReader(reader);

							while ((readLine = bufReader.readLine()) != null)
							{
								String[] data = readLine.split(",");

								Books book = new Books();
								book.setAuthor(data[0]);
								book.setTitle(data[1]);
								book.setPrice(Double.parseDouble(data[2]));

								bookList.add(book);
							}

							tableModel.setList(bookList);
							table.setModel(tableModel);
						}
					} catch (FileNotFoundException e1)
					{
						e1.printStackTrace();
					} catch (IOException e1)
					{
						e1.printStackTrace();
					}
					tableModel.deleteRow(0);
				}
			}
		});

		mSave = new JMenuItem("Save");

		mExit = new JMenuItem("Exit");
		mExit.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
		mExit.addActionListener(e -> f.dispose());

		menuBar.add(menuFile);
		menuFile.add(mOpen);
		menuFile.add(mSave);
		menuFile.addSeparator();
		menuFile.add(mExit);

		// MainFrame
		f.setJMenuBar(menuBar);
		f.add(tablePanel, BorderLayout.CENTER);
		f.add(buttonPanel, BorderLayout.SOUTH);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Books");
		f.pack();
		f.setVisible(true);

	}

	private void addRow()
	{
		Books book = new Books();
		book.setAuthor(author = JOptionPane.showInputDialog("Podaj autora"));
		book.setTitle(title = JOptionPane.showInputDialog("Podaj tytuł"));
		book.setPrice(price = Double
				.parseDouble(JOptionPane.showInputDialog("Podaj cenę")));
		bookList.add(book);

		tableModel.setList(bookList);
		table.setModel(tableModel);
	}
}
