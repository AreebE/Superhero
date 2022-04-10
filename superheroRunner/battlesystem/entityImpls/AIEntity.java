package battlesystem.entityImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import battlesystem.Ability;
import battlesystem.Action;
import battlesystem.BattleLog;
import battlesystem.Element;
import battlesystem.Entity;
import battlesystem.Game;
import battlesystem.InputSystem;
import battlesystem.PassAction;
import battlesystem.State;
import battlesystem.actionImpls.AIAction;

/**
 * An AIEntity used for some entities that don't have predictable patterns
 *
 */
public class AIEntity extends Entity
{
    int currentAbility;
    // Entity creator;
    boolean isTargettable;
    ArrayList<String> attackPattern;

    /**
     * The basic constructor for an AI Entity
     * @param name the name of it
     * @param speed its starting speed
     * @param health starting health
     * @param shieldHealth starting shield
     * @param defaultState default state
     * @param creator who created it 
     * @param attackPattern *NEW* What abilities it will use and in what order
     * @param isTargettable *NEW* If it can be targetted by other characters
     */
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

    /**
     * What to do if it is on a turn
     * @param fighters all entities available
     * @param scanInput the input system used to get some input
     * 
     * @return the list of actions to perform
     */
    @Override
  public List<Action> onTurn(ArrayList<Entity> fighters, InputSystem scanInput){
    System.out.println(this);
    System.out.println("WOOOO Look at me go i have gained sentience(AiEntity needs to be figured out)");
    //List<Action> playerActions = this.getActions(fighters, scanInput);
    return null;
  } 

    /**
     * Another method to get the actions of an AI Entity
     * 
     * @param  allHeros all players present
     * @param inputReader an input system to get some input
     */
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

    /**
     * Get the current ability this entity should use
     * @return the current ability to use.
     */
    public Ability getCurrentAbility()
    {
        // System.out.println(currentAbility + ", " + attackPattern.get(currentAbility));
        return super.getAbility(attackPattern.get(currentAbility));
    }

    /**
     * Effectively the same, though nothing will happen if this entity isn't targettable.
     */
    @Override
    public Object[] dealDamage(
        int damageDealt, 
        boolean isPiercing, 
        boolean ignoresDefense,
        Entity caster,
        Element e,
        Game g,
        BattleLog log
    )
    {
        if (isTargettable)
        {
            return super.dealDamage(damageDealt, isPiercing, ignoresDefense, caster, e, g, log);
        }
        return new Object[]{super.getName(), 0, 0, 0, 0, 0, 0};
    }

    /**
     * Do the same as the Entity one, but this time, update the attack pattern as well.
     * @param log the the log to store what happens here.
     */
    @Override
    public void endOfTurn(BattleLog log, Game g)
    {
        super.endOfTurn(log, g);
        currentAbility = (currentAbility + 1) % attackPattern.size();
    }

    /**
     * If this entity is targettable
     * @return if it can be hurt / inflicted by anything.
     */
    @Override
    public boolean isTargettable()
    {
        return isTargettable;
    }

    /**
     * The attack pattern for this entity
     * @return the attack pattern
     */
    public ArrayList<String> getAttackPattern()
    {
        return this.attackPattern;
    }

    /**
     * The to string method, which displays the creator, then whatever comes next.
     */
    @Override
    public String toString()
    {
        return "Owner - " + getCreator().getName() + "\n" + super.toString();
    }
}