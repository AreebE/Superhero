package battlesystem;

/**
 * A class used to modify certain abilities
 * @param <T> the type of value this should return
 */
public interface AbilityModifier<T> {
  public T triggerModifier(Entity target, Entity caster);
  public Ability.Modifier getModifier();
  
}