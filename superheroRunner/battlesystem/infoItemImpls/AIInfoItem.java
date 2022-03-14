package battlesystem.infoItemImpls;

import java.util.ArrayList;

import battlesystem.Entity;
import battlesystem.EntityInfoItem;
import battlesystem.databaseImpls.Effects;
import battlesystem.databaseImpls.Shields;
import battlesystem.databaseImpls.States;
import battlesystem.entityImpls.AIEntity;

public class AIInfoItem extends EntityInfoItem {

  boolean isTargettable;
  ArrayList<String> attackPattern;

  public AIInfoItem(String name, int speed, ArrayList<String> abilityNames, ArrayList<Effects.Name> startingEffects,
      ArrayList<Shields.Name> startingShields, int maxHealth, int shieldHealth, ArrayList<String> attackPattern,
      boolean isTargettable) {
    super(name, speed, abilityNames, startingEffects, startingShields, maxHealth, shieldHealth);
    this.attackPattern = attackPattern;
    this.isTargettable = isTargettable;
  }

  @Override
  public Entity create(Entity creator) {
    AIEntity ai = new AIEntity(getName(), getSpeed(), getMaxHealth(), getShieldHealth(), States.get(States.Name.NORMAL),
        creator, attackPattern, isTargettable);
    super.addItems(ai);
    return ai;
  }
}