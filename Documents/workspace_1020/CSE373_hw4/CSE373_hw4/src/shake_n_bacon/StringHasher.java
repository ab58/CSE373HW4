package shake_n_bacon;

import providedCode.Hasher;

/**
 * @author <name>
 * @UWNetID <uw net id>
 * @studentID <id number>
 * @email <email address>
 */
public class StringHasher implements Hasher {

    HashTable_SC.Bucket[] buckets;
	/**
	 * TODO Replace this comment with your own as appropriate.
	 */
	@Override
	public int hash(String str) {

		char[] cArr = str.toLowerCase().toCharArray();
        int sum = 0;
        for (int i=0; i<cArr.length; i++)
        {
            sum+=cArr[i];
        }
		return sum%buckets.length;
	}
}
