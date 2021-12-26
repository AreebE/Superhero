import java.util.ArrayList;

public class AIInfoItem
    {
        String name;
        int speed;
        int health;
        int shieldHealth;
        boolean isTargettable;
        ArrayList<AbilityList.Name> attackPattern;

        public AIInfoItem(
            String name, 
            int speed,
            int health,
            int shieldHealth, 
            boolean isTargettable,
            ArrayList<AbilityList.Name> attackPattern)
        {
            this.name = name;
            this.speed = speed;
            this.health = health;
            this.shieldHealth = shieldHealth;
            this.isTargettable = isTargettable;
            this.attackPattern = attackPattern;
        }

        public AIEntity createEntity(Entity creator)
        {
            return new AIEntity(name, speed, health, shieldHealth, creator, attackPattern, isTargettable);
        }
    }