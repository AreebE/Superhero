package battlesystem;

public abstract class State 
{

    private int duration;
    private String name;
    private String desc;
    
    public State(
        int duration, 
        String name, 
        String description)
    {
        
    }
    
    public abstract Action triggerStatus();
}   