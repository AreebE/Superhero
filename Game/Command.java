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
    out.add(new Command("print all superhero names","prints all the of the superheros names to cons"));
    out.add(new Command("add abilities to hero","allows you to give abilities to heros"));
    out.add(new Command("print absolute abilities names","Prints all names of every known ability in the game"));
    
    return out;
  }
  public static void onHelp(){
    ArrayList<Command> in = Command.getAll();
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
  public static boolean isItInHere(String in){
    ArrayList<String> thingys = Command.allNames(Command.getAll());
    for(String t:thingys){
      if(t.toLowerCase().equals(in.toLowerCase())){
        return true;
      }
    }
    return false;
  }
}