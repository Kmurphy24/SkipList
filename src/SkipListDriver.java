import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SkipListDriver {

	public static void main(String[] args) {
		String line;
		Book element;
		SkipList sList = new SkipList(1);
		int unsuccessfulComparisons = 0;
		int successfulComparisons = 0;
		int unsuccessfulSearches = 0;
		int successfulSearches = 0;
		IntObject searchCount = new IntObject(0);
		int count = 0;
		
		try {
			Scanner inFile = new Scanner(new FileInputStream("Books.txt"));
			Scanner keyboard = new Scanner(System.in);

			Scanner testFile = new Scanner(new FileInputStream("TestISBN.txt"));

			PrintWriter out = new PrintWriter(new FileWriter("BSTOutput.txt"));

			while (inFile.hasNextLine()) {
				line = inFile.nextLine();
				Book aBook = new Book(line.substring(0, 10), line.substring(15,
						55).trim(), line.substring(55, 95).trim(),
						Integer.parseInt(line.substring(95, 99)),
						line.substring(104, line.length()));
						sList.add(aBook);
			}
			inFile.close();
			System.out.println("Size is " + sList.getSize());
		}catch (IOException e) {
			System.out.println("Input file or the test file cannot be opened");
			System.exit(0);
	}

	}

}
