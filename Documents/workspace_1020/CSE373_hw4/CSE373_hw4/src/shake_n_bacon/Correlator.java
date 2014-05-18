package shake_n_bacon;

import java.io.IOException;

import providedCode.*;

// Patrick Harper-Joles / Arjun Bhalla
// hatrik42 / ab58
// 1440683 / 1363119
// hatrik42@uw.edu / arjunbhalla675@gmail.com

// This class accepts input files and compares their similarity by tracking
// the words in each file and the number of times they were used.  Then the 
// counts of each word are examined by their percentage of appearance within
// each file and compared against one another.

public class Correlator {
   static double variance;
   private static final int BASE = 1000;
   static int firstTotal;
   static int secondTotal;
   static double totalVar;
   
   // Main method of class.
	public static void main(String[] args) {      
      if (args.length != 2) {
			usage();
		}

		String firstArg = args[0].toLowerCase();
		DataCounter counter = null;
		if (firstArg.equals("-s")) {
			counter = new HashTable_SC(new StringComparator(),
					new StringHasher());
		} else if (firstArg.equals("-o")) {
			counter = new HashTable_OA(new StringComparator(),
					new StringHasher());
		} else {
			usage();
		}

		countWords(args[1], counter);
		DataCount[] counts1 = getCountsArray(counter);
		insertionSort(counts1, new DataCountStringComparator());
      firstTotal = totalWords(counts1);
      
      countWords(args[1], counter);
		DataCount[] counts2 = getCountsArray(counter);
		insertionSort(counts2, new DataCountStringComparator());
      secondTotal = totalWords(counts2);
      
      variance = comparison(counts1, counts2, firstTotal, secondTotal);;
      
		System.out.println(variance);
	}
   
   // Returns a DataCount array of the words and counts of words.
 	private static DataCount[] getCountsArray(DataCounter counter) {
		DataCount[] count = new DataCount[BASE];
      SimpleIterator itr = counter.getIterator();
      int i = 0;
      while (itr.hasNext()) {
         count[i] = itr.next();
         i++; 
      }
      return count;
	}
   
   // Reads input files.
   private static void countWords(String file, DataCounter counter) {
		try {
			FileWordReader reader = new FileWordReader(file);
			String word = reader.nextWord();
			while (word != null) {
				counter.incCount(word);
				word = reader.nextWord();
			}
		} catch (IOException e) {
			System.err.println("Error processing " + file + " " + e);
			System.exit(1);
		}
	}
   
   // Sorts the input files by number of word appearance.
   private static <E> void insertionSort(E[] array, Comparator<E> comparator) {
		for (int i = 1; i < array.length; i++) {
			E x = array[i];
			int j;
			for (j = i - 1; j >= 0; j--) {
				if (comparator.compare(x, array[j]) >= 0) {
					break;
				}
				array[j + 1] = array[j];
			}
			array[j + 1] = x;
		}
	}
   
   // Prints instructions about how to use program to user.
	private static void usage() {
		System.err
				.println("Usage: [-s | -o] <filename of document to analyze>");
		System.err.println("-s for hashtable with separate chaining");
		System.err.println("-o for hashtable with open addressing");
		System.exit(1);
	}
   
   // Returns the total number of words in the input file.
   private static int totalWords (DataCount[] counts) {
      int total = 0;
      for (int i = 0; i < counts.length; i++) {
         total = total + counts[i].count;
      }
      return total;
   }
   
   // Compares the two input files to determine the variance from one another
   // based off of difference.
   private static double comparison (DataCount[] counts1, DataCount[] counts2, 
      int total1, int total2) {
         int limit1 = counts1.length;
         int limit2 = counts2.length;
         double totalVar = 0;
      
         for (int i = 0; i < limit1; i++) {
         
            int cur1 = counts1[i].count;
            double var1 = (double) cur1 / total1;
            if (var1 < 0.01 && var1 > 0.0001) {
            
               for (int j = 0; j < limit2; j++) {
               
                  if (counts2[j].data == counts1[i].data) {
                  
                     int cur2 = counts2[i].count;
                     double var2 = (double) cur2 / total2;
                     if (var2 < 0.01 && var2 > 0.0001) {
                        double compVar = Math.abs(var1 - var2);
                        totalVar = totalVar + compVar;
                     }
                  }
               }
            }         
         }
         return totalVar;
   }
}
