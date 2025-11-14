// Name: Rodrigo Garcia
// USC NetID: 2105066875
// CS 455 PA4
// Fall 2025

/*
   This corresponds to the idea of the rack in the problem description.
   Thus, wherever your program is using a rack, it should be using an object of type Rack.
   As previously discussed, we have already provided the code for a private static Rack method allSubsets.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
   A Rack of Scrabble tiles
 */

public class Rack {
   private String inputRack;

   public Rack(String inputRack) {
      this.inputRack = inputRack;
   }

   /**
    * Get the original rack string
    * @return the rack string
    */
   public String getRackString() {
      return inputRack;
   }

   /**
    * Get all possible words that can be made from this rack using the given dictionary.
    * @param dictionary is the anagram dictionary to use
    * @return list of all possible words made from this rack
    */
   public ArrayList<String> getAllWords(AnagramDictionary dictionary) {
      ArrayList<String> allWords = new ArrayList<>();

      Map<Character, Integer> letterCounts = getLetterCounts();
      String unique = getUniqueLetters(letterCounts);
      int[] mult = getMultiplicity(letterCounts);


      // Get subsets of the rack
      ArrayList<String> subsets = allSubsets(unique, mult, 0);

      // For each subset, get all anagrams from dictionary and add to result
      for (String subset : subsets) {
         ArrayList<String> anagrams = dictionary.getAnagramsOf(subset);
         allWords.addAll(anagrams);
      }
      return allWords;
   }
   /**
    * Count occurrences of each letter in the rack
    * @return a map of letter counts
    */
   private Map<Character, Integer> getLetterCounts() {
      Map<Character, Integer> letterCounts = new LinkedHashMap<>();

      for (int i = 0; i < inputRack.length(); i++) {
         char letter = inputRack.charAt(i);
         letterCounts.put(letter, letterCounts.getOrDefault(letter, 0) + 1);
      }
      return letterCounts;
   }
   /**
    * Get unique letter count from the rack
    * @return string of unique letters
    */
   private String getUniqueLetters(Map<Character, Integer> letterCounts) {

      // create unique string from keys
      StringBuilder unique = new StringBuilder();
      for( char letter : letterCounts.keySet()) {
         unique.append(letter);
      }
      return unique.toString();
   }

   private int[] getMultiplicity(Map<Character, Integer> letterCounts) {
      int[] multiplicity = new int[letterCounts.size()];
      int index = 0;
      for (char letter : letterCounts.keySet()) {
         multiplicity[index] = letterCounts.get(letter);
         index++;
      }
      return multiplicity;
   }

   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param multiplicity the multiplicity of each letter from unique.
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] multiplicity, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, multiplicity, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k)
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= multiplicity[k]; n++) {
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   
}
