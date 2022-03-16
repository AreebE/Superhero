package game.game;

import java.util.Scanner;

public class Asker{
  public static Scanner sc = new Scanner(System.in);
  Asker(){}


  
  public static boolean askYN(String question){
    System.out.println(question + "(Y/N)");
    String input = sc.nextLine();
    if(input.toLowerCase().charAt(0)=='y'){
      return true;
    }
    return false;
  }

  public static int aMCQ(String question, String ... choices){
    System.out.println(question);
    String input = sc.nextLine().toLowerCase();
    for(int i=0;i<choices.length;i++){
      if(input.equals(choices[i].toLowerCase())){
        return i;
      }
      
    }
    return -1;
    //hey thats not a valid choice?
  }
}