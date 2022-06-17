package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PersonDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from person");
			rset = pstmt.executeQuery();

			list = new ArrayList<PersonDTO>();
			while (rset.next()) {
				list.add(new PersonDTO(rset.getInt(1), rset.getString(2), rset.getString(3)));
			}
		} finally {
			DBUtil.close(rset, pstmt, con);
		}
		return list;
	}
	

	public static ArrayList<BookDTO> SearchBooks(String str)throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BookDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from book where book_name = ?");
			pstmt.setString(1, str);
			rset = pstmt.executeQuery();

			list = new ArrayList<BookDTO>();
			while (rset.next()) {
				list.add(new BookDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4),
						rset.getString(5)));
        }
		} finally {
			DBUtil.close(rset, pstmt, con);
		}
  	return list;
}
///////////////////////////////////////////
	public static ArrayList<RentalinfoDTO> getrentalinfo() throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<RentalinfoDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from rental_info");
			rset = pstmt.executeQuery();

			list = new ArrayList<RentalinfoDTO>();
			while (rset.next()) {
				list.add(new RentalinfoDTO(rset.getInt(1),rset.getString(2), rset.getString(3),rset.getInt(4),rset.getInt(5)));
			}
		} finally {
			DBUtil.close(rset, pstmt, con);
		}
		return list;
	}
	
        
        
        
	public static void ReturnBook(RentalinfoDTO dto) throws SQLException {
		int result = (DaoClass.getborrow(dto));
		if(DaoClass.ReturnBook(dto)) {
			DaoClass.updateBook(result);
		}
	}
	

	// 책 id와 유저 id로 책 빌리기 
	public static boolean BorrowUseID(int userId,int bookId) throws SQLException{
		//-- book_number를 받는다 -> rental_info를 생성하는데(여기에 book_number와 id_number를 넣어준다)
		//insert into rental_info(borrow_day,return_day,id_number,book_number) values("2022-05-10","2022-05-20",2,3);
		if(flagReturn(bookId)) {
			BorrowBooks(userId,bookId);
			ChangFlag(bookId);
			return true;
		}else {
			return false;
		}
	}
	///////////////////////////// 플래그 확인 함수 //////////////////////////
	public static boolean flagReturn(int bookId)throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BookDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from book where book_number=? and borrow_flag=1");
			pstmt.setString(1, String.valueOf(bookId));
			rset = pstmt.executeQuery();

			list = new ArrayList<BookDTO>();
			while (rset.next()) {
				list.add(new BookDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4),
						rset.getString(5)));
			}
		} finally {
			DBUtil.close(rset, pstmt, con);
		}
		if(list.size()>0) {
			return true;
		}else {
			return false;			
		}
	}
	////////////////////////////플래그 변경 함수////////////////////////////
	public static boolean ChangFlag(int bookId)throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update book set borrow_flag =0 where book_number=?");
			pstmt.setString(1, String.valueOf(bookId));
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(rset, pstmt, con);
		}
		return true;
	}
	/////////////////////////////rental_info 빌려주기////////////////////
	public static boolean BorrowBooks(int userId,int bookId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into rental_info(borrow_day,return_day,id_number,book_number) values('2022-05-10','2022-05-20',?,?)");
			pstmt.setInt(1, userId);
			pstmt.setInt(2, bookId);
		 	int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
			return false;
		} finally {
			DBUtil.close(rset, pstmt, con);
		}
	}
	//////////////////////////////////////////////////////////////////

	public static boolean updateBook(int rentalcode) throws SQLException {
		return DaoClass.updateBook(rentalcode);
	}


}
