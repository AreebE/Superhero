import java.util.ArrayList;

public class GroupEffect extends Effect 
{
    private int groupDuration;
    public ArrayList<Effect> listOfEffects;


    public GroupEffect(
        String name, 
        String desc, 
        Element element, 
        Effect... effects) 
    {
        super
        (
            0, 
            EffectList.Type.GROUP, 
            0, 
            true, 
            name, 
            desc, 
            element
        );
        listOfEffects = new ArrayList<>();
        groupDuration = 0;
        for (int i = 0; i < effects.length; i++) 
        {
            Effect e = effects[i].copy();
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
        ArrayList<Effect> effects) 
    {
        super
        (
            0, 
            EffectList.Type.GROUP, 
            0, 
            true, 
            name, 
            desc, 
            element
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


    @Override
    public void reduceDuration(
        Entity target) 
    {
        groupDuration--;
        for (int i = listOfEffects.size() - 1; i >= 0; i--) 
        {
            Effect e = listOfEffects.get(i);
            e.reduceDuration(target);
            System.out.println(e.getDuration());
            if (e.getDuration() <= 0) 
            {
                listOfEffects.remove(i);
            }
        }
        if (groupDuration == 0) 
        {
            target.removeEffect(this);
        }
    }


    @Override
    public void applyEffect(
        EffectList.Type type, 
        Entity target) 
    {
        for (int i = listOfEffects.size() - 1; i >= 0; i--) 
        {
            Effect e = listOfEffects.get(i);
            System.out.println(e);
            e.applyEffect(e.getType(), target);
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
                        listOfEffects
                    );
    }

    @Override
    public int getDuration() {
        return groupDuration;
    }
}