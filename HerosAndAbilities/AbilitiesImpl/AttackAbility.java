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
            Ability.Type.ATTACK, 
            em,
            modifiers
        );
        this.isPiercing = isPiercing;
        this.ignoresBaseDefense = ignoresBaseDefense;
        this.em = em;
    }

    public AttackAbility(Ability parent,String info){
      super(parent);
      System.out.println("INFO IS "+info+" IN ATTACKAB");
    }
    public AttackAbility(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Element em, 
        boolean ignoresBaseDefense, 
        boolean isPiercing,
        EnumMap<Ability.Modifier, 
        AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            strength, 
            Ability.Type.ATTACK, 
            em,
            modifiers
        );
        this.isPiercing = isPiercing;
        this.ignoresBaseDefense = ignoresBaseDefense;
        this.em = em;
    }


    @Override
    protected void castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log) 
    {
        // System.out.println("Attack Ability \'" + this.getName() + "\' used on player
        // " + target.getName());
        int currentIndex = log.getCurrentIndex(); 

        int attackStrength = getStrength() + caster.getBaseAttack();
        if (attackStrength < 0) 
        {
            attackStrength = 0;
        }

        Terrain t = caster.getTerrain();

        if(t.isTerrainBuffed(this.em)){
          attackStrength += attackStrength;
        }

        Object[] results = target.dealDamage
        (
            attackStrength, 
            isPiercing, 
            ignoresBaseDefense,
            caster,
            getElement(),
            log
        );
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.ATTACK, results), currentIndex);
        if ((Boolean) results[5])
        {
            super.stopAttack();
        }
        return;
    }

    @Override
    public Ability copy() 
    {
        // System.out.println(getName() + ": " + getModifiers().toString());
        return new AttackAbility
                (
                    getName(), 
                    getDescription(), 
                    getCooldown(), 
                    getStrength(), 
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