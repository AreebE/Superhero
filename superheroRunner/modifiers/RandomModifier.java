package modifiers;

import java.util.Random;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.Entity;

public class RandomModifier implements AbilityModifier<Boolean>{
  private static final Random randomizer = new Random();
  private int chance;
  
  public RandomModifier(int chance){
    this.chance = chance;
  } 

  @Override 
  public Boolean triggerModifier(Entity target, Entity caster){
    System.out.println("triggered random");
    if (randomizer.nextInt(Ability.MAX_CHANCE) < chance){
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Ability.Modifier getModifier(){
    return Ability.Modifier.RANDOM;
  }
}