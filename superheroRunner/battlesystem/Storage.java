package battlesystem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;

import battlesystem.abilityImpls.AbilityLoader;
import battlesystem.effectImpls.EffectLoader;
import battlesystem.encounterImpls.EncounterLoader;
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
    public static final int ENCOUNTERS = 6;

    private String[] originalSrcs;
    private HashMap<String, Ability> abilities;
    private HashMap<String, Effect> effects;
    private HashMap<String, Shield> shields;
    private HashMap<String, State> states;
    private HashMap<String, EntityInfoItem> entities;
    private HashMap<String, EntityInfoItem> spawnables;
    private HashMap<String, Encounter> encounters;

    public Storage(String[] files) throws FileNotFoundException
    {
        originalSrcs = files;
//        System.out.println(Arrays.toString(files) + " = files");
        this.abilities = AbilityLoader.parseJSONFile(files[ABILITIES]);
        this.effects = EffectLoader.parseJSONFile(files[EFFECTS]);
        this.shields = ShieldLoader.parseJSONFile(files[SHIELDS]);
        this.spawnables = InfoItemReader.parseJSONFile(files[SPAWNABLES]);
        this.states = StateLoader.parseJSONFile(files[STATES]);
        this.entities = InfoItemReader.parseJSONFile(files[ENTITIES]);  	  
        this.encounters = EncounterLoader.parseJSONFile(files[ENCOUNTERS]);
    }

    public Storage(
        HashMap<String, Ability> abilities,
        HashMap<String, Effect> effects,
        HashMap<String, Shield> shields,
        HashMap<String, State> states,
        HashMap<String, EntityInfoItem> entities,
        HashMap<String, EntityInfoItem> spawnables,
        HashMap<String, Encounter> encounters
    )
    {
        this.abilities = abilities;
        this.effects = effects;
        this.shields = shields;
        this.states = states;
        this.entities = entities;
        this.spawnables = spawnables;
        this.encounters = encounters;
    }

    public Ability getAbility(String name)
    {
        return abilities.get(name.toLowerCase());
    }

    public Effect getEffect(String name)
    {
//    	System.out.println(name + " , " + effects.get(name.toLowerCase()));

        return effects.get(name.toLowerCase());
    }

    public Shield getShield(String name)
    {
        return shields.get(name.toLowerCase());
    }

    public State getState(String name)
    {
        return states.get(name.toLowerCase());
    }

    public EntityInfoItem getEntity(String name)
    {
        return entities.get(name.toLowerCase());
    }

    public EntityInfoItem getSpawnable(String name)
    {
        return spawnables.get(name.toLowerCase());
    }
    
    public Encounter getEncounter(String name)
    {
    	return encounters.get(name.toLowerCase());
    }

    public boolean addItem(int type, String name, Object item)
    {
    	name = name.toLowerCase();
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

    public boolean hasItem(String name, int category)
    {
        HashMap<String, Saveable> mapToUse = getSaveables(category);
        return mapToUse.containsKey(name);
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
        for (int i = 0; i < SPAWNABLES; i++)
            {
                saveToFile(fileNames[i], i);
            }
    }

    public void saveAllToFiles()
    {
        saveAllToFiles(originalSrcs);
    }
   
}