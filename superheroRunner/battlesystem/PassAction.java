package battlesystem;

public class PassAction extends Action 
{
    public PassAction
    (
        Entity victim
    )
    {
        super(victim, victim,"pass", null, null);
    }

    @Override 
    public boolean isLegalAction()
    {
        return true;
    }

}
