package battlesystem.infoItemImpls;

import java.util.ArrayList;

import battlesystem.Entity;
import battlesystem.EntityInfoItem;
import battlesystem.databaseImpls.Effects;
import battlesystem.databaseImpls.Shields;
import battlesystem.databaseImpls.States;
import battlesystem.entityImpls.AIEntity;

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
   * @param maxHealth the maximum health it starts with
   * @param shieldHealth the starting amount of shield
   * @param attackPattern the attack pattern this has
   * @param isTargettable if it is targettable
   */
  public AIInfoItem(String name, int speed, ArrayList<String> abilityNames, ArrayList<Effects.Name> startingEffects,
      ArrayList<Shields.Name> startingShields, int maxHealth, int shieldHealth, ArrayList<String> attackPattern,
      boolean isTargettable) {
    super(name, speed, abilityNames, startingEffects, startingShields, maxHealth, shieldHealth);
    this.attackPattern = attackPattern;
    this.isTargettable = isTargettable;
  }

  /**
   * Create the AI entity
   * @param creator the creator of this entity
   * @return the successfully created ai.
   */
  @Override
  public Entity create(Entity creator) {
    AIEntity ai = new AIEntity(getName(), getSpeed(), getMaxHealth(), getShieldHealth(), States.get(States.Name.NORMAL),
        creator, attackPattern, isTargettable);
    super.addItems(ai);
    return ai;
  }
}