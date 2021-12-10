


public class SupportAbility extends Ability{
  private int type;
  private int amount;

  // Effect types
  private Effect template;
 
  
  public SupportAbility(String name, String desc, int cooldown, Effect template, AbilityList.AbilityNames enumName, Element em){
    super(name, desc, cooldown, AbilityList.AbilityType.SUPPORT, enumName, em);
    this.template = template;
  }

  @Override
  protected void castAbility(Superhero target, Superhero caster){
    target.addEffect(template.copyEffect());
  }
  
  @Override
  public Ability copyAbility(){
    return new SupportAbility(getName(), getDescription(), getCooldown(), template, getEnumName(), getElement());
  }
}

//Ideas: arena elemental affects, any sort of effect that causes a player to be at an advantage/disadvantage

//if attack Effect --> all attack plus 1
//if defense Effect --> all attacks minus 1