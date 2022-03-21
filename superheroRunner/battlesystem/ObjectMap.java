package battlesystem;

import java.util.Random;
import java.util.HashSet;
import java.util.Iterator;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
* 
* 
* @param <T> the object that this map will store.
*/
public abstract class ObjectMap <T> 
{
	String pathway;
    HashMap<String, T> objectMap;
    
    public ObjectMap(String pathway)
    {
//       keyDatabase = new HashMap<String, Key>();
        this.pathway = pathway;
    	objectMap = new HashMap<String, T>();
    }
/*
 * Ability: Fireball
 * 
 * T = type Ability
 * 
 * "fire"  ->  Fireball  
 * Ability : Lightning Strike
 * 
 * "lightning"
 * "light strike"
 * 
 *   
 */
    public void addEntries(String[][] identifiers, Integer[] types, Object[][] parameters)
    {
    	for (int i = 0; i < identifiers.length; i++)
    	{
    		addEntry(identifiers[i], constructObject(types[i], parameters[i]));
    	}
    }
    
    
    public void addEntries(String[][] identifiers, T[] items)
    {
    	for (int i = 0; i < identifiers.length; i++)
    	{
    		addEntry(identifiers[i], items[i]);
    	}
    }
    
    public void addEntry(String[] identifiers, T thingToAdd)
    {
        for (String i: identifiers)
        {
            objectMap.put(i, thingToAdd);
        }
    }


    public T getEntry(String identifier)
    {
        return objectMap.get(identifier);
    }
    
    public abstract T constructObject(Integer type, Object[] parameters);
//    public abstract void loadFile(File f);
    
    @Override
    public String toString()
    {
    	return objectMap.toString();
    }
}