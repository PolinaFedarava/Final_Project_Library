package by.htp.library.menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

import by.htp.library.dao.impl.LibrarySQLDaoImpl;
import by.htp.library.dao.impl.ReportSQLDaoImpl;
import by.htp.library.entity.Book;
import by.htp.library.entity.User;
import by.htp.library.logic.Authorization;

public class LibrarianMenu {

	public void LibrarianMenuField(User CurrentUser) {
		LibrarySQLDaoImpl librarySQLDaoImpl = new LibrarySQLDaoImpl();
		System.out.println("Please, select an operation" + "\n" + "1. Add a new reader" + "\n" + "2. Add a new book"
				+ "\n" + "3. Book accounting" + "\n" + "4. Create reports");

		System.out.println("Please, write your choice by number.");
		Scanner scanNumber = new Scanner(System.in);
		String writeChoiceNumber = scanNumber.nextLine();
		int writeChoiceNumberINT = Integer.parseInt(writeChoiceNumber);
		switch (writeChoiceNumberINT) {
		case 1:
			System.out.println("Your choice item '1. Add a new reader'");
			System.out.println("Please, fill out fields");

			System.out.println("Type_user: ");
			Scanner scanType_user = new Scanner(System.in);
			String Type_user = scanType_user.nextLine();
			if (Type_user == null || Type_user == " ") {
				System.out.println("The field is not filled!");
			}
			System.out.println("Login: ");
			Scanner scanLogin = new Scanner(System.in);
			String Login = scanLogin.nextLine();
			if (Login == null || Login == " ") {
				System.out.println("The field is not filled!");
			}

			System.out.println("Name_user: ");
			Scanner scanName_user = new Scanner(System.in);
			String Name_user = scanName_user.nextLine();
			if (Name_user == null || Name_user == " ") {
				System.out.println("The field is not filled!");
			}

			System.out.println("Surname_user: ");
			Scanner scanSurname_user = new Scanner(System.in);
			String Surname_user = scanSurname_user.nextLine();
			if (Surname_user == null || Surname_user == " ") {
				System.out.println("The field is not filled!");
			}
			librarySQLDaoImpl.AddNewReader(Type_user, Login, Name_user, Surname_user);
			break;

		case 2:
			System.out.println("Your choice item '2. Add a new book'");
			System.out.println("Please, fill out fields");

			System.out.println("name_book: ");
			Scanner scanName_book = new Scanner(System.in);
			String name_book = scanName_book.nextLine();
			if (name_book == null || name_book == " ") {
				System.out.println("The field is not filled!");
			}
			System.out.println("autor: ");
			Scanner scanAutor = new Scanner(System.in);
			String autor = scanAutor.nextLine();
			if (autor == null || autor == " ") {
				System.out.println("The field is not filled!");
			}

			System.out.println("quantity: ");
			Scanner scanQuantity = new Scanner(System.in);
			String quantityS = scanQuantity.nextLine();
			if (quantityS == null || quantityS == " ") {
				System.out.println("The field is not filled!");
			}
			int quantity = Integer.parseInt(quantityS);

			System.out.println("publisher: ");
			Scanner scanPublisher = new Scanner(System.in);
			String publisher = scanPublisher.nextLine();
			if (publisher == null || publisher == " ") {
				System.out.println("The field is not filled!");
			}
			librarySQLDaoImpl.AddNewBook(name_book, autor, quantity, publisher);
			break;

		case 3:
			System.out.println("Your choice item '3. Book accounting'");
			BookAccountingForLibrarian accountingForLibrarianField = new BookAccountingForLibrarian();
			accountingForLibrarianField.MenuBookAccounting();
			break;
		case 4:
			System.out.println("Your choice item '4. Create reports'");
			System.out.println("Please, select the report you need." + "\n"
					+ "1. Report about readers who are in arrears on the return of books" + "\n"
					+ "2. Report on readed books" + "\n"
					+ "3. Report on employees who read at least 2 and not more than 8 books per month");
			String writeChoice = scanNumber.nextLine();
			int writeChoiceINT = Integer.parseInt(writeChoice);
			switch (writeChoiceINT) {
			case 1:
				ReportSQLDaoImpl report1 = new ReportSQLDaoImpl();
				report1.ReportAboutReaders();
				break;
			case 2:
				ReportSQLDaoImpl report2 = new ReportSQLDaoImpl();
				report2.ReportAboutPopularBook();
				break;
			case 3:
				ReportSQLDaoImpl report3 = new ReportSQLDaoImpl();
				report3.ReportAboutActiveReader();
				break;

			}
			break;
		}
	}

}
