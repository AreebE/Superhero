import java.util.EnumMap;

public class AttackStatusAbility extends AttackAbility{ 
  private Effect sideEffect;

  public AttackStatusAbility(String name, String desc, int cooldown, int strength, AbilityList.AbilityNames enumName, Element em, boolean ignoresBaseDefense, boolean isPiercing, Effect sideEffect, AbilityModifier... modifiers){
    super(name, desc, cooldown, strength, enumName, em, ignoresBaseDefense, isPiercing, modifiers);
    this.sideEffect = sideEffect;
  }

  public AttackStatusAbility(String name, String desc, int cooldown, int strength, AbilityList.AbilityNames enumName, Element em, boolean ignoresBaseDefense, boolean isPiercing, EnumMap<AbilityList.AbilityModifierNames, AbilityModifier> modifiers, Effect sideEffect){
    super(name, desc, cooldown, strength, enumName, em, ignoresBaseDefense, isPiercing, modifiers);
    this.sideEffect = sideEffect;
  }

  @Override
  protected void castAbility(Superhero target, Superhero caster) {
    super.castAbility(target, caster);
    if (isPiercing() || !caster.hasSheild()){
      target.addEffect(sideEffect.copyEffect());
    }
  }

  @Override
  public Ability copyAbility(){
    return new AttackStatusAbility(getName(), getDescription(), getCooldown(), getStrength(), getEnumName(), getElement(), doesIgnoreBaseDefense(), isPiercing(), getModifiers(), sideEffect);
  }
}