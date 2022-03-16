package battlesystem;

import java.util.ArrayList;

import battlesystem.databaseImpls.AbilityManager;
import battlesystem.databaseImpls.Effects;
import battlesystem.databaseImpls.Shields;
import battlesystem.databaseImpls.States;

/**
 * an item for creating an entity.
 *
 */
public class EntityInfoItem implements InfoItem<Entity>
{

    public String name;
    public int speed;
    public ArrayList<String> abilities;
    public ArrayList<Effects.Name> effects;
    public ArrayList<Shields.Name> shields;
    public int maxHealth;
    public int shieldHealth;
   
    /**
     * a basic constructor for an entity
     * @param name the name of the entity
     * @param speed the speed of the entity
     * @param abilityNames the abilities it starts with
     * @param startingEffects the starting effects it has
     * @param startingShields the starting shields it has
     * @param maxHealth the max health it starts with
     * @param shieldHealth the starting amount of shield health
     */
    public EntityInfoItem(
        String name,
        int speed,
        ArrayList<String> abilityNames,
        ArrayList<Effects.Name> startingEffects,
        ArrayList<Shields.Name> startingShields,
        int maxHealth,
        int shieldHealth)
    {
        this.name = name;
        this.speed = speed;
        this.abilities = abilityNames;
        this.effects = startingEffects;
        this.shields = startingShields;
        this.maxHealth = maxHealth;
        this.shieldHealth = shieldHealth;
    }


    /**
     * Create an entity object
     */
    @Override
    public Entity create(Entity creator)
    {
        Entity e = new Entity(name, speed, maxHealth, shieldHealth, States.get(States.Name.NORMAL), creator);
        addItems(e);
        return e;
    }

    /**
     * Add some abilities, effects, and shields to the entity
     * @param e the recipient
     */
    protected void addItems(
        Entity e
    )
    {
        AbilityManager.giveAbilities(e, abilitiesNames());
        Effects.giveEffects(e, effects);
        Shields.giveShields(e, shields);
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