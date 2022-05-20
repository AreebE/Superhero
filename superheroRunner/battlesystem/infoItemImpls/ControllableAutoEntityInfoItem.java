package battlesystem.infoItemImpls;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import battlesystem.Ability;
import battlesystem.Effect;
import battlesystem.Entity;
import battlesystem.EntityInfoItem;
import battlesystem.Game;
import battlesystem.Shield;
import battlesystem.State;
import battlesystem.entityImpls.ControllableAutoEntity;
import battlesystem.objectMapImpls.Effects;
import battlesystem.objectMapImpls.Shields;
import battlesystem.objectMapImpls.States;

/**
 * This is for creating an AI info item
 *
 */
public class ControllableAutoEntityInfoItem extends EntityInfoItem {

	private static final String TARGETTABLE_KEY = "is targettable";
	private static final String ATTACK_PATTERN_KEY = "attack pattern";
  private boolean isTargettable;
  private ArrayList<String> attackPattern;
  
  public ControllableAutoEntityInfoItem(JSONObject json) {
	  super(json);
	  isTargettable = json.getBoolean(TARGETTABLE_KEY);
	  attackPattern = new ArrayList<>();
	  JSONArray jsonAttackPattern = json.getJSONArray(ATTACK_PATTERN_KEY);
	  for (int i = 0; i < jsonAttackPattern.length(); i++)
	  {
		  attackPattern.add(jsonAttackPattern.getString(i));
	  }
  }

  /**
   * A basic constructor
   * @param name the name of the ai
   * @param speed the speed of this ai
   * @param abilityNames the names of the abilities it has
   * @param startingEffects the effects it starts with
   * @param startingShields the shields it starts with
   * @param defaultState the default state of the ai.
   * @param maxHealth the maximum health it starts with
   * @param shieldHealth the starting amount of shield
   * @param attackPattern the attack pattern this has
   * @param isTargettable if it is targettable
   */
  public ControllableAutoEntityInfoItem(
		  String name, 
		  int speed, 
		  ArrayList<String> abilityNames, 
		  ArrayList<String> startingEffects,
		  ArrayList<String> startingShields, 
		  String defaultState,
		  int maxHealth, 
		  int shieldHealth, 
		  ArrayList<String> attackPattern,
		  boolean isTargettable
		  )
  {
    super(name, speed, abilityNames, startingEffects, startingShields, defaultState, maxHealth, shieldHealth);
    this.attackPattern = attackPattern;
    this.isTargettable = isTargettable;
  }

 

/**
   * Create the AI entity
   * @param creator the creator of this entity
   * @return the successfully created ai.
   */
  @Override
  public Entity create(
		  Entity creator,
		  Game g) 
  {
    ControllableAutoEntity ai = new ControllableAutoEntity(getName(), getSpeed(), getMaxHealth(), getShieldHealth(), g.getState(super.defaultState),
        creator, attackPattern, isTargettable);
    super.addItems(ai, g);
    return ai;
  }
  
  @Override 
  public JSONObject toJson()
  {
	  JSONObject item = super.toJson();
	  item.put(TYPE_KEY, InfoItemReader.PATTERN_CONTROLLABLE_AI_INFO);
	  item.put(TARGETTABLE_KEY, isTargettable);
	  
	  JSONArray pattern = new JSONArray();
	  for (int i = 0; i < attackPattern.size(); i++)
	  {
		  pattern.put(attackPattern.get(i).toLowerCase());
	  }
	  item.put(ATTACK_PATTERN_KEY, pattern);
	  return item;
  }
}