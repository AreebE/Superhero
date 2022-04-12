package battlesystem.effectImpls;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.Element;
import battlesystem.Entity;
import battlesystem.objectMapImpls.Effects;

/**
 * A group effect that contains several other effects. Its duration will end once the other effects do.
 *
 */
public class GroupEffect extends Effect 
{
	private static final String LIST_OF_EFFECTS = "all effects";
    private int groupDuration;
    public ArrayList<Effect> listOfEffects;

    public GroupEffect(JSONObject json)
    {
    	super(json);
    	JSONArray jsonEffects = json.getJSONArray(LIST_OF_EFFECTS);
    	listOfEffects = new ArrayList<>();
    	int groupDuration = 0;
    	for (int i = 0; i < jsonEffects.length(); i++)
    	{
    		Effect e = EffectLoader.loadEffect(jsonEffects.getJSONObject(i));
    		listOfEffects.add(e);
    		if (e.getDuration() > groupDuration)
    		{
    			groupDuration = e.getDuration();
    		}
    	}
    }
    
    /**
     * A basic constructor that sets all subeffects to the name of this one.
     * @param name the name of this effect
     * @param desc the description of what it is.
     * @param element the elemental attribute
     * @param effects the subeffects to use.
     */
    public GroupEffect(
        String name, 
        String desc, 
        Element element,
        Effect... effects) 
    {
        super
        (
            0, 
            Effect.Type.GROUP, 
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
            e.setName(name);
            listOfEffects.add(i, e);
            int currentDuration = e.getDuration();
            if (currentDuration > groupDuration) 
            {
                groupDuration = currentDuration;
            }
        }
    }

    /**
     * Another overloaded constructor for the copy ability
     * @param name the name of the group effect
     * @param desc the description of what it does
     * @param element the element of this effect
     * @param effects the subeffects.
     */
    public GroupEffect(
        String name, 
        String desc, 
        Element element, 
        ArrayList<Effect> effects) 
    {
        super
        (
            0, 
            Effect.Type.GROUP, 
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

    /**
     * Another overloaded constructor
     * @param name the name of this effect
     * @param desc the description of how it works
     * @param element its elemental attribute
     * @param effects the effects it has
     * @param additionalStrength what additional strength it has and the one to add more strength to other effects.
     */
    private GroupEffect(
        String name, 
        String desc, 
        Element element, 
        ArrayList<Effect> effects,
        int additionalStrength) 
    {
        super
        (
            0, 
            Effect.Type.GROUP, 
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
            Effect e = effects.get(i).copy(additionalStrength);
            listOfEffects.add(i, e);
            int currentDuration = e.getDuration();
            if (currentDuration > groupDuration) 
            {
                groupDuration = currentDuration;
            }
        }
    }


    /**
     * It reduces all of the effects and if their duration reaches 0, it removes them from the list.
     * 
     * @param target the entity with the target
     * @param log the battle log to record if the effect was removed.
     */
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

    
    /**
     * Applies each of the subeffects
     * 
     * @param target the person with the effect
     * @param log the log that records what happens when the effects were applied.
     */
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

    /**
     * Copy the effect
     * @return a copied version
     */
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
    
    /**
     * copy the effect
     * @param additionalStrength the additional strength to add
     * @return the new group effect
     */
    @Override
    public Effect copy(int additionalStrength) 
    {
        return new GroupEffect
                (
                    getName(), 
                    getDesc(), 
                    getElement(),
                    listOfEffects,
                    additionalStrength
                );
    }
    
    /**
     * Get the duration of this effect.
     * @return the total group duration.
     */
    @Override
    public int getDuration() {
        return groupDuration;
    }
    
    @Override
    public JSONObject toJson()
    {
    	JSONObject effect = super.toJson();
    	effect.put(TYPE_KEY, EffectLoader.GROUP);
    	JSONArray effects = new JSONArray();
    	for (Effect e: listOfEffects)
    	{
    		effects.put(e.toJson());
    	}
    	effect.put(LIST_OF_EFFECTS, effects);
    	return effect;
    }
}