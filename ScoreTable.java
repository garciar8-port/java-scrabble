// Name: Rodrigo Garcia
// USC NetID: 2105066875
// CS 455 PA4
// Fall 2025

/*
   This class has information about Scrabble scores for scrabble letters and words.
   In scrabble not every letter has the same value.
   Letters that occur more often in the English language are worth less (e.g., 'e' and 's' are each worth 1 point), and letters that occur less often are worth more (e.g., 'q' and 'z' are worth 10 points each).
   You may use hard-coded values in its data. Here are all the letter values:
      (1 point)-A, E, I, O, U, L, N, S, T, R
      (2 points)-D, G
      (3 points)-B, C, M, P
      (4 points)-F, H, V, W, Y
      (5 points)-K
      (8 points)- J, X
      (10 points)-Q, Z
   This class should work for both upper and lower case versions of the letters, e.g., 'a' and 'A' will have the same score.
   Hint: You can index an array with a char that is a lower case letter by treating it as an int and subtracting 'a' from it (because the internal numeric codes for letters are all sequential).
   E.g., If your letter is 'd', ('d' - 'a') = 3 and if it's 'e', ('e' - 'a') = 4.
 */

import java.util.Arrays;

public class ScoreTable {

   private static final int[] LETTER_SCORES = {
         1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3,
         1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
   };

   /**
    * Get the score for a single letter
    * @param letter
    * @return the score for that letter
    */
   public static int getLetterScore(char letter) {
      char lowerLetter = Character.toLowerCase(letter);
      // check if it is a letter
      if(lowerLetter < 'a' || lowerLetter > 'z') {
         return 0;
      }

      //
      int index = lowerLetter - 'a';
      return LETTER_SCORES[index];
   }

   /**
    * Get total score for a word
    * @param
    * @return
    */
   public static int getWordScore(String word) {
      int totalScore = 0;
      for (int i = 0; i < word.length(); i++) {
         totalScore += getLetterScore(word.charAt(i));
      }
      return totalScore;
   }
}
