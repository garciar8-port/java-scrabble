// Name: Rodrigo Garcia
// USC NetID: 2105066875
// CS 455 PA4
// Fall 2025

/*
   This contains the main method.
   This class will have a main that's responsible for processing the command-line argument, and handling any error processing.
   It will probably also have the main command loop.
   Most of the other functionality will be delegated to other object(s) created in main and their methods.
 */

import java.io.FileNotFoundException;
import java.util.*;

public class WordFinder {

   public static void main(String[] args) {

      String dictionaryFile = "sowpods.txt";

      if (args.length > 0) {
         dictionaryFile = args[0];
      }
      // Create Anagram dictionary
      AnagramDictionary dictionary = null;
      try {
         dictionary = new AnagramDictionary(dictionaryFile);
      } catch (FileNotFoundException e) {
         System.out.println(e.getMessage());
         System.out.println("Exiting program.");
         return;
      } catch (IllegalDictionaryException e) {
         System.out.println(e.getMessage());
         System.out.println("Exiting program.");
         return;
      }

      Scanner input = new Scanner(System.in);
      System.out.println("Type . to quite.");

      while (true) {
         System.out.println("Rack?");
         String inputRack = input.nextLine();

         if (inputRack.equals(".")) {
            break;
         }

         //process rack
         processRack(inputRack, dictionary);
      }
      input.close();
   }

   /**
    * Process single rack
    * @param rackString
    * @param dictionary
    */
   private static void processRack(String rackString, AnagramDictionary dictionary) {

      Rack rack = new Rack(rackString);

      ArrayList<String> allWords = rack.getAllWords(dictionary);
      System.out.println("We can make " + allWords.size() + " words from " + rackString);

      if(allWords.size() == 0) {
         return;
      }

      ArrayList<WordScore> wordScores = new ArrayList<>();
      for (String word : allWords) {
         int score = ScoreTable.getWordScore(word);
         wordScores.add(new WordScore(word, score));
      }

      // Sort by score (desc), then alphabetically
      Collections.sort(wordScores, new WordScoreComparator());

      //
      System.out.println("All of the words with their scores (sorted by score):");
      for(WordScore ws : wordScores) {
         System.out.println(ws.getScore() + ": " + ws.getWord());
      }
   }

   /**
    *
    */
   private static class WordScore {
      private String word;
      private int score;

      public WordScore(String word, int score) {
         this.word = word;
         this.score = score;
      }

      public String getWord() {
         return word;
      }

      public int getScore() {
         return score;
      }
   }

   /**
    * Comparator to sort WordScore
    */

   private static class WordScoreComparator implements Comparator<WordScore> {
      public int compare(WordScore w1, WordScore w2) {
         // First compare by score
         if(w1.getScore() != w2.getScore()) {
            return w2.getScore() - w1.getScore(); // desc order
         }
         return w1.getWord().compareTo(w2.getWord());
      }
   }
}


