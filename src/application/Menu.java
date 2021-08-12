package application;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.JournalDAO;
import dao.JournalTagsDAO;
import dao.TagDAO;
//import dao.RemindersDAO;
import dao.UserDAO;

public class Menu {

	private JournalDAO journalDao = new JournalDAO();
	private JournalTagsDAO jouralTagsDAO = new JournalTagsDAO();
	private TagDAO tagDAO = new TagDAO();
	//private RemindersDAO remindersDAO = new RemindersDAO();
	private UserDAO userDAO = new UserDAO();
	private Scanner scanner = new Scanner(System.in);
	
private List<String> loginOptions = Arrays.asList(
		"Display all Users", 
		"Select a User", 
		"Create a User", 
		"Delete a User"
		);

//
private List<String> selectUserOptions = Arrays.asList(
		"Which user are you?"
		//list all users to be selected 
		);

//			Once user is selected
private List<String> userOptions = Arrays.asList(
		"Make a journal entry",
		"Update email address"
		);

//When "make a journal" is selected
private List<String> journalOptions = Arrays.asList(
		"Create a Journal Entry",  
		"Display all Journal Entries",
		"Update a Journal Entry",
		"Delete a Journal Entry",
		"See all possible Journal Tags"
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


//public void start() {
//		String selection = "";
//			
//			do {
//				
//			}
//}


//
//   /*
//    * Tags Menu
//    * 1.) List Tags
//    * 2.) Delete Tags
//    * 3.) Create Tag
//    * 4.) Update Tag 

//    * User Menu
//    * 1.) List Users
//    *   Select a user
//    *    a.) Bob
//    *    b.) Arlene
//    * 2.) Create new user
//    *  
//    * User selected
//    * Journal Menu for user
//    * 1.) List journal
//    * 2.) Create journal
//    * 
//    * When viewing a journal, you can
//    * delete a journal entry or update fields. 
//    * 
//    */
//	
//	package application;
//
//
//	import java.sql.SQLException;
//	import java.util.Arrays;
//	import java.util.List;
//	import java.util.Scanner;
//
//	import dao.MemberDao;
//	import dao.TeamDao;
//	import entity.Member;
//	import entity.Team;
//
//	public class Menu {
//		
//		private TeamDao teamDao = new TeamDao();
//		private MemberDao memberDao = new MemberDao();
//		private Scanner scanner = new Scanner(System.in);
//		private List<String> options = Arrays.asList(
//				"Display Teams", 
//				"Display a Team", 
//				"Create a Team", 
//				"Delete a Team", 
//				"Create Team Member", 
//				"Delete Team Member"
//				);
//		
//		public void start() {
//			String selection = "";
//			
//			do {
//				printMenu();
//				selection = scanner.nextLine();
//				try {
//				if (selection.equals("1")) {
//					displayTeams();
//				} else if (selection.equals("2")) {
//					displayTeam();
//				} else if (selection.equals("3")) {
//					createTeam();
//				} else if (selection.equals("4")) {
//					deleteTeam();
//				} else if (selection.equals("5")) {
//					createMember();
//				} else if (selection.equals("6")) {
//					deleteMember();
//				} 
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//				System.out.println("Please press enter to continue");
//				scanner.nextLine();
//			} while (!selection.equals("-1"));
//		}
//
//		private void deleteMember() throws SQLException {
//			System.out.print("Enter member id to delete:");
//			int id = Integer.parseInt(scanner.nextLine());
//			memberDao.deleteMemberById(id);
//			
//		}
//
//		private void createMember() throws SQLException {
//			System.out.print("Enter first name of new member:");
//			String firstName = scanner.nextLine();
//			System.out.print("Enter last name of new member:");
//			String lastName = scanner.nextLine();
//			System.out.print("Enter team id of new member:");
//			int teamId = Integer.parseInt(scanner.nextLine());
//			memberDao.createNewMember(firstName, lastName, teamId);
//		}
//
//		private void deleteTeam() throws SQLException {
//			System.out.print("Enter team id to delete");
//			int id = Integer.parseInt(scanner.nextLine()); // add id verification :) 
//			teamDao.deleteTeamById(id);
//			
//			
//		}
//
//		private void createTeam() throws SQLException {
//			System.out.print("Enter new team name:");
//			String teamName = scanner.nextLine();
//			teamDao.createNewTeam(teamName);
//			
//		}
//
//		private void displayTeam() throws SQLException {
//			System.out.print("Enter team id: ");
//			int id = Integer.parseInt(scanner.nextLine());
//			Team team = teamDao.getTeambyId(id);
//			System.out.println(team.getTeamId() + ": " + team.getName());
//			for (Member member : team.getMembers()) {
//				System.out.println("\tMemberId: " + member.getMemberId() + " - Name: " + member.getFirstName()
//				+" " + member.getLastName());
//			}
//			
//		}
//
//		private void displayTeams() throws SQLException {
//			List<Team> teams = teamDao.getTeams();
//			for (Team team :  teams) {
//				System.out.println(team.getTeamId() + ": " + team.getName());
//			}
//			
//		}
//
//		private void printMenu() {
//			System.out.println("Select an Option: \n ------------------------------------");
//			for (int i = 0; i < options.size(); i++) {
//				System.out.println(i + 1 + ") " + options.get(i));
//			}
//			
//		}
//
//	}

}
