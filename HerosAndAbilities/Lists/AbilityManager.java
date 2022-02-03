package battlesystem;

import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.List;
import java.util.HashSet;
import java.net.URI;

class AbilityManager {
  // this is indended to sorta "Replace" allAbilities
  // cause with Abilites i cant change or edit the
  // allABILITIES hashmap
  public static enum Type {
    ATTACK, DEFENSE, SUPPORT
  }

  public ArrayList<Ability> allAbilities = new ArrayList<>();
  //private AbilityManager abilityManager = OuterGame.getAbManager();
  public AbilityManager() {
  }

  public void load() {
    try {
      this.allAbilities = JsonIoThing.loadAbArray("FileParsing/Abilities.json");
    } catch (Exception e) {
      // e.printStackTrace();
      System.out.println("\u001B[31m WARNING LOAD AB ARRAY COULD NOT LOAD \n LOADING DEFAULT INSTEAD... \u001B[37m");
      loadDefaultAbs();
    }
  }

  public void addAbilityToList(Ability in) {
    this.allAbilities.add(in);
  }

  public Ability getAb(String in) {
    for (Ability t : allAbilities) {
      if (t.name.equals(in)) {
        return t;
      }
    }
    return null;
  }

  public void save() {
    JsonIoThing.saveAbArray(this.allAbilities, "FileParsing/Abilities.json");
  }

  // only intended to be called incase it doesnt find anything in the file
  // or some other error happens
  public void loadDefaultAbs() {
    this.allAbilities = AbilityStorage.getAll();
  }

  public void printAllNames() {
    System.out.println(allAbStrings());
  }

  public Ability getAbility(String input) {
    input = input.toLowerCase();
    allAbilities.remove(null);
    for (Ability t : allAbilities) {
      if (t.getName().toLowerCase().equals(input.toLowerCase())) {
        return t;
      }
    }
    return null;
  }

  public ArrayList<String> allAbStrings() {
    ArrayList<String> out = new ArrayList<String>();
    for (Ability t : allAbilities) {
      out.add(t.getName());
    }
    return out;
  }

  public void giveAbilities(Entity target, ArrayList<String> names) {
    for (String name : names) {
      if (!allAbilities.contains(name)) {
        target.addAbility(getAbility(name).copy());
      }
    }
  }
  

}