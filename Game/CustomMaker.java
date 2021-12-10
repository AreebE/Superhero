import java.util.Scanner;
import java.io.*;

public class CustomMaker {
  Scanner s;
  FileIoThing f;

  CustomMaker() {

    try {
      s = new Scanner(System.in);
      f = new FileIoThing("save.txt");

    } catch (Exception e) {
      System.out.println("error creating scanner or FIT in customaker init");
    }
    try {
      String[] a = { "asdf", "asdfgds", "dasfas" };
      f.writeStringArrToTxt(a);
    } catch (IOException e) {
      System.out.println("error testing stuff "+e );
    }

  }

  public Superhero AskNMakeSuperhero() {
    System.out.println("You have decided to create a custom superhero... \n \n");
    System.out.println("Enter your new superheros name:  ");
    String name = s.next();
    // i eventually need to implement a way to get Custom Fw, H , Sh ect.

    System.out.println("what abilities do you want your hero to have?");
    // if()
    return new Superhero(name, 10, 100, 0);
  }

  public void AskNMakeAbility() {
    // WIP
  }
}