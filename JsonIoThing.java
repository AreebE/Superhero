import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import java.io.FileReader;
import java.util.Iterator;
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
      fr = new FileReader();
    } catch (Exception Ex) {
      Ex.printStackTrace();
      System.exit(1);
    }

  }

  public void saveSuperheroArr(ArrayList<Superhero> heros) {
    System.out.println("\n\u001B[33m"+"Savin Superheros... "+"\u001B[0m\n ");
    Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
    String json = gson.toJson(heros);
    try{
      Files.writeString(thisFilePath,json);
      //System.out.println("\n "+json);
    }catch(IOException e){
      e.printStackTrace();
      System.exit(1);
    }
    

  }

  public Superhero[] loadSuperheroArr() {
    System.out.println("\n\u001B[33m"+"loadin Superheros... "+"\u001B[0m\n ");
    Superhero[] out = new Superhero[1];
    try{
      out= new Gson().fromJson(Files.readString(thisFilePath),Superhero[].class);
    }catch(IOException e){
      e.printStackTrace();
      System.exit(1);
    }
    return out;
  }
}