package modifiers.abilityMods;

import java.util.List;
import java.util.Random;

import org.json.JSONObject;

import gameSystem.Ability;
import gameSystem.BattleLog;
import gameSystem.Entity;
import gameSystem.Game;
import gameSystem.Storage;
import gameSystem.BattleLog.Entry;
import gameSystem.BattleLog.Entry.Interruption;
import gameSystem.BattleLog.Entry.Type;
import gameSystem.abilityImpls.AbilityLoader;

public class RandomModifier implements AbilityModifier{
  private static final Random randomizer = new Random();
  
  private static final String CHANCE_KEY = "chance";
  private int chance;
  
  public RandomModifier(JSONObject json)
  {
	  chance = json.getInt(CHANCE_KEY);
  }
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
        ability.doNotCast();
        return false;
    }
  }

  @Override
  public int getPriority(){
    return Ability.HIT_PRIORITY;
  }

  @Override
	public JSONObject toJson() {
	  JSONObject modifier = new JSONObject();
	  modifier.put(TYPE_KEY, ModifierLoader.RANDOM);
	  modifier.put(CHANCE_KEY, chance);
		return modifier;
	}
  
      @Override 
    public boolean verifyValidity(Storage s)
    {
        return true;
    }
}