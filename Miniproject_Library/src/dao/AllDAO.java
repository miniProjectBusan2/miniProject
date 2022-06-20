package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Exception.NotExistException;
import model.BookDTO;
import model.PersonDTO;
import model.RentalinfoDTO;
import util.DBUtil;

public class AllDAO {
	public static ArrayList<BookDTO> getAllBooks() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BookDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from book");
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
	
	// 도서 현황 전체 출력
	public static ArrayList<RentalinfoDTO> getrentalinfo() throws SQLException, NotExistException{
	      
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
	
	
	// 빌릴 수 있는 책만 검색
	
	public static ArrayList<BookDTO> borrowBooks() throws NotExistException, SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BookDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from book where borrow_flag > 0");
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
	
	// 도서 반납
	
	public static boolean returnBook(RentalinfoDTO dto) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from rental_info where rental_info_id = ?";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getRental_info_id());
			int r = pstmt.executeUpdate();
			if (r != 0) {
				return true;
			}
		} finally {
			pstmt.close();
			con.close();
		}
		return false;
	}
	
	// 북 넘버 가져오는 메소드
	
	public static int getborrow(RentalinfoDTO dto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select book_number from rental_info where rental_info_id = ?");
			pstmt.setInt(1, dto.getRental_info_id());
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				dto.setBook_number(rset.getInt("book_number"));
			}
		} finally {
			DBUtil.close(rset, pstmt, con);
		}
		return dto.getBook_number();
	}
	
	public static boolean updateBook(int rentalcode) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "update book set borrow_flag = borrow_flag + 1 where book_number = ?";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rentalcode);
			int r = pstmt.executeUpdate();
			if (r != 0) {
				return true;
			}
		} finally {
			pstmt.close();
			con.close();
		}
		return false;
	}
	////////////////////////밑으로 용주 Dao /////////////////////////////
	///////////////////////////// 플래그 확인 함수 //////////////////////////
	public static BookDTO flagReturn(int bookId)throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BookDTO dto = null;
//		ArrayList<BookDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select borrow_flag from book where book_number=?");
			pstmt.setInt(1, bookId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				dto = new BookDTO(0, null, null, rset.getInt("borrow_flag"), null);
			}
			
//			list = new ArrayList<BookDTO>();
//			while (rset.next()) {
//				list.add(new BookDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4),
//						rset.getString(5)));
//			}
			return dto;
		} finally {
			DBUtil.close(rset, pstmt, con);
		}
	}
	
	public static PersonDTO personReturn(int userId)throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PersonDTO dto = null;
//		ArrayList<BookDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select phone_num from person where id_number=?");
			pstmt.setInt(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				dto = new PersonDTO(0, rset.getString("phone_num"), "");
			}
			
//			list = new ArrayList<BookDTO>();
//			while (rset.next()) {
//				list.add(new BookDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4),
//						rset.getString(5)));
//			}
			return dto;
		} finally {
			DBUtil.close(rset, pstmt, con);
		}
	}
	////////////////////////////플래그 변경 함수////////////////////////////
	public static boolean changeFlag(int bookId)throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update book set borrow_flag = borrow_flag -1 where book_number=?");
			pstmt.setString(1, String.valueOf(bookId));
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(rset, pstmt, con);
		}
		return false;
	}

	/////////////////////////////rental_info 빌려주기////////////////////
	public static boolean borrowBooks(int userId,int bookId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into rental_info(borrow_day,return_day,id_number,book_number) values(curdate(),adddate(curdate(), 10),?,?)");
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
	/////////////////////////////// 위쪽으로 용주 코드 ///////////////////////////
	//////////////////////////////// 밑쪽으로 도헌이 코드 /////////////////////////
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
////////////////////////////////////////////////////////////////////
public static ArrayList<BookDTO> searchBooks(String str)throws SQLException, NotExistException {
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
///////////////////////////////////////////////////////////

	
}
