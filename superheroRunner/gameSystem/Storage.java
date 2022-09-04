package gameSystem;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;

import gameSystem.abilityImpls.AbilityLoader;
import gameSystem.campaignImpl.CampaignLoader;
import gameSystem.effectImpls.EffectLoader;
import gameSystem.encounterImpls.EncounterLoader;
import gameSystem.eventImpls.EventLoader;
import gameSystem.infoItemImpls.InfoItemReader;
import gameSystem.shieldImpls.ShieldLoader;
import gameSystem.stateImpls.StateLoader;

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
    public static final int EVENTS = 7;
    public static final int CAMPAIGNS = 8;
    public static final int STACKS = 9;
    public static final int UNDEF = Integer.MAX_VALUE;
    
    
    private String[] originalSrcs;
    private HashMap<String, Ability> abilities;
    private HashMap<String, Effect> effects;
    private HashMap<String, Shield> shields;
    private HashMap<String, State> states;
    private HashMap<String, EntityInfoItem> entities;
    private HashMap<String, EntityInfoItem> spawnables;
    private HashMap<String, Encounter> encounters;
    private HashMap<String, Event> events;
    private HashMap<String, Campaign> campaigns;
    private HashMap<String, ItemStack> stacks;

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
        this.events = EventLoader.parseJsonFile(files[EVENTS]);  	  
        this.campaigns = CampaignLoader.parseJsonFile(files[CAMPAIGNS]);
    }
//
//    public Storage(
//        HashMap<String, Ability> abilities,
//        HashMap<String, Effect> effects,
//        HashMap<String, Shield> shields,
//        HashMap<String, State> states,
//        HashMap<String, EntityInfoItem> entities,
//        HashMap<String, EntityInfoItem> spawnables,
//        HashMap<String, Encounter> encounters
//    )
//    {
//        this.abilities = abilities;
//        this.effects = effects;
//        this.shields = shields;
//        this.states = states;
//        this.entities = entities;
//        this.spawnables = spawnables;
//        this.encounters = encounters;
//    }

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
    
    public Event getEvent(String name)
    {
    	return events.get(name.toLowerCase());
    }

    public Campaign getCampaign(String name)
    {
    	return campaigns.get(name.toLowerCase());
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
            case ENCOUNTERS:
                if (encounters.containsKey(name))
                {
                    return false;   
                }
                encounters.put(name, (Encounter) item);
                break;
        }
        return true;
    }

    private void removeItem(int category, String name)
    {
        name = name.toLowerCase();
        switch(category)
        {
            case ABILITIES:
                abilities.remove(name);
                return;
            case EFFECTS:
                effects.remove(name);
                return;
            case SHIELDS:
                shields.remove(name);
                return;
            case SPAWNABLES:
                spawnables.remove(name);
                return;
            case ENTITIES:
                entities.remove(name);
                return;
            case ENCOUNTERS:
                encounters.remove(name);
                return;
            case STATES:
                encounters.remove(name);
                return;
            case EVENTS:
            	events.remove(name);
            case CAMPAIGNS:
            	campaigns.remove(name);
        }
    }

    public boolean hasItem(String name, int category)
    {
        HashMap<String, Saveable> mapToUse = getSaveables(category);
        return mapToUse.containsKey(name.toLowerCase());
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
            case ENCOUNTERS:
                return new HashMap<String, Saveable>(encounters);
            case EVENTS:
                return new HashMap<String, Saveable>(events);
            case CAMPAIGNS:
                return new HashMap<String, Saveable>(campaigns);
     
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

    public void verifyAllItems()
    {
        checkSaveables(EFFECTS);
        checkSaveables(STATES);
        checkSaveables(SHIELDS);
        int countRemoved = 1;
        while (countRemoved > 0)
        {
            countRemoved = 0;
            countRemoved += checkSaveables(ABILITIES);
            countRemoved += checkSaveables(ENTITIES);
            countRemoved += checkSaveables(SPAWNABLES);
//            System.out.println("e " + countRemoved);
        }
        checkSaveables(ENCOUNTERS);
        checkSaveables(EVENTS);
        checkSaveables(CAMPAIGNS);
    }

    private int checkSaveables(int category)
    {
        ArrayList<String> names = new ArrayList<>();
        HashMap<String, Saveable> saveables = getSaveables(category);
        Iterator<String> allKeys = saveables.keySet().iterator();
        while (allKeys.hasNext())
        {
            String name = allKeys.next();
            if (!saveables.get(name).verifyValidity(this))
            {
                System.out.println(name);
                names.add(name);
            }
        }
        for (int i = 0; i < names.size(); i++)
            {
                removeItem(category, names.get(i));
            }
        return names.size();
    }
   
}