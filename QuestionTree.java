// Homework 7
// This program plays a yes/no game of 20 Questions with the user and
// tries to guess what object the user is thinking of.

import java.io.*;
import java.util.*;

public class QuestionTree{

   private QuestionNode overallRoot;
   private Scanner console;
   
   // starts game with one answer object which is
   // "computer"
   public QuestionTree() {
      overallRoot = new QuestionNode("computer");
      console = new Scanner(System.in);
   }
   
   // asks user if he would like to play game with questions from previous
   // file. if yes, plays game with questions and answers from given
   // file 
   public void read(Scanner input) {
      overallRoot = readHelp(input);
   }
   
   // asks user if he would like to play game with questions from previous
   // file. if yes, plays game with questions and answers from given
   // file  
   private QuestionNode readHelp(Scanner input) {
      String firstLine = input.nextLine();
      String secondLine = input.nextLine();
      if(firstLine.equals("Q:")) {
         return new QuestionNode(secondLine, readHelp(input), readHelp(input));
      } else {
         return new QuestionNode(secondLine);
      }
   }
   
   // stores questions and answers from current game to
   // file
   public void write(PrintStream output) {
      writeHelp(output, overallRoot);
   }
   
   // stores questions and answers from current game to
   // file
   private QuestionNode writeHelp(PrintStream output, QuestionNode root) {
      if (root != null) {   
         if (root.left == null && root.right == null) {
            output.println("A:");
         } else {
            output.println("Q:");
         }
         output.println(root.data);
         root.left = writeHelp(output, root.left);
         root.right = writeHelp(output, root.right);
      }
      return root;
   }

   // asks user yes or no questions until they guess 
   // the object correctly, or until the computer loses. 
   // if computer loses, it will ask user what the object 
   // was, and then ask for a question which will help it 
   // differentiate this object from others in the future 
   public void askQuestions () {
      overallRoot = askQuestionsHelp(overallRoot);
   }

   // asks user yes or no questions until they guess 
   // the object correctly, or until the computer loses. 
   // if computer loses, it will ask user what the object 
   // was, and then ask for a question which will help it 
   // differentiate this object from others in the future  
   public QuestionNode askQuestionsHelp(QuestionNode root) {
      if(root.left == null && root.right == null) {
         if(yesTo("Would your object happen to be " + root.data + "?")) {
            System.out.println("Great, I got it right!");
         } else {
            System.out.print("What is the name of your object? ");
            String nameOfObject = console.nextLine();
            QuestionNode objectNode = new QuestionNode(nameOfObject);
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> ");  
            String question = console.nextLine();
            if(yesTo(("And what is the answer for your object?"))) {
               return new QuestionNode(question, objectNode, root);
            } else {
               return new QuestionNode(question, root, objectNode);
            }
         }
      } else {
         if(yesTo(root.data)) {
            root.left = askQuestionsHelp(root.left);
         } else {
            root.right = askQuestionsHelp(root.right);
         } 
      }
      return root;
   } 
   
   // post: asks the user a question, forcing an answer of "y " or "n";
   //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }
}
