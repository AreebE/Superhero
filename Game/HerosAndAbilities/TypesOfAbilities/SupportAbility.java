


public class SupportAbility extends Ability{
  private int type;
  private int amount;

  // Buff types
  private Buff template;
 
  
  public SupportAbility(String name, String desc, int cooldown, Buff template, Integer id, Element em){
    super(name, desc, cooldown, AbilityList.AbilityType.SUPPORT, id, em);
    this.template = template;
  }

  @Override
  protected void castAbility(Superhero target, Superhero caster){
    target.addBuff(template.copyBuff());
  }
  
  @Override
  public Ability copyAbility(){
    return new SupportAbility(getName(), getDescription(), getCooldown(), template, getID(), getElement());
  }
}

//Ideas: arena elemental affects, any sort of effect that causes a player to be at an advantage/disadvantage

//if attack buff --> all attack plus 1
//if defense buff --> all attacks minus 1