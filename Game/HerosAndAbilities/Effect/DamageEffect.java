
public class DamageEffect extends Effect{
  
  private boolean isPiercing;
  private boolean ignoresDefense;

  public DamageEffect(int strength, int duration, boolean permanent, String name, String desc, boolean isPiercing, boolean ignoresDefense){
    super(strength, EffectList.EffectType.NONE, duration, permanent, name, desc);
    this.isPiercing = isPiercing;
    this.ignoresDefense = ignoresDefense;
  }


  @Override 
  public void applyEffect(Superhero target){
   
    target.dealDamage(super.getStrength(), isPiercing, ignoresDefense);
    reduceDuration(target);
  }

  @Override 
  public Effect copyEffect(){
    return new DamageEffect(super.getStrength(), super.getDuration(), super.isPermanent(), super.getName(), super.getDesc(), this.isPiercing, this.ignoresDefense);
  }
  
}