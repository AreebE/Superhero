package battlesystem.abilityImpls;

import java.util.List;

import org.json.JSONObject;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.BattleLog;
import battlesystem.Entity;
import battlesystem.Game;

public class MultiCastModifier implements AbilityModifier{
  private static final String TIMES_KEY = "times";
	private int times;
  
	public MultiCastModifier(JSONObject json)
	{
		times = json.getInt(TIMES_KEY);
	}
  public MultiCastModifier(int times){
    this.times = times;
  } 

  @Override
  public boolean triggerModifier(List<Entity> targets, Entity caster, Ability holder, Game g, BattleLog log)
  {
	  Entity target = targets.get(0);
	  for (int i = 0; i < times; i++)
	  {
		  holder.castAbility(target, caster, g, log);
		  if (!holder.continueAttacking())
		  {
			  Object[] contents = new Object[]{BattleLog.Entry.Interruption.SHIELD};
              log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.INTERRUPTED, contents));
              return false;  
		  }
	  }
	  return true;
  }

  @Override
  public int getPriority(){
    return Ability.HIT_PRIORITY;
  }
  
  @Override
  public JSONObject toJson()
  {
	  JSONObject modifier = new JSONObject();
	  modifier.put(TYPE_KEY, AbilityLoader.MULTI_CAST);
	  modifier.put(TIMES_KEY, times);
	  return modifier;
  }
}