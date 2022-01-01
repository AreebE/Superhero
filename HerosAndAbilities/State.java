package battlesystem;

public abstract class State 
{

    public State(
        int strength, 
        String name, 
        String description)
    {
        
    }
    
    public abstract void triggerStatus();
}   