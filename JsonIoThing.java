import org.json.simple.JSONObject.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.io.FileReader;
import java.util.Iterator;
//import java.io.File;
//import java.io.IOException;
import java.util.*;
import java.io.*;
import Game.*;


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
  public void saveSuperheroArr(ArrayList<Superhero> heros){
    JSONObject j = new JSONObject();
    // workin on it
    for(int i=0;i<heros.toArray().length;i++){
      j.put(heros.get(i).getName(),heros.get(i).toSaveable());
    }
    JSONObject t = new JSONObject();
    t.put("Superheros",j);
    try{
      bw.write(t.toString());
      bw.flush();
    }catch(IOException e){
      System.out.println("HEY IOE EXCEPTION IN JASON: "+e);
    }
    
  }
  public void loadSuperheroArr(){
    
    System.out.println("Trying to load superheros: ");
    try{
      Object obj = new JSONParser().parse(new FileReader("save.json"));
      JSONObject superHeros = (org.json.simple.JSONObject) obj;
      if(superHeros.get("Joe")!=null){
        System.out.println("ITS NULL");
      }else{
        System.out.println("ITS NOT NULL");
      }
      JSONObject j = (JSONObject) superHeros.get("Joe");
      

    }catch(Exception e){
      e.printStackTrace();
      System.exit(1);
    }
  }


  public void printStringArrToCons(String[] in) {
    for (int i = 0; i < in.length; i++) {
      System.out.println("Printing " + in[i] + " at " + i);
    }
  }
}