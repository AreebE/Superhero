import java.util.HashSet;

public class TrapSheild extends Sheild
{
    private Effect counter;


    public TrapSheild(
        String name,
        String desc,
        int duration, 
        Effect counter, 
        boolean nullifies,
        int uses,
        SheildList.Trigger[] eventTriggers,
        ElementList.Name[] elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.counter = counter;
    }
    

    public TrapSheild(
        String name,
        String desc,
        int duration, 
        Effect counter, 
        boolean nullifies,
        int uses,
        HashSet<SheildList.Trigger> eventTriggers,
        HashSet<ElementList.Name> elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.counter = counter;
    }


    @Override
    protected void applySheild(Superhero target, Superhero caster)
    {
                System.out.println("Apply " + isNullifies());
        caster.addEffect(counter);
    }

    @Override
    public Sheild copy()
    {
        return new TrapSheild(getName(), getDesc(), getDuration(), counter.copy(), isNullifies(), getUses(), getEventTriggers(), getElementTriggers());
    }

}