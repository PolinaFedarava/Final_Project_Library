package by.htp.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class AbstractDao {
	
	protected Connection connect() {
		ResourceBundle rb = ResourceBundle.getBundle("db_configUser");
		String driver = rb.getString("dbuser.driver");
		String url = rb.getString("dbuser.url");
		String login = rb.getString("db.login");
		String pass = rb.getString("db.pass");

		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, login, pass);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	protected void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
