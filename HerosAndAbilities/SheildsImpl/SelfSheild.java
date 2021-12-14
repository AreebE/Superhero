import java.util.HashSet;

public class SelfSheild extends Sheild
{
    private Effect selfApply;


    public SelfSheild(
        String name,
        String desc,
        int duration, 
        Effect selfApply, 
        boolean nullifies,
        int uses,
        SheildList.Trigger[] eventTriggers,
        ElementList.Name[] elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.selfApply = selfApply;
    }
    

    public SelfSheild(
        String name,
        String desc,
        int duration, 
        Effect selfApply, 
        boolean nullifies,
        int uses,
        HashSet<SheildList.Trigger> eventTriggers,
        HashSet<ElementList.Name> elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.selfApply = selfApply;
    }


    @Override
    protected void applySheild(Superhero target, Superhero caster)
    {
        target.addEffect(selfApply.copy());
    }

    @Override
    public Sheild copy()
    {
        return new SelfSheild(getName(), getDesc(), getDuration(), selfApply.copy(), isNullifies(), getUses(), getEventTriggers(), getElementTriggers());
    }

}