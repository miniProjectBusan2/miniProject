package model;

public class BookDTO {
	private int book_number;
	private String genre;
	private String publication;//출판일
	private int borrow_flag;
	private String book_name;
	
	
	
	public BookDTO() {super();}
	public BookDTO(int book_number, String genre, String publication, int borrow_flag, String book_name) {
		super();
		this.book_number = book_number;
		this.genre = genre;
		this.publication = publication;
		this.borrow_flag = borrow_flag;
		this.book_name = book_name;
	}
	public int getBook_number() {
		return book_number;
	}
	public void setBook_number(int book_number) {
		this.book_number = book_number;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public int getBorrow_flag() {
		return borrow_flag;
	}
	public void setBorrow_flag(int borrow_flag) {
		this.borrow_flag = borrow_flag;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	@Override
	public String toString() {
		return "BookDTO [book_number=" + book_number + ", genre=" + genre + ", publication=" + publication
				+ ", borrow_flag=" + borrow_flag + ", book_name=" + book_name + "]";
	}
	
}
