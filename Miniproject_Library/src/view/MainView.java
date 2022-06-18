package view;

import control.Controller;
import java.util.Scanner;


public class MainView {
// 빌리는 일자는 +10일 
// 날짜 형식은 2022-08-18
// borrow_flag는 0과 1인데 0이면 불가 1이면 가능
// rental_info의 id_number , book_number 외래키다
	public static void main(String[] args) {
		Controller controllclass = Controller.getInstance();

<<<<<<< HEAD
		Scanner scan = new Scanner(System.in);
		int num=0;
		while(true) {
			controllclass.showMenue();
			System.out.print("정수를 입력 하시오 : ");
			num=scan.nextInt();
			switch(num) {
			case 1: 
//				모든 책 검색
				controllclass.showAllBook();
				break;
			case 2: 
//				// 모든 사람 검색
				controllclass.allPerson();
				break;
			case 3: 
//				// 빌려 갈 수 있는 책 검색		
				controllclass.borrowBooks();
				break;
			case 4:
				// 책이름으로 책검색
				controllclass.searchBooks();
				break;
			case 5: 
				// 대여 현황 전체 출력
				controllclass.borrowPerson();
				break;
			case 6: 
				//책 id와 유저 id로 책 빌리기 
				controllclass.borrowUseID();
				break;
			case 7: 
				// 대여 현황 코드로 책 반납
				controllclass.returnBook();
				break;
			case 8:
				System.out.println("프로그램 종료");
				return;
			default : break;
			}
		}
=======
		// 책이름으로 책검색
//		controllclass.SearchBooks("도헌이책");
		
		// 대여 현황 전체 출력
//		controllclass.BorrowPerson();

		//책 id와 유저 id로 책 빌리기 
		controllclass.BorrowUseID(2,1);
			
		// 대여 현황 코드로 책 반납
//		controllclass.ReturnBook(8);
		//1. rental_info_id를 통해 book_number를 먼저 받아오는 함수
		//2. book_number를 통해 borrow_flag를 1로 update한다.
		//3. rental_info_id를 통해 데이터 삭제
		//rental_info_id를 통해 삭제하고, book_number를 받아온다
		//그 받아온 book_number를 통해 borrow_flag를 1로 update한다.

>>>>>>> branch 'hun' of https://github.com/miniProjectBusan2/miniProject.git
	}
	
	// 보완 사항
	// 날짜 하드코딩 자동 날짜 입력으로 바꾸기(반납 날짜 = 대여 날짜 + 10)
	// 6번 예외처리(ID별 다른 문구 출력)
	// DB 컬럼명, 메소드명 수정
	
	

}