import java.util.EnumMap;

public class CleanseAbility extends SupportAbility {
  public CleanseAbility(String name, String desc, int cooldown, AbilityList.AbilityNames enumName, Element em, AbilityModifier... modifiers){
    super(name, desc, cooldown, null, enumName, em, modifiers);    
  }

  private CleanseAbility(String name, String desc, int cooldown, AbilityList.AbilityNames enumName, Element em, EnumMap<AbilityList.AbilityModifierNames, AbilityModifier> modifiers){
    super(name, desc, cooldown, null, enumName, em, modifiers);
  }

   @Override
  protected void castAbility(Superhero target, Superhero caster){
    target.removeEffects(getElement().getID());
  }

  @Override
  public Ability copyAbility(){
    return new CleanseAbility(getName(), getDescription(), getCooldown(), getEnumName(), getElement(), getModifiers());
  }
}