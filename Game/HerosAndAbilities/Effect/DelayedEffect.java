public class DelayedEffect extends Effect {
  public DelayedEffect(int strength, EffectList.EffectType type, int timer, String name, String desc, Element element, boolean... pierces){
    super(strength, type, timer, true, name, desc, element, pierces);
  }

  @Override
  public void applyEffect(Superhero target){
    reduceDuration(target);
  }

  @Override 
  protected void removeEffect(Superhero target){
    applyEffect(super.getEffectType(), target);
    target.removeEffect(this);
  }

  @Override
  public Effect copyEffect(){
    return new DelayedEffect(getStrength(), getEffectType(), getDuration(), getName(), getDesc(), getElement(), getPierces());
  }
}