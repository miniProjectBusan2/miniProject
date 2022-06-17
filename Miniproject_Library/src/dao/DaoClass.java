package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Exception.NotExistException;
import model.BookDTO;
import model.RentalinfoDTO;
import util.DBUtil;

public class DaoClass {
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
	
	public static boolean ReturnBook(RentalinfoDTO dto) throws SQLException, NotExistException {
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
}
