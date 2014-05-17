package shake_n_bacon;

import java.io.IOException;

import providedCode.*;

/**
 * Patrick Harper-Joles / Arjun Bhalla
 * hatrik42 / ab58
 * 1440683 / 1363119
 * hatrik42@uw.edu / arjunbhalla675@gmail.com
 * 
 *        TODO: REPLACE this comment with your own as appropriate.
 * 
 *        This should be done using a *SINGLE* iterator. This means only 1
 *        iterator being used in Correlator.java, *NOT* 1 iterator per
 *        DataCounter (You should call dataCounter.getIterator() just once in
 *        Correlator.java). Hint: Take advantage of DataCounter's method.
 * 
 *        Although you can share argument processing code with WordCount, it
 *        will be easier to copy & paste it from WordCount and modify it here -
 *        it is up to you. Since WordCount does not have states, making private
 *        method public to share with Correlator is OK. In general, you are not
 *        forbidden to make private method public, just make sure it does not
 *        violate style guidelines.
 * 
 *        Make sure WordCount and Correlator do not print anything other than
 *        what they are supposed to print (e.g. do not print timing info, input
 *        size). To avoid this, copy these files into package writeupExperiment
 *        and change it there as needed for your write-up experiments.
 */
public class Correlator {
   double variance = 0.0;
   int firstTotal = 0;
   int secondTotal = 0;
   
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
      
      comparison(counts1, counts2, firstTotal, secondTotal);
      
      variance = totalVar;
      
		System.out.println(variance);
	}
   
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

	/*
	 * Print error message and exit
	 */
	private static void usage() {
		System.err
				.println("Usage: [-s | -o] <filename of document to analyze>");
		System.err.println("-s for hashtable with separate chaining");
		System.err.println("-o for hashtable with open addressing");
		System.exit(1);
	}
   
   //
   private static int totalWords (DataCount[] counts) {
      int total = 0;
      for (int i = 0; i < counts.length; i++) {
         total = total + counts[i].count;
      }
      return total;
   }
   
   //
   private static double comparison (DataCount[] counts1, DataCount[] counts2, int total1, int total2) {
      int limit1 = counts1.length;
      int limit2 = counts2.length;
      double totalVar = 0;
      
      for (int i = 0; i < limit1; i++) {
         
         int cur1 = counts1[i].count;
         double var1 = (double) cur1 / total1;
         if (var1 < 0.01 && var1 > 0.0001) {
            
            for (int j = 0; j < limit2; j++) {
               
               if (counts2[j].data == counts1.data) {
                  
                  int cur2 = counts2[i].count;
                  double var2 = (double) cur2 / total2;
                  if (var2 < 0.01 && var2 > 0.0001) {
                     compVar = Math.abs(var1 - var2);
                     totalvar = totalVar + compVar;
                  }
               }
            }
         }         
      }
   }
}
