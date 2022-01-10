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
        EnumMap<Abilities.Modifier, 
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
    protected String castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers) 
    {
        // System.out.println("Attack Ability \'" + this.getName() + "\' used on player
        // " + target.getName());
        int attackStrength = getStrength() + caster.getBaseAttack();
        if (attackStrength < 0) 
        {
            attackStrength = 0;
        }

        Terrain t = caster.getTerrain();

        if(t.isTerrainBuffed(this.em)){
          attackStrength += attackStrength;
        }

        StringBuilder action = new StringBuilder();
        int[] values = target.dealDamage
        (
            attackStrength, 
            isPiercing, 
            ignoresBaseDefense,
            caster,
            getElement(),
            action
        );
        if (values[Entity.KEEP_GOING_INDEX] == Ability.MISS)
        {
            super.stopAttack();
        }
        return action.append(formActionString(values, target)).toString();
    }

    private String formActionString(int[] values, Entity target)
    {
        StringBuilder action = new StringBuilder(target.getName());
        // action.append(values[Entity.KEEP_GOING_INDEX]);
        int healthLost = values[Entity.HEALTH_LOST_INDEX];
        int shieldLost = values[Entity.SHIELD_LOST_INDEX];
        if (healthLost == 0
            && shieldLost == 0)
        {
            action.append(" received no damage.");
            return action.toString();
        }
        action.append(" lost ");
        if (shieldLost != 0)
        {
            action.append(shieldLost)
                .append(" shield (Has ")
                .append(target.getStatistic(Entity.Statistic.SHIELD))
                .append(" shield left) ");

            if (healthLost != 0)
            {
                action.append("and ");
            }
        }
        if (healthLost != 0)
        {
            action.append(healthLost)
                .append(" health (Has ")
                .append(target.getStatistic(Entity.Statistic.HEALTH))
                .append(" health left)");
        }
        return action.toString();
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