// package Game.ablilites.Buffs;
public class Buff {

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
  public void applyBuff(Superhero hero){
    // System.out.println("called super");
    
    switch(typeOfBuff){
      case ATTACK:
        // System.out.println("updating attack");
        hero.addAttack(strength);
        break; 
      case DEFENSE:
        hero.addDefense(strength);      
        break;
      case SUPPORT:
        hero.healHealth(strength);
        break;
    }
    if (duration == 0){
      hero.removeBuff(this);
      return;
    }
    duration--;
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
