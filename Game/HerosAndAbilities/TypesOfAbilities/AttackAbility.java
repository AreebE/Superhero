

public class AttackAbility extends Ability{
  private int strength;
  private boolean isPiercing;
  private boolean ignoresBaseDefense;

  public AttackAbility(String name, String desc, int cooldown, int strength, Integer id, Element em, boolean ignoresBaseDefense, boolean isPiercing){
    super(name, desc, cooldown, AbilityList.AbilityType.ATTACK, id, em);
    this.strength = strength;
    this.isPiercing= isPiercing;
    this.ignoresBaseDefense = ignoresBaseDefense;
  }

  @Override 
  protected void castAbility(Superhero target, Superhero caster){
    // System.out.println("Attack Ability \'" + this.getName() + "\' used on player " + target.getName());
    if (getID() == AbilityList.PASS_TURN){
      return;
    }
    target.dealDamage(strength + caster.getBaseAttack(), isPiercing, ignoresBaseDefense);
  }

  @Override
  public Ability copyAbility(){
    return new AttackAbility(getName(), getDescription(), getCooldown(), strength, getID(), getElement(), ignoresBaseDefense, isPiercing);
  }
}
// 