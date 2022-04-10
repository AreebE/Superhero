package battlesystem.infoItemImpls;

import java.util.ArrayList;


import battlesystem.Ability;
import battlesystem.ObjectMap;
import battlesystem.Effect;
import battlesystem.Entity;
import battlesystem.EntityInfoItem;
import battlesystem.Game;
import battlesystem.Shield;
import battlesystem.State;
import battlesystem.entityImpls.AIEntity;
import battlesystem.objectMapImpls.Effects;
import battlesystem.objectMapImpls.Shields;
import battlesystem.objectMapImpls.States;

/**
 * This is for creating an AI info item
 *
 */
public class AIInfoItem extends EntityInfoItem {

  boolean isTargettable;
  ArrayList<String> attackPattern;

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
  public AIInfoItem(
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
    AIEntity ai = new AIEntity(getName(), getSpeed(), getMaxHealth(), getShieldHealth(), g.getState(super.defaultState),
        creator, attackPattern, isTargettable);
    super.addItems(ai, g);
    return ai;
  }
}