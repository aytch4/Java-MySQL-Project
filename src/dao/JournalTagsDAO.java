package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import entity.JournalTags;
/*
create table journalTag_tags(
		  journalTag int NOT NULL,
		  tag int NOT NULL,
		  primary key(journalTag, tag),
		  foreign key(journalTag) references journalTag(id),
		  foreign key(tag) references tags(id)
		);
*/
//
public class JournalTagsDAO {

	private Connection connection;
	// private UserDAO userDAO;
	private final String GET_JOURNALTAGS_QUERY = "SELECT * FROM journal_tags";
	private final String GET_JOURNALTAG_BY_ID_QUERY = "SELECT * FROM journal_tags WHERE id = ?";
	//private final String CREATE_NEW_JOURNALTAG_QUERY = "INSERT INTO journal_tags() VALUES (?)";
	private final String DELETE_JOURNALTAG_BY_ID_QUERY = "DELETE FROM journal_tags WHERE id =?";
	private final String UPDATE_JOURNALTAG_BY_ID_QUERY = "UPDATE journal_tags SET tag = ? WHERE id=?";
	//private final String UPDATE_JOURNALTAG_TITLE_BY_ID_QUERY = "UPDATE journalTags SET title = ? WHERE id=?";
	//private final String UPDATE_JOURNALTAG_CONTENT_BY_ID_QUERY = "UPDATE journalTags SET content = ? WHERE id=?";
	
	public JournalTagsDAO() {
		connection = DBConnection.getConnection();
	}
	
	public List<JournalTags> getJournalTagTags() throws SQLException {
		ResultSet rs= connection.prepareStatement(GET_JOURNALTAGS_QUERY).executeQuery();
		List<JournalTags> journalTags = new ArrayList<JournalTags>();
		
		while (rs.next()) {
			journalTags.add(populateJournalTags(rs.getInt(1),rs.getInt(2)));
		}
		return journalTags;
		
	}
	
	public JournalTags getJournalTagbyId(int journalId, int tagId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_JOURNALTAG_BY_ID_QUERY);
		ps.setInt(1, journalId);
		ps.setInt(2, tagId);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateJournalTags(rs.getInt(1),rs.getInt(2));
	}
	
//	public void createNewJournalTag(String journalTagName) throws SQLException {
//		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_JOURNALTAG_QUERY);
//		ps.setString(1, journalTagName);
//		ps.executeUpdate();
//	}
	
	public void deleteJournalTagById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_JOURNALTAG_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void updateJournalTagById(String tag, int id) throws SQLException {
	PreparedStatement ps = connection.prepareStatement(UPDATE_JOURNALTAG_BY_ID_QUERY);
	ps.setString(1, tag);
	ps.setInt(2, id);
	ps.executeUpdate();
}
//	public void updateJournalTagByTitle(String title, int id) throws SQLException {
//		PreparedStatement ps = connection.prepareStatement(UPDATE_JOURNALTAG_TITLE_BY_ID_QUERY);
//		ps.setString(1, title);
//		ps.setInt(2, id);
//		ps.executeUpdate();
//	}
//	
//	public void updateJournalTagByContent(String content, int id) throws SQLException {
//		PreparedStatement ps = connection.prepareStatement(UPDATE_JOURNALTAG_CONTENT_BY_ID_QUERY);
//		ps.setString(1, content);
//		ps.setInt(2, id);
//		ps.executeUpdate();
//	}
	//
	private JournalTags populateJournalTags(int journalId, int tagId) throws SQLException {
		return new JournalTags(journalId, tagId);
	}

}
