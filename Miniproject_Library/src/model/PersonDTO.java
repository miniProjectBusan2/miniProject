package model;

public class PersonDTO {
	private int id_number;
	private String phone_num;
	private String user_name;
	
	
	@Override
	public String toString() {
		return "PersonDTO [id_number=" + id_number + ", phone_num=" + phone_num + ", user_name=" + user_name + "]";
	}
	public PersonDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PersonDTO(int id_number, String phone_num, String user_name) {
		super();
		this.id_number = id_number;
		this.phone_num = phone_num;
		this.user_name = user_name;
	}
	public int getId_number() {
		return id_number;
	}
	public void setId_number(int id_number) {
		this.id_number = id_number;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
}
