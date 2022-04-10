package battlesystem.modifiers;

import java.util.List;
import java.util.Random;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.BattleLog;
import battlesystem.Entity;
import battlesystem.Game;

public class RandomModifier implements AbilityModifier{
  private static final Random randomizer = new Random();
  private int chance;
  
  public RandomModifier(int chance){
    this.chance = chance;
  } 
  
  @Override
  public boolean triggerModifier(List<Entity> targets, Entity caster, Ability ability, Game g, BattleLog log)
  {
//    System.out.println("triggered random");
    if (randomizer.nextInt(Ability.MAX_CHANCE) < chance)
    {
      return true;
    } else 
    {
    	Object[] contents = new Object[]{BattleLog.Entry.Interruption.RANDOM};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.INTERRUPTED, contents));
      return false;
    }
  }

  @Override
  public int getPriority(){
    return Ability.HIT_PRIORITY;
  }
}