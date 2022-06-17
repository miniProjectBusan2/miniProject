package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoClass;
import model.BookDTO;
import model.RentalinfoDTO;

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
