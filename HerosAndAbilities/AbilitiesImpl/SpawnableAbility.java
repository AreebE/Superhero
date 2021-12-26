import java.util.List;
import java.util.EnumMap;

public class SpawnableAbility extends DefenseAbility
{
    AIInfoItem info;

    public SpawnableAbility(
        String name, 
        String desc, 
        int cooldown, 
        AIInfoItem info, 
        AbilityList.Name enumName,
        Element em, 
        AbilityModifier... modifiers) 
    {
        super
        (
            name, 
            desc,
            cooldown, 
            null,
            enumName, 
            em,
            modifiers
        );
        this.info = info;
    }


    public SpawnableAbility(
        String name, 
        String desc, 
        int cooldown,
        AIInfoItem info, 
        AbilityList.Name enumName,
        Element em, 
        EnumMap<AbilityList.ModifierName, AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            null, 
            enumName, 
            em, 
            modifiers
        );
        this.info = info;
    }
    

    @Override
    protected boolean castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers) 
    {
        AIEntity entity = info.createEntity(caster);
        AbilityList.giveAbilities(entity, entity.getAttackPattern());
        allPlayers.add(entity);
        return true;
    }

    @Override
    public Ability copy() 
    {
        return new SpawnableAbility
                (   
                    getName(), 
                    getDescription(), 
                    getCooldown(),  
                    info,
                    getEnumName(),
                    getElement(), 
                    getModifiers()
                );
    }
}