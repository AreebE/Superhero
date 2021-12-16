
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
    JSONArray jarr = new JSONArray();
    // workin on it
    for(int i=0;i<heros.toArray().length;i++){
      JSONObject temp = new JSONObject(heros.get(i));
      jarr.put(i,temp);

      
    }
    System.out.println("AYO jarrr is: "+jarr.toString(1));
    fw.write(jarr.toJSONString());
  }


  public void printStringArrToCons(String[] in) {
    for (int i = 0; i < in.length; i++) {
      System.out.println("Printing " + in[i] + " at " + i);
    }
  }
}