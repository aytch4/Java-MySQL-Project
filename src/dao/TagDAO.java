package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Tag;

public class TagDAO {
	
	private static final String CREATE_NEW_TAG_QUERY = "INSERT INTO tag() VALUES(?)";
	private static final String UPDATE_TAG_BY_ID_QUERY = "UPDATE tag SET name = ? WHERE id = ?";
	private static final String DELETE_TAG_BY_ID_QUERY = "DELETE FROM tag WHERE id = ?";
	private Connection connection;
	
	private final String GET_TAG_QUERY = "SELECT * FROM tag";
	private final String GET_TAG_BY_ID_QUERY = "SELECT * FROM tag WHERE id = ?";
	private final String GET_TAG_BY_NAME_QUERY = "SELECT * FROM tag WHERE name = ?";
	
	/**
	 * Retrieves a tag based on it's unique id/
	 * 
	 * @param id The unique id of the tag.
	 * @return The tag if found, otherwise null.
	 * @throws SQLException 
	 */
	public Tag get(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_TAG_BY_ID_QUERY);
		ps.setInt(1,  id);
		ResultSet rs =ps.executeQuery();
		rs.next();
		return (null);
	}

	/**
	 * Retrieves a tag based on it's name.
	 * 
	 * @param id The unique name of the tag.
	 * @return The tag if found, otherwise null.
	 * @throws SQLException 
	 */
	public Tag get(String name) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_TAG_BY_NAME_QUERY);
		ps.setString(1,  name);
		ResultSet rs =ps.executeQuery();
		rs.next();
		return (null);

	}

	/**
	 * Retrieves all of the available tags.
	 * 
	 * @return The enumeration of tag, if no tags are present then an empty list is
	 *         returned.
	 * @throws SQLException 
	 */
	public List<Tag> all() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_TAG_QUERY).executeQuery();
		List<Tag> Tag = new ArrayList<Tag>();
		
		while (rs.next()) {
			Tag.add(populateTag(rs.getInt(1), rs.getString(2)));
		}
		return (new ArrayList<Tag>());
	}

	private Tag populateTag(int int1, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates a new tag entry.
	 * 
	 * @param tag The new tag
	 * @param name 
	 * @return The newly create tag if successful, otherwise null.
	 * @throws SQLException 
	 */
	public Tag create(Tag tag, String name) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_TAG_QUERY);
		ps.setString(1, name);
		ps.executeUpdate();
		return (null);
	}

	/**
	 * Updates the values of an existing tag.
	 * 
	 * @param id  The id of the existing tag to modify.
	 * @param tag The new tag information.
	 * @param newTag 
	 * @return The modified tag information if successful, otherwise null.
	 * @throws SQLException 
	 */ 
	public Tag update(int id, Tag tag, String newTag) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_TAG_BY_ID_QUERY);
		ps.setInt(2, id);
		ps.setString(1, newTag);
		ps.executeUpdate();
		return (null);
	}

	/**
	 * Deletes an existing tag from the database.
	 * 
	 * @param id The id of the tag to remove.
	 * @return The removed tag if successful, null if otherwise.
	 * @throws SQLException 
	 */
	public Tag delete(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_TAG_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
		return (null);
	}
}
