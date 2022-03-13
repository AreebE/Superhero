package battlesystem.effectImpls;

import java.util.ArrayList;

public class GroupEffect extends Effect 
{
    private int groupDuration;
    public ArrayList<Effect> listOfEffects;


    public GroupEffect(
        String name, 
        String desc, 
        Element element,
        Effects.Name enumName, 
        Effect... effects) 
    {
        super
        (
            0, 
            Effects.Type.GROUP, 
            0, 
            true, 
            name, 
            desc, 
            element,
            enumName,
            new EffectModifier[0]
        );
        listOfEffects = new ArrayList<>();
        groupDuration = 0;
        for (int i = 0; i < effects.length; i++) 
        {
            Effect e = effects[i].copy();
            e.setName(name);
            listOfEffects.add(i, e);
            int currentDuration = e.getDuration();
            if (currentDuration > groupDuration) 
            {
                groupDuration = currentDuration;
            }
        }
    }


    private GroupEffect(
        String name, 
        String desc, 
        Element element, 
        ArrayList<Effect> effects,
        Effects.Name enumName) 
    {
        super
        (
            0, 
            Effects.Type.GROUP, 
            0, 
            true, 
            name, 
            desc, 
            element,
            enumName,
            new EffectModifier[0]
        );

        listOfEffects = new ArrayList<>();
        groupDuration = 0;
        for (int i = 0; i < effects.size(); i++) 
        {
            Effect e = effects.get(i).copy();
            listOfEffects.add(i, e);
            int currentDuration = e.getDuration();
            if (currentDuration > groupDuration) 
            {
                groupDuration = currentDuration;
            }
        }
    }

    private GroupEffect(
        String name, 
        String desc, 
        Element element, 
        ArrayList<Effect> effects,
        Effects.Name enumName,
        int additionalStrength) 
    {
        super
        (
            0, 
            Effects.Type.GROUP, 
            0, 
            true, 
            name, 
            desc, 
            element,
            enumName,
            new EffectModifier[0]
        );

        listOfEffects = new ArrayList<>();
        groupDuration = 0;
        for (int i = 0; i < effects.size(); i++) 
        {
            Effect e = effects.get(i).copy(additionalStrength);
            listOfEffects.add(i, e);
            int currentDuration = e.getDuration();
            if (currentDuration > groupDuration) 
            {
                groupDuration = currentDuration;
            }
        }
    }


    @Override
    public void reduceDuration(
        Entity target,
        BattleLog log)
    {
        groupDuration--;
        for (int i = listOfEffects.size() - 1; i >= 0; i--) 
        {
            Effect e = listOfEffects.get(i);
            e.reduceDuration();
            // System.out.println(e.getDuration());
            if (e.getDuration() <= 0) 
            {
                listOfEffects.remove(i);
            }
        }
        if (groupDuration <= 0) 
        {
            Object[] contents = new Object[]{target.getName(), 0, 0, 0, 0, 0, 0};
            log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.EFFECT_REMOVED, contents));
            target.removeEffect(this);
        }
    }


    @Override
    public void applyEffect(
        Entity target,
        BattleLog log) 
    {
        for (int i = listOfEffects.size() - 1; i >= 0; i--) 
        {
            Effect e = listOfEffects.get(i);
            e.applyEffect(target, log);
        }
    }

    
    @Override
    public Effect copy() 
    {
        return new GroupEffect
                    (
                        getName(), 
                        getDesc(), 
                        getElement(), 
                        listOfEffects,
                        getEnumName()
                    );
    }

    @Override
    public Effect copy(int additionalStrength) 
    {
        return new GroupEffect
                (
                    getName(), 
                    getDesc(), 
                    getElement(),
                    listOfEffects,
                    getEnumName(),
                    additionalStrength
                );
    }

    @Override
    public int getDuration() {
        return groupDuration;
    }
}