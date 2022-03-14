package modifiers;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.Entity;

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