

public class AttackAbility extends Ability{
  private int strength;
  private boolean isPiercing;
  private boolean ignoresBaseDefense;

  public AttackAbility(String name, String desc, int cooldown, int strength, AbilityList.AbilityNames enumName, Element em, boolean ignoresBaseDefense, boolean isPiercing){
    super(name, desc, cooldown, AbilityList.AbilityType.ATTACK, enumName, em);
    this.strength = strength;
    this.isPiercing= isPiercing;
    this.ignoresBaseDefense = ignoresBaseDefense;
  }

  @Override 
  protected void castAbility(Superhero target, Superhero caster){
    // System.out.println("Attack Ability \'" + this.getName() + "\' used on player " + target.getName());
    if (getEnumName().equals(AbilityList.AbilityNames.PASS_TURN)){
      return;
    }
    int attackStrength = this.strength + caster.getBaseAttack();
    if (attackStrength < 0){
      attackStrength = 0;
    }
    target.dealDamage(attackStrength, isPiercing, ignoresBaseDefense);
  }

  @Override
  public Ability copyAbility(){
    return new AttackAbility(getName(), getDescription(), getCooldown(), strength, getEnumName(), getElement(), ignoresBaseDefense, isPiercing);
  }

  @Override
  public String toString(){
    StringBuilder sBuilder = new StringBuilder(super.toString());
    if (ignoresBaseDefense){
      sBuilder.append(" (It ignores the base defense) ");
    }
    if (isPiercing){
      sBuilder.append(" (It pierces the sheild) ");
    }
    return sBuilder.toString();
  }
}
// 