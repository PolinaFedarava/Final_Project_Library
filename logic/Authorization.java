package by.htp.library.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import by.htp.library.dao.impl.UserAuthorizationDao;
import by.htp.library.entity.User;
import by.htp.library.menu.LibrarianMenu;

public class Authorization {

	public User MenuAutorazation() {

		String writeLog = null;
		String writePass = null;
		String writeReaderTicket = null;
		String writeNewPassword = null;
		User CurrentUser = null;
		User NewSignUpReader = null;

		System.out.println("Log in or Sign up?");
		System.out.println("Please, write an answer.");
		Scanner scan = new Scanner(System.in);
		String answer = scan.nextLine();

		if (answer.equals("Log in") || answer.equals("log in") || answer.equals("Login") || answer.equals("login")) {
			System.out.println("Please, write login");
			Scanner scanLogL = new Scanner(System.in);
			writeLog = scanLogL.nextLine();
			System.out.println("Please, write password");
			Scanner scanPass = new Scanner(System.in);
			writePass = scanPass.nextLine();
			if (writePass.length() >= 6) {
				String verificationPass = writePass.replaceAll("\\D+", "");
				if (verificationPass.length() >= 1) {
					System.out.println("Done");
				} else
					System.out.println("Incorrect password1");
			} else
				System.out.println("Incorrect password2");

			UserAuthorizationDao userAuthorizationDao = new UserAuthorizationDao();
			CurrentUser = userAuthorizationDao.UserAuthorization(writeLog, writePass);
			if (CurrentUser == null) {
				System.out.println("Wrong login or password. Try again");
				return null;
			} else if (CurrentUser != null) {
				System.out.println("Welcome " + CurrentUser.getName_user() + " " + CurrentUser.getSurname_user() + "!");
			}
		}

		if (answer.equals("Sign up") || answer.equals("sign up") || answer.equals("Signup")
				|| answer.equals("signup")) {
			System.out.println("Please, write number of reader's ticket");
			Scanner scanReaderTicket = new Scanner(System.in);
			writeReaderTicket = scanReaderTicket.nextLine();
			System.out.println("Your ticket number will be your login.");
			System.out.println("Please, create a password");
			System.out.println("The password must consist of a string of at least 6 characters"
					+ " and includes at least 1 number");
			Scanner scanNewPassword = new Scanner(System.in);
			writeNewPassword = scanNewPassword.nextLine();
			if (writeNewPassword.length() >= 6) {
				String verificationPass = writeNewPassword.replaceAll("\\D+", "");
				if (verificationPass.length() >= 1) {
					System.out.println("You are registered, please log in");
				} else
					System.out.println("Incorrect password1");
			} else
				System.out.println("Incorrect password2");
			UserAuthorizationDao userAddPassword = new UserAuthorizationDao();
			userAddPassword.AddPassword(writeReaderTicket, writeNewPassword);
		}

		else if (!(answer.equals("Log in") || answer.equals("log in") || answer.equals("Login")
				|| answer.equals("login") || answer.equals("Sign up") || answer.equals("sign up")
				|| answer.equals("Signup") || answer.equals("signup"))) {
			System.out.println("Oops! The entered answer is incorrect.");
			System.out.println("Try again");
			return null;
		}

		return CurrentUser;
	}
}
