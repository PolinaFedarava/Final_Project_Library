package by.htp.library.menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import by.htp.library.dao.impl.LibrarySQLDaoImpl;
import by.htp.library.dao.impl.ReaderSQLDaoImpl;
import by.htp.library.entity.Book;
import by.htp.library.entity.User;

public class ReaderMenu {
	
	public void ReaderMenuField(User CurrentUser) {
		
		ReaderSQLDaoImpl checkDebtDao = new ReaderSQLDaoImpl ();
		int id_user = CurrentUser.getId_user();
		checkDebtDao.CheckDebt (id_user);
		System.out.println("Please, select an operation" + "\n" + "1. View the books catalog" + "\n"
				+ "2. Details of the book" + "\n");

		System.out.println("Please, write your choice by number.");
		Scanner scanNumberReader = new Scanner(System.in);
		String writeChoiceNumberReader = scanNumberReader.nextLine();
		int writeChoiceNumberReaderINT = Integer.parseInt(writeChoiceNumberReader);

		switch (writeChoiceNumberReaderINT) {
		case 1:
			System.out.println("Your choice item '1. View the books catalog'");
			ReaderSQLDaoImpl booksCatalog = new ReaderSQLDaoImpl();
			List<Book> AllbooksCatalog = booksCatalog.listBooks(true);
			System.out.println(AllbooksCatalog);
			break;
		case 2:
			System.out.println("Your choice item '2. Details of the book'");
			System.out.println("Write the id of the book, which you are interested");
			Scanner scanBookId = new Scanner(System.in);
			String writeChoiceBookId = scanBookId.nextLine();
			int writeChoiceBookIdINT = Integer.parseInt(writeChoiceBookId);
			ReaderSQLDaoImpl bookInfo = new ReaderSQLDaoImpl ();
			Book currentbook = bookInfo.InformationAboutBook(writeChoiceBookIdINT);
			System.out.println(currentbook);
			break;
		}

	}

}


