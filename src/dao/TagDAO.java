package dao;

import java.util.ArrayList;
import java.util.List;
import entity.Tag;

public class TagDAO {
	/**
	 * Retrieves a tag based on it's unique id/
	 * 
	 * @param id The unique id of the tag.
	 * @return The tag if found, otherwise null.
	 */
	public Tag get(int id) {
		return (null);
	}

	/**
	 * Retrieves a tag based on it's name.
	 * 
	 * @param id The unique name of the tag.
	 * @return The tag if found, otherwise null.
	 */
	public Tag get(String name) {
		return (null);
	}

	/**
	 * Retrieves all of the available tags.
	 * 
	 * @return The enumeration of tag, if no tags are present then an empty list is
	 *         returned.
	 */
	public List<Tag> all() {
		return (new ArrayList<Tag>());
	}

	/**
	 * Creates a new tag entry.
	 * 
	 * @param tag The new tag
	 * @return The newly create tag if successful, otherwise null.
	 */
	public Tag create(Tag tag) {
		return (null);
	}

	/**
	 * Updates the values of an existing tag.
	 * 
	 * @param id  The id of the existing tag to modify.
	 * @param tag The new tag information.
	 * @return The modified tag information if successful, otherwise null.
	 */
	public Tag update(int id, Tag tag) {
		return (null);
	}

	/**
	 * Deletes an existing tag from the database.
	 * 
	 * @param id The id of the tag to remove.
	 * @return The removed tag if successful, null if otherwise.
	 */
	public Tag delete(int id) {
		return (null);
	}
}
