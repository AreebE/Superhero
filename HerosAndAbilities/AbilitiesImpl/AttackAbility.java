package battlesystem;

import java.util.EnumMap;
import java.util.List;


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
        Abilities.Name enumName,
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
            Abilities.Type.ATTACK, 
            enumName, 
            em, 
            modifiers
        );
        this.isPiercing = isPiercing;
        this.ignoresBaseDefense = ignoresBaseDefense;
        this.em = em;
    }


    public AttackAbility(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Abilities.Name enumName,
        Element em, 
        boolean ignoresBaseDefense, 
        boolean isPiercing,
        EnumMap<Abilities.ModifierName, 
        AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            strength, 
            Abilities.Type.ATTACK, 
            enumName, 
            em, 
            modifiers
        );
        this.isPiercing = isPiercing;
        this.ignoresBaseDefense = ignoresBaseDefense;
        this.em = em;
    }


    @Override
    protected boolean castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers) 
    {
        // System.out.println("Attack Ability \'" + this.getName() + "\' used on player
        // " + target.getName());
        if (getEnumName().equals(Abilities.Name.PASS_TURN)) 
        {
            return true;
        }

        int attackStrength = getStrength() + caster.getBaseAttack();
        if (attackStrength < 0) 
        {
            attackStrength = 0;
        }

        Terrain t = caster.getTerrain();

        if(t.isTerrainBuffed(this.em)){
          attackStrength += attackStrength;
        }

        boolean keepGoing = target.dealDamage
        (
            attackStrength, 
            isPiercing, 
            ignoresBaseDefense,
            caster,
            getElement()
        );
        return keepGoing;
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
            sBuilder.append(" (It pierces the shield) ");
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