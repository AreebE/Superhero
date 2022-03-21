package battlesystem.objectMapImpls;

import java.util.ArrayList;

import battlesystem.ObjectMap;
import battlesystem.Effect;
import battlesystem.effectImpls.*;
import battlesystem.EffectModifier;
import battlesystem.Element;
import battlesystem.Elements;
import battlesystem.Entity;
import battlesystem.Key;

public class EffectsMap extends ObjectMap<Effect> {
	
	public EffectsMap(String pathway) {
		super(pathway);
		// TODO Auto-generated constructor stub
	}

	public static final int BASE = 0;
	public static final int DECAY = 1;
	public static final int DELAY = 2;
	public static final int GROUP = 3;
	public static final int INSTANT = 4;
	public static final int ONE_TIME = 5;
	public static final int PASSIVE = 6;


	/* Base effect: 
	 *   int strength, 
        Effect.Type type, 
        int duration, 
        boolean permanent, 
        String name, 
        String desc,
        Element element, 
        boolean[] pierces,
        EffectModifier[] modifiers
        __________________________
        
        
	 */
	@Override
	public Effect constructObject(Integer type, Object[] parameters) {
		// TODO Auto-generated method stub
		int strength = 0;
		Effect.Type typeOfStat = Effect.Type.HEALTH;
		int duration = 1;
		boolean permanent = false;
		String name = "404";
		String desc = "Could not find";
		Element e = Elements.getElement(Elements.Name.NULL);
		int decayRate = 0;
		int decayTurnStarts = 0;
		boolean[] pierces = null;
		EffectModifier[] modifiers = null;
		
		switch(type)
		{
			case BASE:
				strength 	=	(Integer) 			parameters[0];
				typeOfStat 	= 	(Effect.Type) 		parameters[1];
				duration 	=	(Integer) 			parameters[2];
				permanent 	= 	(Boolean) 			parameters[3];
				name 		=	(String) 			parameters[4];
				desc 		=	(String) 			parameters[5];
				e 			=	(Element) 			parameters[6];
				pierces 	=	getPierces(			parameters[7]);
				modifiers 	= 	(EffectModifier[]) 	parameters[8];
				return new Effect(strength, typeOfStat, duration, permanent, name, desc, e, pierces, modifiers);
			case DECAY:
				strength 		= (Integer) 			parameters[0];
				decayRate 		= (Integer) 			parameters[1];
				decayTurnStarts = (Integer) 			parameters[2];
				typeOfStat 		= (Effect.Type) 		parameters[3];
				duration 		= (Integer) 			parameters[4];
				name 			= (String) 				parameters[5];
				desc 			= (String) 				parameters[6];
				e 				= (Element) 			parameters[7];
				pierces 		= getPierces(			parameters[8]);
				modifiers 		= (EffectModifier[]) 	parameters[9];
				return new DecayEffect(strength, decayRate, decayTurnStarts, typeOfStat, duration, name, desc, e, pierces, modifiers);
			case DELAY:
				strength 	=	(Integer) 			parameters[0];
				typeOfStat 	= 	(Effect.Type) 		parameters[1];
				duration 	=	(Integer) 			parameters[2];
				name 		=	(String) 			parameters[3];
				desc 		=	(String) 			parameters[4];
				e 			=	(Element) 			parameters[5];
				pierces 	=	getPierces(			parameters[6]);
				modifiers 	= 	(EffectModifier[]) 	parameters[7];
				return new DelayedEffect(strength, typeOfStat, duration, name, desc, e, pierces, modifiers);
			case GROUP: 
				name 	= (String) 	parameters[0];
				desc	= (String) 	parameters[1];
				e		= (Element)	parameters[2];
				String[] allNames = (String[]) parameters[3];
				ArrayList<Effect> effects = new ArrayList<>();
				for (String n: allNames)
				{
					effects.add(this.getEntry(n));
				}
				return new GroupEffect(name, desc, e, effects);
			case INSTANT:
				strength 		= 	(Integer)			parameters[0];
				typeOfStat 		= 	(Effect.Type) 		parameters[1];
				name 			= 	(String)			parameters[2];
				desc 			=	(String)			parameters[3];
				e				=	(Element)			parameters[4];
				pierces			=	getPierces			(parameters[5]);
				modifiers 		= 	(EffectModifier[])	parameters[6];
				return new InstantEffect(strength, typeOfStat, name, desc, e, pierces, modifiers);
			case ONE_TIME:
				strength 		=	(Integer)			parameters[0];
				typeOfStat		=	(Effect.Type)		parameters[1];
				duration		=	(Integer)			parameters[2];
				name			=	(String)			parameters[3];
				desc 			=	(String)			parameters[4];
				e				=	(Element)			parameters[5];
				pierces			=	getPierces			(parameters[6]);
				modifiers		=	(EffectModifier[])	parameters[7];
				return new OneTimeEffect(decayTurnStarts, typeOfStat, decayTurnStarts, desc, desc, e, pierces, modifiers);
			case PASSIVE:
				strength 		=	(Integer)			parameters[0];
				typeOfStat		=	(Effect.Type)		parameters[1];
				name			=	(String)			parameters[2];
				desc			=	(String)			parameters[3];
				e				=	(Element)			parameters[4];
				pierces			=	getPierces			(parameters[5]);
				modifiers 		=	(EffectModifier[])	parameters[6];
				return new PassiveEffect(strength, typeOfStat, name, desc, e, pierces, modifiers);
		}
		return null;
	}
	
	private boolean[] getPierces(Object o)
	{
		try 
		{
			Boolean[] pierces = (Boolean[]) (o);
			return new boolean[] {pierces[0], pierces[1]};
		}
		catch (ClassCastException cce)
		{
			return null;
		}
	}
	
	
}
