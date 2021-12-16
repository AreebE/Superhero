import java.util.Random;
import java.lang.Comparable;
import java.util.ArrayList;

public class Superhero implements Comparable<Superhero>, TurnEndReceiver 
{
    // free will between 1 and 20;

    private String name;
    private String desc = "default desc";
    private int freeWill;
    private ArrayList<Ability> abilities;
    private ArrayList<Effect> effects;
    private int health;
    private int maxHealth;
    private int sheildHealth;
    private int baseAttack;
    private int baseDefense;

    public Superhero(
        String name, 
        int freeWill, 
        int health, 
        int sheildHealth) 
    {
        this.name = name;
        this.freeWill = freeWill;
        this.health = health;
        this.maxHealth = health;
        this.sheildHealth = sheildHealth;
        this.abilities = new ArrayList<>();
        this.effects = new ArrayList<>();
        this.baseAttack = 0;
        this.baseDefense = 0;
        AbilityList.giveAbility(this, AbilityList.Name.PASS_TURN);
    }


    /**
     * Methods involving free will
     */
    public int getFreeWill() 
    {
        return this.freeWill;
    }


    @Override
    public int compareTo(
        Superhero other) 
    {
        return this.freeWill - other.getFreeWill();
    }
    //


    /**
     * Provides information on what the superhero does
     */
    @Override
    public String toString() 
    {
        StringBuilder superheroString = new StringBuilder(name).append(" - will of ")
                                                                .append(freeWill)
                                                                .append(". \n")
                                                                .append("\u001B[35m");
        if (abilities.size() == 0) 
        {
            superheroString.append("There are no abilities.\n");
        } 
        else 
        {
            superheroString.append("The abilities are: \n");
            for (int i = 0; i < abilities.size(); i++) 
            {
                Ability a = abilities.get(i);
                superheroString.append("* ")
                                .append(a.toString());
                if (!a.ableToUseAbility()) 
                {
                    superheroString.append(" (on cooldown. needs ")
                                    .append(a.getTurnsNeeded())
                                    .append(" more turns)");
                }
                superheroString.append("\n");
            }
        }
        // moved it down

        superheroString.append("\u001B[31m");
        if (effects.size() == 0) 
        {
            superheroString.append("No Effects/ deEffects applied.\n");
        } else 
        {
            superheroString.append("The Effects/deEffects are:\n");
            for (int i = 0; i < effects.size(); i++) 
            {
                superheroString.append("* ")
                                .append(effects.get(i))
                                .append("\n");
            }
        }

        superheroString.append("\u001B[34m")
                        .append("* health - ")
                        .append(health)
                        .append("\n")
                        .append("* sheild - ")
                        .append(sheildHealth)
                        .append("\n")
                        .append("* base attack - ")
                        .append(baseAttack)
                        .append("\n")
                        .append("* base defense - ")
                        .append(baseDefense)
                        .append("\n")
                        .append("\u001B[0m")
                        .append("\n");
        return superheroString.toString();
    }


    /*
     * Writes this superhero to a Single Line so that its usable by Fileiothing also
     * should health/sheildHealth be persistent? // not entirely sure, maybe for
     * only the base health/ base sheild? as in all heros get 100 health and x
     * shield at the beggingn of each round // for now, that could work. but for
     * more customizability, we may remove that set health later on
     */
    public ArrayList<String> toSaveable(){
    int[] ints = {
      freeWill,
      health,
      maxHealth,
      baseAttack,
      baseDefense,
    };
    ArrayList<String> out =new ArrayList<String>();
    out.add(name);
    out.add(desc);
    for(int i=0;i<ints.length;i++){
      out.add(Integer.toString(ints[i]));
    }
    for(int i=0;i<abilities.toArray().length;i++){
      out.add(abilities.get(i).getName());
    }
    

    
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
        AbilityList.Name enumName) 
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


    public boolean hasAbility(Ability ability) 
    {
        return abilities.contains(ability);
    }
    //

    /*
     * Methods involving a player's health
     */
    public void addSheildHealth(int sheild) 
    {
        this.sheildHealth += sheild;
    }


    public int getSheildHealth() 
    {
        return this.sheildHealth;
    }


    public boolean hasSheild() 
    {
        return this.sheildHealth == 0;
    }


    public void healHealth(
        int healed) 
    {
        System.out.println("called the heal health");
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


    public boolean isPlayerHealthZero() 
    {
        return this.health == 0;
    }


    /**
     * deals damage to the target based on the attacks stregth, the casters base
     * attack and the targets base defense.
     *
     * @param damageDealt    the total damage of the attack
     *
     * @param isPiercing     whether the attack ignores sheild health
     *
     * @param ignoresDefense whether the attack ignores the base defense
     *
     */
    public void dealDamage(
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
            return;
        } 
        else if (isPiercing) 
        {
            health -= damageDealt;
        } 
        else if (sheildHealth >= damageDealt) 
        {
            sheildHealth -= damageDealt;
            return;
        } 
        else
        {
            damageDealt -= sheildHealth;
            sheildHealth = 0;
            health -= damageDealt;
        }
        if (health < 0) 
        {
            health = 0;
        }
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


    public void removeEffect(
        Effect removed) 
    {
        effects.remove(removed);
    }


    public void removeEffects(
        ElementList.Name elementID)
    {
        for (int i = effects.size() - 1; i >= 0; i--) 
        {
            Effect e = effects.get(i);
            if (e.isRemovable()
                    &&  ( 
                            elementID.equals(ElementList.Name.ALL) 
                            || elementID.equals(e.getElement().getID())
                        )
                ) 
            {
                removeEffect(e);
            }
        }
    }
    //


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
     * Actions done at the end of the turn
     */
    @Override
    public void endOfTurn() 
    {
        useEffects();
        reduceCooldowns();
    }


    public void useEffects() 
    {
        for (int i = effects.size() - 1; i >= 0; i--) 
        {
            Effect b = effects.get(i);
            b.applyEffect(this);
        }
    }


    public void reduceCooldowns() 
    {
        for (Ability a : abilities) 
        {
            a.reduceCooldown();
        }
    }

}