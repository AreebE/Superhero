package org.json;
import org.json.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.*;
import Game.*;

class JsonIoThing{
  File thisFile;
  JSONObject obj= new JSONObject();
  FileWriter fw;
  BufferedWriter bw;

  public JsonIoThing(String fileName) {
    this.thisFile = new File("save.json");
    try {
      //fs = new Scanner(thisFile);
      fw = new FileWriter(thisFile);
      bw = new BufferedWriter(fw);
    } catch (Exception Ex) {
      System.out.println("HEY exce in FileIoThing init: " + Ex);

    }
    

  }
  public void put(Superhero test){
    obj.put(test);
  }


  public void printStringArrToCons(String[] in) {
    for (int i = 0; i < in.length; i++) {
      System.out.println("Printing " + in[i] + " at " + i);
    }
  }
}