package battlesystem.stateImpls;

import battlesystem.Entity;
import battlesystem.State;

public class VigorState extends State 
{
    private int numActions;
    public VigorState(
        String name, 
        String desc, 
        int duration, 
        int actions)
    {
        super(name, desc, duration);
        this.numActions = actions;
    }

    @Override
    public Integer triggerStatus(Entity victim)
    {
        return numActions;
    }

    @Override
    public State copy()
    {
        return new VigorState(getName(), getDesc(), getDuration(), numActions);
    }
}