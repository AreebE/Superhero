package battlesystem;

public class NormalState extends State 
{
    public NormalState(String name, String desc, int duration)
    {
        super(name, desc, duration);
    }

    @Override
    public Action triggerStatus(Entity victim)
    {
        return null;
    }

    @Override
    public State copy()
    {
        return new NormalState(getName(), getDesc(), getDuration());
    }
}