// package Game.ablilites.Effects;

public class InstantEffect extends Effect {
  
  public InstantEffect(int strength, EffectList.EffectType type, String name, String desc, Element element){
    super(strength, type, 0, true, name, desc, element);
  }

  @Override
  public void reduceDuration(Superhero target){
    target.removeEffect(this);
  }



  @Override
  public Effect copyEffect(){
    return new InstantEffect(getStrength(), getEffectType(), getName(), getDesc(), getElement());
  }
}