package battlesystem.entityImpls;

import battlesystem.Entity;
import battlesystem.infoItemImpls.MoveItem;
import java.util.List;
import battlesystem.State;

public class BasicAIEntity extends Entity
{
    public class BasicItem extends MoveItem<Void>
    {
        public BasicItem(int order, String abilityName, int category)
        {
            super(order, category, abilityName);    
        }
        
        @Override
        public void adjustPriority(Void object)
        {
            
        }
    }
    
    private List<MoveItem> moves;
    public BasicAIEntity(
        String name, 
        int speed, 
        int health, 
        int shieldHealth,
        State defaultState,
        Entity creator,
        List<MoveItem> moves
    )
    {
        super(name, speed, health, shieldHealth, defaultState, creator);
        this.moves = moves;
    }
}