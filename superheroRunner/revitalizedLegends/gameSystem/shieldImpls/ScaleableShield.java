package revitalizedLegends.gameSystem.shieldImpls;

import org.json.JSONObject;

import revitalizedLegends.gameSystem.BattleLog;
import revitalizedLegends.gameSystem.Effect;
import revitalizedLegends.gameSystem.Entity;
import revitalizedLegends.gameSystem.Game;
import revitalizedLegends.modifiers.conditionalItems.ScaleableItem;

public class ScaleableShield extends ConditionalShield {

	private static final String LIMIT_KEY = "limit";
	private static final String ON_FAILURE_KEY = "on failure";
	
	private ScaleableItem item;
	
	private int limit;
	private String onFailure;

	private int currentTimes;

	public ScaleableShield(JSONObject json) {
		super(json);
		item = (ScaleableItem) super.getItem();
		limit = json.getInt(LIMIT_KEY);
		onFailure = json.getString(ON_FAILURE_KEY);
		this.currentTimes = 0;
		// TODO Auto-generated constructor stub
	}

	public ScaleableShield(
			String name, 
			String desc, 
			String onSuccess, 
			String onFailure,
			int duration, 
			int uses, 
			ScaleableItem item,
			int limit
			) {
		super(name, desc, onSuccess, duration, uses, item, false);
		this.limit = limit;
		this.currentTimes = 0;
		this.item = item;
		this.onFailure = onFailure;
		// TODO Auto-generated constructor stub
	}
	
	@Override 
	public void passTurn(
			  Entity target, 
		      Game g,
		      BattleLog log
		      ) 
	{
		int times = item.getTimes(target);
		int increment = (times < currentTimes)? -1: 1;
		Effect e = g.getEffect((increment == -1)? onFailure: super.getSelfApply());
		while (times != currentTimes)
		{
			super.applyShield(target, target, g, log, e);
			currentTimes += increment;
		}
		
	}

}
