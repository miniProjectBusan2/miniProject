package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import control.Controllclass;
import model.BookDTO;
import model.PersonDTO;
import model.RentalinfoDTO;
import util.DBUtil;

public class Service {
	
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
	

}
