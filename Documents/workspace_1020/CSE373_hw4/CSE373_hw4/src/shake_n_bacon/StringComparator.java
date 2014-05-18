package shake_n_bacon;

import providedCode.*;

// Patrick Harper-Joles / Arjun Bhalla
// hatrik42 / ab58
// 1440683 / 1363119
// hatrik42@uw.edu / arjunbhalla675@gmail.com

// This class compares String values against each other.

public class StringComparator implements Comparator<String> {
   
   // Compares String values to determine which has a higher integer value.
   public int compare(String s1, String s2) {
      String shorter = null;
      String longer = null;
      int shorterLength = Math.min(s1.length(),s2.length());
      if (s1.length()==shorterLength) {
         shorter = s1;
         longer = s2;
      } 
      else {
         shorter = s2;
         longer = s1;
      }
      boolean different = false;
      for (int i=0; i<shorter.length()&&!different; i++) {
         char cShort = shorter.charAt(i);
         char cLong = longer.charAt(i);
         if (cShort<cLong) {
            different = true;
            if (shorter.length()==s1.length())
               return -1;
            else
               return 1;
         } 
         else if (cShort>cLong) {
            different = true;
            if (shorter.length()==s1.length())
               return 1;
            else
               return -1;
            } 
         else if (i==shorter.length()-1&&longer.length()>shorter.length()) {
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

