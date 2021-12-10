import java.io.File;
import java.util.*;
import java.io.*;

class FileIoThing {
  File thisFile;
  char seperChar = ';';
  Scanner fs;
  int lines;
  boolean append = true;
  FileWriter fw;
  BufferedWriter bw;

  public FileIoThing(String fileName) {
    this.thisFile = new File(fileName);
    try {
      fs = new Scanner(thisFile);
      lines = countLines();
      fw = new FileWriter(thisFile, append);
      bw = new BufferedWriter(fw);
    } catch (Exception Ex) {
      System.out.println("HEY exce in FileIoThing init: " + Ex);

    }

  }

  public void setAppend(boolean i) {
    try {
      append = i;
      fw = new FileWriter(thisFile, append);
      bw = new BufferedWriter(fw);
    } catch (IOException e) {
      System.out.println("error at SetAppend");
    }

  }

  public void setSeperChar(char i) {
    this.seperChar = i;
  }

  public String[] giveAllLines() {
    String[] out = new String[lines];
    // System.out.println("linesis "+lines);
    for (int i = 0; i < lines; i++) {
      out[i] = fs.nextLine();
      // System.out.println("ThisLineis "+out[i]);
    }
    return out;
  }

  public int countLines() throws IOException {
    // System.out.println("in countlines");
    Scanner tfs = new Scanner(thisFile);
    int c = 0;
    while (tfs.hasNextLine()) {
      tfs.nextLine();
      c++;
    }
    // System.out.println("int c is "+c);
    tfs.close();
    return c;
  }

  public void writeStringArrToTxt(String[] toWrite) throws IOException {
    boolean fL= false;
    for (int i = 0; i < toWrite.length; i++) {

      if (append && !fL) {
        bw.newLine();
      }
      bw.write(toWrite[i]);
      bw.flush();
      fL=false;

    }
    bw.close();
  }

  public void printStringArrToCons(String[] in) {
    for (int i = 0; i < in.length; i++) {
      System.out.println("Printing " + in[i] + " at " + i);
    }
  }

  public String makeStringArrIntoString(String[] in) {
    String out = in[0];
    for (int i = 0; i < in.length - 1; i++) {
      out = out + seperChar + in[i + 1];
    }
    return out;
  }

  public String[] cutThisLineUp(String in) {
    // System.out.println("AYO IN IS " + in);
    char[] chars = in.toCharArray();

    int old = 0;
    String[] newer = new String[1];
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == seperChar) {
        newer = add(newer, in.substring(old, i));
        old = i + 1;
      }
    }
    newer = add(newer, in.substring(old, chars.length));
    return newer;
  }

  private String[] add(String[] old, String toAdd) {
    String[] o = new String[(old.length + 1)];
    for (int i = 0; i < old.length; i++) {
      o[i] = old[i];
    }
    o[old.length] = toAdd;
    return o;
  }
}