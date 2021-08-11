package application;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.JournalDAO;
import dao.RemindersDAO;
import dao.UserDAO;

public class Menu {

	private JournalDAO journalDao = new JournalDAO();
	private RemindersDAO remindersDAO = new RemindersDAO();
	private UserDAO userDAO = new UserDAO();
	private Scanner scanner = new Scanner(System.in);
//	private List<String> options = Arrays.asList("");
	

   /*
    * Tags Menu
    * 1.) List Tags
    * 2.) Delete Tags
    * 3.) Create Tag
    * 
    * User Menu
    * 1.) List Users
    *   Select a user
    *    a.) Bob
    *    b.) Arlene
    * 2.) Create new user
    *  
    * User selected
    * Journal Menu for user
    * 1.) List journal
    * 2.) Create journal
    * 
    * When viewing a journal, you can
    * delete a journal entry or update fields. 
    * 
    */
}
