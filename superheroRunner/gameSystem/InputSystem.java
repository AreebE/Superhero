package gameSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * An input system for getting some necessary things.
 *
 */
public interface InputSystem {
    public String getAbilityName();
    public Entity getSingleTarget();
    public List<Entity> getSecondaryTargets(Integer limit, Entity caster);   
    public int getChoice(String prompt, ArrayList<String[]> choices);
}
