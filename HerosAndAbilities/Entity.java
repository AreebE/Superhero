package battlesystem;

import java.util.Random;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Entity implements Comparable<Entity>
{

    public static final int HEALTH_LOST_INDEX = 0;
    public static final int SHIELD_LOST_INDEX = 1;
    public static final int KEEP_GOING_INDEX = 2;

    // free will between 1 and 20;
    private Entity creator;
    private String name;
    private int speed;
    private ArrayList<Ability> abilities;
    private ArrayList<Effect> effects;
    private ArrayList<Shield> shields;
    private State state;
    private int health;
    private int maxHealth;
    private int shieldHealth;
    private int baseAttack;
    private int baseDefense;
    private static Terrain t;


    public static enum Statistic
    {
        SPEED,
        MAX_HEALTH,
        HEALTH,
        BASE_ATTACK,
        BASE_DEFENSE,
        SHIELD
    }

    public Entity(
        String name, 
        int speed, 
        int health, 
        int shieldHealth,
        Entity creator) 
    {
        this.name = name;
        this.speed = speed;
        this.health = health;
        this.maxHealth = health;
        this.shieldHealth = shieldHealth;
        this.abilities = new ArrayList<>();
        this.effects = new ArrayList<>();
        this.shields = new ArrayList<>();
        this.state = States.get(States.Name.NORMAL);
        this.baseAttack = 0;
        this.baseDefense = 0;
        this.creator = creator;
        Abilities.giveAbility(this, Abilities.Name.PASS_TURN);
    }


    protected Entity getCreator()
    {
        return this.creator;
    }

    /**
     * Provides information on what the Entity does
     */
    @Override
    public String toString() 
    {
        StringBuilder EntityString = new StringBuilder(name).append(" - will of ")
                                                                .append(speed)
                                                                .append(". \n")
                                                                .append("\u001B[35m");
        if (abilities.size() == 0) 
        {
            EntityString.append("There are no abilities.\n");
        } 
        else 
        {
            EntityString.append("The abilities are: \n");
            for (int i = 0; i < abilities.size(); i++) 
            {
                Ability a = abilities.get(i);
                EntityString.append("* ")
                                .append(a.toString());
                if (!a.ableToUseAbility()) 
                {
                    EntityString.append(" (on cooldown. needs ")
                                    .append(a.getTurnsNeeded())
                                    .append(" more turns)");
                }
                EntityString.append("\n");
            }
        }
        // moved it down
        EntityString.append("The state is: ")
                    .append(state.toString())
                    .append("\n")
                    .append("\u001B[31m");
        if (effects.size() == 0) 
        {
            EntityString.append("No Effects/ deEffects applied.\n");
        } else 
        {
            EntityString.append("The Effects/deEffects are:\n");
            for (int i = 0; i < effects.size(); i++) 
            {
                EntityString.append("* ")
                                .append(effects.get(i))
                                .append("\n");
            }
        }

        EntityString.append("\u001B[35m");
        if (shields.size() == 0) 
        {
            EntityString.append("No shields applied.\n");
        } else 
        {
            EntityString.append("The Shields are:\n");
            for (int i = 0; i < shields.size(); i++) 
            {
                EntityString.append("* ")
                                .append(shields.get(i))
                                .append("\n");
            }
        }

        EntityString.append("\u001B[34m")
                        .append("* health - ")
                        .append(health)
                        .append("/")
                        .append(maxHealth)
                        .append("\n")
                        .append("* shield - ")
                        .append(shieldHealth)
                        .append("\n")
                        .append("* base attack - ")
                        .append(baseAttack)
                        .append("\n")
                        .append("* base defense - ")
                        .append(baseDefense)
                        .append("\n")
                        .append("\u001B[0m")
                        .append("\n");
        return EntityString.toString();
    }


    /*
     * Writes this Entity to a Single Line so that its usable by Fileiothing also
     * should health/shieldHealth be persistent? // not entirely sure, maybe for
     * only the base health/ base shield? as in all heros get 100 health and x
     * shield at the beggingn of each round // for now, that could work. but for
     * more customizability, we may remove that set health later on
     */
    public String ToSaveable() 
    {
        String out = "";
        out = (out + name + speed);
        return out;
    }


    public String getName() 
    {
        return this.name;
    }
    //

    /*
     * Methods involving a player's ability/abilities
     */
    public void addAbility(
        Ability newAbility) 
    {
        abilities.add(newAbility);
    }


    public ArrayList<Ability> getAbilities() 
    {
        return this.abilities;
    }


    public Ability getAbility(
        Abilities.Name enumName) 
    {
        // System.out.println(this);
        for (Ability a : abilities) 
        {
            // System.out.println(a);
            // System.out.println(a.getID());
            // // System.out.println(id);
            if (a.getEnumName().equals(enumName)) 
            {
                if (a.ableToUseAbility()) 
                {
                    return a;
                } 
                else 
                {
                    return null;
                }
            }
        }
        return null;
    }


    public boolean hasAbility(
        Ability ability) 
    {
        return abilities.contains(ability);
    }
    
    public boolean hasGroupAbility(
        Abilities.Name name)
    {
        return getAbility(name).hasModifier(Abilities.Modifier.GROUP);
    }
    /*
     * Methods involving a player's health
     */
    public void addShieldHealth(int shield) 
    {
        this.shieldHealth += shield;
    }


    public int getShieldHealth() 
    {
        return this.shieldHealth;
    }


    public boolean hasShield() 
    {
        return this.shieldHealth == 0;
    }


    public void healHealth(
        int healed) 
    {
        // System.out.println("called the heal health");
        health += healed;
        if (health > maxHealth) 
        {
            health = maxHealth;
        }
    }


    public int getHealth() 
    {
        return this.health;
    }

    public void addMaxHealth(int addHealth)
    {   
        maxHealth += addHealth;
        health += addHealth;
    }

    public boolean isHealthZero(StringBuilder actions) 
    {
        if (this.health <= 0)
        {
            searchForShield(Shields.Trigger.DEATH, Elements.getElement(Elements.Name.ALL), creator, this, actions);
        }
        return this.health <= 0;
    }

    public boolean isHealthZero() 
    {
        return this.health <= 0;
    }

    /**
     * deals damage to the target based on the attacks stregth, the casters base
     * attack and the targets base defense.
     *
     * @param damageDealt    the total damage of the attack
     *
     * @param isPiercing     whether the attack ignores shield health
     *
     * @param ignoresDefense whether the attack ignores the base defense
     *
     * @return     if the player can keep attacking
     */
    public int[] dealDamage(
        int damageDealt, 
        boolean isPiercing, 
        boolean ignoresDefense,
        Entity caster,
        Element e,
        StringBuilder actions) 
    {   
        int[] returnValues = new int[]{0, 0, 0};
        
        boolean endAttack = false;
        Shields.Trigger type = null;
        if (caster != null)
        {
            type = Shields.Trigger.ATTACK;
        } 

        if (!ignoresDefense) 
        {
            damageDealt -= baseDefense;
        }
        
        if (0 >= damageDealt) 
        {
            return returnValues;
        } 
        else if (isPiercing) 
        {
            if (type != null)
            {
                endAttack = searchForShield(type, e, this, caster, actions);
            }
            if (endAttack){
                returnValues[KEEP_GOING_INDEX] = Ability.MISS;
                return returnValues;
            }
            health -= damageDealt;
            returnValues[HEALTH_LOST_INDEX] = damageDealt;
        } 
        else if (shieldHealth >= damageDealt) 
        {
            shieldHealth -= damageDealt;
            returnValues[SHIELD_LOST_INDEX] = damageDealt;
            return returnValues;
        } 
        else
        {
            returnValues[SHIELD_LOST_INDEX] = shieldHealth;
            damageDealt -= shieldHealth;
            shieldHealth = 0;
            endAttack = searchForShield(Shields.Trigger.SHIELD_BREAK, e, this, caster, actions);
            if (type != null)
            {
                endAttack = endAttack || searchForShield(type, e, this, caster, actions);
            }
            if (endAttack){
                returnValues[KEEP_GOING_INDEX] = Ability.MISS;
                return returnValues;
            }
            returnValues[HEALTH_LOST_INDEX] = damageDealt;
            health -= damageDealt;
        }
        
        if (health < 0) 
        {
            health = 0;
        }
        return returnValues;
    }

    public boolean dealEffectDamage(
        int damageDealt,
        boolean isPiercing,
        boolean ignoresDefense)
    {
         if (!ignoresDefense) 
        {
            damageDealt -= baseDefense;
        }

        if (0 >= damageDealt) 
        {
            return true;
        } 
        else if (isPiercing) 
        {
            health -= damageDealt;
        } 
        else if (shieldHealth >= damageDealt) 
        {
            shieldHealth -= damageDealt;
            return true;
        } 
        else
        {
            damageDealt -= shieldHealth;
            shieldHealth = 0;            
            health -= damageDealt;
            return false;
        }
        
        if (health < 0) 
        {
            health = 0;
        }
        return true;
    }
    //


    /*
     * Methods for adding/removing Effects
     */
    public void addEffect(
        Effect newEffect) 
    {
        effects.add(newEffect);
    }

    public void applyEffect(
        Effect e,
        StringBuilder actions
    )
    {
        e.useEffect(this, actions);
    }

    public void removeEffect(
        Effect removed) 
    {
        effects.remove(removed);
    }

    public void removeEffect(
        Effect removed,
        StringBuilder actions) 
    {
        effects.remove(removed);
        actions.append(removed.getName());
    }


    public void removeEffects(
        Elements.Name elementID,
        StringBuilder actions)
    {
        int times = 0;
        for (int i = effects.size() - 1; i >= 0; i--) 
        {
            Effect e = effects.get(i);
            if (e.isRemovable()
                    &&  ( 
                            elementID.equals(Elements.Name.ALL) 
                            || elementID.equals(e.getElement().getID())
                        )
                ) 
            {
                if (times == 0)
                {
                    actions.append("Removed effect(s) ");
                }
                else 
                {
                    actions.append(", ");
                }
                times++;
                removeEffect(e, actions);
            }
        }
    }
    //

    /*
    * Methods for adding/removing shields
    */

    public void addShield(
        Shield s)
    {
        shields.add(s);
    }


    public void removeShield(
        Shield s)
    {
        shields.remove(s);
    }


    public void reduceShieldDurations(
        StringBuilder actions
    )
    {
        for (int i = shields.size() - 1; i >= 0; i--)
        {
            shields.get(i).passTurn(this, actions);
        }
    }


    public boolean searchForShield(
        Shields.Trigger trigger, 
        Element element,
        Entity target, 
        Entity caster,
        StringBuilder actions)
    {
        boolean nullifyEffect = false;
        for (int i = shields.size() - 1; i >= 0; i--)
        {
            Shield s = shields.get(i);
            if (s.wouldTrigger(trigger, element))
            {
                boolean nullify = s.triggerShield(target, caster, actions);
                if (nullify)
                {
                    nullifyEffect = true;
                }
            }
        }
        return nullifyEffect;
    }

    /*
    * Methods for speed
    */
    public void addSpeed(
        int speed)
    {
        this.speed += speed;
    }

    public int getSpeed()
    {
        return this.speed;
    }

     @Override
    public int compareTo(
        Entity other) 
    {
        return this.speed - other.getSpeed();
    }


    /*
     * Methods for adding/getting attack or defense
     */
    public void addDefense(
        int defense) 
    {
        baseDefense += defense;
    }


    public void addAttack(
        int attack) 
    {
        baseAttack += attack;
    }


    public int getBaseAttack() 
    {
        return baseAttack;
    }


    public int getBaseDefense() 
    {
        return baseDefense;
    }
    //

    /*
     * terrain 
    */

    public void setTerrain(Terrain t){
      this.t = t;
    }

    public Terrain getTerrain(){
      return t;
    }

    /*
    * Getting an action 
    */
    public List<Action> getActions(
        List<Entity> allHeros,
        InputSystem inputReader)
    {
        List<Action> actions = new ArrayList<>();
        Integer numActions = state.applyStatus(this);
        if (numActions == 0)
        {
            actions.add(new PassAction(this));
            return actions;
        }
        for (int i = 0; i < numActions; i++)
        {
            Action a = null;
            while (a == null || !a.isLegalAction())
            {
                Entity target = inputReader.getSingleTarget();
                String name = inputReader.getAbilityName();
                a = new Action(target, this, name, allHeros, inputReader);
            }
            actions.add(a);
        }
        return actions;
    }

    /*
    *
    */
    public int getStatistic(Statistic stat)
    {
        switch (stat)
        {
            case SPEED:
                return speed;
            case HEALTH:
                return health;
            case MAX_HEALTH:
                return maxHealth;
            case BASE_ATTACK:
                return baseAttack;
            case BASE_DEFENSE:
                return baseDefense; 
            case SHIELD:
                return shieldHealth;
        }
        return 0;
    }

    /*
    * Changing state
    */
    public void replaceState(State newState)
    {
        this.state = newState;
    }

    public State getState()
    {
        return this.state;
    }

    public void resetState()
    {
        this.state = States.get(States.Name.NORMAL);
    }

    /*
     * Actions done at the end of the turn
     */
    public String endOfTurn() 
    {
        StringBuilder actions = new StringBuilder("\n");
        // use effects
        useEffects(actions);
        actions.append("\n");
        // Reset cooldowns
        reduceCooldowns(actions);
        actions.append("\n");

        // Reduce sheilds
        reduceShieldDurations(actions);
        actions.append("\n");
        // Reduce state duration
        reduceStateDurations();
        
        actions.append("The user is in a ")
                .append(state.getName())
                .append(" state.");

        return actions.toString();
    }


    public void useEffects(StringBuilder actions) 
    {
        for (int i = effects.size() - 1; i >= 0; i--) 
        {
            // System.out.println(this);
            Effect b = effects.get(i);
            b.useEffect(this, actions);
            actions.append("\n");
        }
    }

    public void reduceCooldowns(StringBuilder actions) 
    {
        for (Ability a : abilities) 
        {
            a.reduceCooldown();
            if (a.ableToUseAbility())
            {
                actions.append(a.getName())
                    .append(" is able to be used.")
                    .append("\n");
            }
        }
    }

    public void reduceStateDurations()
    {
        state.reduceDuration(this);
    }

    
}