public interface AbilityModifier<T> {
  public T triggerModifier(Entity target, Entity caster);
  public AbilityList.ModifierName getModifier();
}