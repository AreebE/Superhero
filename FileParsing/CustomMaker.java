package battlesystem;

import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
//this thing is kinda nuts and needs some work also it no longer 
// deals with files so i dont think it needs to be in FileParsing
public class CustomMaker {
  CustomMaker() {
  }
  public static Entity AskNMakeSuperhero() {
    System.out.println("You have decided to create a custom superhero... \n \n");
    System.out.println("Enter your new superheros name:  ");
    String name =  new Scanner(System.in).next();
    return new Entity(name, 10, 100, 0, null);
  }

  public void AskNMakeAbility() {
    // very WIP
    System.out.println("You have decided to create a custom Ability... \n \n");
    System.out.println("Enter your new Abilities name:  ");
    String name =  new Scanner(System.in).next();
    System.out.println("Enter your new Abilities description:  ");
    String desc =  new Scanner(System.in).next();
    System.out.println("Enter your new Abilities cooldown:  ");
    int cldn =  new Scanner(System.in).nextInt();
    System.out.println("Enter your new Abilities strength:  ");
    int str =  new Scanner(System.in).nextInt();
    System.out.println("Enter your new Abilities type: (NOT IMPLEMENTED YET...) ");
    

    //WIP


    /*
    ATTACK, 
    DEFENSE, 
    SUPPORT

    String name, 
    String desc, 
    int cooldown, 
    int strength, 
    Abilities.Type type,
    Abilities.Name enumName, 
    Element em, 
    AbilityModifier... modifiers)
    */
  }
}