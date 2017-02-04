package zad3;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

class BooksTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	private List<Books> bookslist = new ArrayList<Books>();
	private String[] columnNames = {"Autor", "Tytuł", "Cena", "Okładka"};

	public void setList(List<Books> list)
	{
		this.bookslist = list;
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column)
	{
		return columnNames[column];
	}

	public int getRowCount()
	{
		return bookslist.size();
	}

	public int getColumnCount()
	{
		return columnNames.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex)
	{
		switch (columnIndex)
		{
			case 0 :
				return bookslist.get(rowIndex).getAuthor();
			case 1 :
				return bookslist.get(rowIndex).getTitle();
			case 2 :
				return bookslist.get(rowIndex).getPrice();
			case 3 :
				return bookslist.get(rowIndex).getIcon();
			default :
				return null;
		}
	}

	public Class<?> getColumnClass(int column)
	{
		switch (column)
		{
			case 0 :
			case 1 :
				return String.class;
			case 2 :
				return Double.class;
			case 3 :
				return ImageIcon.class;
			default :
				return Object.class;
		}
	}
	public boolean isCellEditable(int row, int col)
	{
		if (col != 2)
		{
			return false;
		} else
		{
			return true;
		}

	}

	@Override
	public void setValueAt(Object value, int row, int col)
	{
		Books booksRow = bookslist.get(row);
		if (0 == col)
		{
			booksRow.setAuthor((String) value);
		} else if (1 == col)
		{
			booksRow.setTitle((String) value);
		} else if (2 == col)
		{
			booksRow.setPrice((Double) value);
		} else if (3 == col)
		{
			booksRow.setIcon((ImageIcon) value);
		}

	}

	public void deleteRow(int index)
	{
		bookslist.remove(index);
		fireTableRowsDeleted(index, index);
	}

	public void addRow(Books book)
	{

		bookslist.add(book);
		fireTableRowsInserted(bookslist.size() - 1, bookslist.size() - 1);

	}

	public void deleteAllRows()
	{
		bookslist.removeAll(bookslist);
		fireTableRowsDeleted(0, getRowCount());
	}
}
