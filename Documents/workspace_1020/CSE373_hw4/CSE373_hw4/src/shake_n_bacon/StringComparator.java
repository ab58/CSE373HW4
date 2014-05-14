package shake_n_bacon;

import providedCode.*;

/**
 * @author <name>
 * @UWNetID <uw net id>
 * @studentID <id number>
 * @email <email address>
 * 
 *        TODO: REPLACE this comment with your own as appropriate.
 * 
 *        1. This comparator is used by the provided code for both data-counters
 *        and sorting. Because of how the output must be sorted in the case of
 *        ties, your implementation should return a negative number when the
 *        first argument to compare comes first alphabetically.
 * 
 *        2. Do NOT use any String comparison provided in Java's standard
 *        library; the only String methods you should use are length and charAt.
 * 
 *        3. You can use ASCII character codes to easily compare characters
 *        http://www.asciitable.com/
 * 
 *        4. When you are unsure about the ordering, you can try
 *        str1.compareTo(str2) to see what happens. Your
 *        stringComparator.compare(str1, str2) should behave the same way as
 *        str1.compareTo(str2). They don't have to return the same value, but
 *        their return values should have the same sign (+,- or 0).
 */
public class StringComparator implements Comparator<String> {

	/**
	 * TODO Replace this comment with your own as appropriate.
	 */
	@Override
	public int compare(String s1, String s2) {
		// TODO: To-be implemented
        String shorter = null;
        String longer = null;
        int shorterLength = Math.min(s1.length(),s2.length());
        if (s1.length()==shorterLength) {
            shorter = s1;
            longer = s2;
        } else {
            shorter = s2;
            longer = s1;
        }
        boolean different = false;
        for (int i=0; i<shorter.length()&&!different; i++)
        {
            char cShort = shorter.charAt(i);
            char cLong = longer.charAt(i);
            if (cShort<cLong) {
                different = true;
                if (shorter.length()==s1.length())
                    return -1;
                else
                    return 1;
            } else if (cShort>cLong) {
                different = true;
                if (shorter.length()==s1.length())
                    return 1;
                else
                    return -1;
            } else if (i==shorter.length()-1&&longer.length()>shorter.length()) {
                different = true;
                if (shorter.length()==s1.length())
                    return -1;
                else
                    return 1;
            }
        }
        return 0;
	}
}
