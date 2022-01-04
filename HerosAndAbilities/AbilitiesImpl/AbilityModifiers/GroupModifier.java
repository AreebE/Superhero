package battlesystem;

public class GroupModifier implements AbilityModifier<Double>
{
    private int percentage;
    private int limit;

    public GroupModifier(int percentage, int limit)
    {
        this.percentage = percentage;
        this.limit = limit;
    }

    @Override 
    public Double triggerModifier(Entity target, Entity caster)
    {
        return percentage / 100.0;
    }

    @Override
    public Abilities.Modifier getModifier()
    {
        return Abilities.Modifier.GROUP;
    }

    public Integer getLimit()
    {
        return limit;
    }

    
}