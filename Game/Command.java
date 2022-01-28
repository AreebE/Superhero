package battlesystem;

import java.util.ArrayList;
//note this doesnt actuall do anything other 
//than keep all the names and descriptions 
//together, never actually executes the command
class Command{
  public String name;
  public String desc;
  public Command(String name,String desc){
    this.name=name;
    this.desc=desc;
  }
  public boolean isThis(String in){
    return this.name.equals(in.toLowerCase());
  }
  public static ArrayList<Command> getAll(){
    ArrayList<Command> out = new ArrayList<Command>();
    out.add(new Command("play","plays the game"));
    out.add(new Command("save superheros","saves the superheros"));
    out.add(new Command("load superheros","loads the superheros"));
    out.add(new Command("create superhero","prompts you to create your own superhero"));
    out.add(new Command("save superheros","saves the superheros"));
    out.add(new Command("Exit","Exits the game"));
    return out;
  }
  public static void onHelp(ArrayList<Command> in){
    System.out.println("The Commands are:");
    for(Command t: in){
      System.out.println(t.name + " = "+t.desc);
    }
  }
  public static ArrayList<String> allNames(ArrayList<Command> in){
    ArrayList<String> out = new ArrayList<String>();
    for(Command t:in){
      out.add(t.name);
    }
    out.add("help");
    return out;
  }
  public static boolean isItInHere(String in,ArrayList<String> here){
    for(String t:here){
      if(t.toLowerCase().equals(in.toLowerCase())){
        return true;
      }
    }
    return false;
  }
}