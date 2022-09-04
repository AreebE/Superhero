package modifiers.conditionalItems;

import org.json.JSONObject;

import gameSystem.Entity;

public class ScaleableItem extends ConditionItem {

	private static final String INTERVAL_KEY = "interval";

	private int interval;
	private int min;
	
	public ScaleableItem(JSONObject json) 
	{
		super(json);
		interval = json.getInt(INTERVAL_KEY);
		min = json.getInt(ConditionItem.NUMBER_REQUIREMENT_KEY);
		// TODO Auto-generated constructor stub
	}

	public ScaleableItem(
			String nameNeeded, 
			String categoryName, 
			int numberReq,
			int interval) 
	{
		super(nameNeeded, categoryName, ConditionItem.COMPARE_GREATER_THAN, numberReq);
		// TODO Auto-generated constructor stub
		this.interval = interval;
		this.min = numberReq;
	}
	
	
	public int getTimes(Entity target)
	{
		return (super.meetsRequirements(target))? (super.getNumber(target) - min) / interval: 0;
	}
	
	@Override 
	public JSONObject toJson()
	{
		JSONObject json = super.toJson();
		json.put(ConditionItem.TYPE_KEY, ConditionLoader.SCALEABLE);
		json.put(INTERVAL_KEY, interval);
		return json;
	}
}
