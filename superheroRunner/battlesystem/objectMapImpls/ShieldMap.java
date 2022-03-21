package battlesystem.objectMapImpls;

import battlesystem.ObjectMap;
import battlesystem.Effect;
import battlesystem.Elements;
import battlesystem.Shield;
import battlesystem.shieldImpls.*;

public class ShieldMap extends ObjectMap<Shield> {
	
	private ObjectMap<Effect> effects;
	
	public static final int DEATH = 0;
	public static final int DUAL = 1;
	public static final int SELF_DEATH = 2;
	public static final int SELF = 3;
	public static final int TRAP = 4;
	
	public ShieldMap(ObjectMap<Effect> effects)
	{
		super("E");
		this.effects = effects;
	}
	
	@Override
	public Shield constructObject(Integer type, Object[] parameters) {
		// TODO Auto-generated method stub
		String name;
		String desc;
		int duration;
		int uses;
		Effect e;
		Effect other;
		boolean nullifies;
		Shield.Trigger[] triggers;
		Elements.Name[] names;
		switch(type)
		{
			case DEATH:
				name		=	(String)	parameters[0];
				desc		=	(String)	parameters[1];
				duration	=	(Integer)	parameters[2];
				e			=	effects.getEntry((String) parameters[3]);
				nullifies	=	(Boolean)	parameters[4];
				uses		=	(Integer)	parameters[5];
				return new DeathShield(name, desc, duration, e, nullifies, uses);
				
			case DUAL:
				name		=	(String)	parameters[0];
				desc		=	(String)	parameters[1];
				duration	=	(Integer)	parameters[2];
				e			=	effects.getEntry((String) parameters[3]);
				other		=	effects.getEntry((String) parameters[4]);
				nullifies	=	(Boolean)	parameters[5];
				uses		=	(Integer)	parameters[6];
				triggers	=	(Shield.Trigger[]) parameters[7];
				names		=	(Elements.Name[])	parameters[8];
				return new DualShield(name, desc, duration, e, other, nullifies, uses, triggers, names);
				
			case SELF_DEATH:
				name		=	(String)	parameters[0];
				desc		=	(String)	parameters[1];
				duration	=	(Integer)	parameters[2];
				e			=	effects.getEntry((String) parameters[3]);
				nullifies	=	(Boolean)	parameters[4];
				uses		=	(Integer)	parameters[5];
				return new SelfDeathShield(name, desc, duration, e, nullifies, uses);
				
			case SELF:
				name		=	(String)	parameters[0];
				desc		=	(String)	parameters[1];
				duration	=	(Integer)	parameters[2];
				e			=	effects.getEntry((String) parameters[3]);
				nullifies	=	(Boolean)	parameters[4];
				uses		=	(Integer)	parameters[5];
				triggers	=	(Shield.Trigger[]) parameters[6];
				names		=	(Elements.Name[])	parameters[7];
				return new SelfShield(name, desc, duration, e, nullifies, uses, triggers, names);
				
			case TRAP:
				name		=	(String)	parameters[0];
				desc		=	(String)	parameters[1];
				duration	=	(Integer)	parameters[2];
				e			=	effects.getEntry((String) parameters[3]);
				nullifies	=	(Boolean)	parameters[4];
				uses		=	(Integer)	parameters[5];
				triggers	=	(Shield.Trigger[]) parameters[6];
				names		=	(Elements.Name[])	parameters[7];
				return new TrapShield(name, desc, duration, e, nullifies, uses, triggers, names);
		}
		return null;
	}

}
