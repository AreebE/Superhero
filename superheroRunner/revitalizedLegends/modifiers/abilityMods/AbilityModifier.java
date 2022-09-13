package revitalizedLegends.modifiers.abilityMods;

import java.util.List;

import org.json.JSONObject;

import revitalizedLegends.gameSystem.Ability;
import revitalizedLegends.gameSystem.BattleLog;
import revitalizedLegends.gameSystem.Entity;
import revitalizedLegends.gameSystem.Game;
import revitalizedLegends.gameSystem.Saveable;

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