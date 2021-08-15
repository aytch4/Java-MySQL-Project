package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.JournalDAO;
import dao.JournalTagsDAO;
import dao.TagDAO;
import dao.UserDAO;
import entity.Journal;
import entity.Tag;
import entity.User;

public class Menu {

	private JournalDAO journalDao = new JournalDAO();
	private JournalTagsDAO journalTagsDAO = new JournalTagsDAO();
	private TagDAO tagDAO = new TagDAO();
	
	private UserDAO userDAO = new UserDAO();
	private Scanner scanner = new Scanner(System.in);

	private List<String> loginOptions = Arrays.asList(
			"Display all Users", 
			"Select a User", 
			"Create a User", 
			"Delete a User"
	);



//When "make a journal" is selected
	private List<String> userOptions = Arrays.asList("Create a Journal Entry", "Display all Journal Entries",
			"Update a Journal Entry", "Delete a Journal Entry", "View journal tag options"
//			,"Update email address"
	);

//When they ask to update a journal entry 
	private List<String> journalUpdateOptions = Arrays.asList("Update a Journal Entry by Title",
			"Update a Journal Entry by Content");

//When "See all possible Journal Tags" is selected 

	private List<String> tagOptions = Arrays.asList("Display all tags",
			// "Search for tag via name",
			"Search for tag via tag id", "Create new tag", "Update tag", "Delete tag");

