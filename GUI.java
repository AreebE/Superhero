import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {


  public void GUI(){

    //5 abilites per superhero + pass turn
    Boolean start = false;

    JFrame frame = new JFrame();
    frame.setSize(200, 400);
    JPanel welcomeScreen = new JPanel();

    welcomeScreen.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

    welcomeScreen.setLayout(new GridLayout(4, 0));
    JTextArea welcome = new JTextArea("Hello! Welcome to the Superhero Project. ");
    JTextArea w2 = new JTextArea("Down in the console please enter the questions asks.");
    JTextArea w3 = new JTextArea(" When finshed please select the Next Button.");
    //look into how to resize this...
    welcome.setEditable(false);
    w2.setEditable(false);
    w3.setEditable(false);
    welcomeScreen.add(welcome);
    welcomeScreen.add(w2);
    welcomeScreen.add(w3);

    JButton next = new JButton("NEXT");
    welcomeScreen.add(next);
    next.addActionListener(this);
    
    frame.add(welcomeScreen);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    while(start == false){
      System.out.println("this works so far");
      start = true;

    }

  }

  @Override
  public void actionPerformed(ActionEvent e){

  }
}