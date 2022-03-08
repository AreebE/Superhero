package battlesystem;

import java.io.*;
import java.util.*;
//this thing is kinda nuts and needs some work also it no longer 
// deals with files so i dont think it needs to be in FileParsing
public class CustomMaker {
  CustomMaker() {
  }
  public static Entity askNMakeSuperhero() {
    Scanner sc = new Scanner(System.in);
    System.out.println("You have decided to create a custom superhero... \n \n");
    System.out.println("Enter your new superheros name:  ");
    String name = sc.nextLine();
    System.out.println("Enter your new superheros speed:  ");
    int speed = sc.nextInt();
    System.out.println("Enter your new superheros maxhealth:  ");
    int health = sc.nextInt();
    System.out.println("Enter your new superheros shieldHealth:  ");
    int sheildhealth = sc.nextInt();
    return new Entity(name,speed, health, sheildhealth, States.get(States.Name.NORMAL), null);
    /*
    String name, 
        int speed, 
        int health, 
        int shieldHealth,
        State defaultState,
        Entity creator
    */
  }

  public static Ability askNMakeAbility() {
    // very WIP
    Scanner s = new Scanner(System.in);
    System.out.println("You have decided to create a custom Ability... \n \n");
    System.out.println("Enter your new Abilities name:  ");
    String name =  s.next();
    System.out.println("Enter your new Abilities description:  ");
    String desc = s.next();
    System.out.println("Enter your new Abilities cooldown:  ");
    int cldn =  s.nextInt();
    System.out.println("Enter your new Abilities strength:  ");
    int str =  s.nextInt();
    System.out.println("Enter your new Abilities Type: (NOT IMPLEMENTED PROPERLY YET...) ");
    String t = s.next();
    Ability.Type a = null;
    switch(t){
      case "ATTACK":
        a= Ability.Type.ATTACK;
        break;
      case "DEFENSE":
        a= Ability.Type.DEFENSE;
        break;
      case "SUPPORT":
        a=Ability.Type.SUPPORT;
        break;
    }
    System.out.println("Enter your new Abilities Type: (NOT IMPLEMENTED PROPERLY YET...) ");

    System.out.println("NEW AB IS \n"+name+desc+cldn+str);
    return null;
  }
}