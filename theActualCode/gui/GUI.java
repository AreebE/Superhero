package game.gui;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

import game.battlesystem.Entity;


public class GUI{
    public static GUI theGUI = new GUI();
    public Entity opponet;

    public GUI(){
        JFrame frame = new JFrame();
        JButton jones = new JButton("CLICK ME");
        jones.addActionListener(new ActionThing());
        //jones.addActionListener(new otherThing());
        frame.setSize(300,300);
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        mb.add(m1);
        JMenuItem SaveExit = new JMenuItem("Save and Exit");
        JMenuItem Save = new JMenuItem("PIZZA");
        JMenuItem Exit = new JMenuItem("Exit");
        Save.addActionListener(new ActionThing());
        m1.add(SaveExit);
        m1.add(Save);
        m1.add(Exit);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add( jones);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //This screen will let the user select their opponet
    public void getOpponet(){
      //how many opponets?
    }
    public static GUI giveGUI(){
      return theGUI;
    }

    //this screen will let the user choise a action
    public void getAction(){
      //how many actions 
    }
}