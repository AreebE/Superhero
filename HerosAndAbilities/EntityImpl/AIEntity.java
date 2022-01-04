package battlesystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AIEntity extends Entity
{
    int currentAbility;
    // Entity creator;
    boolean isTargettable;
    ArrayList<Abilities.Name> attackPattern;

    public AIEntity(
        String name, 
        int speed, 
        int health, 
        int shieldHealth,
        Entity creator,
        ArrayList<Abilities.Name> attackPattern,
        boolean isTargettable)
    {
        super(name, speed, health, shieldHealth, creator);
        // this.creator = creator;
        this.isTargettable = isTargettable;
        this.attackPattern = attackPattern;
        
        currentAbility = 0;
    }

  

    @Override
    public Action getAction(
        List<Entity> allHeros,
        InputSystem inputReader)
    {
        Entity target = inputReader.getSingleTarget();
        Action a = new AIAction(target, this, allHeros, inputReader);
        if (a.isLegalAction())
        {
            return a;
        }
        else {
            return null;
        }
    }

    public Ability getCurrentAbility()
    {
        // System.out.println(currentAbility + ", " + attackPattern.get(currentAbility));
        return super.getAbility(attackPattern.get(currentAbility));
    }

    @Override
    public boolean dealDamage(
        int damageDealt, 
        boolean isPiercing, 
        boolean ignoresDefense,
        Entity caster,
        Element e
    )
    {
        if (isTargettable)
        {
            super.dealDamage(damageDealt, isPiercing, ignoresDefense, caster, e);
        }
        return false;
    }

    @Override
    public void endOfTurn()
    {
        super.endOfTurn();
        currentAbility = (currentAbility + 1) % attackPattern.size();
    }

    public boolean isTargettable()
    {
        return isTargettable;
    }

    public ArrayList<Abilities.Name> getAttackPattern()
    {
        return this.attackPattern;
    }

    @Override
    public String toString()
    {
        return "Owner - " + getCreator().getName() + "\n" + super.toString();
    }
}