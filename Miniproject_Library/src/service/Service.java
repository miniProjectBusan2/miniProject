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
	
	public static boolean updateBook(int rentalcode) throws SQLException {
		return DaoClass.updateBook(rentalcode);
	}

}
