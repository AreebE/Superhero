import java.util.ArrayList;
import java.util.List;

public class AIEntity extends Entity
{
    int currentAbility;
    Entity creator;
    boolean isTargettable;
    ArrayList<AbilityList.Name> attackPattern;

    public AIEntity(
        String name, 
        int speed, 
        int health, 
        int shieldHealth,
        Entity creator,
        ArrayList<AbilityList.Name> attackPattern,
        boolean isTargettable)
    {
        super(name, speed, health, shieldHealth);
        this.creator = creator;
        this.isTargettable = isTargettable;
        this.attackPattern = attackPattern;
        
        currentAbility = 0;
    }

    public class AIAction extends Entity.Action
    {
        private AIEntity caster;

        public AIAction(
            Entity target, 
            AIEntity caster, 
            List<Entity> otherTargets,
            List<Entity> allHeros)
        {
            super(target, caster, null, otherTargets, allHeros);
            this.caster = caster;
        }

        public boolean isLegalAction()
        {
            return  (
                        !(getTarget() instanceof AIEntity) 
                        || ((AIEntity) getTarget()).isTargettable() 
                    );
        }

        @Override
        public void performAction()
        {
            // System.out.println(caster);
            caster
            .getAbility()
            .useAbility(
                getTarget(), 
                caster, 
                getOtherTargets(), 
                getAllHeros()
            );
        }
    }

    @Override
    public Entity.Action getAction(
        Entity target, 
        String name,
        List<Entity> otherTargets,
        List<Entity> allHeros)
    {
        Entity.Action a = new AIAction(target, this, otherTargets, allHeros);
        if (a.isLegalAction())
        {
            return a;
        }
        else {
            return null;
        }
    }

    public Ability getAbility()
    {
        // System.out.println(currentAbility + ", " + attackPattern.get(currentAbility));
        return super.getAbility(attackPattern.get(currentAbility));
    }

    @Override
    public boolean dealDamage(
        int damageDealt, 
        boolean isPiercing, 
        boolean ignoresDefense,
        Entity caster,
        Element e
    )
    {
        if (isTargettable)
        {
            super.dealDamage(damageDealt, isPiercing, ignoresDefense, caster, e);
        }
        return false;
    }

    @Override
    public void endOfTurn()
    {
        super.endOfTurn();
        currentAbility = (currentAbility + 1) % attackPattern.size();
    }

    public boolean isTargettable()
    {
        return isTargettable;
    }

    public ArrayList<AbilityList.Name> getAttackPattern()
    {
        return this.attackPattern;
    }

    @Override
    public String toString()
    {
        return "Owner - " + creator.getName() + "\n" + super.toString();
    }
}