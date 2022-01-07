package battlesystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.util.Iterator;
import java.util.*;
import java.io.*;

class JsonIoThing {
  File thisFile;

  FileWriter fw;
  BufferedWriter bw;

  public JsonIoThing(String fileName) {

    this.thisFile = new File("save.json");

    try {
      // fs = new Scanner(thisFile);
      fw = new FileWriter(thisFile);
      bw = new BufferedWriter(fw);
    } catch (Exception Ex) {
      System.out.println("HEY exce in JsonIoThing init: " + Ex);
    }

  }

  public void saveSuperheroArr(ArrayList<Entity> heros) {
    
    System.out.println("Savin");


    Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
    String json = gson.toJson(heros.get(0));
    System.out.println("\n "+json);
    Entity a = gson.fromJson(json,Entity.class);
    System.out.println("NAMES "+a.getName());

  }

  public void loadSuperheroArr() {
    // there is gson.fromJson(Json,class)
  }

  public void printStringArrToCons(String[] in) {
    for (int i = 0; i < in.length; i++) {
      System.out.println("Printing " + in[i] + " at " + i);
    }
  }
}