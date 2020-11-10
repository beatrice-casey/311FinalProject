     /* ***************************************** * CSCI205 - Software Engineering and Design * Fall 2019
      * Instructor: Prof. Brian King
      *
      * Name: Bea Casey
      * Section: 11am * Date: 11/9/20
      * Time: 8:11 PM
      *
      * Project: 311FinalProject
      * Package: PACKAGE_NAME
      * Class: SpellCheck
      *
      * Description:
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

          public boolean LCS_Length(String X, String Y) {
               int m = X.length();
               int n = Y.length();

               if (m == n) {
                    return true;
               } else {
                    return false;
               }

          }

          public static void loadWordList() throws FileNotFoundException {
               File file = new File("words.txt");
               Scanner scanner = new Scanner(file);
               while (scanner.hasNext()) {
                    words.add(scanner.next());
               }


          }

          public static void main(String[] args) throws FileNotFoundException {
               loadWordList();
               Scanner in = new Scanner(System.in);
               System.out.println("Enter a word to check: ");

               String input = in.nextLine();


               checkInList(input);

          }

          private static void checkInList(String input) {
               List<String> possibleResults = new ArrayList<String>();
               for (int i = 0; i < words.size(); i++) {
                    if (words.get(i).contains(input)) {
                         possibleResults.add(words.get(i));
                         System.out.println(possibleResults);
                    }
               }
               for (int i = 0; i < possibleResults.size(); i++) {
                    if (input.equals(possibleResults.get(i))) {
                         System.out.println(possibleResults.get(i));
                    }
               }

          }
          
     }
