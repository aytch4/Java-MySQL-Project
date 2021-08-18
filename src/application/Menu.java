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
import entity.JournalTags;
import entity.Tag;
import entity.User;

public class Menu {

	private JournalDAO journalDao = new JournalDAO();
	private JournalTagsDAO journalTagsDAO = new JournalTagsDAO();
	private TagDAO tagDAO = new TagDAO();

	private UserDAO userDAO = new UserDAO();
	private Scanner scanner = new Scanner(System.in);

	private List<String> loginOptions = Arrays.asList("Display all Users", "Select a User", "Create a User",
			"Delete a User");

//Options for user
	private List<String> userOptions = Arrays.asList("Create a Journal Entry", "Display all Journal Entries",
			"Update a Journal Entry", "Delete a Journal Entry", "View journal tag options");

	private List<String> tagOptions = Arrays.asList("Display all tags", "Search for Journal Entries via tag",
			"Create new tag", "Update tag", "Delete tag");

//starts and login menu
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
				System.out.println("Invalid input.  Enter a valid user id.");
			}
			;
//			System.out.println("Press enter to continue.");
//			scanner.nextLine();
		} while (!selection.equals("-1"));
		// System.out.println("Thanks for stopping by!");

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

//prints user options menu and completes actions
	private void userOptionsMenu() {
		String selection = "";
		String subselection = "";

		do {
			printUserOptionsMenu();
			scanner = new Scanner(System.in);
			selection = scanner.nextLine();

			try {
//Option 1 -create an entry
				if (selection.equals("1")) {
					System.out.println("What would you like to name your new journal entry \n");
					String entryName = scanner.nextLine();
					System.out.println("Enter journal content: ");
					String content = scanner.nextLine();
//we didn't know how to have it automatically choose the user you are logged in as so we made this work around
					System.out.println("Please confirm your identity by entering your user id.");
					displayAllUsers();
					int userId = Integer.parseInt(scanner.nextLine());
					tagDAO.displayAllTags();
					System.out.println("Add a tag id for this post: ");
					int journalTagId = Integer.parseInt(scanner.nextLine());
					journalDao.createNewJournal(entryName, content, journalTagId, userId);

					userOptionsMenu();

//Option 2 - Display all entries
				} else if (selection.equals("2")) {
					System.out.println("Journal entries \n");
					displayAllEntries();

					System.out.println("Press enter to continue.");
					scanner.nextLine();
					userOptionsMenu();

//Option 3 - Update journal entry
				} else if (selection.equals("3")) {
//					
					displayAllEntries();
					System.out.println("Which journal entry would you like to update?");
					int id = Integer.parseInt(scanner.nextLine());

					System.out.println("Enter the new content: ");
					String newContent = scanner.nextLine();
					journalDao.updateJournalById(id, newContent);

//Option 4 - Delete an entry
				} else if (selection.equals("4")) {
					displayAllEntries();

					System.out.println("Enter the id of the entry you would like to delete: ");
					int id = Integer.parseInt(scanner.nextLine());
					journalDao.deleteJournalById(id);

//Option 5 - Tags menu
				} else if (selection.equals("5")) {
					System.out.println("Journal Tags \n");
					do {
						printTagOptionsMenu();
						scanner = new Scanner(System.in);
						subselection = scanner.nextLine();

						try {
							// Option 1 - display all tags
							if (subselection.equals("1")) {
								tagDAO.displayAllTags();

								// Option 2 - Search entries by tag
							} else if (subselection.equals("2")) {
								tagDAO.displayAllTags();
								System.out.println(
										"Enter the id of the tag which you would like to view the journal entries from: ");
								int tagId = Integer.parseInt(scanner.nextLine());

								displayAllJournalsByTag(journalDao.getJournalEntriesByJournalTag(tagId));
								// Option 3 - Create new tag
							} else if (subselection.equals("3")) {
								System.out.println("Enter new tag name: ");
								String newTag = scanner.nextLine();
								tagDAO.createNewTag(newTag);

								// Option 4 - Update/change tag
							} else if (subselection.equals("4")) {
								System.out.println("Enter the id of the tag you would like to update: ");
								int idOfTagToUpdate = Integer.parseInt(scanner.nextLine());
								System.out.println("Enter the new tag: ");
								String updatedTag = scanner.nextLine();
								tagDAO.updateTagById(idOfTagToUpdate, updatedTag);

								// Option 5 - Remove tag
							} else if (subselection.equals("5")) {
								System.out.println("Enter the id of the tag you would like to remove: ");
								int idOfTagToRemove = Integer.parseInt(scanner.nextLine());
								tagDAO.deleteTag(idOfTagToRemove);

							}
						} catch (SQLException e) {
							System.out.println("Invalid input.  Enter a valid selection.");
						}

					} while (!(subselection.equals("-1")));
				}
			} catch (SQLException e) {
				System.out.println("Invalid input.  Enter a valid selection.");
			}
		} while (!selection.equals("-1"));

	}

