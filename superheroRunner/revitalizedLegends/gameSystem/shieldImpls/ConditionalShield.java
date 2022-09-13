package revitalizedLegends.gameSystem.shieldImpls;

import java.util.HashSet;

import org.json.JSONObject;

import revitalizedLegends.gameSystem.BattleLog;
import revitalizedLegends.gameSystem.Element;
import revitalizedLegends.gameSystem.Elements;
import revitalizedLegends.gameSystem.Entity;
import revitalizedLegends.gameSystem.Game;
import revitalizedLegends.gameSystem.Shield;
import revitalizedLegends.gameSystem.Storage;
import revitalizedLegends.modifiers.conditionalItems.ConditionItem;
import revitalizedLegends.modifiers.conditionalItems.ConditionLoader;

public class ConditionalShield extends SelfShield {

	private static final String ITEM_KEY = "condition";
	private static final String APPLIES_ONCE_KEY = "applies once";
	private static final String CONSUMES_KEY = "consumes";
	
	private ConditionItem item;
	private boolean consumes;
	
	public ConditionalShield(JSONObject json)
	{
		super(json);
		item = ConditionLoader.loadConditionItem(json.getJSONObject(ITEM_KEY));
		consumes = json.getBoolean(CONSUMES_KEY);
	}
	
	public ConditionalShield(
			String name, 
			String desc, 
			String onSuccess,
			int duration, 
			int uses,
			ConditionItem item,
			boolean consumes
			) {
		super(name, desc, duration, onSuccess, false, uses, new HashSet<Shield.Trigger>(), new HashSet<Elements.Name>());
		this.item = item;
		this.consumes = consumes;
	}
	
	 /**
     * Reduce the duration, but remove once it reaches 0.
     * @param target the entity to target
     * @param log the battlelog to record the removal
     */
	@Override
    public void passTurn(
        Entity target, 
        Game g,
        BattleLog log)
    {
		changeForCondition(
				target,
				g,
				log,
				item.meetsRequirements(target));
    	super.passTurn(target, g, log);
    }
    
    protected void changeForCondition(
            Entity target, 
            Game g,
            BattleLog log,
            boolean shouldTrigger)
    {
    	if (shouldTrigger)
    	{
        	if (consumes)
        	{
        		item.changeEntity(target, log);
        	}
        	this.applyShield(target, target, g, log);    		
    	}
    }
  
	@Override
	public boolean wouldTrigger(
			Shield.Trigger eventTrigger, 
	        Element element)
	{
		return false;
	}
	
	@Override
	public boolean verifyValidity(Storage s) {
		// TODO Auto-generated method stub
		return super.verifyValidity(s) && item.verifyValidity(s);
	}


	@Override
	public Shield copy() {
		// TODO Auto-generated method stub
		return new ConditionalShield(
					getName(),
					getDesc(),
					getSelfApply(),
					getDuration(),
					getUses(),
					item,
					consumes
				);
	}
	
	@Override
	public JSONObject toJson()
	{
		JSONObject json = super.toJson();
		json.put(TYPE_KEY, ShieldLoader.CONDITIONAL);
		json.put(ITEM_KEY, item.toJson());
		json.put(CONSUMES_KEY, consumes);
		
		return json;
	}

	protected ConditionItem getItem() {
		// TODO Auto-generated method stub
		return item;
	}
	
	protected boolean doesConsume()
	{
		return consumes;
	}

}
