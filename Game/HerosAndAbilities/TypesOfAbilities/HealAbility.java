public class HealAbility extends DefenseAbility{
  public HealAbility(String name, String desc, int cooldown, int healPower, AbilityList.AbilityNames enumName, Element em){
    super(name, desc, cooldown, healPower, enumName, em);
  }

  @Override 
  protected void castAbility(Superhero target, Superhero caster) {
    int strength = super.getDefense();
    target.healHealth(strength);
  };

  @Override
  public Ability copyAbility(){
    return new HealAbility(getName(), getDescription(), getCooldown(), getDefense(), getEnumName(), getElement());
  }
}