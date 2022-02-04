package battlesystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.net.URI;
import Game.*;
import java.util.Arrays;

class JsonIoThing {
  public JsonIoThing() {
  }

  public static void saveSuperheroArr(ArrayList<Entity> heros,String s) {
    System.out.println("\n\u001B[33m" + "Savin Entities... " + "\u001B[0m\n ");
    ArrayList<EntityInfoItem> toSave = new ArrayList<>();
    File file = new File(s);
    for (Entity t : heros) {
      toSave.add(t.toEII());
    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    try {
      FileWriter fw = new FileWriter(file);
      gson.toJson(toSave, fw);
      fw.flush();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(69);
    }
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
        if (t == null)
        {
            continue;
        }
      out.add(new Entity(t));
    }
    System.out.println("\u001B[32mLoad Heros Success!\u001B[0m");
    return out;
  }

  public static void saveAbArray(ArrayList<Ability> in,String s) {
    System.out.println("\n\u001B[33m" + "Savin Abilities... " + "\u001B[0m\n ");
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    File file = new File(s);
    try {
      FileWriter fw = new FileWriter(file);
      gson.toJson(in.toArray(), fw);
      fw.flush();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(69);
    }
    System.out.println("\u001B[32mAbility Save Success!\u001B[0m");

  }

  public static ArrayList<Ability> loadAbArray(String s) {
    File file = new File(s);
    System.out.println("\n\u001B[33m" + "loadin Abilites... " + "\u001B[0m\n ");
    try {
      Ability[] o = new Gson().fromJson(Files.readString(file.toPath()), Ability[].class);
      ArrayList<Ability> out = new ArrayList<Ability>();
      for (Ability t : o) {
        out.add(t);
      }
       System.out.println("\u001B[32mLoad Abs Success!\u001B[0m");
      return out;
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(69);
    }
   

    return null;
  }
}