public class OneTimeEffect extends Effect {
  private boolean used;

  public OneTimeEffect(int strength, EffectList.EffectType type, int duration, String name, String desc){
    super(strength, type, duration, false, name, desc, null);
  }

  @Override 
  public void applyEffect(Superhero target){
    if (!used){
      applyEffect(getEffectType(), target);
      used = true;
    }
    reduceDuration(target);
  }

  @Override
  public Effect copyEffect(){
    return new OneTimeEffect(getStrength(), getEffectType(), getDuration(), getName(), getDesc());
  }
}