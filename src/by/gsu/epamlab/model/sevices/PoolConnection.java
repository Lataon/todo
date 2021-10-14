package by.gsu.epamlab.model.sevices;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnection {
	private static MysqlDataSource dataSource;
	private final static  String URL_HEADER = "jdbc:mysql://localhost:32769/";
	private final static String URL_FOOTER = "?serverTimezone=Europe/Minsk";

	public static void init(String dbName, String user, String password) {
		if(dataSource != null) {
			return;
		}
		dataSource = new MysqlDataSource();
		dataSource.setUrl(URL_HEADER + dbName + URL_FOOTER);
		dataSource.setUser(user);
		dataSource.setPassword(password);
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}