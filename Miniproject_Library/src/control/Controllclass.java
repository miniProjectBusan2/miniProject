package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.BookDTO;
import service.Service;
import util.DBUtil;

public class Controllclass {
private static Controllclass instance = new Controllclass();
private Controllclass() {};
public static Controllclass getInstance() {
	return instance;
}
	
	Service service = Service.getInstance();

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
		System.out.println("========모든 사람 검색===========");
	}
	
	public void BorrowBooks() { // 영훈   select * from book where borrow_flag = 1;
		System.out.println("========빌려 갈 수 있는 책 검색==========");
	}
	
	public void SearchBooks() { // 용주  select * from book where book_name = '도헌이책';
	System.out.println("========책 이름으로 책 검색==========");
		try {
			for(BookDTO book:service.SearchBooks("도헌이책")) {
				System.out.println(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void BorrowPerson() { // 도헌이  select * from rental_info;
	System.out.println("========대여 현황 전체 출력==========");
	}
	
	public void BorrowUseID() { 
		// 용주 - borrow_flag가 1이면 그걸 insert( )하고, borrow_flag 0 으로 바꾼다.
		//-- book_number를 받는다 -> rental_info를 생성하는데(여기에 book_number와 id_number를 넣어준다)
		//insert into rental_info(borrow_day,return_day,id_number,book_number) values("2022-05-10","2022-05-20",2,3);
		//update book set borrow_flag =1 where book_number=2;
	System.out.println("========책 id와 유저 id로 책 빌리기 ==========");
		try {
			if(service.BorrowUseID(1,1)) {
				System.out.println("책 빌리기 성공");
			}else {
				System.out.println("책 빌리기 실패");
			}
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	public void ReturnBook() { // 영훈 - delete를 하고, book의 borrow_flag를 1로 바꿔준다.
//		insert into rental_info(borrow_day,return_day,id_number,book_number) values("2022-05-08","2022-06-08",3,3);
//		delete from rental_info where rental_info_id =3;
	System.out.println("========대여 현황 코드로 책 반납==========");
	}
}
