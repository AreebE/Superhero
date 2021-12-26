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
  public AbilityList.ModifierName getModifier(){
    return AbilityList.ModifierName.MULTICAST;
  }
}