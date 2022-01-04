package battlesystem;

public class StunState extends State 
{
    public StunState(String name, String desc, int duration)
    {
        super(name, desc, duration);
    }

    @Override
    public Action triggerStatus(Entity victim)
    {
        return new PassAction(victim);
    }

    @Override
    public State copy()
    {
        return new StunState(getName(), getDesc(), getDuration());
    }
}