	public void start() {
		String selection = "";

		do {
			printLogInMenu();
			selection = scanner.nextLine();

			try {
				if (selection.equals("1")) {
					displayAllUsers();
				} else if (selection.equals("2")) {
					selectUser();
				} else if (selection.equals("3")) {
					createUser();
				} else if (selection.equals("4")) {
					deleteUser();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			;
			System.out.println("Press enter to continue.");
			scanner.nextLine();
		} while (!selection.equals("-1"));
		System.out.println("Thanks for stopping by!");

	}

	private void printLogInMenu() {
		System.out.println("Select an Option: \n ------------------------------------");
		for (int i = 0; i < loginOptions.size(); i++) {
			System.out.println(i + 1 + ") " + loginOptions.get(i));
		}
	}

	private void displayAllUsers() throws SQLException {
		List<User> users = userDAO.getUsers();
		for (User user : users) {
			System.out.println(user.getUserId() + ": " + user.getFirstname() + " " + user.getLastname());
		}
	}

	private void selectUser() throws SQLException {
		displayAllUsers();
		System.out.println("Enter your user ID");
		int id = Integer.parseInt(scanner.nextLine());
		User firstname = userDAO.getUserById(id);
		System.out.println("------------------------------------");
		System.out.println(" Welcome " + firstname.getFirstname());
		System.out.println("------------------------------------");

		userOptionsMenu();
	}

	private void userOptionsMenu() {
		String selection = "";
		String subselection = "";

		do {
			printUserOptionsMenu();
			scanner = new Scanner(System.in);
			selection = scanner.nextLine();

			try {
				if (selection.equals("1")) {
					System.out.println("What would you like to name your new journal entry \n");
					String entryName = scanner.nextLine();
					System.out.println("Enter journal content: ");
					String content = scanner.nextLine();

					System.out.println("Please confirm your identity by entering your user id.");
					displayAllUsers();
					int userId = Integer.parseInt(scanner.nextLine());
					System.out.println("Add a tag id for this post: ");
					int journalTagId = Integer.parseInt(scanner.nextLine());
					journalDao.createNewJournal(entryName, content, journalTagId, userId);



					userOptionsMenu();

				} else if (selection.equals("2")) {
					System.out.println("Journal entries \n");
					displayAllEntries();

					System.out.println("Press enter to continue.");
					scanner.nextLine();
					userOptionsMenu();

				} else if (selection.equals("3")) {
//					do {
//						printJournalUpdateOptionsMenu();
//						scanner = new Scanner(System.in);
//						subselection = scanner.nextLine();
//
//						if (selection.equals("1")) {
					System.out.println("Which journal entry would you like to update?");
					int id = Integer.parseInt(scanner.nextLine());
					// journalDao.updateJournalById(id);
//					System.out.println("Enter the new title: ");
//					String newTitle = scanner.nextLine();
					System.out.println("Enter the new content: ");
					String newContent = scanner.nextLine();
					journalDao.updateJournalById(id, newContent);

//						} else if (subselection.equals("2")) {
//							System.out.println("Which journal entry would you like to update?");
//							String content = scanner.nextLine();
//							journalDao.updateJournalByContent(content);

//					} while (!subselection.equals("-1"));
//					System.out.println("Invalid Input");

				} else if (selection.equals("4")) {
					displayAllEntries();

					System.out.println("Enter the id of the entry you would like to delete: ");
					int id = Integer.parseInt(scanner.nextLine());
					journalDao.deleteJournalById(id);
//				} else {
//					System.out.println("Invalid input");
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			while (!selection.equals("-1"))
//				;
//
//			System.out.println("Thanks for stopping by!");

//			try {
//				if (selection.equals("1")) {
//					tagDAO.displayAllTags();
//				} else if (selection.equals("3")) {
//					do {
//						printJournalUpdateOptionsMenu();
//						scanner = new Scanner(System.in);
//						subselection = scanner.nextLine();
//						System.out.println("What would you like to name your new journal entry \n");
//						String entryName = scanner.nextLine();
//						System.out.println("Type the new content: ");
//						String newContent = scanner.nextLine();
//						System.out.println("Please confirm your identity by entering your user id.");
//						displayAllUsers();
//						int user = Integer.parseInt(scanner.nextLine());
//						journalDao.createNewJournal(entryName, newContent, user);
//					}
//						if (subselection.equals("1")) {
//							System.out.println("Which journal entry would you like to update?");
//							displayAllEntries()
//							String title = scanner.nextLine();
//							journalDao.updateJournalById(title);
//						} else if (subselection.equals("2")) {
//							System.out.println("Enter the title of the journal entry would you like to update?");
//							String title = scanner.nextLine();
//							journalDao.updateJournalByContent(id);
//						} else if (!(subselection.equals("-1"))) {
//							System.out.println("Invalid Option");
////merge issue?
//						
//
//					} while (!(subselection.equals("-1")));

				} else if (selection.equals("4")) {
					System.out.println("Which entry would you like to delete? \n");
					int idToDelete = Integer.parseInt(scanner.nextLine());
					journalDao.deleteJournalById(idToDelete);
					printTagOptionsMenu();
				} else if (selection.equals("5")) {
					System.out.println("Journal Tags \n");
					do {
						printTagOptionsMenu();
						scanner = new Scanner(System.in);
						subselection = scanner.nextLine();

						try {
							if (subselection.equals("1")) {
								tagDAO.displayAllTags();

//							} else if (subselection.equals("2")) {
//								System.out.println("Enter the name of the tag you would like to view: ");
//								String tagName = scanner.nextLine();
//								tagDAO.getTagByName(tagName);
							} else if (subselection.equals("2")) {
								System.out.println("Enter the id of the tag you would like to view: ");
								int tagId = Integer.parseInt(scanner.nextLine());
								Tag name = tagDAO.getTagById(tagId);
								System.out.println(name.getName());
//								System.out.println(tagId.equals());
							} else if (subselection.equals("3")) {
								System.out.println("Enter new tag name: ");
								String newTag = scanner.nextLine();
								tagDAO.createNewTag(newTag);
							} else if (subselection.equals("4")) {
								System.out.println("Enter the id of the tag you would like to update: ");
								int idOfTagToUpdate = Integer.parseInt(scanner.nextLine());
								System.out.println("Enter the new tag: ");
								String updatedTag = scanner.nextLine();
								tagDAO.updateTagById(idOfTagToUpdate, updatedTag);
							} else if (subselection.equals("5")) {
								System.out.println("Enter the id of the tag you would like to remove: ");
								int idOfTagToRemove = Integer.parseInt(scanner.nextLine());
								tagDAO.deleteTag(idOfTagToRemove);
							} else if (!(subselection.equals("-1"))) {
								System.out.println("Invalid Option");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}

					} while (!(subselection.equals("-1")));
//				} else if (selection.equals("6")) {
//					System.out.println("Enter the id of the user whose email address you would like to update:");
//					int id = Integer.parseInt(scanner.nextLine());
//					System.out.println("Enter the new email address: ");
//					String emailaddress = scanner.nextLine();
//					userDAO.updateUser(id, emailaddress);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} while (!selection.equals("-1"));

	}

//	private void displayAllEntries() throws SQLException {
//		List<Journal> journals = journalDao.getJournals();
//    

//    merge issue?
//      	} while (!(subselection.equals("-1"))):
//					
//					}	
////			} else if (selection.equals("4") ) {
////				System.out.println("Which entry would you like to delete? \n");
////				int idToDelete = Integer.parseInt(scanner.nextLine());
////				journalDao.deleteJournalById(idToDelete);
////			} else if (selection.equals("5") ) {
////				System.out.println("Journal Tags \n");
////				
////				journalTagsDAO.getJournalTagbyId();
////			} else if (selection.equals("6") ) {
////				System.out.println("Enter the new email address:");
////				String emailaddress = scanner.nextLine();
////				userDAO.updateUser(id, emailaddress);
////			
////		} while (!(selection.equals("-1")));
//	}

	private void displayAllEntries() throws SQLException {
		List<Journal> journals = journalDao.getJournals();
//end
		for (Journal journal : journals) {
			System.out.println(
					journal.getId() + ": " + journal.getDate() + " " + journal.getTitle() + "\n\t" + journal.getContent() + "\n\t");
		}
	}

	private void createUser() throws SQLException {
		System.out.println("Enter your first name: ");
		String firstname = scanner.nextLine();
		System.out.println("Enter your last name: ");
		String lastname = scanner.nextLine();
		System.out.println("Enter your email address: ");
		String emailaddress = scanner.nextLine();
		userDAO.createNewUser(firstname, lastname, emailaddress);
	}

	private void deleteUser() throws SQLException {
		System.out.println("Enter user ID for the account you want to remove: ");
		int id = Integer.parseInt(scanner.nextLine());
		userDAO.deleteUser(id);
		System.out.println("We're gonna miss you. Stay safe out there");
	}// merge issue?

	private void printUserOptionsMenu() {
		System.out.println("Select an Option:  \n ------------------------------------");
		for (int i = 0; i < userOptions.size(); i++) {
			System.out.println(i + 1 + ") " + userOptions.get(i));
		}
	}

//	private void printjournalOptionsMenu() {
//		System.out.println("Select an Option:  \n ------------------------------------");
//		for (int i = 0; i < userOptions.size(); i++) {
//			System.out.println(i + 1 + ") " + userOptions.get(i));
//		}
//	}
//		
//	private void printJournalUpdateOptionsMenu() {
//		System.out.println("Select an Option:  \n ------------------------------------");
//		for (int i = 0; i < journalUpdateOptions.size(); i++) {
//			System.out.println(i + 1 + ") " + journalUpdateOptions.get(i));
//		}
//
//	}

//	private void printUserOptionsMenu() {
//		System.out.println("Select an Option:  \n ------------------------------------");
//		for (int i = 0; i < userOptions.size(); i++) {
//			System.out.println(i + 1 + ") " + journalOptionsMenu.get(i));
//		}
//	}

//	private void printJournalUpdateOptionsMenu() {
//		System.out.println("Select an Option:  \n ------------------------------------");
//		for (int i = 0; i < userOptions.size(); i++) {
//			System.out.println(i + 1 + ") " + userOptions.get(i));
//
//		}
//	}

	private void printTagOptionsMenu() {
		System.out.println("Select an Option:  \n ------------------------------------");
		for (int i = 0; i < tagOptions.size(); i++) {
			System.out.println(i + 1 + ") " + tagOptions.get(i));
		}
	}

	// }

}
