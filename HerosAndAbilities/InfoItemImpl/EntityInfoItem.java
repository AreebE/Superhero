package battlesystem;

import java.util.ArrayList;

public class EntityInfoItem implements InfoItem<Entity>
{

    public String name;
    public int speed;
    public ArrayList<Abilities.Name> abilities;
    public ArrayList<Effects.Name> effects;
    public ArrayList<Shields.Name> shields;
    public int maxHealth;
    public int shieldHealth;
   
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