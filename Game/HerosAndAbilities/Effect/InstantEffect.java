// package Game.ablilites.Effects;

public class InstantEffect extends Effect {
  
  public InstantEffect(int strength, EffectList.EffectType type, String name, String desc){
    super(strength, type, 0, true, name, desc);
  }

  @Override
  public void applyEffect(Superhero hero){
    // System.out.println("called instant");
    EffectList.EffectType typeOfEffect = getEffectType();
    System.out.println(typeOfEffect);
    int strength = this.getStrength();
    switch(typeOfEffect){
      case ATTACK:
      // System.out.println("Adding attack");
        hero.addAttack(strength);
        break; 
      case DEFENSE:
        hero.addDefense(strength);      
        break;
      case SUPPORT:
        hero.healHealth(strength);
        break;
    }
    hero.removeEffect(this); 
  }

  @Override
  public Effect copyEffect(){
    return new InstantEffect(getStrength(), getEffectType(), getName(), getDesc());
  }
}