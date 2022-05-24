package battlesystem.entityImpls;

import battlesystem.Action;
import battlesystem.BattleLog;
import battlesystem.Entity;
import battlesystem.Game;
import battlesystem.infoItemImpls.BaseAIInfoItem;
import battlesystem.infoItemImpls.MoveItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import battlesystem.State;
import battlesystem.actionImpls.AIAction;
import battlesystem.actionImpls.BasicAIAction;
import battlesystem.InputSystem;
import battlesystem.PassAction;

public class BasicAIEntity extends Entity
{

	private int currentItem;
    private List<MoveItem> moves;
    
    public static class TargettingSystem implements InputSystem
    {
    	private String name;
    	private int priority;
    	private List<Entity> entities;
    	private Entity selectedTarget;
    	private int casterTeamID;
    	
    	public TargettingSystem(String name, int casterTeamID, int priority, List<Entity> entities)
    	{
    		this.name = name;
    		this.priority = priority;
    		this.entities = entities;
    		this.casterTeamID = casterTeamID;
    	}
    	
		@Override
		public String getAbilityName() {
			return name;
		}

		@Override
		public Entity getSingleTarget() {
			return getSingleTarget(true);
		}
		
		private Entity getSingleTarget(
				boolean primaryTarget)
		{
			Random r = new Random();
			int chosenNum = r.nextInt(entities.size());
			while (
						(entities.get(chosenNum).getTeamID() == casterTeamID
						&& priority == MoveItem.NEGATIVE)
					||
						(entities.get(chosenNum).getTeamID() != casterTeamID
						&& priority == MoveItem.POSITIVE)
					|| 
						!entities.get(chosenNum).isTargettable()
			)
			{
				chosenNum = r.nextInt(entities.size());
			}
			if (primaryTarget)
			{
				this.selectedTarget = entities.get(chosenNum);
			}
			return entities.get(chosenNum);
		}
		
		@Override
		public List<Entity> getSecondaryTargets(Integer limit, Entity caster) {
			List<Entity> otherTargets = new ArrayList<>();
			if (limit == -1)
			{
				for (int i = 0; i < entities.size(); i++) {
					Entity currentEntity = entities.get(i);
					if (!currentEntity.equals(selectedTarget) && !currentEntity.equals(caster) && currentEntity.isTargettable())
					{
						otherTargets.add(currentEntity);
					}
				}
			}
			else 
			{
				for (int i = 0; i < limit; i++)
				{
					otherTargets.add(getSingleTarget(false));
				}
			}
			return otherTargets;
		}
    	
    }
    
    public BasicAIEntity(
        String name, 
        int speed, 
        int health, 
        int shieldHealth,
        State defaultState,
        Entity creator,
        List<MoveItem> moves
    )
    {
        super(name, speed, health, shieldHealth, defaultState, creator);
        this.moves = moves;
        this.currentItem = 0;
    }
    
    @Override 
    public List<Action> getActions(
    		List<Entity> allEntities,
    		InputSystem userInput)
    {
    	List<Action> actions = new ArrayList<Action>();
    	int actionsToMake = super.getState().applyStatus(this);
    	if (actionsToMake == 0)
    	{
    		actions.add(new PassAction(this));
    	}
    	else 
    	{
    		MoveItem item = moves.get(currentItem);
    		int startingPoint = currentItem;
    		boolean firstTime = true;
    		while (moveIsUnavailable(item)
    				&& 
    				(startingPoint == currentItem	
    				|| firstTime)
    			)
    		{
    			currentItem = (currentItem + 1) % moves.size();
    			item = moves.get(currentItem);
    			firstTime = false;
    		}
    		
    		if (startingPoint == currentItem && !firstTime)
    		{
    			for (int i = 0; i < actionsToMake; i++)
    			{
        			actions.add(new PassAction(this));
    			}
    			return actions;
    		}
			Entity target = getTarget(item.getPriority(), allEntities);
			TargettingSystem targettingSystem = new TargettingSystem(item.getMove(), super.getTeamID(), item.getPriority(), allEntities);
    		for (int i = 0; i < actionsToMake; i++)
    		{
    			actions.add(new BasicAIAction(this, allEntities, targettingSystem));
    		}
    	}
		return actions;
    }
    
    private boolean moveIsUnavailable(MoveItem item) {
		// TODO Auto-generated method stub
    	String abilityName = item.getMove();
		return super.getAbility(abilityName).ableToUseAbility();
	}

	private Entity getTarget(int priority, List<Entity> allEntities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public void endOfTurn(
    		BattleLog log,
    		Game g
    )
    {
    	super.endOfTurn(log, g);
    	currentItem++;
    }
    
}