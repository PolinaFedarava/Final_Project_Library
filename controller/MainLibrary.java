package by.htp.library.controller;

import java.util.Scanner;

import by.htp.library.dao.LibrarySQLDao;

import by.htp.library.dao.impl.LibrarySQLDaoImpl;
import by.htp.library.entity.Book;
import by.htp.library.entity.User;
import by.htp.library.logic.Authorization;
import by.htp.library.menu.LibrarianMenu;
import by.htp.library.menu.ReaderMenu;

public class MainLibrary {

	public static void main(String[] args) {

		System.out.println("Welcome to the library!");
		Authorization autorazationField = new Authorization();
		User CurrentUser = autorazationField.MenuAutorazation();
		if (CurrentUser == null) {
			return;
		}
		if (CurrentUser.getType_user().equals("Librarian")) {
			LibrarianMenu librarianMenuField = new LibrarianMenu();
			librarianMenuField.LibrarianMenuField(CurrentUser);
		}

		if (CurrentUser.getType_user().equals("Reader")) {
			ReaderMenu readerMenuField = new ReaderMenu();
			readerMenuField.ReaderMenuField(CurrentUser);
		}
	}

}
