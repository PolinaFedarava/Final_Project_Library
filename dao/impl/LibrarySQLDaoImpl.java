package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import by.htp.library.dao.AbstractDao;
import by.htp.library.dao.LibrarySQLDao;
import by.htp.library.entity.Book;
import by.htp.library.entity.User;

public class LibrarySQLDaoImpl extends AbstractDao implements LibrarySQLDao {


	@Override
	public List<User> listUsers() {
		Connection con = connect();
		List<User> list = new ArrayList();
		User user = null;
		try {
			String SQL_SELECT_LISTUSERS = "SELECT * FROM user ";
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_LISTUSERS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId_user(rs.getInt("id_user"));
				user.setType_user(rs.getString("Type_user"));
				user.setLogin(rs.getString("Login"));
				user.setPassword(rs.getString("Password"));
				user.setName_user(rs.getString("Name_user"));
				user.setSurname_user(rs.getString("Surname_user"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return list;

	}

	

	@Override
	public Book AddNewBook(String name_book, String autor, int quantity, String publisher) {
		Book book = null;
		Connection con = connect();
		String SQL_SELECT_ADDNEWBOOK = "INSERT INTO book (name_book, autor, quantity, publisher) " + "VALUES ( '"
				+ name_book + "', '" + autor + "'" + ", '" + quantity + "' , '" + publisher + "')";
		System.out.println();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL_SELECT_ADDNEWBOOK);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return book;

	}

	@Override
	public User AddNewReader(String Type_user, String Login, String Name_user, String Surname_user) {
		User user = null;
		Connection con = connect();

		String SQL_SELECT_ADDNEWREADER = "INSERT INTO user (Type_user, Login, Name_user, Surname_user) " + "VALUES ( '"
				+ Type_user + "', '" + Login + "'" + ", '" + Name_user + "' , '" + Surname_user + "')";
		System.out.println();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL_SELECT_ADDNEWREADER);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return user;
	}

	

	@Override
	public Book ReturnBook(int id_user) {
		Book book = null;
		Connection con = connect();
		try {
			String SQL_SELECT_USERANDBOOK = "SELECT a.id_book, b.name_book FROM books_to_readers a, book b "
					+ "WHERE a.id_user = '" + id_user + "' AND a.end_date IS null AND a.id_book = b.id_book";
			List<Book> listReaderBook = new ArrayList();
			PreparedStatement ps1 = con.prepareStatement(SQL_SELECT_USERANDBOOK);
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setId_book(rs.getInt("id_book"));
				book.setName_book(rs.getString("name_book"));
				listReaderBook.add(book);
				System.out.println(listReaderBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return book;
	}

	@Override
	public void SetEndDate(int id_book) {
		Connection con = connect();
		try {
			String SQL_UPDATE_ADDENDDATE = "UPDATE books_to_readers SET end_date = CURRENT_DATE() "
					+ "WHERE id_book = '" + id_book + "' ";
			PreparedStatement ps2;
			ps2 = con.prepareStatement(SQL_UPDATE_ADDENDDATE);
			ps2.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}

	}

	@Override
	public void IncreaseQuantityBook(int id_book) {
		Connection con = connect();
		try {
			String SQL_UPDATE_QUANTITY = "UPDATE book SET quantity = (quantity + 1) " + "WHERE id_book = '" + id_book
					+ "' ";
			PreparedStatement ps3;
			ps3 = con.prepareStatement(SQL_UPDATE_QUANTITY);
			ps3.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	@Override
	public Book GiveBook(int id_user, int id_book) {
		Connection con = connect();
		try {
			PreparedStatement ps;
			String SQL_SELECT_GiveBook = "INSERT INTO books_to_readers (id_user, id_book, start_date) " + "VALUES ( '"
					+ id_user + "', '" + id_book + "'" + ", CURRENT_DATE())";
			ps = con.prepareStatement(SQL_SELECT_GiveBook);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return null;
	}

	@Override
	public void MinusQuantity(int id_book) {
		Connection con = connect();
		String SQL_SELECT_ADDPASSWORD = "UPDATE book SET quantity =  (quantity - 1)  WHERE id_book = " + "'" + id_book
				+ "'";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL_SELECT_ADDPASSWORD);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}

	}

	@Override
	public Boolean CheckReaderBooks(int id_user) {
		Boolean result = null;
		String SQL_SELECT_CHECKREADERBOOK = "SELECT books_number  FROM (SELECT count(*) books_number "
				+ "FROM books_to_readers WHERE id_user = '" + id_user + "' AND end_date is null) holded_books"
				+ " WHERE books_number >= 3";
		String SQL_SELECT_CHECKRETURNBOOKS = "SELECT * FROM books_to_readers WHERE id_user = '" + id_user
				+ "' AND current_date() > DATE_ADD(start_date, INTERVAL 30 DAY)";
		Connection con = connect();
		try {
			PreparedStatement ps1 = con.prepareStatement(SQL_SELECT_CHECKREADERBOOK);
			PreparedStatement ps2 = con.prepareStatement(SQL_SELECT_CHECKRETURNBOOKS);
			ResultSet rs1 = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			if (rs1 == null || rs2 == null) {
				result = true;
			}
			if (rs1 != null) {
				result = false;
				System.out.println("The reader has more than 3 books");
			}
			if (rs2 != null) {
				result = false;
				System.out.println("The reader has a debt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return result;
	}

	

	

}
