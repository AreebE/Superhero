package gameSystem.shieldImpls;

import org.json.JSONObject;

import gameSystem.BattleLog;
import gameSystem.Effect;
import gameSystem.Elements;
import gameSystem.Entity;
import gameSystem.Game;
import gameSystem.Shield;
import gameSystem.Storage;

 public class ReflectiveShield 
     extends Shield
 {

     public ReflectiveShield(
         String name,
         String desc,
         int duration,
         boolean nullifies,
         int usesLeft,
         Elements.Name[] elementNames)
     {
         super(name, desc, duration, nullifies, usesLeft, new Trigger[]{Shield.Trigger.EFFECT_APPLIED}, elementNames);
     }

     @Override
     public JSONObject toJson()
     {
         JSONObject json = super.toJson();
         json.put(Shield.TYPE_KEY, ShieldLoader.REFLECTIVE);
         return json;
     }

     @Override
     public Shield copy()
     {
         return null;
     }

	@Override
	protected void applyShield(Entity target, Entity caster, Game g, BattleLog log) {
		// TODO Auto-generated method stub
		Effect reflectedEffect = target.getMostRecentEffect();
		target.removeEffect(reflectedEffect);
		caster.addStartingEffect(reflectedEffect);
		log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.REFLECT, 
				new String[]{
						target.getName(),
						caster.getName(),
						reflectedEffect.getName()
				}));
	}

       @Override
    public boolean verifyValidity(Storage s)
    {
        return true;
    }
 }