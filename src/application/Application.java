package application;

import java.util.List;

import dao.TagDAO;
import entity.Tag;

public class Application {
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.start();
		
//		TagDAO tagProvider = new TagDAO();
//		List<Tag> tags = tagProvider.all();
//		for(Tag tag : tags) {
//			System.out.printf("Tag: %d, %s\r\n", tag.getId(), tag.getName());
//		}
	}
}
