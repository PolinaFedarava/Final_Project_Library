package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.library.dao.AbstractDao;
import by.htp.library.dao.ReportSQLDao;

public class ReportSQLDaoImpl extends AbstractDao implements ReportSQLDao {

	@Override
	public void ReportAboutReaders() {
		Connection con = connect();
		try {
			String SQL_SELECT_ReportAboutReaders = "SELECT a.Name_user, a.Surname_user, b.name_book, c.start_date, "
					+ "(TO_DAYS(current_date())-TO_DAYS(DATE_ADD(c.start_date, INTERVAL 30 DAY)))  day_delay_book "
					+ "FROM user a, book b, books_to_readers c "
					+ "WHERE a.id_user = c.id_user AND b.id_book = c.id_book AND c.end_date IS null "
					+ "AND current_date() > DATE_ADD(c.start_date, INTERVAL 30 DAY)";
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_ReportAboutReaders);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Name_user" + rs.getString("Name_user") + ", Surname_user"
						+ rs.getString("Surname_user") + ", name_book" + rs.getString("name_book") + ", start_date"
						+ rs.getString("start_date") + ", day_delay_book" + rs.getString("day_delay_book"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}

	}

	@Override
	public void ReportAboutPopularBook() {
		Connection con = connect();
		try {
			String SQL_SELECT_ReportAboutReaders = "SELECT btr.id_book, b.name_book, "
					+ "COUNT(btr.id_book) popularity FROM books_to_readers btr, book b "
					+ "where btr.id_book = b.id_book group by btr.id_book order by popularity desc";
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_ReportAboutReaders);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("id_book = " + rs.getString("id_book") + ", name_book = " + rs.getString("name_book")
						+ ", popularity = " + rs.getString("popularity"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}

	}

	@Override
	public void ReportAboutActiveReader() {
		Connection con = connect();
		try {
			String SQL_SELECT_ReportAboutActiveReader = "select a.* from"
					+ " (SELECT COUNT(btr.id_user) veryreaders, u.Name_user, u.Surname_user "
					+ "FROM books_to_readers btr, user u WHERE btr.id_user=u.id_user"
					+ " AND end_date is not null group by btr.id_user) a"
					+ " where a.veryreaders > 2 and a.veryreaders < 8";
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_ReportAboutActiveReader);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("veryreaders = " + rs.getString("veryreaders") + ", Name_user = "
						+ rs.getString("Name_user") + ", Surname_user = " + rs.getString("Surname_user"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}

	}

}
