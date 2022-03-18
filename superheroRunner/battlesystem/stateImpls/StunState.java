package battlesystem.stateImpls;

import battlesystem.Entity;
import battlesystem.State;

/**
 * The stun state, used if a character is meant to be unable to take any actiosn for a while.
 *
 */
public class StunState extends State 
{
	/**
	 * Just a basic constructor: not much else to say.
	 * @param name The name of the state 
	 * @param desc the description of how it works
	 * @param duration how long it lasts, if artificially placed
	 */
    public StunState(String name, String desc, int duration)
    {
        super(name, desc, duration);
    }

    /**
     * A method to get the number of actions the victim can make.
     * @param victim useless for this version
     * @return 0 at any time.
     */
    @Override
    public Integer triggerStatus(Entity victim)
    {
        return 0;
    }

    /**
     * A copy method to create a state.
     * @return a new stun state.
     */
    @Override
    public State copy()
    {
        return new StunState(getName(), getDesc(), getDuration());
    }
}