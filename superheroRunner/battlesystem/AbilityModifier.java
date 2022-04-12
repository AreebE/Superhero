package battlesystem;

import java.util.List;

import org.json.JSONObject;

/**
 * A class used to modify certain abilities
 */
public interface AbilityModifier {
	public static final String TYPE_KEY = "type";
  public boolean triggerModifier(List<Entity> target, Entity caster, Ability holder, Game g, BattleLog Log);
  public int getPriority();
  
  public JSONObject toJson();
}