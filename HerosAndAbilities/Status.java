package battlesystem;

public abstract class Status 
{

    public Status(
        int strength, 
        String name, 
        String description)
    {
        
    }
    
    public abstract void triggerStatus();
}   