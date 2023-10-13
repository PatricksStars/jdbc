package com.lt.jdbc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lt.jdbc.ConnectFactory;

public class JdbcUtil {
	
	private volatile static Connection conn;
	
	public JdbcUtil() {
		
	}

	public static Connection getConn () throws ClassNotFoundException, SQLException {
		if(conn == null) {
			synchronized (Connection.class) {
				if(conn == null) {
					conn = new ConnectFactory().getConn();
				}
			}
		}
		return conn;
	}
	
	public static ResultSet getResultSet (String sql) {
		try {
			Statement stmt = getConn().createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			return rs;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void insert(String table,String clounm,String value) {
		Statement stmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ").append(table).append("(").append(clounm).append(") ").
				append("VALUES(").append(value).append(")"); 
			System.out.println(sql.toString());
			stmt = getConn().createStatement();
			stmt.execute(sql.toString());
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void delete(String table ,String condition) {
		Statement stmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM ").append(table).append(" WHERE ").append(condition);
			stmt = getConn().createStatement();
			stmt.execute(sql.toString());
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void update(String table,String upCondition,String condition) {
		Statement stmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ").append(table).append(" SET ").append(upCondition).append(" WHERE ").append(condition);
			stmt = getConn().createStatement();
			stmt.execute(sql.toString());
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static boolean exist(String table,String condition) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(*) FROM ").append(table).append(" WHERE ").append(condition);
			stmt = getConn().createStatement();
			rs =  stmt.executeQuery(sql.toString());
			while(rs.next()) {
				return rs.getInt(1)>0 ? true : false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
