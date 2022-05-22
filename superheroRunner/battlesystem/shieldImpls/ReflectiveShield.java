package battlesystem.shieldImpls;

import org.json.JSONObject;
import battlesystem.Shield;
import battlesystem.Entity;
import battlesystem.Effect;
import battlesystem.Elements;

// public class ReflectiveShield 
//     extends Shield
// {

//     public ReflectiveShield(
//         String name,
//         String desc,
//         int duration,
//         boolean nullifies,
//         int usesLeft,
//         Elements.Name[] elementNames)
//     {
//         super(name, desc, duration, duration, nullifies, usesLeft, new Trigger[]{Shield.Trigger.EFFECT_APPLIED}, elementNames);
//     }

//     @Override
//     public JSONObject toJSON()
//     {
//         JSONObject json = super.toJson();
//         json.put(Shield.TYPE_KEY, ShieldLoader.REFLECTIVE);
//         return json;
//     }

//     @Override
//     public Shield copy()
//     {
//         return null;
//     }
    
// }