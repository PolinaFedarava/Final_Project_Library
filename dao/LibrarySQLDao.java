package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Book;
import by.htp.library.entity.User;

public interface LibrarySQLDao {

	List<User> listUsers();

	

	Book AddNewBook(String name_book, String autor, int quantity, String publisher);

	Book ReturnBook(int id_user);

	void SetEndDate(int id_book);

	Book GiveBook(int id_user, int id_book);

	void IncreaseQuantityBook(int id_book);

	void MinusQuantity(int id_book);

	Boolean CheckReaderBooks(int id_user);


	User AddNewReader(String Type_user, String Login, String Name_user, String Surname_user);

	

}
