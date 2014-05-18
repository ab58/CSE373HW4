package shake_n_bacon;

import providedCode.*;

// Patrick Harper-Joles / Arjun Bhalla
// hatrik42 / ab58
// 1440683 / 1363119
// hatrik42@uw.edu / arjunbhalla675@gmail.com

// This class creates a HashTable using the Linear Probing model.

public class HashTable_OA extends DataCounter {
   private DataCount[] arrayOA;
   private Hasher ha;
   
   // Constructs new HashTable_OA objects.
	public HashTable_OA(Comparator<String> c, Hasher h) {
		arrayOA = new DataCount[17];
      ha = h; 
	}

	// Creates DataCount nodes, hashes the given string value, and inserts
   // the DataCount node into the HashTable at the hashed value index.
	public void incCount(String data) {
		if (getSize() >= arrayOA.length / 2) {
         resize();
      }
      int key = ha.hash(data);
      key = key % arrayOA.length;
      while (arrayOA[key] != null && arrayOA[key].data != data) {
         if (key == arrayOA.length - 1) {
            key = 0;
         }
         else {
            key++;
         }
      }
      if (arrayOA[key] == null) {
         DataCount temp = new DataCount(data, 1);
         arrayOA[key] = temp;                 
      }
      else {
         arrayOA[key].count++;
      }
	}

	// Returns the number of unique DataCount nodes in the HashTable.
	public int getSize() {
		int size = 0;
      for (int i = 0; i < arrayOA.length; i++) {
         if (arrayOA[i] != null) {
            if (unique(arrayOA[i].data, i)) {
               size++;
            }
         }
      }
		return size;
	}

	// Returns the count in the HashTable of the given DataCount data value.
	public int getCount(String data) {
	   int key = ha.hash(data);
      while (arrayOA[key].data != data) {
         key++;
      }
      if (arrayOA[key].data == data) {
         return arrayOA[key].count;
      }
      return 0;
        
	}

   // Creates a class iterator used to look through HashTable DataCount nodes.
	public SimpleIterator getIterator() {
      return new SimpleIterator() {
         int cur = 0;
         
         // Returns true if the next DataCount node exists.
         public boolean hasNext() {
            if (cur == 0 && arrayOA[cur] != null) {
               return true; 
            }
            else if (cur != arrayOA.length - 1 && arrayOA[cur + 1] != null) {
               return true;
            }
            else {
               return false;
            }
         }
         
         // Returns the next DataCount node.
         public DataCount next() {
            if (hasNext()) {
               DataCount next = arrayOA[cur];
               cur++;
               return next;
            }
            cur++;
            return null;
         }
      };
   }
   
   // Searches for empty indexes within HashTable.
   public int searcher (int key) {
      if (arrayOA[key] != null) {
         searcher(key + 1);
      }
      else {
         return key;
      }
      return -1;
   }
   
   // Resizes the current HashTable.
   public void resize () {
      DataCount[] temp = new DataCount[maxPrimeUnderDouble(arrayOA.length)];
      for (int i = 0; i < arrayOA.length; i++) {
         if (arrayOA[i] != null) {
            int key = ha.hash(arrayOA[i].data);
            key = key % temp.length;
            while (temp[key] != null) {
               if (key == temp.length - 1) {
                  key = 0;
               }
               else {
                  key++;
               }
            }
            if (temp[key] == null) {
               temp[key] = arrayOA[i];                 
            }
         }
      }
      arrayOA = temp;
   }
   
   // Returns the highest prime number under double the current integer value.
   private int maxPrimeUnderDouble(int x) {
      int y = x;
      x = x * 2;
      int i = x - 1;
      for (;i >= y; i--) {
         boolean isPrime = true;
         for (int j = 2; j <= Math.sqrt(i) && isPrime; j++) {
            if (i % j == 0) {
                isPrime = false;
            }
         }
         if (isPrime) {
            y = i;
            break;
         }
      }
      return y;
   }
   
   // Searches the current HashTable for duplicate DataCount data values.
   private boolean unique (String cur, int x) {
      for (int i = 0; i < arrayOA.length; i++) {
         if (i == x) {
            i++;
         }
         else if (arrayOA[i] != null && arrayOA[i].data == cur) {
            return false;
         }
         else {
            return true;
         }
      }
      return false;
   }
}
