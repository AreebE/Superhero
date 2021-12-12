public interface AbilityModifier<T> {
  public T triggerModifier(Superhero target, Superhero caster);
  public AbilityList.AbilityModifierNames getModifier();
}