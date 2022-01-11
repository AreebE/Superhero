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
    } catch (Exception Ex) {
      Ex.printStackTrace();
      System.exit(1);
    }

  }

  public void saveSuperheroArr(ArrayList<Entity> heros) {
    System.out.println("\n\u001B[33m"+"Savin Entities... "+"\u001B[0m\n ");
    try{
    fw = new FileWriter(thisFile);
    } catch (Exception Ex) {
      Ex.printStackTrace();
      System.exit(1);
    }
    
    
    Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
    String json = gson.toJson(heros);
    try{
      Files.writeString(thisFilePath,json);
    }catch(IOException e){
      e.printStackTrace();
      System.exit(1);
    }
    

  }

  public ArrayList<Entity> loadSuperheroArr() {
    System.out.println("\n\u001B[33m"+"loadin Entities... "+"\u001B[0m\n ");
    Entity[] o = null;
    try{
      o=  new Gson().fromJson(Files.readString(thisFilePath),Entity[].class);
      //System.out.println(o[0].getName());

    }catch(IOException e){
      e.printStackTrace();
      System.exit(1);
    }
    ArrayList<Entity> out = new ArrayList<Entity>(Arrays.asList(o));
    for(Entity t:out){
      t.updateAbilities();
    }
    //System.out.println("ps in load is "+out.toArray().length);
    
    return out;
  }
}