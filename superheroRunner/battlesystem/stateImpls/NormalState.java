package battlesystem.stateImpls;

import battlesystem.Entity;
import battlesystem.State;

/**
 * A normal state for most characters
 *
 */
public class NormalState extends State 
{
	/**
	 * The most basic consturctor
	 * @param name the name of the state
	 * @param desc the description of what it means
	 * @param duration how long it lasts (if artifically placed)
	 */
    public NormalState(String name, String desc, int duration)
    {
        super(name, desc, duration);
    }

    /**
     * It will always return 1.
     * @param victim useless for this version.
     * @return how many actions the victim can make
     */
    @Override
    public Integer triggerStatus(Entity victim)
    {
        return 1;
    }

    /**
     * A simple enough copy method
     * @return a new normal state.
     */
    @Override
    public State copy()
    {
        return new NormalState(getName(), getDesc(), getDuration());
    }
}