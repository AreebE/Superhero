package battlesystem;

public class DeathShield extends Shield
{

    private Effect effect;

    public DeathShield(
        String name,
        String desc,
        int duration, 
        Effect effect,
        boolean nullifies,
        int uses)
    {
        super(name, desc, duration, nullifies, uses, new Shields.Trigger[]{Shields.Trigger.DEATH}, new Elements.Name[]{Elements.Name.ALL});
        this.effect = effect;
        // System.out.println(effect);
    }
  
    @Override
    public Shield copy()
    {
        return new DeathShield
        (
            getName(),
            getDesc(),
            getDuration(),
            effect.copy(),
            isNullifies(),
            getUses()
        );
    }

    @Override
    protected void applyShield(
        Entity victim, 
        Entity caster,
        BattleLog log)
    {
        // System.out.println("appltied death shield to " + victim.getName());
        Object[] contents = new Object[]{victim.getName(), getUses() - 1, caster.getName(), effect.getName(), null, getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.SHIELD_TRIGGER, contents));
        caster.applyEffect(effect.copy(), log);
    }

    protected Effect getEffect()
    {
        return effect;
    }
}