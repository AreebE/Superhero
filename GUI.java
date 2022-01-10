import javax.swing.*;
import java.awt.*;

public class GUI{

  public GUI(){

    //5 abilites per superhero + pass turn

    JFrame frame = new JFrame();
    frame.setSize(500, 500);
    JPanel panel = new JPanel();

    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
    frame.add(panel);
    panel.setLayout(new GridLayout(0, 2));

    JButton attack = new JButton("Attack");
    JButton defense = new JButton("Defense");
    JButton pass = new JButton("Pass");
    JButton summon = new JButton("Summon");
    JButton boost = new JButton("Boost");
    panel.add(attack);
    panel.add(defense);
    panel.add(pass);
    panel.add(summon);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }
}