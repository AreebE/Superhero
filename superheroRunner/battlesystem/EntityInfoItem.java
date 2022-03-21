package battlesystem;

import java.util.ArrayList;



/**
 * an item for creating an entity.
 *
 */
public class EntityInfoItem
{

    public String name;
    public int speed;
    public ArrayList<String> abilities;
    public ArrayList<String> effects;
    public ArrayList<String> shields;
    public String defaultState;
    public int maxHealth;
    public int shieldHealth;
   
    /**
     * a basic constructor for an entity
     * @param name the name of the entity
     * @param speed the speed of the entity
     * @param abilityNames the abilities it starts with
     * @param startingEffects the starting effects it has
     * @param startingShields the starting shields it has
     * @param defaultState the default state of the entity
     * @param maxHealth the max health it starts with
     * @param shieldHealth the starting amount of shield health
     */
    public EntityInfoItem(
        String name,
        int speed,
        ArrayList<String> abilityNames,
        ArrayList<String> startingEffects,
        ArrayList<String> startingShields,
        String defaultState,
        int maxHealth,
        int shieldHealth)
    {
        this.name = name;
        this.speed = speed;
        this.abilities = abilityNames;
        this.effects = startingEffects;
        this.shields = startingShields;
        this.maxHealth = maxHealth;
        this.defaultState = defaultState;
        this.shieldHealth = shieldHealth;
    }


    /**
     * Create an entity object
     */
    public Entity create(
    		Entity creator,
    		ObjectMap<Ability> abDatabase,
    		ObjectMap<Effect> efDatabase,
    		ObjectMap<Shield> shDatabase,
    		ObjectMap<State> stDatabase)
    {
        Entity e = new Entity(name, speed, maxHealth, shieldHealth, stDatabase.getEntry(defaultState), creator);
        addItems(e, abDatabase, efDatabase, shDatabase);
        return e;
    }

    /**
     * Add some abilities, effects, and shields to the entity
     * @param e the recipient
     */
    protected void addItems(
        Entity e,
        ObjectMap<Ability> abDatabase,
        ObjectMap<Effect> efDatabase,
        ObjectMap<Shield> shMap
    )
    {
        for (String a: abilities)
        {
        	e.addAbility(abDatabase.getEntry(a));
        }
        for (String ef: effects)
        {
        	e.addEffect(efDatabase.getEntry(ef));
        }
        for (String s: shields)
        {
        	e.addShield(shMap.getEntry(s));
        }
    }

    /**
     * the name of the person
     * @return the name
     */
    protected String getName()
    {
        return this.name;
    }

    /**
     * get speed
     * @return speed
     */
    protected int getSpeed()
    {
        return this.speed;
    }

    /**
     * get max health
     * @return max health
     */
    protected int getMaxHealth()
    {
        return this.maxHealth;
    }

    /**
     * get starting shield
     * @return starting shield
     */
    protected int getShieldHealth()
    {
        return this.shieldHealth;
    }
    
    /**
     * get the ability names
     * @return the ability names.
     */
    private ArrayList<String> abilitiesNames(){
      return this.abilities;
    }
}