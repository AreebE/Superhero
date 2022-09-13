package revitalizedLegends.gameSystem;

/**
 * An action for characters that will pass their turn.
 *
 */
public class PassAction extends Action 
{
	/**
	 * a constructor for pass action 
	 * @param victim the person who will be passing
	 */
    public PassAction
    (
        Entity victim
    )
    {
        super(victim, victim,"pass turn", null, null);
    }

    /**
     * Always return true: this should be legal at any time.
     */
    @Override 
    public boolean isLegalAction()
    {
        return true;
    }

}
