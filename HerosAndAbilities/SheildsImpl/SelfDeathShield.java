package battlesystem;

public class SelfDeathShield extends DeathShield 
{

    public SelfDeathShield(
        String name,
        String desc,
        int duration, 
        Effect effect,
        boolean nullifies,
        int uses)
    {
        super(name, desc, duration, effect, nullifies, uses);
    }

    @Override
    public Shield copy()
    {
        return new SelfDeathShield
        (
            getName(),
            getDesc(),
            getDuration(),
            getEffect().copy(),
            isNullifies(),
            getUses()
        );
    }

    @Override
    protected void applyShield(
        Entity victim, 
        Entity caster,
        StringBuilder actions)
    {
        actions.append(victim.getName())
            .append(" recieved the effect of ")
            .append(getEffect().getName())
            .append("\n");
        victim.applyEffect(getEffect().copy(), actions);
    }
}