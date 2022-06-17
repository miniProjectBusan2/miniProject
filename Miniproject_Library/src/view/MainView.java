package view;

import control.Controllclass;
import java.util.Scanner;


public class MainView {
// 빌리는 일자는 +10일 
// 날짜 형식은 2022-08-18
// borrow_flag는 0과 1인데 0이면 불가 1이면 가능
// rental_info의 id_number , book_number 외래키다
	public static void main(String[] args) {
		Controllclass controllclass = Controllclass.getInstance();

		Scanner scan = new Scanner(System.in);
		int num=0;
		while(true) {
			controllclass.showMenue();
			System.out.print("정수를 입력 하시오 : ");
			num=scan.nextInt();
			switch(num) {
			case 1: 
//				모든 책 검색
				controllclass.ShowAllBook();
				break;
			case 2: 
//				// 모든 사람 검색
				controllclass.AllPerson();
				break;
			case 3: 
//				// 빌려 갈 수 있는 책 검색		
				controllclass.BorrowBooks();
				break;
			case 4:
				// 책이름으로 책검색
				controllclass.SearchBooks();
				break;
			case 5: 
				// 대여 현황 전체 출력
				controllclass.BorrowPerson();
				break;
			case 6: 
				//책 id와 유저 id로 책 빌리기 
				controllclass.BorrowUseID();
				break;
			case 7: 
				// 대여 현황 코드로 책 반납
				controllclass.ReturnBook();
				break;
			default : break;
			}
		}
	}
	

}