package model;

public class RentalinfoDTO {
	private int rental_info_id;
	private String borrow_day;
	private String return_day;
	private int id_number;
	private int book_number;
	
	
	
	public int getRental_info_id() {
		return rental_info_id;
	}
	public void setRental_info_id(int rental_info_id) {
		this.rental_info_id = rental_info_id;
	}
	public String getBorrow_day() {
		return borrow_day;
	}
	public void setBorrow_day(String borrow_day) {
		this.borrow_day = borrow_day;
	}
	public String getReturn_day() {
		return return_day;
	}
	public void setReturn_day(String return_day) {
		this.return_day = return_day;
	}
	public int getId_number() {
		return id_number;
	}
	public void setId_number(int id_number) {
		this.id_number = id_number;
	}
	public int getBook_number() {
		return book_number;
	}
	public void setBook_number(int book_number) {
		this.book_number = book_number;
	}
	public RentalinfoDTO(int rental_info_id, String borrow_day, String return_day, int id_number, int book_number) {
		super();
		this.rental_info_id = rental_info_id;
		this.borrow_day = borrow_day;
		this.return_day = return_day;
		this.id_number = id_number;
		this.book_number = book_number;
	}
	@Override
	public String toString() {
		return "RentalinfoDTO [rental_info_id=" + rental_info_id + ", borrow_day=" + borrow_day + ", return_day="
				+ return_day + ", id_number=" + id_number + ", book_number=" + book_number + "]";
	}
	public RentalinfoDTO(int rental_info_id) {
		super();
		this.rental_info_id = rental_info_id;
	}
	
	
}
