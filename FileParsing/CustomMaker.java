package battlesystem;

import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
//this thing is kinda nuts and needs some work
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
    // WIP
  }
}