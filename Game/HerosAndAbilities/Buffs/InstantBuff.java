// package Game.ablilites.Buffs;

public class InstantBuff extends Buff {
  
  public InstantBuff(int strength, BuffList.BuffType type, String name, String desc){
    super(strength, type, 0, true, name, desc);
  }

  @Override
  public void applyBuff(Superhero hero){
    // System.out.println("called instant");
    BuffList.BuffType typeOfBuff = getBuffType();
    System.out.println(typeOfBuff);
    int strength = this.getStrength();
    switch(typeOfBuff){
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
    hero.removeBuff(this); 
  }

  @Override
  public Buff copyBuff(){
    return new InstantBuff(getStrength(), getBuffType(), getName(), getDesc());
  }
}