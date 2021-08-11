package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Journal;
import java.util.Date;

/*create table journal (
		id int auto_increment not null,
	    date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	    title varchar(256) not null,
	    content text not null,
	    user int not null,
	    primary key (id),
	    foreign key (user) references user(id)
	);*/

public class JournalDAO {

	private Connection connection;
	// private UserDAO userDAO;
	private final String GET_JOURNALS_QUERY = "SELECT * FROM journals";
	private final String GET_JOURNAL_BY_ID_QUERY = "SELECT * FROM journals WHERE id = ?";
	private final String CREATE_NEW_JOURNAL_QUERY = "INSERT INTO journals(title, content, user) VALUES (?,?,?)";
	private final String DELETE_JOURNAL_BY_ID_QUERY = "DELETE FROM journals WHERE id =?";
	private final String UPDATE_JOURNAL_TITLE_BY_ID_QUERY = "UPDATE journals SET title = ? WHERE id=?";
	private final String UPDATE_JOURNAL_CONTENT_BY_ID_QUERY = "UPDATE journals SET content = ? WHERE id=?";
	
	public JournalDAO() {
		connection = DBConnection.getConnection();
		//userDAO = new UserDAO(); 
	}
	
	public List<Journal> getJournals() throws SQLException {
		ResultSet rs= connection.prepareStatement(GET_JOURNALS_QUERY).executeQuery();
		List<Journal> journals = new ArrayList<Journal>();
		
		while (rs.next()) {
			journals.add(populateJournal(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
		}
		return journals;
		
	}
	
	public Journal getJournalbyId(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_JOURNAL_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateJournal(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getInt(5));
	}
	
	public void createNewJournal(String journalName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_JOURNAL_QUERY);
		ps.setString(1, journalName);
		ps.executeUpdate();
	}
	
	public void deleteJournalById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_JOURNAL_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void updateJournalByTitle(String title, int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_JOURNAL_TITLE_BY_ID_QUERY);
		ps.setString(1, title);
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	
	public void updateJournalByContent(String content, int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_JOURNAL_CONTENT_BY_ID_QUERY);
		ps.setString(1, content);
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	
	private Journal populateJournal(int id, Date date, String title, String content, int userId) throws SQLException {
		return new Journal(id, date, title, content, userId);
	}

}


