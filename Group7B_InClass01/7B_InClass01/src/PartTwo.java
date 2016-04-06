
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
 * Assignment No: InClass Assignment1 - Part2 
 * File Name: PartTwo.java
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan
 * 
 * Purpose: . Prints the set of overlapping users, sorted in ascending order by
 * age
 */

public class PartTwo {

	static Set<User> userSet = new HashSet<User>();
	static List<User> repeatedUsers2 = new ArrayList<User>();

	public static void main(String[] args) {

		loadFileContents("userList1.txt");
		compareFileContents("userList2.txt");
		System.out.println("Overlapping Users:");
		System.out.println("Total Number Of Overlapping Users:"
				+ repeatedUsers2.size());
		printSecondSolution();
	}

	private static void printSecondSolution() {

		Collections.sort(repeatedUsers2, new Comparator<User>() {
			@Override
			public int compare(User user1, User user2) {
				return user1.getAge() - user2.getAge();
			}
		});
		for (User user : repeatedUsers2) {
			System.out.println(user.toString());
		}
	}

	private static void compareFileContents(String string) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				PartTwo.class.getClassLoader().getResourceAsStream(string)));
		String line;
		try {

			while ((line = reader.readLine()) != null) {
				User user = new User(line);
				if (userSet.contains(user)) {
					repeatedUsers2.add(user);
				}
			}
		} catch (Exception e) {

		}
	}

	public static void loadFileContents(String fileName) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				PartTwo.class.getClassLoader().getResourceAsStream(fileName)));
		String line;
		try {

			while ((line = reader.readLine()) != null) {
				User user = new User(line);

				userSet.add(user);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
