import javax.swing.*;
import java.awt.*;

public class GUI {

  public GUI(){

    //5 abilites per superhero + pass turn

    JFrame frame = new JFrame();
    frame.setSize(200, 400);
    JPanel welcomeScreen = new JPanel();

    welcomeScreen.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

    JTextArea welcome = new JTextArea("Hello! Welcome to the Superhero Project. Down in the console please enter the questions asks. When finshed please select the Next Button.");
    //look into how to resize this...
    welcome.setEditable(false);
    welcomeScreen.add(welcome);

    frame.add(welcomeScreen);
    
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }
}