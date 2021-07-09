package db;

public class Zosyo {
	private int id;
	private String bookname;
	private String authorname;
	private int year;
	private String storename;
	private String point;
	private String text;

	public Zosyo(int id, String bookname, String authorname, int year, String storename, String point, String text) {
		this.id = id;
		this.bookname = bookname;
		this.authorname = authorname;
		this.year = year;
		this.storename = storename;
		this.point = point;
		this.text = text;
	}

	public int getId() {return id;}
	public String getBookname() {return bookname;}
	public String getAuthorname() {return authorname;}
	public int getYear() {return year;}
	public String getStorename() {return storename;}
	public String getPoint() {return point;}
	public String getText() {return text;}
}