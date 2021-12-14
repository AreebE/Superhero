import java.util.HashSet;

public class DualSheild extends Sheild
{
    private Effect selfApply;
    private Effect casterApply;

    public DualSheild(
        String name,
        String desc,
        int duration, 
        Effect self,
        Effect caster, 
        boolean nullifies,
        int uses,
        SheildList.Trigger[] eventTriggers,
        ElementList.Name[] elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.selfApply = self;
        this.casterApply = caster;
    }
    

    public DualSheild(
        String name,
        String desc,
        int duration, 
        Effect self,
        Effect caster, 
        boolean nullifies,
        int uses,
        HashSet<SheildList.Trigger> eventTriggers,
        HashSet<ElementList.Name> elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.selfApply = self;
        this.casterApply = caster;
    }


    @Override
    protected void applySheild(Superhero target, Superhero caster)
    {
        target.addEffect(selfApply.copy());
        caster.addEffect(casterApply.copy());
    }

    @Override
    public Sheild copy()
    {
        return new DualSheild(getName(), getDesc(), getDuration(), selfApply.copy(), casterApply.copy(), isNullifies(), getUses(), getEventTriggers(), getElementTriggers());
    }

}