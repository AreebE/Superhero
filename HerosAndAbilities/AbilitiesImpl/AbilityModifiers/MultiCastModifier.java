public class MultiCastModifier implements AbilityModifier<Integer>{
  private int times;
  
  public MultiCastModifier(int times){
    this.times = times;
  } 

  @Override 
  public Integer triggerModifier(Superhero target, Superhero caster){
    return times;
  }

  @Override
  public AbilityList.ModifierName getModifier(){
    return AbilityList.ModifierName.MULTICAST;
  }
}