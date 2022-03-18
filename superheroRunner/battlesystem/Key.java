package battlesystem;

import java.util.HashSet;

/**
* Used when you need to distinguish one object from another
*/
public class Key
{
    private HashSet<String> identifiers;

    /**
    * A public constructor for the key, giving it some identifiers
    * 
    * @param identifiers what is used to identify this key.
    */
    public Key(HashSet<String> identifiers)
    {
        this.identifiers = identifiers;
    }

    /**
    * A method to check if a string input would work for this key. It is best used if you want to ensure the program recognizes what you are asking as a valid name.
    * 
    * @param input the string input to identify
    * 
    * @return if this string is contained in the identifiers
    */
    public boolean keyWouldRespond(String input)
    {
        return identifiers.contains(input);
    }

    /**
    * Reassign the identifiers if necessary
    *
    * @param identifiers the new identifiers to add
    */

    public void reassignIdentifiers(HashSet<String> identifiers)
    {
        this.identifiers = identifiers;
    }
}