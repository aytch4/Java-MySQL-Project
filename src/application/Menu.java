package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.JournalDAO;
import dao.JournalTagsDAO;
import dao.TagDAO;
//import dao.RemindersDAO;
import dao.UserDAO;
import entity.Journal;
import entity.User;

public class Menu {

	//private JournalDAO journalDao = new JournalDAO();
	//private JournalTagsDAO jouralTagsDAO = new JournalTagsDAO();
	private TagDAO tagDAO = new TagDAO();
	//private RemindersDAO remindersDAO = new RemindersDAO();
	private UserDAO userDAO = new UserDAO();
	private Scanner scanner = new Scanner(System.in);
	
private List<String> loginOptions = Arrays.asList(
		"Display all Users", //works w/o a problem
		"Select a User", 
		"Create a User", //works 
		"Delete a User"
		);

//
//private List<String> selectUserOptions = Arrays.asList(
//		
//		userDao.getUsers;
		
		//list all users to be selected 
		 //maybe part of the select options 

//			Once user is selected
//private List<String> userOptions = Arrays.asList(
//		"Make a journal entry",
//		"Update email address"
//		);

//When "make a journal" is selected
private List<String> userOptions = Arrays.asList(
		"Create a Journal Entry",  
		"Display all Journal Entries",
		"Update a Journal Entry",
		"Delete a Journal Entry",
		"See all possible Journal Tags",
		"Update email address"
		);

//When they ask to update a journal entry 
private List<String> journalUpdateOptions = Arrays.asList(
		"Update a Journal Entry by Title",  
		"Update a Journal Entry by Content"
		);

//When "See all possible Journal Tags" is selected 

private List<String> tagOptions = Arrays.asList(
		"Display all tags",
		"Search for tag via name",
		"Search for tag via tag id",
		"Create new tag",
		"Update tag",
		"Delete tag"
		);


public void start() {
		String selection = "";
			
			do {
				printLogInMenu();
				selection = scanner.nextLine();
				
				try {
					if (selection.equals("1")) {
						displayAllUsers();
					} else if (selection.equals("2")) {
						//selectUser();
					} else if (selection.equals("3")) {
						createUser();
					} else if (selection.equals("4")) {
						deleteUser();
					}
					
				} catch (SQLException e){
					e.printStackTrace();
				};
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
/*
private void selectUser() throws SQLException {
	displayAllUsers();
	System.out.println("Enter your user ID");
	int id = Integer.parseInt(scanner.nextLine());
	User firstname = userDAO.getUserById(id);
	System.out.println("------------------------------------");
	System.out.println(" Welcome " + firstname.getFirstname());
	System.out.println("------------------------------------");
	
	String selection = "";
	String subselection = "";
	
	do {
		printUserOptionsMenu();
		scanner = new Scanner(System.in);
		subselection = scanner.nextLine();
		
		try {
			if (selection.equals("1") ) {
				System.out.println("What would you like to name your new journal entry \n");
				String entryName = scanner.nextLine();
				journalDao.createNewJournal(entryName);			
			} else if (selection.equals("2") ) {
				System.out.println("Journal entries \n");
				displayAllEntries();
			} else if (selection.equals("3") ) {
				 //necessary? how are they going to find it?  
				do {
					printJournalUpdateOptionsMenu();
					scanner = new Scanner(System.in);
					subselection = scanner.nextLine();
					
					try {
						if (subselection.equals("1") ) {
							System.out.println("Which journal entry would you like to update?");
							String title = scanner.nextLine();
							journalDao.updateJournalByTitle(title, id);
						} else if (subselection.equals("2") ) {
							System.out.println("Which journal entry would you like to update?");
							String content = scanner.nextLine();
							journalDao.updateJournalByContent(content, id);
						} else if (!(subselection.equals("-1"))) {
							System.out.println("Invalid Option");
						}
					
					} while (!(subselection.equals("-1")));
					
				}
			} else if (selection.equals("4") ) {
				System.out.println("Which entry would you like to delete? \n");
				int idToDelete = Integer.parseInt(scanner.nextLine());
				journalDao.deleteJournalById(idToDelete);
			} else if (selection.equals("5") ) {
				System.out.println("Journal Tags \n");
				
				journalTagsDAO.getJournalTags();
			} else if (selection.equals("6") ) {
				System.out.println("Enter the new email address:");
				String emailaddress = scanner.nextLine();
				userDAO.updateUser(id, emailaddress);
			
		} while (!(selection.equals("-1")));
	}

private void displayAllEntries() throws SQLException {
	List<Journal> journals = journalDao.getJournals();
		for (Journal journal : journals) {
			System.out.println(journal.getId() + ": " + journal.getDate() + " " + journal.getTitle() + "." + journal.getContent());
			} 
		}
*/
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
	System.out.println("Enter user ID for the account you want to remove :");
	int id = Integer.parseInt(scanner.nextLine());
	userDAO.deleteUser(id);
	System.out.println("We're gonna miss you, stay safe out there");
	}

//private void printUserOptionsMenu() {
//	System.out.println("Select an Option:  \n ------------------------------------");
//	for (int i = 0; i < userOptions.size(); i++) {
//		System.out.println(i + 1 + ") " + userOptions.get(i));
//	}
//}
	
	
//private void printjournalOptionsMenu() {
//	System.out.println("Select an Option:  \n ------------------------------------");
//	for (int i = 0; i < userOptions.size(); i++) {
//		System.out.println(i + 1 + ") " + userOptions.get(i));
//	}
//		
/* private void printJournalUpdateOptionsMenu() {
	System.out.println("Select an Option:  \n ------------------------------------");
	for (int i = 0; i < userOptions.size(); i++) {
		System.out.println(i + 1 + ") " + userOptions.get(i));
	}
}

private void printTagOptionsMenu() {
	System.out.println("Select an Option:  \n ------------------------------------");
	for (int i = 0; i < userOptions.size(); i++) {
		System.out.println(i + 1 + ") " + userOptions.get(i));
	}
		}	*/

}



