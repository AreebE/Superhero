package game.battlesystem.stateImpls;

import game.battlesystem.Entity;
import game.battlesystem.State;

public class StunState extends State 
{
    public StunState(String name, String desc, int duration)
    {
        super(name, desc, duration);
    }

    @Override
    public Integer triggerStatus(Entity victim)
    {
        return 0;
    }

    @Override
    public State copy()
    {
        return new StunState(getName(), getDesc(), getDuration());
    }
}