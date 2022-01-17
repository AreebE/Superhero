package battlesystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import Game.*;

class JsonIoThing {
  File thisFile;
  String thisFileName;
  Path thisFilePath;

  FileWriter fw;
  FileReader fr;
  

  public JsonIoThing(String fileName) {
    this.thisFile = new File(fileName);
    this.thisFileName = fileName;
    this.thisFilePath = Path.of(thisFileName);
    try {
      fr = new FileReader(thisFile);
      fw = new FileWriter(thisFile);
    } catch (Exception Ex) {
      Ex.printStackTrace();
      System.exit(1);
    }

  }

  public void saveSuperheroArr(ArrayList<Entity> heros) {
    System.out.println("\n\u001B[33m"+"Savin Entities... "+"\u001B[0m\n ");
    ArrayList<EntityInfoItem> toSave = new ArrayList<>();

    for(Entity t: heros){
      toSave.add(t.toEII());
    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    System.out.println("the printy thing is "+gson.toJson(toSave));
    try{
      gson.toJson(toSave,fw);

      fw.flush();
    }catch(IOException e){
      e.printStackTrace();
      System.exit(69);
    }

  }


  public ArrayList<Entity> loadSuperheroArr() {
    
    System.out.println("\n\u001B[33m"+"loadin Entities... "+"\u001B[0m\n ");
    
    EntityInfoItem[] o = null;
    try{
      o=new Gson().fromJson(Files.readString(thisFilePath),EntityInfoItem[].class);
    }catch(IOException e){
      e.printStackTrace();
      System.exit(69);
    }
    ArrayList<Entity> out = new ArrayList<Entity>();
    for(EntityInfoItem t:o){
      out.add(new Entity(t));
    }
    
    return out;
  }
}