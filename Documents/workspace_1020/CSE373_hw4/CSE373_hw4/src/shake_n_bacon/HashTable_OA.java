package shake_n_bacon;

import providedCode.*;

/**
 * Patrick Harper-Joles / Arjun Bhalla
 * hatrik42 / ab58
 * 1440683 / 1363119
 * hatrik42@uw.edu / arjunbhalla675@gmail.com
 * 
 *        TODO: Replace this comment with your own as appropriate.
 * 
 *        1. You may implement HashTable with open addressing discussed in
 *        class; You can choose one of those three: linear probing, quadratic
 *        probing or double hashing. The only restriction is that it should not
 *        restrict the size of the input domain (i.e., it must accept any key)
 *        or the number of inputs (i.e., it must grow as necessary).
 * 
 *        2. Your HashTable should rehash as appropriate (use load factor as
 *        shown in the class).
 * 
 *        3. To use your HashTable for WordCount, you will need to be able to
 *        hash strings. Implement your own hashing strategy using charAt and
 *        length. Do NOT use Java's hashCode method.
 * 
 *        4. HashTable should be able to grow at least up to 200,000. We are not
 *        going to test input size over 200,000 so you can stop resizing there
 *        (of course, you can make it grow even larger but it is not necessary).
 * 
 *        5. We suggest you to hard code the prime numbers. You can use this
 *        list: http://primes.utm.edu/lists/small/100000.txt NOTE: Make sure you
 *        only hard code the prime numbers that are going to be used. Do NOT
 *        copy the whole list!
 * 
 *        TODO: Develop appropriate tests for your HashTable.
 */
public class HashTable_OA extends DataCounter {
   private DataCount[] array;
   private Hasher ha;
   
	public HashTable_OA(Comparator<String> c, Hasher h) {
		array = new DataCount[17];
      ha = h; 
	}

	@Override
	public void incCount(String data) {
		if (getSize() >= array.length / 2) {
         resize();
      }
      int key = ha.hash(data);
      key = key % array.length;
      while (array[key] != null && array[key].data != data) {
         key++;
      }
      if (array[key] == null) {
         DataCount temp = new DataCount(data, 1);
         array[key] = temp;                 
      }
      else {
         array[key].count++;
      }
	}

	@Override
	public int getSize() {
		int size = 0;
      for (int i = 0; i < array.length; i++) {
         if (array[i] !=null) {
            size++;
         }
      }
		return size;
	}

	@Override
	public int getCount(String data) {
	   int key = ha.hash(data);
      while (array[key].data != data) {
         key++;
      }
      if (array[key].data == data) {
         return array[key].count;
      }
      return 0;
        
	}

	@Override
	public SimpleIterator getIterator() {
public SimpleIterator getIterator() {
      return new SimpleIterator() {
         int cur = 0;
         
         //
         public boolean hasNext() {
            if (cur == 0 && array[cur] != null) {
               return true; 
            }
            else if (cur != array.length && array[cur + 1] != null) {
               return true;
            }
            else {
               return false;
            }
         }
         
         //
         public DataCount next() {
            if (hasNext()) {
               DataCount next = array[cur];
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
      if (array[key] != null) {
         searcher(key + 1);
      }
      else {
         return key;
      }
   }
   
   //
    //
   public void resize () {
      DataCount[] temp = new DataCount[maxPrimeUnderDouble(array.length)];
      for (int i = 0; i < array.length; i++) {
         if (array[i] != null) {
            int x = ha.hash(array[i].data);
            temp[x] = array[i];
         }
      }
      array = temp;
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
}
