package game;

import java.util.ArrayList;
//note this doesnt actuall do anything other 
//than keep all the names and descriptions 
//together, never actually executes the command
class Command{
  public String name;
  public String desc;
  public String alias;
  public Command(String name,String desc,String alias){
    this.name=name;
    this.desc=desc;
    this.alias=alias;
  }
  public boolean isThis(String in){
    return this.name.equals(in.toLowerCase());
  }
  public static ArrayList<Command> getAll(){
    ArrayList<Command> out = new ArrayList<Command>();
    out.add(new Command("play","plays the game","p"));
    out.add(new Command("save superheros","saves the superheros","ss"));
    out.add(new Command("load superheros","loads the superheros","ls"));
    out.add(new Command("create superhero","prompts you to create your own superhero","cs"));
    out.add(new Command("Exit","Exits the game","e"));
    out.add(new Command("print all superhero names","prints all the of the superheros names to cons","ps"));
    out.add(new Command("add abilities to hero","allows you to give abilities to heros","ah"));
    out.add(new Command("print absolute abilities names","Prints all names of every known ability in the game","pa"));
    
    return out;
  }
  public static void onHelp(){
    ArrayList<Command> in = Command.getAll();
    System.out.println("The Commands are:");
    for(Command t: in){
      System.out.println(t.name +" OR "+t.alias+ " = \n    "+t.desc);
    }
  }
  public static ArrayList<String> allNames(ArrayList<Command> in){
    ArrayList<String> out = new ArrayList<String>();
    for(Command t:in){
      out.add(t.name);
      out.add(t.alias);
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