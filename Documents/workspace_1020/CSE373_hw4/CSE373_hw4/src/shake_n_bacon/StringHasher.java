package shake_n_bacon;

import providedCode.Hasher;

// Patrick Harper-Joles / Arjun Bhalla
// hatrik42 / ab58
// 1440683 / 1363119
// hatrik42@uw.edu / arjunbhalla675@gmail.com

//

public class StringHasher implements Hasher {
   
   //
   public int hash(String str) {
      int sum = 0;
      for (int i = 0; i < str.length(); i++) {
         sum += str.charAt(i);;
      }
      return sum;
   }
}
