package gameSystem.stateImpls;

import org.json.JSONObject;

import gameSystem.Entity;
import gameSystem.State;

/**
 * The vigor state is used if an entity is going to make more actions.
 *
 */
public class VigorState extends State 
{
	private static final String NUM_ACTIONS_KEY = "actions";
    private int numActions;
    
    public VigorState(
    		JSONObject json)
    {
    	super(json);
    	numActions = json.getInt(NUM_ACTIONS_KEY);
    }
    /**
     * the basic constructor for this.
     * 
     * @param name the name of this state
     * @param desc the description of this state
     * @param duration the duration this state lasts for.
     * @param actions *NEW* how many actions this player can make.
     */
    public VigorState(
        String name, 
        String desc, 
        int duration, 
        int actions)
    {
        super(name, desc, duration);
        this.numActions = actions;
    }

    /**
     * Gives a number equal to the number of actions mentioned earlier.
     * 
     * @param victim effectively useless for this.
     * 
     * @return the number of actions the victim can make
     */
    @Override
    public Integer triggerStatus(Entity victim)
    {
        return numActions;
    }
    
    
    /**
     * Make a copy of this state for the sake of cloning.
     * 
     * @return a copy of this state
     */
    @Override
    public State copy()
    {
        return new VigorState(getName(), getDesc(), getDuration(), numActions);
    }
    
    public JSONObject toJson() {
    	JSONObject json = super.toJson();
    	json.put(NUM_ACTIONS_KEY, numActions);
    	json.put(TYPE_KEY, StateLoader.VIGOR);
    	return json;
	}
}