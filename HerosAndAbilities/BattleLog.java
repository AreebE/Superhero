package battlesystem;

import java.util.ArrayList;

public abstract class BattleLog<T>
{   

    private int currentEntry;
    private ArrayList<Entry> entries;
    
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

        public enum Interruption 
        {
            SHIELD,
            RANDOM
        }

        public Entry(    
            Type type,
            Object[] contents
        )
        {
            this.type = type;
            this.contents = contents;
        }

        public Type getType()
        {
            return type;
        }

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
}