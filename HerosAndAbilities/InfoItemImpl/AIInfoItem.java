package battlesystem;

import java.util.ArrayList;

public class AIInfoItem extends EntityInfoItem
    {
     
        boolean isTargettable;
        ArrayList<Abilities.Name> attackPattern;

        public AIInfoItem(
            String name,
            int speed,
            ArrayList<Abilities.Name> abilityNames,
            ArrayList<Effects.Name> startingEffects,
            ArrayList<Shields.Name> startingShields,
            int maxHealth,
            int shieldHealth,
            ArrayList<Abilities.Name> attackPattern,
            boolean isTargettable)
        {
            super(name, speed, abilityNames, startingEffects, startingShields, maxHealth, shieldHealth);
            this.attackPattern = attackPattern;
            this.isTargettable = isTargettable;
        }

        @Override
        public Entity create(Entity creator)
        {
            AIEntity ai = new AIEntity(getName(), getSpeed(), getMaxHealth(), getShieldHealth(), States.get(States.Name.NORMAL), creator, attackPattern, isTargettable);
            super.addItems(ai);
            return ai;
        }
    }