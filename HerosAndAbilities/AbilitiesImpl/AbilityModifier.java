package battlesystem;

public interface AbilityModifier<T> {
  public T triggerModifier(Entity target, Entity caster);
  public Ability.Modifier getModifier();
}