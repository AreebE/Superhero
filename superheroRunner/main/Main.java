package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;

import battlesystem.modifiers.RandomModifier;
import battlesystem.objectMapImpls.States;
import game.OuterGame;


//should we make like a "main menu" type of thing so that
//it would be easier to do stuff like change the file or 
//create new heros and/or abilities and choose to save and load...

class Main {
  public static void main(String[] args) throws FileNotFoundException {
	  File f = new File("res/testState.json");
	  try {
		f.createNewFile();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
//	  new RandomModifier(20);
	  OutputStream o = new FileOutputStream(f);
	  String file = States.get(States.Name.NORMAL).toJson().toString();
	  System.out.println(file);
	  try {
		o.write(file.getBytes());
		System.out.println("written" + f.getAbsolutePath());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
//	     new OuterGame();
  }
}