package com.com.com;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class MemberDB {

	public boolean createTable() {
		boolean isSuccess = false;
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/210601Member.db",
					config.toProperties());

			// use
			String query = "CREATE TABLE Member (idx INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, pwd TEXT, name, address TEXT, birthday TEXT, created TEXT, updated TEXT)";

			Statement statement = connection.createStatement();
			int result = statement.executeUpdate(query);
			if (result == 1) {
				isSuccess = true;
			}
			statement.close();

			// close
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			// 오류구문 출력
		}
		return isSuccess;
	}

	public boolean insertData(Member mb) {
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/210601Member.db",
					config.toProperties());

//			String query = "INSERT INTO Member (id, pwd, name, address, birthday, created, updated) VALUES "
//					+ "('" + mb.id + "' ,'" 
//					+ mb.pwd + "', '" 
//					+ mb.name + "', '" 
//					+ mb.address + "', '" 
//					+ mb.birthday + "', '" 
//					+ mb.now + "', '" 
//					+ mb.updated + "'"
//					+ ")";
//					
//
//			Statement statement = connection.createStatement();
//			int result = statement.executeUpdate(query);
//			//일반적인 상황에서는 statement를 사용하는 게 편함
			
			//아이디 중복 검사
			String query2 = "SELECT * FROM Member WHERE id=?";
			PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
			preparedStatement2.setString(1, mb.id);
			ResultSet resultSet = preparedStatement2.executeQuery();
			if(resultSet.next()) {
				preparedStatement2.close();
				connection.close();
				return false;
			}
			preparedStatement2.close();
			
			mb.pwd = sha256(mb.pwd);

			String query = "INSERT INTO Member (id, pwd, name, birthday, address, created, updated)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, mb.id);
			preparedStatement.setString(2, mb.pwd);
			preparedStatement.setString(3, mb.name);
			preparedStatement.setString(4, mb.birthday);
			preparedStatement.setString(5, mb.address);
			preparedStatement.setString(6, mb.now);
			preparedStatement.setString(7, mb.updated);
			int result = preparedStatement.executeUpdate();
			// 값이 입력이 될 때는 preparedStatement 쓰는 게 편함
			// 입력에 성공하면 1을 반환함
//			System.out.println(result);
			
			if (result < 1) {
				preparedStatement.close();
				connection.close();
				return false;
			}
			preparedStatement.close();
			connection.close();
			// close
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// password hash sha256
	public String sha256(String pwd) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// MessageDigest : 암호화를 위한 클래스
			md.update(pwd.getBytes());
			// getBytes : 유니코드 문자열을 바이트코드로 인코딩
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

	public String selectData() {
		String resultString = "";
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/210601Member.db",
					config.toProperties());

			String query = "SELECT * FROM Member";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int idx = resultSet.getInt("idx");
				String id = resultSet.getString("id");
				String pwd = resultSet.getString("pwd");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				String birthday = resultSet.getString("birthday");
				String created = resultSet.getString("created");
				String updated = resultSet.getString("updated");
				resultString += "<tr>";
				resultString = resultString + "<td>" + idx + "</td><td>" + id + "</td><td>" + pwd + "</td><td>" + name
						+ "</td><td>" + address + "</td><td>" + birthday + "</td><td>" + created + "</td><td>" + updated
						+ "</td><td><a href='update?idx=" + idx + "'>수정하기</a></td><td><a href='delete_action?idx=" + idx
						+ "'>삭제하기</a></td>";

				resultString += "</tr>";

			}
			preparedStatement.close();
			connection.close();

		} catch (Exception e) {

		}
		return resultString;
	}

	public Member detailsData(int idx) {
		Member resultData = new Member();
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/210601Member.db",
					config.toProperties());

			String query = "SELECT * FROM Member WHERE idx=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, idx);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				resultData.idx = resultSet.getInt("idx");
				resultData.id = resultSet.getString("id");
				resultData.pwd = resultSet.getString("pwd");
				resultData.name = resultSet.getString("name");
				resultData.address = resultSet.getString("address");
				resultData.birthday = resultSet.getString("birthday");
				resultData.now = resultSet.getString("created");

			}
			preparedStatement.close();

			// close
			connection.close();

		} catch (Exception e) {

		}
		return resultData;
	}

	public Member updateData(int idx, String id, String pwd, String name, String address, String birthday) {
		Member resultData = new Member();
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/210601Member.db",
					config.toProperties());

			String query = "UPDATE Member SET id=?, pwd=?, name=?, address=?, birthday=?, updated=datetime('now') WHERE idx=?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pwd);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, address);
			preparedStatement.setString(5, birthday);
			preparedStatement.setInt(6, idx);
			int result = preparedStatement.executeUpdate();
			preparedStatement.close();

			// close
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultData;
	}

	public Member deleteData(int idx) {
		Member resultData = new Member();
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/210601Member.db",
					config.toProperties());

			String query = "DELETE FROM Member WHERE idx=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, idx);
			ResultSet resultSet = preparedStatement.executeQuery();

			preparedStatement.close();

			// close
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultData;

	}

	public String searchData(String searchName) {
		String resultString = "";
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/210601Member.db",
					config.toProperties());

			String query = "SELECT * FROM Member WHERE name LIKE ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, '%' + searchName + '%');
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int idx = resultSet.getInt("idx");
				String id = resultSet.getString("id");
				String pwd = resultSet.getString("pwd");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				String birthday = resultSet.getString("birthday");
				String created = resultSet.getString("created");
				String updated = resultSet.getString("updated");
				resultString += "<tr>";
				resultString = resultString + "<td>" + idx + "</td><td>" + id + "</td><td>" + pwd + "</td>" + " "
						+ "<td>" + name + "</td>" + "<td>" + birthday + "</td>" + " " + "<td>" + address + "</td><td>"
						+ created + "</td><td>" + "<a href='update?idx=" + idx
						+ "'>수정하기</a></td><td><a href='delete_action?idx=" + idx + "'>삭제하기</a></td>";

				resultString += "</tr>";

			}
			preparedStatement.close();

			// close
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;

	}
	
	public boolean loginDB(String id, String pwd) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/210601Member.db",
					config.toProperties());
			
			pwd = this.sha256(pwd);

			String query = "SELECT * FROM Member WHERE id=? AND pwd=?";
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
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public int verificationData(String id, String password) {// 수정을 위한 검증 
		int returnIdx = -1 ;
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:\\tomcat\\user.db",config.toProperties());
			
			String pwd = sha256(password);
//			System.out.println(pwd);
			String query = "SELECT * FROM users WHERE id=? and pwd = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pwd);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				returnIdx = resultSet.getInt("idx");
				//DB에 등록된 인덱스 값을 returnIdx으로 받음
			}
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnIdx;
	}
}