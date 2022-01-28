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


  public AbilityManager() {
    //System.out.println("ABILITYMANAGER IS BEING MADE");
    
    

  }

  public void loadAbs(){
    try {
      this.allAbilities = JsonIoThing.loadAbArray("FileParsing/Abilities.json");
    } catch (Exception e) {
      //e.printStackTrace();
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
    JsonIoThing.saveAbArray(this.allAbilities,"FileParsing/Abilities.json");
  }

  // only intended to be called incase it doesnt find anything in the file
  // or some other error happens
  public void loadDefaultAbs(){
    Abilities.Name[] allNames = Abilities.Name.values();
    for(Abilities.Name t:allNames){
      //System.out.println("IN LOADDEF LOOP");
      allAbilities.add(Abilities.getAbility(t));
    }
  }

}