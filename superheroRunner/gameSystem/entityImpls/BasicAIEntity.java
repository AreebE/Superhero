package gameSystem.entityImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gameSystem.Action;
import gameSystem.Campaign;
import gameSystem.BattleLog;
import gameSystem.Entity;
import gameSystem.Game;
import gameSystem.InputSystem;
import gameSystem.PassAction;
import gameSystem.State;
import gameSystem.actionImpls.AIAction;
import gameSystem.actionImpls.BasicAIAction;
import gameSystem.infoItemImpls.BaseAIInfoItem;
import gameSystem.infoItemImpls.MoveItem;

public class BasicAIEntity extends Entity
{

	private int currentItem;
    private List<MoveItem> moves;
    
    public static class TargettingSystem implements InputSystem
    {
    	private String name;
    	private int category;
    	private List<Entity> entities;
    	private Entity selectedTarget;
    	private int casterTeamID;
    	
    	public TargettingSystem(String name, int casterTeamID, int category, List<Entity> entities)
    	{
    		this.name = name;
    		this.category = category;
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
						&& category == MoveItem.NEGATIVE)
					||
						(entities.get(chosenNum).getTeamID() != casterTeamID
						&& category == MoveItem.POSITIVE)
					|| 
						!entities.get(chosenNum).isTargettable()
			)
			{
                // System.out.println(entities.get(chosenNum).getName() + ", " + category);
				chosenNum = r.nextInt(entities.size());
			}
            // System.out.println(entities.get(chospenNum).getName() + ", " + category);
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

		@Override
		public int getChoice(String prompt, ArrayList<String[]> choices) {
			// TODO Auto-generated method stub
			return 0;
		}

         public Campaign.Direction getDirection(ArrayList<Campaign.Direction> possibleDirections)
        {
           
            return null;
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
    				&& ((startingPoint != currentItem	
    				|| firstTime))
    			)
    		{
    			startingPoint = (startingPoint + 1) % moves.size();
    			item = moves.get(startingPoint);
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
			TargettingSystem targettingSystem = new TargettingSystem(item.getMove(), super.getTeamID(), item.getCategory(), allEntities);
            for (int i = 0; i < actionsToMake; i++)
    		{
    			actions.add(new BasicAIAction(this, allEntities, targettingSystem));
    		}
    	}
		return actions;
    }
    
    private boolean moveIsUnavailable(MoveItem item) {
		// TODO Auto-generated method stub
    	String abilityName = item.getMove().toLowerCase();
        
		return super.getAbility(abilityName) == null;
	}


	@Override
    public void endOfTurn(
    		BattleLog log,
    		Game g
    )
    {
    	super.endOfTurn(log, g);
    	currentItem = (currentItem + 1) % moves.size();
    }
    
}