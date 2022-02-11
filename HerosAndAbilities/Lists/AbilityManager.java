package battlesystem;

import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.List;

import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.util.HashSet;
import java.net.URI;

class AbilityManager {
  public static ArrayList<Ability> staticListAll = null;
  public ArrayList<Ability> allAbilities = new ArrayList<>();
  //private AbilityManager abilityManager = OuterGame.getAbManager();
  public AbilityManager() {
    load();
  }

  public void load() {
    //System.out.println("\u001B[31m WARNING LOAD AB ARRAY COULD NOT LOAD \n LOADING DEFAULT INSTEAD... \u001B[37m");
    loadDefaultAbs();
    this.statify();
  
  }

  public void addAbilityToList(Ability in) {
    this.allAbilities.add(in);
  }

  public static Ability getAb(String in) {
    for (Ability t : staticListAll) {
      if (t.name.equals(in)) {
        return t;
      }
    }
    return null;
  }



  // only intended to be called incase it doesnt find anything in the file
  // or some other error happens
  public void loadDefaultAbs() {
    this.allAbilities = AbilityStorage.getAll();
    this.statify();
  }

  public void printAllNames() {
    System.out.println(allAbStrings());
  }

  public static Ability getAbility(String input) {
    input = input.toLowerCase();
    staticListAll.remove(null);
    for (Ability t : staticListAll) {
      //System.out.println("Suff is "+" "+t.getName());
      if (t.getName().toLowerCase().equals(input.toLowerCase())) {
        return t.copy();
      }
    }
    return null;
  }

  public static ArrayList<String> allAbStrings() {
    ArrayList<String> out = new ArrayList<String>();
    for (Ability t : staticListAll) {
      out.add(t.getName());
    }
    return out;
  }

  public static void giveAbilities(Entity target, ArrayList<String> names) {
    for (String name : names) {
      if (!staticListAll.contains(name)) {
        target.addAbility(getAbility(name).copy());
      }
    }
  }
  
  public void statify(){
    this.staticListAll = this.allAbilities;
  }
  //methods for the static calls
  
  

}