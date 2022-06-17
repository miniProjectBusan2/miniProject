package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Exception.NotExistException;
import model.BookDTO;

import model.PersonDTO;

import model.RentalinfoDTO;
import service.Service;
import util.DBUtil;

public class Controllclass {

private static Controllclass instance = new Controllclass();
private Controllclass() {};
public static Controllclass getInstance() {
	return instance;
}

	Service service = Service.getInstance();
//	Service service = new Service();

	public void ShowAllBook() {
		System.out.println("========모든 책 검색==========");
		try {
			for(BookDTO book:service.getAllBooks()) {
				System.out.println(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void AllPerson() { // 도헌이     select * from person;
		System.out.println("========모든 사람 검색==========");

		try {
			for(PersonDTO person:service.getAllPerson()) {
				System.out.println(person);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void BorrowBooks() { // 영훈   select * from book where borrow_flag = 1;
		System.out.println("========빌려 갈 수 있는 책 검색==========");
			try {
					for(BookDTO book : service.borrowBooks()) {
						System.out.println(book);
					}
			} catch ( NotExistException e) {
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void SearchBooks() { // 용주  select * from book where book_name = '도헌이책';
	System.out.println("========책 이름으로 책 검색==========");
	System.out.println("책이름 입력 : ");
	String str=service.getStrings();
		try {
			for(BookDTO book:service.SearchBooks(str)) {
				System.out.println(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	

	public void BorrowPerson() { // 도헌이  select * from rental_info;
	System.out.println("========대여 현황 전체 출력==========");
	try {
		
		for(RentalinfoDTO rentalinfo : service.getrentalinfo())
			
			System.out.println(rentalinfo);
			
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	

	public void BorrowUseID() { 
		int bookId;
		int userId;
		// 용주 - borrow_flag가 1이면 그걸 insert( )하고, borrow_flag 0 으로 바꾼다.
		//-- book_number를 받는다 -> rental_info를 생성하는데(여기에 book_number와 id_number를 넣어준다)
		//insert into rental_info(borrow_day,return_day,id_number,book_number) values("2022-05-10","2022-05-20",2,3);
		//update book set borrow_flag =1 where book_number=2;
	System.out.println("========책 id와 유저 id로 책 빌리기 ==========");
	System.out.println("책 ID 입력 : ");
	bookId=service.getInt();
	System.out.println("유저 ID 입력 : ");
	userId=service.getInt();;
		try {
			if(service.BorrowUseID(bookId,userId)) {
				System.out.println("책 빌리기 성공");
			}else {
				System.out.println("책 빌리기 실패");
			}
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	public void ReturnBook() { // 영훈 - delete를 하고, book의 borrow_flag를 1로 바꿔준다.
//		insert into rental_info(borrow_day,return_day,id_number,book_number) values("2022-05-08","2022-06-08",3,3);
//		delete from rental_info where rental_info_id =3;
		System.out.println("rental_info_id 입력 : ");
		int rentalcode=service.getInt();
		try {
			
			service.ReturnBook(new RentalinfoDTO(rentalcode));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	System.out.println("========대여 현황 코드로 책 반납==========");
	
	// 블랙리스트 등록, ... 
	
	
	}
	
	public static void showMenue() {
		System.out.println("[ 메뉴 ]");
		System.out.println("1. 모든 책 검색");
		System.out.println("2. 모든 사람 검색");
		System.out.println("3. 빌려 갈 수 있는 책 검색	");	
		System.out.println("4. 책이름으로 책검색");
		System.out.println("5. 대여 현황 전체 출력");
		System.out.println("6. 책 id와 유저 id로 책 빌리기 ");
		System.out.println("7. 대여 현황 코드로 책 반납");
	}
	
	
}
