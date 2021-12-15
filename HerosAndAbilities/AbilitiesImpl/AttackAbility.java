import java.util.EnumMap;

public class AttackAbility extends Ability 
{
    private boolean isPiercing;
    private boolean ignoresBaseDefense;
    private Element em;

    public AttackAbility(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        AbilityList.Name enumName,
        Element em, 
        boolean ignoresBaseDefense, 
        boolean isPiercing, 
        AbilityModifier... modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            strength, 
            AbilityList.Type.ATTACK, 
            enumName, 
            em, 
            modifiers
        );
        this.isPiercing = isPiercing;
        this.ignoresBaseDefense = ignoresBaseDefense;
        this.em = getElement();
    }


    public AttackAbility(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        AbilityList.Name enumName,
        Element em, 
        boolean ignoresBaseDefense, 
        boolean isPiercing,
        EnumMap<AbilityList.ModifierName, 
        AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            strength, 
            AbilityList.Type.ATTACK, 
            enumName, 
            em, 
            modifiers
        );
        this.isPiercing = isPiercing;
        this.ignoresBaseDefense = ignoresBaseDefense;
        this.em = getElement();
    }


    @Override
    protected void castAbility(
        Superhero target, 
        Superhero caster) 
    {
        // System.out.println("Attack Ability \'" + this.getName() + "\' used on player
        // " + target.getName());
        if (getEnumName().equals(AbilityList.Name.PASS_TURN)) 
        {
            return;
        }

        int attackStrength = getStrength() + caster.getBaseAttack();
        
        if (attackStrength < 0) 
        {
            attackStrength = 0;
        }

        Terrain t = caster.getsTerrain();
        if(t.getsTerrainBuff(this.em)){
          attackStrength += attackStrength;
        }

        target.dealDamage
        (
            attackStrength, 
            isPiercing, 
            ignoresBaseDefense
        );
    }


    @Override
    public Ability copy() 
    {
        return new AttackAbility
                (
                    getName(), 
                    getDescription(), 
                    getCooldown(), 
                    getStrength(), 
                    getEnumName(), 
                    getElement(),
                    ignoresBaseDefense, 
                    isPiercing, 
                    getModifiers()
                );
    }


    @Override
    public String toString() 
    {
        StringBuilder sBuilder = new StringBuilder(super.toString());
        if (ignoresBaseDefense) 
        {
            sBuilder.append(" (It ignores the base defense) ");
        }
        if (isPiercing) 
        {
            sBuilder.append(" (It pierces the sheild) ");
        }
        return sBuilder.toString();
    }


    protected boolean doesIgnoreBaseDefense() 
    {
        return ignoresBaseDefense;
    }


    protected boolean isPiercing() 
    {
        return isPiercing;
    }
}
//