package modifiers.abilityMods;

import java.util.List;

import org.json.JSONObject;

import gameSystem.Ability;
import gameSystem.BattleLog;
import gameSystem.Entity;
import gameSystem.Game;
import gameSystem.Storage;
import gameSystem.BattleLog.Entry;
import gameSystem.BattleLog.Entry.Type;

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
	public boolean triggerModifier(List<Entity> target, Entity caster, Ability holder, Game g, BattleLog log) {
		// TODO Auto-generated method stub
		currentCharges++;
		if (currentCharges < maxCharges)
		{
			holder.doNotSetCooldown();
		}
        else 
        {
            Object[] contents = new Object[]{};
            log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.OVERCHARGED, contents));
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
        json.put(AbilityModifier.TYPE_KEY, ModifierLoader.CHARGE);
        return json;
    }

}
