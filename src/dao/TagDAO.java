package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Tag;

public class TagDAO {

	private static final String CREATE_NEW_TAG_QUERY = "INSERT INTO tags(name) VALUES(?)";
	private static final String UPDATE_TAG_BY_ID_QUERY = "UPDATE tags SET name = ? WHERE id = ?";
	private static final String DELETE_TAG_BY_ID_QUERY = "DELETE FROM tags WHERE id = ?";
	private Connection connection;

	private final String GET_ALL_TAGS_QUERY = "SELECT * FROM tags";
	private final String GET_TAG_BY_ID_QUERY = "SELECT * FROM tags WHERE id = ?";
	private final String GET_TAG_BY_NAME_QUERY = "SELECT * FROM tags WHERE name = ?";

	public TagDAO() {
		connection = DBConnection.getConnection();
	}

	public Tag getTagByName(String name) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_TAG_BY_NAME_QUERY);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateTags(rs.getInt(1), rs.getString(2));
	}

	private Tag populateTags(int id, String name) {
		return new Tag(id, name);
	}

	public Tag getTagById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_TAG_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateTags(rs.getInt(1), rs.getString(2));
	}

	public List<Tag> getAllTags() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_ALL_TAGS_QUERY).executeQuery();
		List<Tag> tags = new ArrayList<Tag>();

		while (rs.next()) {
			tags.add(populateTags(rs.getInt(1), rs.getString(2)));
		}
		return tags;
	}

	public void createNewTag(String name) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_TAG_QUERY);
		ps.setString(1, name);
		ps.executeUpdate();
	}

	public void updateTagById(int id, String newTag) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_TAG_BY_ID_QUERY);
		ps.setInt(2, id);
		ps.setString(1, newTag);
		ps.executeUpdate();
	}

	public void deleteTag(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_TAG_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();

	}

	public void displayAllTags() throws SQLException {
		List<Tag> tags = getAllTags();
		for (Tag tag : tags) {
			System.out.println(tag.getId() + ": " + tag.getName());
		}

	}

}