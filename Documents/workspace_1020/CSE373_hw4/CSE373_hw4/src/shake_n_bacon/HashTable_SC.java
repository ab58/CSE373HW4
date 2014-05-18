package shake_n_bacon;


import providedCode.*;




// Patrick Harper-Joles / Arjun Bhalla
// hatrik42 / ab58
// 1440683 / 1363119
// hatrik42@uw.edu / arjunbhalla675@gmail.com


// This class creates a HashTable using the Seperate Chaining model.


public class HashTable_SC extends DataCounter {

    // Inner class for the creation of BucketNodes.
    public class Bucket {
        public BucketNode head;

        // Inner class defining BucketNode objects.
        public class BucketNode {
            public BucketNode next;
            public DataCount element;


            public BucketNode(DataCount el) {
                next = null;
                element = el;
            }
        }

        // Constructs new Bucket objects.
        public Bucket() {
            head = null;
        }

        // Adds new BucketNodes to current Bucket list.
        public void add(DataCount dc) {
            BucketNode newNode = new BucketNode(dc);
            if (head==null) {
                head = newNode;
                head.next = null;
            }
            else {
                BucketNode bn = head;
                for (; bn!=null; bn=bn.next) {
                    bn.next = null;
                    bn = newNode;
                }
            }
        }
    }

    public Bucket[] HTArr;
    public StringComparator sc;
    public StringHasher sh;


    /*Auxiliary method for finding highest prime number less than double the table size;
     *resizing will be to this value.*/
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
    public void resize() {
        int newSize = maxPrimeUnderDouble(HTArr.length);
        HTArr = new Bucket[newSize];
    }


    //
    public HashTable_SC(Comparator<String> c, Hasher h) {
        sc = (StringComparator)c;
        sh = (StringHasher)h;
        HTArr = new Bucket[13];
        //sh.buckets = HTArr;
    }


    // Creates DataCount nodes, hashes the given string value, and inserts
    // the DataCount node into the HashTable at the hashed value index.
    public void incCount(String data) {
        if (getSize()>=HTArr.length/2)
            resize();
        int bucketIndex = sh.hash(data);
        bucketIndex = bucketIndex % HTArr.length;
        if (HTArr[bucketIndex]==null) {
            HTArr[bucketIndex] = new Bucket();
            HTArr[bucketIndex].add(new DataCount(data.toLowerCase(),1));
        }
        //there's a bucket with something in it at that index; must compare our data with strings
        else {
            Bucket.BucketNode bn = HTArr[bucketIndex].head;
            boolean found = false;
            for (; bn!=null&&!found; bn=bn.next) {
                if (sc.compare(data.toLowerCase(),bn.element.data)==0) {
                    found = true;
                    bn.element.count++;
                }
            }
            if (!found) {
                HTArr[bucketIndex].add(new DataCount(data.toLowerCase(),1));
            }
        }
    }


    // Returns the number of unique DataCount nodes in the HashTable.
    public int getSize() {
        int x = 0;
        for (int i=0; i<HTArr.length; i++) {
            if (HTArr[i]!=null)
                for (Bucket.BucketNode n=HTArr[i].head; n!=null; n=n.next)
                    x++;
        }
        return x;
    }


    // Returns the count in the HashTable of the given DataCount data value.
    public int getCount(String data) {
        boolean found = false;
        for (int i=0; i<HTArr.length&&!found; i++) {
            if (HTArr[i]!=null) {
                Bucket.BucketNode bn = HTArr[i].head;
                for (; bn!=null&&!found; bn=bn.next) {
                    if (sc.compare(data.toLowerCase(),bn.element.data) == 0) {
                        found = true;
                        return bn.element.count;
                    }
                }
            }
        }
        return 0;
    }

    // Creates a class iterator used to look through HashTable DataCount nodes.
    public SimpleIterator getIterator() {
        return new SimpleIterator () {
            private Bucket[] array;
            private int curIndex;
            private Bucket.BucketNode curNode;
            private boolean hasNext;

            // Returns the next DataCount node.
            public DataCount next() {
                if (curNode!=null) {
                    curNode = curNode.next;
                    if (curNode!=null) {
                        /////////////////////////
                        //return curNode.element;
                        if (curIndex==HTArr.length-1)
                            //////////////////////////////////////////////////////
                            throw new IllegalArgumentException("No next element!");
                        else {
                            for (; curIndex<HTArr.length; curIndex++) {
                                if (HTArr[curIndex]!=null) {
                                    Bucket curBucket = HTArr[curIndex];
                                    curNode = curBucket.head;
                                    break;
                                }
                            }
                            return curNode.element;
                        }
                    }
                    else {
                        for (; curIndex<HTArr.length; curIndex++) {
                            if (HTArr[curIndex]!=null) {
                                Bucket curBucket = HTArr[curIndex];
                                curNode = curBucket.head;
                                break;
                            }
                        }
                        return curNode.element;
                    }
                }
                return null;
            }


            // Returns true if a next DataCount value exists.
            public boolean hasNext() {
               System.out.println("curIndex is "+curIndex);
                if (curIndex==HTArr.length)
                       return false;
                else if (HTArr[curIndex+1]==null)
                    return false;
                else if (curNode==null)
                    return false;
                else if (curNode.next==null)
                    return false;
                else
                    return true;
                /*if (curNode!=null) {
                    curNode = curNode.next;
                    if (curNode!=null)
                        return true;
                    else
                        for (; curIndex<HTArr.length; curIndex++) {
                            if (HTArr[curIndex]!=null)
                                return true;
                        }
                }
                else {
                    for (; curIndex<HTArr.length; curIndex++) {
                        if (HTArr[curIndex]!=null)
                            return true;
                    }
                }
                return false;*/
            }
        };
    }
}
