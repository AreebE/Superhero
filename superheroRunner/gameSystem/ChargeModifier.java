package gameSystem;

import java.util.List;

import org.json.JSONObject;

public class ChargeModifier implements AbilityModifier {

	private static final String NUM_OF_CHARGES_KEY = "charges";
	
	private final int maxCharges;
	private int currentCharges;
	
	public ChargeModifier(JSONObject json)
	{
		maxCharges = json.getInt(NUM_OF_CHARGES_KEY);
		currentCharges = 0;
	}
	
	@Override
	public boolean verifyValidity(Storage s) {
		return true;
	}

	@Override
	public boolean triggerModifier(List<Entity> target, Entity caster, Ability holder, Game g, BattleLog Log) {
		// TODO Auto-generated method stub
		currentCharges++;
		if (currentCharges < maxCharges)
		{
			holder.doNotSetCooldown();
		}
		return true;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return Ability.SUCCESS_PRIORITY;
	}

	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put(NUM_OF_CHARGES_KEY, maxCharges);
		return json;
	}

}
