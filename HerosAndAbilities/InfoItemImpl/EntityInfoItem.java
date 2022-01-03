package battlesystem;

import java.util.ArrayList;

public class EntityInfoItem implements InfoItem<Entity>
{

    private String name;
    private int speed;
    private ArrayList<Abilities.Name> abilities;
    private ArrayList<Effects.Name> effects;
    private ArrayList<Shields.Name> shields;
    private int maxHealth;
    private int shieldHealth;
   
    public EntityInfoItem(
        String name,
        int speed,
        ArrayList<Abilities.Name> abilityNames,
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


    @Override
    public Entity create(Entity creator)
    {
        Entity e = new Entity(name, speed, maxHealth, shieldHealth, creator);
        addItems(e);
        return e;
    }

    protected void addItems(
        Entity e
    )
    {
        Abilities.giveAbilities(e, abilities);
        Effects.giveEffects(e, effects);
        Shields.giveShields(e, shields);
    }

    protected String getName()
    {
        return this.name;
    }

    protected int getSpeed()
    {
        return this.speed;
    }

    protected int getMaxHealth()
    {
        return this.maxHealth;
    }

    protected int getShieldHealth()
    {
        return this.shieldHealth;
    }
}