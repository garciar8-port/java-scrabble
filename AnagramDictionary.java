// Name: Rodrigo Garcia
// USC NetID: 2105066875
// CS 455 PA4
// Fall 2025

/*
   This will contain the dictionary data organized by anagrams.
   It is required to have at least the two public methods whose headers are given in the starter file.
   You are allowed to add other methods to this interface.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {

   private HashMap<String, ArrayList<String>> anagramMap;

   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
                                                    IllegalDictionaryException {
      anagramMap = new HashMap<>();

      File file = new File(fileName);
      // check if file exists
      if (!file.exists()) {
         throw new FileNotFoundException("ERROR: \"" + fileName + "\" does not exist");
      }

      Scanner input = new Scanner(file);
      // read each word from dictionary
      while (input.hasNextLine()) {
         String word = input.nextLine().trim();

         if (word.isEmpty()) {
            continue;
         }
         // get sorted letters
         String sortedLetters = getSortedLetters(word);
         // Check for duplicates and add to map
         if (anagramMap.containsKey(sortedLetters)) {
            ArrayList<String> wordList = anagramMap.get(sortedLetters);

            //Check if this word exist
            if (wordList.contains(word)) {
               input.close();
               throw new IllegalDictionaryException("ERROR: Illegal dictionary" + word);
            }
            wordList.add(word);
         } else {
            // First word with sorted form
            ArrayList<String> wordList = new ArrayList<>();
            wordList.add(word);
            anagramMap.put(sortedLetters, wordList);
         }
      }
      input.close();
   }
   /**
      Method to convert a string to sorted letters to look up anagrams.
      @param word
      @return sorted version of the word's letters
    */
   private String getSortedLetters(String word){
      char[] chars = word.toCharArray();
      Arrays.sort(chars);
      return new String(chars);
      }

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {

      String inputRack = getSortedLetters(string);

      if(anagramMap.containsKey(inputRack)) {
         // return a copy of the list
         return new ArrayList<>(anagramMap.get(inputRack));
      }

      return new ArrayList<String>();
   }
   
   
}
