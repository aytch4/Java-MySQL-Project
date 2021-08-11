package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDAO {
	
	private static final String CREATE_NEW_USER_QUERY = "INSERT INTO user() VALUES (?)";
	private static final String UPDATE_USER_QUERY = "UPDATE user SET emailaddress = ? WHERE userId = ?";
	private static final String DELETE_USER_QUERY = "DELETE FROM USER WHERE password = ?";
	
	private final String GET_ALL_USERS_QUERY = "SELECT * FROM user";
	
	private Connection connection;
	
	public UserDAO() {
		connection = DBConnection.getConnection();  
	}
	
	public List<User> getUsers(Connection connection) throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_ALL_USERS_QUERY).executeQuery();
		List<User> User = new ArrayList<User>();
		
		while (rs.next()) {
			User.add(populateUser(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		return User;
	}

	public User getUserById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_ALL_USERS_QUERY);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateUser(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
	}
	
	public void createNewUser(int id, String firstname, String lastname, String emailaddress) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_USER_QUERY);
		ps.setInt(1, id);
		ps.setString(2, firstname);
		ps.setString(3, lastname);
		ps.setString(4, emailaddress);
		ps.executeUpdate();
	}
	
	public void updateUser(int id, String emailaddress) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_USER_QUERY);
		ps.setInt(1, id);
		ps.setString(2, emailaddress);
		ps.executeUpdate();
	}
	
	public void deleteUser(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_USER_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	
	private User populateUser(int id, String firstname, String lastname, String emailaddress) throws SQLException{
		return new User(id, firstname, lastname, emailaddress);
	}
}
