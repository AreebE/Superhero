package game;

import javax.swing.*;
import java.awt.*;

import battlesystem.Entity;

public class GUI{

  //use entity instead of superhero
  public Entity opponet;

    public GUI(){
        JFrame frame = new JFrame();
        JPanel welcomeScreen = new JPanel();
        JTextArea welcomeMessage = new JTextArea("Welcome to the superheros app --- In the console please create a custom ability to use");
        welcomeMessage.setEditable(false);
        welcomeScreen.add(welcomeMessage);
        frame.add(welcomeScreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //This screen will let the user select their opponet
    public void getOpponet(){
      //how many opponets?
    }

    //this screen will let the user choise a action
    public void getAction(){
      //how many actions 
    }
}