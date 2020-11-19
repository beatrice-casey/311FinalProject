     /* ***************************************** * CSCI311
      * Instructor: Prof. Talmage
      *
      * Name: Bea Casey, Wenyi Qian, Sam Citron, John Hunt
      * Date: 11/9/20
      * Time: 8:11 PM
      *
      * Project: 311FinalProject
      * Class: SpellCheck
      *
      * Description: A spell-check algorithm implemented with a LCS algorithm
      *
      * **************************************** */

     import java.io.File;
     import java.io.FileNotFoundException;
     import java.sql.SQLOutput;
     import java.util.ArrayList;
     import java.util.List;
     import java.util.Scanner;

     public class SpellCheck {

          private static List<String> words= new ArrayList<String>();
          private static List<Integer> LCSScores= new ArrayList<Integer>();

          public static int LCS_Length(String X, String Y) {
               int m = X.length();
               int n = Y.length();
               int [][]LCSLength = new int [m+1][n+1];
               int i;
               int j;
               for (i = 1; i < m; i++) {
                    LCSLength[i][0] = 0;
               }
               for (j = 0; j < n; j++) {
                    LCSLength[0][j]= 0;
               }

               for (i = 1; i < m + 1; i++) {
                    for (j = 1; j < n + 1; j++) {
                         if (X.charAt(i-1) == Y.charAt(j-1)) {
                              LCSLength[i][j] = LCSLength[i - 1][j - 1] + 1;
                         } else if (LCSLength[i-1][j] >= LCSLength[i][j-1]) {
                              LCSLength[i][j] = LCSLength[i-1][j];
                         } else {
                              LCSLength[i][j] = LCSLength[i][j-1];
                         }
                    }
               }
               return LCSLength[m][n];
          }


          public static void loadWordList() throws FileNotFoundException {
               File file = new File("words.txt");
               Scanner scanner = new Scanner(file);
               while (scanner.hasNext()) {
                    words.add(scanner.next());
               }


          }
          public static void checkInList(String input) {
               for (int i = 0; i < words.size(); i++) {
                    LCSScores.add(LCS_Length(input, words.get(i)));
               }
               List<Integer> max = LCSMax();
               printCorrections(max, input);

          }

          private static void printCorrections(List<Integer> max, String input) {
               List<String> possibilities = new ArrayList<String>();
               System.out.println("Possible corrections based on LCS are: ");
               for (int i = 0; i < max.size(); i++) {
                    System.out.println(words.get(max.get(i)));
                    possibilities.add(words.get(max.get(i)));
               }
               if (possibilities.contains(input)) {
                    System.out.println("chosen correction is: " + input);
               }
               else {
                    System.out.println("chosen correction is: " + words.get(max.get(max.size()-1)));
               }


          }

          private static List<Integer> LCSMax() {
               int max = 0;
               List<Integer> maxLCS= new ArrayList<Integer>();
               for (int i = 0; i < LCSScores.size(); i++) {
                    if (LCSScores.get(i) > max) {
                         max = LCSScores.get(i);
                    }

               }
               for (int i = 0; i < LCSScores.size(); i++) {
                    if (LCSScores.get(i) == max) {
                         maxLCS.add(i);
                    }

               }
               return maxLCS;
          }

          public static void main(String[] args) throws FileNotFoundException {
               loadWordList();
               Scanner in = new Scanner(System.in);
               System.out.println("Enter a word to check: ");

               String input = in.nextLine();


               checkInList(input);


          }




     }
