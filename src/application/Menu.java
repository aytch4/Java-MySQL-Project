package application;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.JournalDao;
import dao.RemindersDAO;
import dao.UserDAO;

public class Menu {

	private JournalDao journalDao = new JournalDao();
	private RemindersDAO remindersDAO = new RemindersDAO();
	private UserDAO userDAO = new UserDAO();
	private Scanner scanner = new Scanner(System.in);
//	private List<String> options = Arrays.asList("");
	
}
