package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Exception.NotExistException;
import dao.AllDAO;
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
	
	
	public static ArrayList<BookDTO> getAllBooks() throws SQLException  {
		return AllDAO.getAllBooks();
	}
	

	public ArrayList<BookDTO> borrowBooks() throws NotExistException, SQLException {
		ArrayList<BookDTO> bookDTO = AllDAO.borrowBooks();
		if(bookDTO.size() != 0) {
			return bookDTO;			
		}else {
			throw new NotExistException("빌릴 수 있는 책이 없습니다.");
		}
	}
	
	
	
	
	public int getborrow(RentalinfoDTO dto) throws SQLException {
		return AllDAO.getborrow(dto);
	}
	public static ArrayList<PersonDTO> getAllPerson() throws SQLException{
		return AllDAO.getAllPerson();
	}
	

	public static ArrayList<BookDTO> searchBooks(String str)throws SQLException, NotExistException {
		ArrayList<BookDTO> bookDTO = AllDAO.searchBooks(str);
		if(bookDTO.size() != 0) {
			return bookDTO;			
		}else {
			throw new NotExistException("검색하신 책이 없습니다.");
		}		
	}
///////////////////////////////////////////
	public static ArrayList<RentalinfoDTO> getrentalinfo() throws SQLException, NotExistException{
		ArrayList<RentalinfoDTO> bookDTO = AllDAO.getrentalinfo();
		if(bookDTO.size() != 0) {
			return bookDTO;			
		}else {
			throw new NotExistException("대여 현황이 없습니다.");
		}
		
	}
	
        
        
        
	public static void returnBook(RentalinfoDTO dto) throws SQLException, NotExistException {
		int result = (AllDAO.getborrow(dto));
	
			if(AllDAO.returnBook(dto)) {
				AllDAO.updateBook(result);
				System.out.println("반납이 완료되었습니다.");
			}else {
				throw new NotExistException("입력하신 코드가 맞지 않습니다.");
			}
		
		
	}
	

	// 책 id와 유저 id로 책 빌리기  용주
	public static boolean borrowUseID(int userId,int bookId) throws SQLException, NotExistException{
		//-- book_number를 받는다 -> rental_info를 생성하는데(여기에 book_number와 id_number를 넣어준다)
		//insert into rental_info(borrow_day,return_day,id_number,book_number) values("2022-05-10","2022-05-20",2,3);
		if(AllDAO.flagReturn(bookId) != null) {
			if(AllDAO.personReturn(userId) != null) {
				if(AllDAO.flagReturn(bookId).getBorrow_flag() > 0) {
					AllDAO.borrowBooks(userId, bookId);
					AllDAO.changeFlag(bookId);
					return true;
				} 
				else {
					throw new NotExistException("도서 수량이 없습니다.");
				}
			} else {
				throw new NotExistException("유저 정보가 맞지 않습니다.");
			}
		}else{
			throw new NotExistException("도서 정보가 맞지 않습니다.");
		}
	}
	
	// insert
	
	
	

	//////////////////////////////////////////////////////////////////

	public static boolean updateBook(int rentalcode) throws SQLException {
		return AllDAO.updateBook(rentalcode);
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