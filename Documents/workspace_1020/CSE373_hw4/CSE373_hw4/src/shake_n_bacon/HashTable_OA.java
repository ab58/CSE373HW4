package shake_n_bacon;

import providedCode.*;

// Patrick Harper-Joles / Arjun Bhalla
// hatrik42 / ab58
// 1440683 / 1363119
// hatrik42@uw.edu / arjunbhalla675@gmail.com

//

public class HashTable_OA extends DataCounter {
   private DataCount[] arrayOA;
   private Hasher ha;
   
   //
	public HashTable_OA(Comparator<String> c, Hasher h) {
		arrayOA = new DataCount[17];
      ha = h; 
	}

	//
	public void incCount(String data) {
		if (getSize() >= arrayOA.length / 2) {
         resize();
      }
      int key = ha.hash(data);
      key = key % arrayOA.length;
      while (arrayOA[key] != null && arrayOA[key].data != data) {
         key++;
      }
      if (arrayOA[key] == null) {
         DataCount temp = new DataCount(data, 1);
         arrayOA[key] = temp;                 
      }
      else {
         arrayOA[key].count++;
      }
	}

	//
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

	//
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

   // 
	public SimpleIterator getIterator() {
      return new SimpleIterator() {
         int cur = 0;
         
         //
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
         
         //
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
   
   //
   public int searcher (int key) {
      if (arrayOA[key] != null) {
         searcher(key + 1);
      }
      else {
         return key;
      }
      return -1;
   }
   
   //
   public void resize () {
      DataCount[] temp = new DataCount[maxPrimeUnderDouble(arrayOA.length)];
      for (int i = 0; i < arrayOA.length; i++) {
         if (arrayOA[i] != null) {
            int x = ha.hash(arrayOA[i].data);
            temp[x] = arrayOA[i];
         }
      }
      arrayOA = temp;
   }
   
   //
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
   
   //
   private boolean unique (String cur, int x) {
      for (int i = 0; i < arrayOA.length; i++) {
         if (i == x) {
          
         }
         else if (arrayOA[i].data == cur) {
            return false;
         }
      }
      return true;
   }
}
