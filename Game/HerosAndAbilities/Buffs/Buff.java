// package Game.ablilites.Buffs;
public class Buff {
  
  public interface BuffModifier {
    public void applyEffect(Buff b, Superhero target);
  }

  private int strength;
  private BuffList.BuffType typeOfBuff;
  private int duration;
  private boolean permanent;
  private boolean used = false;
  private String name;
  private String desc;

  public Buff(int strength, BuffList.BuffType type, int duration, boolean permanent, String name, String desc){
    this.strength = strength;
    this.typeOfBuff = type;
    this.duration = duration;
    this.permanent = permanent;
    this.name = name;
    this.desc = desc;
  }

  /** Will apply its effect to the given superhero
  */
  public void applyBuff(Superhero target){
    // System.out.println("called super");
    
    switch(typeOfBuff){
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
      target.removeBuff(this);
      return;
    }
  }
  /* Creates a copy of the buff so that two people wouldn't share the same one
  */
  public Buff copyBuff(){
    return new Buff(strength, typeOfBuff, duration, permanent, name, desc);
  }


  protected BuffList.BuffType getBuffType(){
    return this.typeOfBuff;      
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
