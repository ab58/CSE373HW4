package shake_n_bacon;

import providedCode.Hasher;

// Patrick Harper-Joles / Arjun Bhalla
// hatrik42 / ab58
// 1440683 / 1363119
// hatrik42@uw.edu / arjunbhalla675@gmail.com

// This class breaks a String into char ASCII values and adds them
// to a running total for return.

public class StringHasher implements Hasher {
   
   // Returns the value of all the given String value's characters.
   public int hash(String str) {
      int sum = 0;
      for (int i = 0; i < str.length(); i++) {
         sum += str.charAt(i);;
      }
      return sum;
   }
}
