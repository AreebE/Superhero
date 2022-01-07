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
  BufferedWriter bw;
  FileReader fr;
  

  public JsonIoThing(String fileName) {
    this.thisFile = new File(fileName);
    this.thisFileName = fileName;
    this.thisFilePath = Path.of(thisFileName);
    try {
      // fs = new Scanner(thisFile);
      fw = new FileWriter(thisFile);
      bw = new BufferedWriter(fw);
      fr = new FileReader(thisFile);
    } catch (Exception Ex) {
      Ex.printStackTrace();
      System.exit(1);
    }

  }

  public void saveSuperheroArr(ArrayList<Entity> heros) {
    System.out.println("\n\u001B[33m"+"Savin Entities... "+"\u001B[0m\n ");
    Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
    String json = gson.toJson(heros);
    try{
      Files.writeString(thisFilePath,json);
    }catch(IOException e){
      e.printStackTrace();
      System.exit(1);
    }
    

  }

  public Entity[] loadSuperheroArr() {
    System.out.println("\n\u001B[33m"+"loadin Entities... "+"\u001B[0m\n ");
    Entity[] out = new Entity[1];
    try{
      out= new Gson().fromJson(Files.readString(thisFilePath),Entity[].class);
    }catch(IOException e){
      e.printStackTrace();
      System.exit(1);
    }
    return out;
  }
}