package battlesystem.shieldImpls;

import org.json.JSONObject;
import battlesystem.Shield;
import battlesystem.Entity;
import battlesystem.Game;
import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.Elements;

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
		
	}
    
 }