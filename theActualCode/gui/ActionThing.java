package game.gui;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class ActionThing implements ActionListener{
  public ActionThing(){
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    System.out.println("BUTTON DOES THINGS");
    JButton j =(JButton) arg0.getSource();
    j.setLocation(100,200);
    System.out.println("UHHHH");
  }
}
