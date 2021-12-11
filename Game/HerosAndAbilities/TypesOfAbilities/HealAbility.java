public class HealAbility extends DefenseAbility{
  public HealAbility(String name, String desc, int cooldown, int strength, AbilityList.AbilityNames enumName, Element em, AbilityModifier... modifiers){
    super(name, desc, cooldown, strength, enumName, em, modifiers);
  }

  @Override 
  protected void castAbility(Superhero target, Superhero caster) {
    target.healHealth(getStrength());
  };

  @Override
  public Ability copyAbility(){
    return new HealAbility(getName(), getDescription(), getCooldown(), getStrength(), getEnumName(), getElement());
  }
}