package game.battlesystem.modifiers;

import game.battlesystem.Ability;
import game.battlesystem.AbilityModifier;
import game.battlesystem.Entity;

public class MultiCastModifier implements AbilityModifier<Integer>{
  private int times;
  
  public MultiCastModifier(int times){
    this.times = times;
  } 

  @Override 
  public Integer triggerModifier(Entity target, Entity caster){
    return times;
  }

  @Override
  public Ability.Modifier getModifier(){
    return Ability.Modifier.MULTICAST;
  }
}