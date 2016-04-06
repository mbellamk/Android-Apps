import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Assignment No: InClass Assignment1 - Part 1 
 * File Name: PartOne.java 
 * Purpose: Prints the set of repeated user records in ascending order by last name
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan
 */

public class PartOne {

	static Set<User> userSet = new HashSet<User>();
	static List<User> repeatedUsers1 = new ArrayList<User>();

	public static void main(String[] args) {

		loadFileContents("userList1.txt");
		System.out.println("Repeated Users:");
		System.out.println("Total Number Of Repeated Users:"
				+ repeatedUsers1.size());
		printFirstSolution();
	}

	private static void printFirstSolution() {

		Collections.sort(repeatedUsers1, new Comparator<User>() {
			@Override
			public int compare(User user1, User user2) {
				return user1.getLastName().compareTo(user2.getLastName());
			}
		});
		for (User user : repeatedUsers1) {
			System.out.println(user.toString());
		}
	}

	public static void loadFileContents(String fileName) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				PartOne.class.getClassLoader().getResourceAsStream(fileName)));
		String line;
		try {

			while ((line = reader.readLine()) != null) {
				User user = new User(line);
				if (userSet.contains(user)) {
					repeatedUsers1.add(user);
				}
				userSet.add(user);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
