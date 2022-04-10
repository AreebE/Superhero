package battlesystem.modifiers;

import java.util.List;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.BattleLog;
import battlesystem.Entity;
import battlesystem.Game;

public class MultiCastModifier implements AbilityModifier{
  private int times;
  
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
}