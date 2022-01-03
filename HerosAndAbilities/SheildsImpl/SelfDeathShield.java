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
        Entity caster)
    {
        System.out.println(" apply shield to " + caster.getName());
        caster.applyEffect(getEffect().copy());
    }
}