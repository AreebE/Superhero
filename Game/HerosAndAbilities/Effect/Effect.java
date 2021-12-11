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
  private String name;
  private String desc;
  private boolean[] pierces; 


  public Effect(int strength, EffectList.EffectType type, int duration, boolean permanent, String name, String desc){
    this(strength, type, duration, permanent, name, desc, null);
  }

  public Effect(int strength, EffectList.EffectType type, int duration, boolean permanent, String name, String desc, boolean... pierces){
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
    applyEffect(typeOfEffect, target);
    reduceDuration(target);
  }
  

  protected void applyEffect(EffectList.EffectType type, Superhero target){
    switch(typeOfEffect){
      case ATTACK:
        // System.out.println("updating attack");
        target.addAttack(strength);
        break; 
      case DEFENSE:
        target.addDefense(strength);   
        break;
      case HEALTH:
        target.healHealth(strength);
        break;
      case DAMAGE:
        target.dealDamage(strength, pierces[PIERCES_DEFENSE_INDEX], pierces[PIERCES_SHEILD_INDEX]);
    }
  }

  public void reduceDuration(Superhero target){
     duration--;
    if (duration == 0){
      removeEffect(target);
    }
  }

  protected void removeEffect(Superhero target){
    if (!permanent){
      switch (typeOfEffect){
        case ATTACK:
          target.addAttack(-strength * duration);
          break;
        case DEFENSE:
          target.addDefense(-strength * duration);
          break;
      }
    }
    target.removeEffect(this);
  }
  /* Creates a copy of the Effect so that two people wouldn't share the same one
  */
  public Effect copyEffect(){
    return new Effect(strength, typeOfEffect, duration, permanent, name, desc, pierces);
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

  protected boolean[] getPierces(){
    return pierces;
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
