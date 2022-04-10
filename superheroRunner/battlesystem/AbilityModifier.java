package battlesystem;

import java.util.List;

/**
 * A class used to modify certain abilities
 */
public interface AbilityModifier {
  public boolean triggerModifier(List<Entity> target, Entity caster, Ability holder, Game g, BattleLog Log);
  public int getPriority();
  
}