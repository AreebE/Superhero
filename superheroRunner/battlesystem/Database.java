package battlesystem;

import java.util.Random;
import java.util.HashSet;
import java.util.HashMap;

/**
* This class is going to store important information, though only for one generic type of object.
* It also contains 2 hashmaps; one for strings, the other for keys. Seems a bit redundant at first, but this is so keys 
* can be changed with relative ease.
*
* @param <T> the object that this database will store.
*/
public abstract class Database<T> 
{
    HashMap<String, Key> keyDatabase;
    HashMap<Key, T> objectDatabase;
    
    public Database()
    {
       keyDatabase = new HashMap<String, Key>();
        objectDatabase = new HashMap<Key, T>();
    }

    public void addEntry(String[] identifiers, T thingToAdd)
    {
        HashSet<String> list = new HashSet<String>();
        Key k = new Key(list);
        for (String i: identifiers)
        {
            list.add(i);
            keyDatabase.put(i, k);
        }
        objectDatabase.put(k, thingToAdd);
        assignKey(k, thingToAdd);
    }

    protected abstract void assignKey(Key k, T object);

    public T getEntry(String identifier)
    {
        return objectDatabase.get(keyDatabase.get(identifier));
    }
}