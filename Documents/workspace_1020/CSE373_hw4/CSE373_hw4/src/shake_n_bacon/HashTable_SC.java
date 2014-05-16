package shake_n_bacon;

import providedCode.*;

import java.util.NoSuchElementException;

/**
 * @author <name>
 * @UWNetID <uw net id>
 * @studentID <id number>
 * @email <email address>
 * 
 *        TODO: Replace this comment with your own as appropriate.
 * 
 *        1. You may implement HashTable with separate chaining discussed in
 *        class; the only restriction is that it should not restrict the size of
 *        the input domain (i.e., it must accept any key) or the number of
 *        inputs (i.e., it must grow as necessary).
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
public class HashTable_SC extends DataCounter {

    public class Bucket
    {
        public BucketNode head;

        public class BucketNode
        {
            public BucketNode next;
            public DataCount element;

            public BucketNode(DataCount el)
            {
                next = null;
                element = el;
            }
        }

        public Bucket()
        {
            head = null;
        }

        public void add(DataCount dc)
        {
            BucketNode newNode = new BucketNode(dc);
            if (head==null)
            {
                head = newNode;
                head.next = null;
            } else
            {
                BucketNode bn = head;
                for (; bn!=null; bn=bn.next)
                {
                }
                bn.next = null;
                bn = newNode;
            }
        }
    }

    public Bucket[] HTArr;
    public StringComparator sc;
    public StringHasher sh;

    /*Auxiliary method for finding highest prime number less than double the table size;
    * resizing will be to this value.*/
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

    /*Auxiliary method to resize the table*/
    public void resize()
    {
        int newSize = maxPrimeUnderDouble(HTArr.length);
        HTArr = new Bucket[newSize];
    }

	public HashTable_SC(Comparator<String> c, Hasher h) {
		// TODO: To-be implemented
        sc = (StringComparator)c;
        sh = (StringHasher)h;
        HTArr = new Bucket[13];
        sh.buckets = HTArr;
	}

	@Override
	public void incCount(String data) {
		// TODO Auto-generated method stub
        if (getSize()>=HTArr.length/2)
            resize();
        int bucketIndex = sh.hash(data);
        if (HTArr[bucketIndex]==null)
        {
            HTArr[bucketIndex] = new Bucket();
            HTArr[bucketIndex].add(new DataCount(data.toLowerCase(),1));
        } else //there's a bucket with something in it at that index; must compare our data with strings
        {
            Bucket.BucketNode bn = HTArr[bucketIndex].head;
            boolean found = false;
            for (; bn!=null&&!found; bn=bn.next)
            {
                if (sc.compare(data.toLowerCase(),bn.element.data)==0)
                {
                    found = true;
                    bn.element.count++;
                }
            }
            if (!found)
            {
                HTArr[bucketIndex].add(new DataCount(data.toLowerCase(),1));
            }
        }
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
        int x = 0;
        for (int i=0; i<HTArr.length; i++)
        {
            if (HTArr[i]!=null)
                for (Bucket.BucketNode n=HTArr[i].head; n!=null; n=n.next)
                    x++;
        }
		return x;
	}

	@Override
	public int getCount(String data) {
		// TODO Auto-generated method stub
        boolean found = false;
        for (int i=0; i<HTArr.length&&!found; i++)
        {
            if (HTArr[i]!=null)
            {
                Bucket.BucketNode bn = HTArr[i].head;

                for (; bn!=null&&!found; bn=bn.next) {
                    if (sc.compare(data.toLowerCase(),bn.element.data) == 0)
                    {
                        found = true;
                        return bn.element.count;
                    }
                }
            }
        }
		return 0;
	}

    private class HashTable_SC_Iterator implements SimpleIterator {

        private Bucket[] array;
        private int curIndex;
        private Bucket.BucketNode curNode;

        public HashTable_SC_Iterator(Bucket[] b) {
            array = b;
            curIndex = 0;
        }
        @Override
        public DataCount next() {

            if (curNode!=null)
            {
                curNode = curNode.next;
                if (curNode!=null)
                    return curNode.element;
                else if (curIndex==array.length-1)
                    throw new NoSuchElementException("No next element!");
                else
                    for (; curIndex<array.length; curIndex++)
                    {
                        if (array[curIndex]!=null) {
                            Bucket curBucket = array[curIndex];
                            curNode = curBucket.head;
                            break;
                        }
                    }
                    return curNode.element;

            } else
            {
               for (; curIndex<array.length; curIndex++)
               {
                   if (array[curIndex]!=null) {
                       Bucket curBucket = array[curIndex];
                       curNode = curBucket.head;
                       break;
                   }
               }
               return curNode.element;
            }
        }

        @Override
        public boolean hasNext() {
            if (curNode!=null)
            {
                curNode = curNode.next;
                if (curNode!=null)
                    return true;
                else
                    for (; curIndex<array.length; curIndex++)
                    {
                        if (array[curIndex]!=null)
                            return true;
                    }
            } else
            {
                for (; curIndex<array.length; curIndex++)
                {
                    if (array[curIndex]!=null)
                        return true;
                }
            }
            return false;
        }
    }

	@Override
	public SimpleIterator getIterator() {
		return new HashTable_SC_Iterator(HTArr);
	}

}
