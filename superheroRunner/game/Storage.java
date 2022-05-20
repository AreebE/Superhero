package game;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;

import battlesystem.Ability;
import battlesystem.Effect;
import battlesystem.Shield;
import battlesystem.State;
import battlesystem.Saveable;
import battlesystem.EntityInfoItem;
import battlesystem.abilityImpls.AbilityLoader;
import battlesystem.effectImpls.EffectLoader;
import battlesystem.infoItemImpls.InfoItemReader;
import battlesystem.shieldImpls.ShieldLoader;
import battlesystem.stateImpls.StateLoader;

import java.io.FileNotFoundException;


public class Storage
{
    public static final int ABILITIES = 0;
    public static final int EFFECTS = 1;
    public static final int SHIELDS = 2;
    public static final int STATES = 3;
    public static final int ENTITIES = 4;
    public static final int SPAWNABLES = 5;

    private HashMap<String, Ability> abilities;
    private HashMap<String, Effect> effects;
    private HashMap<String, Shield> shields;
    private HashMap<String, State> states;
    private HashMap<String, EntityInfoItem> entities;
    private HashMap<String, EntityInfoItem> spawnables;

    public Storage(String[] files) throws FileNotFoundException
    {
        this.abilities = AbilityLoader.parseJSONFile(files[ABILITIES]);
        this.effects = EffectLoader.parseJSONFile(files[EFFECTS]);
        this.shields = ShieldLoader.parseJSONFile(files[SHIELDS]);
        this.spawnables = InfoItemReader.parseJSONFile(files[SPAWNABLES]);
        this.states = StateLoader.parseJSONFile(files[STATES]);
        this.entities = InfoItemReader.parseJSONFile(files[ENTITIES]);  	  
    }

    public Storage(
        HashMap<String, Ability> abilities,
        HashMap<String, Effect> effects,
        HashMap<String, Shield> shields,
        HashMap<String, State> states,
        HashMap<String, EntityInfoItem> entities,
        HashMap<String, EntityInfoItem> spawnables
    )
    {
        this.abilities = abilities;
        this.effects = effects;
        this.shields = shields;
        this.states = states;
        this.entities = entities;
        this.spawnables = spawnables;
    }

    public Ability getAbility(String name)
    {
        return abilities.get(name);
    }

    public Effect getEffect(String name)
    {
        return effects.get(name);
    }

    public Shield getShield(String name)
    {
        return shields.get(name);
    }

    public State getState(String name)
    {
        return states.get(name);
    }

    public EntityInfoItem getEntity(String name)
    {
        return entities.get(name);
    }

    public EntityInfoItem getSpawnable(String name)
    {
        return spawnables.get(name);
    }

    public boolean addItem(int type, String name, Object item)
    {
        switch (type)
        {
            case ABILITIES:
                if (abilities.containsKey(name))
                {
                    return false;   
                }
                abilities.put(name, (Ability) item);
                break;
                
            case EFFECTS:
                if (effects.containsKey(name))
                {
                    return false;   
                }
                effects.put(name, (Effect) item);
                break;
                
            case SHIELDS:
                if (shields.containsKey(name))
                {
                    return false;   
                }
                shields.put(name, (Shield) item);
                break;
                
            case STATES:
                if (states.containsKey(name))
                {
                    return false;   
                }
                states.put(name, (State) item);
                break;
                
            case ENTITIES:
                if (entities.containsKey(name))
                {
                    return false;   
                }
                entities.put(name, (EntityInfoItem) item);
                break;
                
            case SPAWNABLES:
                if (spawnables.containsKey(name))
                {
                    return false;   
                }
                spawnables.put(name, (EntityInfoItem) item);
                break;
        }
        return true;
    }

    public HashMap<String, Saveable> getSaveables(int category)
    {
        switch(category)
        {
            case ABILITIES: 
                return new HashMap<String, Saveable>(abilities);
            case EFFECTS:
                return new HashMap<String, Saveable>(effects);
            case SHIELDS:
                return new HashMap<String, Saveable>(shields);
            case STATES:
                return new HashMap<String, Saveable>(states);
            case ENTITIES:
                return new HashMap<String, Saveable>(entities);
            case SPAWNABLES:
                return new HashMap<String, Saveable>(spawnables);
        }
        return null;
    }
    
    public void saveToFile(String fileName, int type)
    {
        JSONArray objects = new JSONArray();
        HashMap<String, Saveable> objectsToSave = getSaveables(type);
        Iterator<String> keys = objectsToSave.keySet().iterator();
        while (keys.hasNext())
        {
            String currentKey = keys.next();
            try 
            {
                objects.put(0, objectsToSave.get(currentKey).toJson());  
            } 
            catch (JSONException jsone)
            {
                System.out.println(jsone.toString());
            }
        }
    }

    public void saveAllToFiles(String[] fileNames)
    {
        
    }
   
}