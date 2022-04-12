package battlesystem;

import java.util.ArrayList;

/**
 * 
 *
 * @param <T> the type of thing to return
 * 
 */
public abstract class BattleLog<T>
{   

    private int currentEntry;
    private ArrayList<Entry> entries;
    
    /**
     *  An inner class that is a single entry 
     */
    public static class Entry  
    {
        private Object[] contents;
        private Type type;

        public enum Type
        {
            ATTACK_STATUS,
            ATTACK,
            SHIELD_TRIGGER,
            INTERRUPTED,
            CLEANSE,
            EFFECT_REMOVED,
            DEFENSE,
            PASS,
            SPAWN,
            STATE_CHANGE,
            EFFECT_APPLIED,
            ABILITY,
            SHIELD_LOST,
            CURRENT_STATE,
            KNOCKED_OUT,
            APPLY_EFFECT,
            END_OF_TURN,
            STAT_DISPLAY
        } 

        /**
         * Types of interruption that can occur.
         *
         */
        public enum Interruption 
        {
            SHIELD,
            RANDOM
        }

        /**
         * A constructor for a basic entry
         * @param type the type of this entry.
         * @param contents the contents of this entry
         */
        public Entry(    
            Type type,
            Object[] contents
        )
        {
            this.type = type;
            this.contents = contents;
        }

        /**
         * 
         * @return the type of entry
         */
        public Type getType()
        {
            return type;
        }

        /**
         * 
         * @return the contents of this array.
         */
        public Object[] getContents()
        {
            return this.contents;
        }
    }

    
    public BattleLog()
    {
        entries = new ArrayList<>();
    }

    public int getCurrentIndex()
    {
        return entries.size();
    }

    public void addEntry(Entry a)
    {
        // System.out.println(a)
        entries.add(a);   
    }

    public void addEntry(Entry a, int index)
    {
        entries.add(index, a);
    }

    protected ArrayList<Entry> getEntries(){
        return this.entries;
    }

    public abstract T getFullLog();
    
    public void clear()
    {
    	entries.clear();
    }
}