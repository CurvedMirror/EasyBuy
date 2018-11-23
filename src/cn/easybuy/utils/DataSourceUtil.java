package cn.easybuy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataSourceUtil {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	
	static {
		init();
	}

	public static void init() {
		Properties params = new Properties();
		String configFile = "database.properties";
		InputStream is = DataSourceUtil.class.getClassLoader()
				.getResourceAsStream(configFile);
		try {
			params.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver = params.getProperty("driver");
		url = params.getProperty("url");
		user = params.getProperty("username");
		password = params.getProperty("password");

	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
