package battlesystem.stateImpls;

public class NormalState extends State 
{
    public NormalState(String name, String desc, int duration)
    {
        super(name, desc, duration);
    }

    @Override
    public Integer triggerStatus(Entity victim)
    {
        return 1;
    }

    @Override
    public State copy()
    {
        return new NormalState(getName(), getDesc(), getDuration());
    }
}