//displays journal entries by tag, tidied up display
	private void displayAllJournalsByTag(List<Journal> journals) {
		for (Journal journal : journals) {
			System.out.println("ID: " + journal.getId() + " - " + journal.getDate() + " - Title: " + journal.getTitle()
					+ "\n\t Content: " + journal.getContent() + "\t");
		}
	}

//Fixed so it displays only 1 users entries, not all, added display of tags too, tidied up display
	private void displayAllEntries() throws SQLException {
		displayAllUsers();
		System.out.println("Please enter your id to confirm your identity: ");
		int user = Integer.parseInt(scanner.nextLine());
		List<Journal> journals = journalDao.getJournalsByUser(user);

		for (Journal journal : journals) {
			System.out.println("ID: " + journal.getId() + " - " + journal.getDate() + " - Title: " + journal.getTitle()
					+ "\n\t Content: " + journal.getContent() + "\t");
			JournalTags journalTag = journalTagsDAO.getJournalTagByJournalId(journal.getId());
			Tag tag = tagDAO.getTagById(journalTag.getTagId());
			System.out.println("\t TAG: " + tag.getId() + ": " + tag.getName() + "\n");
		}
	}

//Creates and welcomes new user
	private void createUser() throws SQLException {
		System.out.println("Enter your first name: ");
		String firstname = scanner.nextLine();
		System.out.println("Enter your last name: ");
		String lastname = scanner.nextLine();
		System.out.println("Enter your email address: ");
		String emailaddress = scanner.nextLine();
		userDAO.createNewUser(firstname, lastname, emailaddress);
		System.out.println("------------------------------------");
		System.out.println(" Welcome " + firstname);
		System.out.println("------------------------------------");
		userOptionsMenu();
	}

//YAY!!! Figured out how to delete user with its children in one function
	private void deleteUser() throws SQLException {
		System.out.println(
				"Press enter to confirm that you would like to delete a user and all journal entries.  Press -1 to go back.");
		scanner.nextLine();
		System.out.println("Enter user ID for the account you want to remove: ");
		displayAllUsers();
		int id = Integer.parseInt(scanner.nextLine());
		journalDao.deleteJournalByUser(id);
		userDAO.deleteUser(id);
		// System.out.println("Press enter to continue");
	}

	private void printUserOptionsMenu() {
		System.out.println("Select an Option:  \n ------------------------------------");
		for (int i = 0; i < userOptions.size(); i++) {
			System.out.println(i + 1 + ") " + userOptions.get(i));
		}
	}

	private void printTagOptionsMenu() {
		System.out.println("Select an Option:  \n ------------------------------------");
		for (int i = 0; i < tagOptions.size(); i++) {
			System.out.println(i + 1 + ") " + tagOptions.get(i));
		}
	}
}
