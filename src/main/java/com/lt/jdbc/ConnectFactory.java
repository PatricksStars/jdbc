package com.lt.jdbc;

import org.springframework.stereotype.Service;

import com.lt.jdbc.util.Property;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ConnectFactory {
	
	public Connection getConn() {
		try {
			Class.forName(Property.getDriver());
			return DriverManager.getConnection(Property.getUrl(),Property.getName(),Property.getPassword());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getResultSet (Connection conn,String sql) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insert(Connection conn,String table,String clounm,String value) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ").append(table).append("(").append(clounm).append(") ").
				append("VALUES(").append(value).append(")"); 
			
			Statement stmt = conn.createStatement();
			stmt.execute(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Connection conn,String table ,String condition) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM ").append(table).append(" WHERE ").append(condition);
			Statement stmt = conn.createStatement();
			stmt.execute(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Connection conn,String table,String upCondition,String condition) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ").append(table).append(" SET ").append(upCondition).append(" WHERE ").append(condition);
			Statement stmt = conn.createStatement();
			stmt.execute(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
