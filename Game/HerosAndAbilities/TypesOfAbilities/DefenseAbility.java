

public class DefenseAbility extends Ability {
  int defense = 0;
  public DefenseAbility(String name, String desc, int cooldown, int defense, AbilityList.AbilityNames enumName, Element em){
    super(name, desc, cooldown, AbilityList.AbilityType.DEFENSE, enumName, em);
    this.defense = defense;
  }

  @Override
  protected void castAbility(Superhero target, Superhero caster){
    target.addSheildHealth(defense);
  }

  @Override
  public Ability copyAbility(){
    return new DefenseAbility(getName(), getDescription(), getCooldown(), defense, getEnumName(), getElement());
  }

  protected int getDefense(){
    return defense;
  }
}

//  regeneration, heal, and sheilds/ resistance effects