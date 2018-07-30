package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.library.dao.AbstractDao;
import by.htp.library.entity.User;

public class UserAuthorizationDao extends AbstractDao {
	
	public User UserAuthorization(String Login, String Password) {
		Connection con = connect();
		User user = null;
		try {
			String SQL_SELECT_LIBRARIANAUTORIZE = "SELECT * FROM user WHERE login = " + "'" + Login + "'"
					+ " AND password = " + "'" + Password + "'";
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_LIBRARIANAUTORIZE);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId_user(rs.getInt("id_user"));
				user.setType_user(rs.getString("Type_user"));
				user.setLogin(rs.getString("Login"));
				user.setPassword(rs.getString("Password"));
				user.setName_user(rs.getString("Name_user"));
				user.setSurname_user(rs.getString("Surname_user"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return user;
	}
	
	public void AddPassword(String writeReaderTicket, String writeNewPassword) {
		Connection con = connect();
		String SQL_SELECT_ADDPASSWORD = "UPDATE user SET Password = '" + writeNewPassword + "' WHERE Login = '"
				+ writeReaderTicket + "'";
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
}
