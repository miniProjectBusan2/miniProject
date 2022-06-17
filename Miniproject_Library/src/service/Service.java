package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dao.DaoClass;
import model.BookDTO;

import model.PersonDTO;
import model.RentalinfoDTO;
import util.DBUtil;


public class Service {
	public static Service instance=new Service();
	private Service() {}
	public static Service getInstance() {
		return instance;
	}
	
	
	public ArrayList<BookDTO> getAllBooks() throws SQLException  {
		return DaoClass.getAllBooks();
	}
	

	public ArrayList<BookDTO> borrowBooks() throws SQLException  {
		return DaoClass.borrowBooks();
	}
	
	public int getborrow(RentalinfoDTO dto) throws SQLException {
		return DaoClass.getborrow(dto);
	}
	public static ArrayList<PersonDTO> getAllPerson() throws SQLException{
		return DaoClass.getAllPerson();
	}
	

	public static ArrayList<BookDTO> SearchBooks(String str)throws SQLException {
		return DaoClass.SearchBooks(str);
	}
///////////////////////////////////////////
	public static ArrayList<RentalinfoDTO> getrentalinfo() throws SQLException{
		return DaoClass.getrentalinfo();
	}
	
        
        
        
	public static void ReturnBook(RentalinfoDTO dto) throws SQLException {
		int result = (DaoClass.getborrow(dto));
		if(DaoClass.ReturnBook(dto)) {
			if(DaoClass.updateBook(result)) {
				System.out.println("반납 성공");
				return;
			}
		}
		System.out.println("반납 실패");
	}
	

	// 책 id와 유저 id로 책 빌리기  용주
	public static boolean BorrowUseID(int userId,int bookId) throws SQLException{
		//-- book_number를 받는다 -> rental_info를 생성하는데(여기에 book_number와 id_number를 넣어준다)
		//insert into rental_info(borrow_day,return_day,id_number,book_number) values("2022-05-10","2022-05-20",2,3);
		if(DaoClass.flagReturn(bookId)) {
			DaoClass.BorrowBooks(userId,bookId);
			DaoClass.ChangFlag(bookId);
			return true;
		}else {
			return false;
		}
	}

	//////////////////////////////////////////////////////////////////

	public static boolean updateBook(int rentalcode) throws SQLException {
		return DaoClass.updateBook(rentalcode);
	}
	//////////////////////////////////////////////////////////////////
	public static String getStrings() {
		Scanner scan=new Scanner(System.in);
		String str=scan.next();
		return str;
	}
	///////////////////////////////////////////////////////////
	public static int getInt() {
		Scanner scan=new Scanner(System.in);
		int num=scan.nextInt();
		return num;		
	}
}
