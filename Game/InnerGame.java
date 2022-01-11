package battlesystem;
import java.util.ArrayList;
import java.util.Scanner;

class InnerGame {
  ArrayList<Entity> fighters;
  GUI g;
  Scanner inputReader = new Scanner(System.in);
  Terrain t = new Terrain();

  public InnerGame(ArrayList<Entity> fighters, GUI g) {
    this.fighters = fighters;
    this.g = g;
    System.out.println("Starting game!");
  }

  public void playGame() {
    int remainingFighters =fighters.toArray().length;
    System.out.println("rem players is "+remainingFighters);
    while(remainingFighters > 1){
      Entity turned = fighters.get(1);
      System.out.println(turned);



      remainingFighters = 0;
    }
    //why is terriain given to the superhero??
    //fighters.get(0).setTerrain(t);
    //fighters.get(1).setTerrain(t);
    /*
    int removeCount = 0;
    while (removeCount < 2) {
      for (int i = 0; i < fighters.size(); i++) {
        //we need to figure out how to determine what is currentPlayer
        //currentPlayer = fighters.get(i);
        //System.out.println(currentPlayer);
        //List<Action> playerActions = currentPlayer.getActions(fighters, system);

      }
      for (int i = fighters.size() - 1; i >= 0; i--) {
        if (fighters.get(i).isHealthZero()) {
          Entity target = fighters.remove(i);
          System.out.println(target.getName() + " was eliminated. " + target.toString());
          removeCount++;
        }
      }
      
    }
    */
    //System.out.println(fighters);

  }
  public int isLastOne(){
    int t=fighters.toArray().length;
    for(Entity e:fighters){
      if(e.isHealthZero()){
        t--;
      }
      System.out.println("numOfFightersAliveIs: "+t);
    }
    return t;
  }
}

  

