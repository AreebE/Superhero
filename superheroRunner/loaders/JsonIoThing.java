package loaders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;
import java.nio.file.*;
import java.io.*;

import java.net.URI;
import game.*;
import battlesystem.Entity;
import battlesystem.EntityInfoItem;

import java.util.Arrays;

public class JsonIoThing {
  public JsonIoThing() {
  }

  public static void saveSuperheroArr(ArrayList<Entity> heros,String s) {
    System.out.println("\n\u001B[33m" + "Savin Entities... " + "\u001B[0m\n ");
    ArrayList<EntityInfoItem> toSave = new ArrayList<>();
    File file = new File(s);
//    for (Entity t : heros) {
//      toSave.add(t.toEII());
//    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    saveObj(toSave, s);
    System.out.println("\u001B[32mSave Success!\u001B[0m");

  }

  public static ArrayList<Entity> loadSuperheroArr(String s) {
    File file = new File(s);
    System.out.println("\n\u001B[33m" + "loadin Entities... " + "\u001B[0m\n ");
    EntityInfoItem[] o = null;
    try {
      o = new Gson().fromJson(Files.readString(file.toPath()), EntityInfoItem[].class);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(69);
    }
    ArrayList<Entity> out = new ArrayList<Entity>();
    for (EntityInfoItem t : o) {
//    	System.out.println(t.abilities);
        if (t == null)
        {
          continue;
        }
      out.add(new Entity(t));
    }
    System.out.println("\u001B[32mLoad Heros Success!\u001B[0m");
    return out;
  }


    
   


  
  public static void saveObj( Object in,String fileName){
    try{
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      FileWriter fw = new FileWriter(new File(fileName));
      gson.toJson(in, fw);
      fw.flush();
    }catch (IOException e) {
      e.printStackTrace();
      System.exit(69);
    }
  }
}