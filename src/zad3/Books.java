package zad3;

import javax.swing.ImageIcon;

public class Books
{
	private String author;
	private String title;
	private double price;
	private ImageIcon icon;

	public Books()
	{
	}
	public Books(String author, String title, double price, ImageIcon i)
	{
		this.setAuthor(author);
		this.setTitle(title);
		this.setPrice(price);
		this.setIcon(i);
	}
	public String getAuthor()
	{
		return author;
	}
	public void setAuthor(String author)
	{
		this.author = author;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double d)
	{
		this.price = d;
	}
	public ImageIcon getIcon()
	{
		return icon;
	}
	public void setIcon(ImageIcon icon)
	{
		this.icon = icon;
	}
}
