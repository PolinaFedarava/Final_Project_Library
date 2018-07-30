package by.htp.library.menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import by.htp.library.dao.impl.LibrarySQLDaoImpl;
import by.htp.library.dao.impl.ReaderSQLDaoImpl;
import by.htp.library.entity.Book;
import by.htp.library.entity.User;

public class BookAccountingForLibrarian {

	public void MenuBookAccounting() {

		System.out.println("Please, select an operation in book accounting" + "\n" + "1. Give the book to the reader"
				+ "\n" + "2. Return of the book");
		System.out.println("Please, write your choice by number.");
		Scanner scanNumberReader = new Scanner(System.in);
		String writeChoiceNumberLibrarian = scanNumberReader.nextLine();
		int writeChoiceNumberLibrarianINT = Integer.parseInt(writeChoiceNumberLibrarian);
		switch (writeChoiceNumberLibrarianINT) {
		case 1:
			System.out.println("1. Give the book to the reader");
			GiveBook();
			break;
		case 2:
			System.out.println("2. Return of the book");
			ReturnBook();
			break;
		}
	}

	public void ReturnBook() {

		Book book = null;

		LibrarySQLDaoImpl librarySQLDaoImpl = new LibrarySQLDaoImpl();
		List<User> id_userForChoice = librarySQLDaoImpl.listUsers();

		System.out.println(id_userForChoice);
		System.out.println("Select a user and enter his id_user");
		Scanner scan = new Scanner(System.in);
		String writeId_user = scan.nextLine();
		int id_user = Integer.parseInt(writeId_user);

		librarySQLDaoImpl.ReturnBook(id_user);

		System.out.println("Select a book and enter his id_book");

		String writeId_book = scan.nextLine();
		int id_book = Integer.parseInt(writeId_book);

		librarySQLDaoImpl.SetEndDate(id_book);
		System.out.println("The book is returned");

		librarySQLDaoImpl.IncreaseQuantityBook(id_book);

		System.out.println("Number of books in the library increased");
	}

	public void GiveBook() {
		User user = null;
		LibrarySQLDaoImpl librarySQLDaoImpl = new LibrarySQLDaoImpl();
		ReaderSQLDaoImpl readerSQLDaoImpl = new ReaderSQLDaoImpl ();
		List<User> id_userForChoice = librarySQLDaoImpl.listUsers();
		System.out.println(id_userForChoice);
		System.out.println("Select a user and enter his id_user");
		Scanner scan = new Scanner(System.in);
		String writeId_user = scan.nextLine();
		int id_user = Integer.parseInt(writeId_user);

		List<Book> verifiedListBook = readerSQLDaoImpl.listBooks(false);
		
		System.out.println(verifiedListBook);
		System.out.println("Select a book and enter its id_book");
		String writeId_book = scan.nextLine();
		int id_book = Integer.parseInt(writeId_book);

		librarySQLDaoImpl.GiveBook(id_user, id_book);

		librarySQLDaoImpl.MinusQuantity(id_book);

		readerSQLDaoImpl.CheckDebt(id_user);

	}

}
