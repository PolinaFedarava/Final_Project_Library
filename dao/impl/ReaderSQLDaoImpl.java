package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.dao.AbstractDao;
import by.htp.library.dao.ReaderSQLDao;
import by.htp.library.entity.Book;


public class ReaderSQLDaoImpl extends AbstractDao implements ReaderSQLDao
{

	@Override
	public void CheckDebt(int id_user) {
		String SQL_SELECT_CHECKRETURUSERNBOOKS = "SELECT * FROM books_to_readers WHERE id_user = '" + id_user
				+ "' AND current_date() > DATE_ADD(start_date, INTERVAL 30 DAY)";
		Connection con = connect();
		try {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_CHECKRETURUSERNBOOKS);
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				System.out.println("Attention!!! You have debts to the library. Return the book to the library!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}

	}

	@Override
	public List<Book> listBooks(Boolean ShowAllBooks) {
		String SQL_SELECT_LISTBOOKS = "SELECT * FROM book";
		if (!ShowAllBooks) {
			SQL_SELECT_LISTBOOKS = "SELECT * FROM book WHERE quantity > 0";
		}
		Connection con = connect();
		List<Book> listBook = new ArrayList();
		Book book = null;
		try {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_LISTBOOKS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setId_book(rs.getInt("id_book"));
				book.setName_book(rs.getString("name_book"));
				book.setAutor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setQuantity(rs.getInt("quantity"));
				listBook.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return listBook;
	}

	@Override
	public Book InformationAboutBook(int writeChoiceBookId) {
		String SQL_SELECT_BOOKINFO = "SELECT * FROM book WHERE id_book = ' " + writeChoiceBookId + "'";
		Connection con = connect();
		Book book = null;
		try {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_BOOKINFO);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setId_book(rs.getInt("id_book"));
				book.setName_book(rs.getString("name_book"));
				book.setAutor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setQuantity(rs.getInt("quantity"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return book;
	}

}
