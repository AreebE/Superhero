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
        StringBuilder actions)
    {
        // System.out.println("appltied death shield to " + victim.getName());
        caster.applyEffect(effect.copy(), actions);
    }

    protected Effect getEffect()
    {
        return effect;
    }
}