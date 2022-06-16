package view;

import control.Controllclass;


public class MainView {
// 빌리는 일자는 +10일 
// 날짜 형식은 2022-08-18
// borrow_flag는 0과 1인데 0이면 불가 1이면 가능
// rental_info의 id_number , book_number 외래키다
	public static void main(String[] args) {
		Controllclass controllclass = new Controllclass();
		// 모든 책 검색
		controllclass.ShowAllBook();
		
		// 모든 사람 검색
		controllclass.AllPerson();
		
		// 빌려 갈 수 있는 책 검색		
		controllclass.BorrowBooks();
		
		// 책이름으로 책검색
		controllclass.SearchBooks();
		
		// 대여 현황 전체 출력
		controllclass.BorrowPerson();

		//책 id와 유저 id로 책 빌리기 
		controllclass.BorrowUseID();
		
		// 대여 현황 코드로 책 반납
		controllclass.ReturnBook();
	}
}