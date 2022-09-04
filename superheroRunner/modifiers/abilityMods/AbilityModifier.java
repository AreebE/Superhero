package modifiers.abilityMods;

import java.util.List;

import org.json.JSONObject;

import gameSystem.Ability;
import gameSystem.BattleLog;
import gameSystem.Entity;
import gameSystem.Game;
import gameSystem.Saveable;

/**
 * A class used to modify certain abilities
 */
public interface AbilityModifier 
    extends Saveable
{
	public static final String TYPE_KEY = "type";
  public boolean triggerModifier(List<Entity> target, Entity caster, Ability holder, Game g, BattleLog Log);
  public int getPriority();
  
  public JSONObject toJson();
}