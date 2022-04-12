package battlesystem;

import java.util.List;

/**
 * An input system for getting some necessary things.
 *
 */
public interface InputSystem {
    public String getAbilityName();
    public Entity getSingleTarget();
    public List<Entity> getSecondaryTargets(Integer limit, Entity caster);   
}