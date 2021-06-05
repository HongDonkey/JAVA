package com.kopo.login;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

import com.kopo.login.Member;

public class UserDB {
	public boolean createDB() { // 테이블 생성
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/user.db", config.toProperties());
			// use
			String query = "CREATE TABLE users (idx INTEGER PRIMARY KEY AUTOINCREMENT"
					+ ", id TEXT, pwd TEXT, name TEXT, birthday TEXT, address TEXT, created TEXT, updated TEXT)";
			// sqlite는 정수는 INTEGER, 실수는 REAL, 문자열 TEXT
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate(query);
			statement.close();

			// close
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean insertDB(Member member) { // 회원정보 삽입
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:\\tomcat\\user.db", config.toProperties());

			// 아이디 중복 여부 검사
			String query1 = "SELECT * FROM users WHERE id=?";
			PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
			preparedStatement1.setString(1, member.id);
			ResultSet resultSet = preparedStatement1.executeQuery();
			if (resultSet.next()) {
				preparedStatement1.close();
				connection.close();
				return false;
			}
			preparedStatement1.close();			
			
			member.pwd = sha256(member.pwd);// password hash sha256 -> 주로사용

			String query = "INSERT INTO users (id, pwd, name, birthday, address, created, updated) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, member.id);
			preparedStatement.setString(2, member.pwd);
			preparedStatement.setString(3, member.name);
			preparedStatement.setString(4, member.birthday);
			preparedStatement.setString(5, member.address);
			preparedStatement.setString(6, member.created);
			preparedStatement.setString(7, member.updated);
			int result = preparedStatement.executeUpdate();

			if (result < 1) {
				preparedStatement.close();
				connection.close();
				return false;
			}
			preparedStatement.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean loginDB(String id, String pwd) { // 로그인
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:\\tomcat\\user.db", config.toProperties());

			pwd = this.sha256(pwd);// password hash sha256 -> 주로사용
			
			String query = "SELECT * FROM users WHERE id=? AND pwd=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pwd);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				preparedStatement.close();
				connection.close();
				return true;
			} else {
				preparedStatement.close();
				connection.close();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String selectData() { // 회원정보 전체조회
		String resultString = "";
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:\\tomcat\\user.db", config.toProperties());

			String query = "SELECT * FROM users";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int idx = resultSet.getInt("idx");
				String id = resultSet.getString("id");
				String pwd = resultSet.getString("pwd");
				String name = resultSet.getString("name");
				String birthday = resultSet.getString("birthday");
				String address = resultSet.getString("address");
				String created = resultSet.getString("created");
				String updated = resultSet.getString("updated");

				resultString = resultString + "<tr>" 
						+ "<td>" + idx + "</td>" 
						+ "<td>" + id + "</td>" 
						+ "<td>" + pwd+ "</td>" 
						+ "<td>" + name + "</td>" 
						+ "<td>" + birthday + "</td>" 
						+ "<td>" + address + "</td>"
						+ "<td>" + created + "</td>" 
						+ "<td>" + updated + "</td>" 
						+ "<td><a href='delete?idx=" + idx + "'>삭제하기</a></td>" + "</tr>";
			}
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}
	
	public Member detailsData(int idx) { // 특정회원 조회
		Member resultData = new Member();
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:\\tomcat\\user.db", config.toProperties());

			String query = "SELECT * FROM users WHERE idx=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, idx);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				resultData.idx = resultSet.getInt("idx");
				resultData.id = resultSet.getString("id");
				resultData.pwd = resultSet.getString("pwd");
				resultData.name = resultSet.getString("name");
				resultData.birthday = resultSet.getString("birthday");
				resultData.address = resultSet.getString("address");
				resultData.created = resultSet.getString("created");
				resultData.updated = resultSet.getString("updated");
			}
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
		}
		return resultData;
	}
	
//	public Member detailsDataWithId(String id) { // 특정회원 조회
//		Member resultData = new Member();
//		try {
//			Class.forName("org.sqlite.JDBC");
//			SQLiteConfig config = new SQLiteConfig();
//			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:\\tomcat\\user.db", config.toProperties());
//
//			String query = "SELECT * FROM users WHERE id=?";
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, id);
//			ResultSet resultSet = preparedStatement.executeQuery();
//			if (resultSet.next()) {
//				resultData.idx = resultSet.getInt("idx");
//				resultData.id = resultSet.getString("id");
//				resultData.pwd = resultSet.getString("pwd");
//				resultData.name = resultSet.getString("name");
//				resultData.birthday = resultSet.getString("birthday");
//				resultData.address = resultSet.getString("address");
//				resultData.created = resultSet.getString("created");
//				resultData.updated = resultSet.getString("updated");
//			}
//			preparedStatement.close();
//			connection.close();
//		} catch (Exception e) {
//		}
//		return resultData;
//	}


	public int verificationData(String id, String password) {// 수정을 위한 검증 
		int returnIdx = -1 ;
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:\\tomcat\\user.db",config.toProperties());
			
			String pwd = sha256(password);
			System.out.println(pwd);
			String query = "SELECT * FROM users WHERE id=? and pwd = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pwd);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				returnIdx = resultSet.getInt("idx");
			}
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnIdx;
	}
	

	public boolean updateData(String id, String pwd, String name, String address, String updated) { // 회원정보 갱신
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:\\tomcat\\user.db",
					config.toProperties());

			String query = "UPDATE users SET pwd=?, name=?,address=?,updated=? WHERE id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			String hasPwd = sha256(pwd);
			
			preparedStatement.setString(1, hasPwd);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, address);
			preparedStatement.setString(4, updated);
			preparedStatement.setString(5, id);

			int result = preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			if (result == 1) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteData(int idx) { // 회원정보 삭제
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:\\tomcat\\user.db",config.toProperties());
			String query = "DELETE FROM users WHERE idx=? ";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, idx);
			int result = preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
			if (result == 1) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String searchData(String InputId) { // 회원정보 검색
		Member resultData = new Member();
		String resultString = "";
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:\\tomcat\\user.db",
					config.toProperties());
			String query = "SELECT * FROM users WHERE name=? ";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, InputId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int idx = resultSet.getInt("idx");
				String id = resultSet.getString("id");
				String pwd = resultSet.getString("pwd");
				String name = resultSet.getString("name");
				String birthday = resultSet.getString("birthday");
				String address = resultSet.getString("address");
				String created = resultSet.getString("created");
				String updated = resultSet.getString("updated");

				resultString = resultString + "<tr>" 
						+ "<td>" + idx + "</td>" 
						+ "<td>" + id + "</td>" 
						+ "<td>" + pwd + "</td>" 
						+ "<td>" + name + "</td>" 
						+ "<td>" + birthday + "</td>" 
						+ "<td>" + address + "</td>"
						+ "<td>" + created + "</td>" 
						+ "<td>" + updated + "</td>" 
						+ "<td><a href='delete?idx=" + idx + "'>삭제하기</a></td>" + "</tr>";
			}
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}

	public String sha256(String msg) { // 비밀번호 암호화
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(msg.getBytes());

			StringBuilder builder = new StringBuilder();
			for (byte b : md.digest()) {
				builder.append(String.format("%02x", b));
			}
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
