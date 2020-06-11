import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class SubredditFacts {
	// change this variable below in case you are looking at another data set.
	private static String commentSource = "../2015.json";

	public static void main(String[] args) {
		HashMap<String, double[]> test = SubredditFacts.parse();
		for (String i : test.keySet()) {
			System.out.print(i + " ");
			for (double num : test.get(i)) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}

	public static HashMap<String, double[]> parse() {
		try {
			// Read the file based on commentSource
			File redditComments = new File(commentSource);
			Scanner reader = new Scanner(redditComments);

			// make an arraylist to store the comments
			HashMap<String, double[]> complicatedLinkList = new HashMap<String, double[]>();

			// intalize json convertor to convert json objects into java objects
			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting();
			Gson gson = builder.create();

			// read through all the comments until there is no more lines.
			// use count to make use of a small data set for testing purposes
			while (reader.hasNextLine()) { // very slow for 1.5 million comments
				String data = reader.nextLine(); // read the data on current line
				RedditComment comment = gson.fromJson(data, RedditComment.class); // convert json into
				// java object
				// Pair newData = new Pair(comment.getSubreddit());
				String temp = comment.getSubreddit(); // break down comment into words
				if (complicatedLinkList.containsKey(temp)) {
					complicatedLinkList.get(temp)[0]++;
					complicatedLinkList.get(temp)[1] += (double)comment.getScore();
					complicatedLinkList.get(temp)[2] = complicatedLinkList.get(temp)[1] / complicatedLinkList.get(temp)[0];
					complicatedLinkList.get(temp)[2] = (Math.round(complicatedLinkList.get(temp)[2] * 100)) / 100;
				} else {
					double[] newList = new double[3];
					newList[0] = 1.0;
					newList[1] = (double) comment.getScore();
					newList[2] = (double) newList[1] / newList[0];
					newList[2] = ( Math.round(newList[2] * 100) ) / 100;
					complicatedLinkList.put(temp, newList);
				}
			}

<<<<<<< HEAD
                count++;
            }
=======
			return complicatedLinkList; // return the comments
		} catch (FileNotFoundException e) { // in case the file isn't found.
			System.out.println("File Not Found");
			e.printStackTrace();
			return null; // we need to return anything
		}
>>>>>>> 925a1d623e8aee861179a3d4a99bd3ebc1b96f2d

	}
}