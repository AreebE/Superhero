package battlesystem.shieldImpls;

public class SelfDeathShield extends DeathShield 
{

    public SelfDeathShield(
        String name,
        String desc,
        int duration, 
        Effect effect,
        boolean nullifies,
        int uses,
        Shields.Name enumName)
    {
        super(name, desc, duration, effect, nullifies, uses, enumName);
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
            getUses(),
            getEnumName()
        );
    }

    @Override
    protected void applyShield(
        Entity victim, 
        Entity caster,
        BattleLog log)
    {
        Object[] contents = new Object[]{
            victim.getName(), 
            getUses() - 1, 
            victim.getName(), 
            getEffect().getName(), 
            null,
            getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.SHIELD_TRIGGER, contents));
        victim.applyEffect(getEffect().copy(), log);
    }
}