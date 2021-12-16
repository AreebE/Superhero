
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
 
import java.io.FileReader;
import java.util.Iterator;
//package org.json;
import org.json.*;
import java.io.File;
import java.io.IOException;
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
    JSONParser parser = new JSONParser();
    System.out.println("Trying to load superheros: ");
    try{
      Object obj = parser.parse(new FileReader("save.json"));
      JSONObject p = (JSONObject) obj;
      JSONObject s =(JSONObject) p.get("superheros");
      
      //Superhero out = new Superhero();
    }catch(Exception e){
      System.out.println("ERROR IN JASON "+e);
    }
  }


  public void printStringArrToCons(String[] in) {
    for (int i = 0; i < in.length; i++) {
      System.out.println("Printing " + in[i] + " at " + i);
    }
  }
}