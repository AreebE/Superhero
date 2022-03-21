package battlesystem.objectMapImpls;

import java.util.ArrayList;

import battlesystem.ObjectMap;
import battlesystem.Entity;
import battlesystem.State;
import battlesystem.stateImpls.*;

public class StateMap extends ObjectMap<State> {
	
	public StateMap(String pathway) {
		super(pathway);
		// TODO Auto-generated constructor stub
	}

	public static final int NORMAL = 0;
	public static final int STUNNED = 1;
	public static final int VIGOR = 2;
	
	@Override
	public State constructObject(Integer type, Object[] parameters) {
		// TODO Auto-generated method stub
		String name;
		String desc;
		int duration;
		int actions;
		switch(type)
		{
			case NORMAL:
				name 		= 		(String)	parameters[0];
				desc		=		(String)	parameters[1];
				duration	=		(Integer)	parameters[2];
				return new NormalState(name, desc, duration);
				
			case STUNNED:
				name 		= 		(String)	parameters[0];
				desc		=		(String)	parameters[1];
				duration	=		(Integer)	parameters[2];
				return new StunState(name, desc, duration);
				
			case VIGOR:
				name 		= 		(String)	parameters[0];
				desc		=		(String)	parameters[1];
				duration	=		(Integer)	parameters[2];
				actions 	=		(Integer) 	parameters[3];
				return new VigorState(name, desc, duration, actions);
		}
		return null;
	}

}
