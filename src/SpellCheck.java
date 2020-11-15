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
          private static List<String> possibilites= new ArrayList<String>();
          private static int [][] b;
          private static int [][] c;

          public static void LCS_Length(String X, String Y) {
               int m = X.length();
               int n = Y.length();
               b = new int[m][n];
               c = new int [m][n];
               int i;
               int j;
               for (i = 1; i < m; i++) {
                    c[i][0] = 0;
               }
               for (j = 0; j < n; j++) {
                    c[0][j]= 0;
               }

               for (i = 1; i < m; i++) {
                    for (j = 1; j < n; j++) {
                         if (X.charAt(i) == Y.charAt(j)) {
                              c[i][j] = c[i - 1][j - 1] + 1;
                              b[i][j] = b[i-1][j-1];
                         } else if (c[i-1][j] >= c[i][j-1]) {
                              c[i][j] = c[i-1][j];
                              b[i][j] = b[i-1][j];
                         } else {
                              c[i][j] = c[i][j-1];
                              b[i][j] = b[i][j-1];
                         }
                    }
               }

          }

          private static void Print_LCS(String X, int x_length, int y_length) {
               if (x_length == 0 || y_length == 0) {
                    return;
               }
               if (b[x_length][y_length] == b[x_length-1][y_length-1]) {
                    Print_LCS(X, x_length-1, y_length-1);
                    System.out.println(X);
               } else if(b[x_length][y_length] == b[x_length-1][y_length]) {
                    Print_LCS(X, x_length-1, y_length);
               } else {
                    Print_LCS(X, x_length, y_length-1);
               }
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
                    LCS_Length(input, words.get(i));
                    //System.out.println(c[input.length()-1][words.get(i).length()-1]);
                    if (c[input.length()-1][words.get(i).length()-1] >= input.length()-1) {
                       //System.out.println("pass");
                        Print_LCS(words.get(i), input.length() - 1, words.get(i).length() - 1);
                         possibilites.add(words.get(i));
                    }
               }

               for (int i = 0; i < possibilites.size(); i++) {
                    if (possibilites.get(i).length() != input.length()) {
                         possibilites.remove(i);
                    }
               }

               for (int i = 0; i < possibilites.size(); i++) {
                    System.out.println(possibilites.get(i));
               }


          }

          public static void main(String[] args) throws FileNotFoundException {
               loadWordList();
               Scanner in = new Scanner(System.in);
               System.out.println("Enter a word to check: ");

               String input = in.nextLine();


               checkInList(input);


          }




     }
