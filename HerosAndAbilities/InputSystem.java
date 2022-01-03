package battlesystem;

import java.util.List;

public interface InputSystem {
    public String getAbilityName();
    public Entity getSingleTarget();
    public List<Entity> getSecondaryTargets(Integer limit);   
}