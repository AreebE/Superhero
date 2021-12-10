// package Game.ablilites.Effects;
// i dont think multiplayer replit and github branches work
// that's probably true
public class Effect {
  
  public interface EffectModifier {
    public void applyEffect(Effect b, Superhero target);
  }

  private final static int PIERCES_DEFENSE_INDEX = 0;
  private final static int PIERCES_SHEILD_INDEX = 1;
  private int strength;
  private EffectList.EffectType typeOfEffect;
  private int duration;
  private boolean permanent;
  private boolean used = false;
  private String name;
  private String desc;
  private boolean[] pierces; 


  public Effect(int strength, EffectList.EffectType type, int duration, boolean permanent, String name, String desc){
    
    this.strength = strength;
    this.typeOfEffect = type;
    this.duration = duration;
    this.permanent = permanent;
    this.name = name;
    this.desc = desc;
    this.pierces = pierces;
  }

  public Effect(int strength, EffectList.EffectType type, int duration, boolean permanent, String name, String desc, boolean[] pierces){
    this.strength = strength;
    this.typeOfEffect = type;
    this.duration = duration;
    this.permanent = permanent;
    this.name = name;
    this.desc = desc;
    this.pierces = pierces;
  }

  /** Will apply its effect to the given superhero
  */
  public void applyEffect(Superhero target){
    // System.out.println("called super");
    
    switch(typeOfEffect){
      case ATTACK:
        // System.out.println("updating attack");
        target.addAttack(strength);
        break; 
      case DEFENSE:
        target.addDefense(strength);      
        break;
      case SUPPORT:
        target.healHealth(strength);
        break;
    }
    reduceDuration(target);
  }

  public void reduceDuration(Superhero target){
     duration--;
    if (duration == 0){
      target.removeEffect(this);
      return;
    }
  }
  /* Creates a copy of the Effect so that two people wouldn't share the same one
  */
  public Effect copyEffect(){
    return new Effect(strength, typeOfEffect, duration, permanent, name, desc);
  }


  protected EffectList.EffectType getEffectType(){
    return this.typeOfEffect;      
  }

  protected int getStrength(){
    return this.strength;
  }

  protected int getDuration(){
    return this.duration;
  }
  
  protected boolean isPermanent(){
    return permanent;
  }

  public String getName(){
    return this.name;
  }

  public String getDesc(){
    return this.desc;
  }

  @Override
  public String toString(){
    return name + " - " + desc + " (" + duration + " turns left)";
  }
}
