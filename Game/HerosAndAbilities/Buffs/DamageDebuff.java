
public class DamageDebuff extends Buff{
  
  private boolean isPiercing;
  private boolean ignoresDefense;

  public DamageDebuff(int strength, int duration, boolean permanent, String name, String desc, boolean isPiercing, boolean ignoresDefense){
    super(strength, BuffList.BuffType.NONE, duration, permanent, name, desc);
    this.isPiercing = isPiercing;
    this.ignoresDefense = ignoresDefense;
  }


  @Override 
  public void applyBuff(Superhero target){
    target.dealDamage(super.getStrength(), isPiercing, ignoresDefense);
  }

  
}