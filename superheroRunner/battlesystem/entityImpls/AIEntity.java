package battlesystem.entityImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import battlesystem.Ability;
import battlesystem.Action;
import battlesystem.BattleLog;
import battlesystem.Element;
import battlesystem.Entity;
import battlesystem.InputSystem;
import battlesystem.PassAction;
import battlesystem.State;
import battlesystem.actionImpls.AIAction;

public class AIEntity extends Entity
{
    int currentAbility;
    // Entity creator;
    boolean isTargettable;
    ArrayList<String> attackPattern;

    public AIEntity(
        String name, 
        int speed, 
        int health, 
        int shieldHealth,
        State defaultState,
        Entity creator,
        ArrayList<String> attackPattern,
        boolean isTargettable)
    {
        super(name, speed, health, shieldHealth, defaultState, creator);
        // this.creator = creator;
        this.isTargettable = isTargettable;
        this.attackPattern = attackPattern;
        
        currentAbility = 0;
    }

    @Override
  public List<Action> onTurn(ArrayList<Entity> fighters, InputSystem scanInput){
    System.out.println(this);
    System.out.println("WOOOO Look at me go i have gained sentience(AiEntity needs to be figured out)");
    //List<Action> playerActions = this.getActions(fighters, scanInput);
    return null;
  } 

    @Override
    public List<Action> getActions(
        List<Entity> allHeros,
        InputSystem inputReader)
    {
        List<Action> actions = new ArrayList<>();
        Integer limit = getState().applyStatus(this);
        if (limit == 0)
        {
            actions.add(new PassAction(this));
        }
        for (int i = 0; i < limit; i++)
        {
            Action a = null;
            while (a == null || !a.isLegalAction())
            {
                Entity target = inputReader.getSingleTarget();
                a = new AIAction(target, this, allHeros, inputReader);
            }
            actions.add(a);
        }
        return actions;
    }

    public Ability getCurrentAbility()
    {
        // System.out.println(currentAbility + ", " + attackPattern.get(currentAbility));
        return super.getAbility(attackPattern.get(currentAbility));
    }

    @Override
    public Object[] dealDamage(
        int damageDealt, 
        boolean isPiercing, 
        boolean ignoresDefense,
        Entity caster,
        Element e,
        BattleLog log
    )
    {
        if (isTargettable)
        {
            return super.dealDamage(damageDealt, isPiercing, ignoresDefense, caster, e, log);
        }
        return new Object[]{super.getName(), 0, 0, 0, 0, 0, 0};
    }

    @Override
    public void endOfTurn(BattleLog log)
    {
        super.endOfTurn(log);
        currentAbility = (currentAbility + 1) % attackPattern.size();
    }

    public boolean isTargettable()
    {
        return isTargettable;
    }

    public ArrayList<String> getAttackPattern()
    {
        return this.attackPattern;
    }

    @Override
    public String toString()
    {
        return "Owner - " + getCreator().getName() + "\n" + super.toString();
    }
}