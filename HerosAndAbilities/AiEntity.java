package battlesystem;

import java.util.Random;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class AiEntity extends Entity{
  public AiEntity(
    String name, 
    int speed, 
    int health, 
    int shieldHealth,
    State defaultState,
    Entity creator)
  {
    super(name,speed,health,shieldHealth,defaultState,creator);
    
  }
  @Override
  public List<Action> onTurn(ArrayList<Entity> fighters,InnerGame.ScannerInput scanInput){
    System.out.println(this);
    System.out.println("WOOOO Look at me go i have gained sentience(AiEntity needs to be figured out)");
    //List<Action> playerActions = this.getActions(fighters, scanInput);
    return null;
  } 
}