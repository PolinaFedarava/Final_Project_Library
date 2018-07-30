package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Book;

public interface ReaderSQLDao {

	void CheckDebt(int id_user);
	
	List<Book> listBooks(Boolean ShowAllBooks);
	
	Book InformationAboutBook(int writeChoiceBookId);

	
}
