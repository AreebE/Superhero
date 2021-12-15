

import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class CustomMaker {
  Scanner s;
  FileIoThing f;

  CustomMaker() {

    try {
      s = new Scanner(System.in);
      f = new FileIoThing("save.json");
      f.setAppend(false);

    } catch (Exception e) {
      System.out.println("error creating scanner or FIT in customaker init: " + e);
    }
    

  }

  public Superhero AskNMakeSuperhero() {
    System.out.println("You have decided to create a custom superhero... \n \n");
    System.out.println("Enter your new superheros name:  ");
    String name = s.next();
    // i eventually need to implement a way to get Custom Fw, H , Sh ect.

    // System.out.println("what abilities do you want your hero to have?");
    // if()
    return new Superhero(name, 10, 100, 0);
  }

  public void saveThisHero(Superhero s) {
    Object[] a = s.ToSaveable().toArray();
    String[] b = Arrays.copyOf(a, a.length, String[].class);
    f.writeStringArrToTxt(b);
  }

  public void saveThisHeroArr(ArrayList<Superhero> s) {
    String[] allToWrite = new String[s.toArray().length]; 
    for (int i = 0; i < s.toArray().length; i++) {
      ArrayList<String> temps = s.get(i).ToSaveable();
      String[] tempStringArray = Arrays.copyOf(temps.toArray(), temps.toArray().length, String[].class);
      allToWrite[i] = f.makeStringArrIntoString(tempStringArray);
    }
    f.writeStringArrToTxt(allToWrite);
  }

  public void AskNMakeAbility() {
    // WIP
  }
}