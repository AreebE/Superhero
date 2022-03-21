package battlesystem.objectMapImpls;

import java.util.ArrayList;

import battlesystem.ObjectMap;
import battlesystem.EntityInfoItem;
import battlesystem.infoItemImpls.AIInfoItem;

public class EntityMap extends ObjectMap<EntityInfoItem> {
	
	public EntityMap(String pathway) {
		super(pathway);
		// TODO Auto-generated constructor stub
	}

	public static final int BASIC_ENTITY = 0;
	
	@Override
	public EntityInfoItem constructObject(Integer type, Object[] parameters) {
		String name;
		int speed;
		ArrayList<String> abNames;
		ArrayList<String> efNames;
		ArrayList<String> shNames;
		String state;
		int maxHealth;
		int shieldHealth;
		ArrayList<String> attackPattern;
		boolean targettable;
		
		switch(type)
		{
			case BASIC_ENTITY:
				name	 		=	(String)				parameters[0];
				speed			=	(Integer)				parameters[1];
				abNames			=	(ArrayList<String>)		parameters[2];
				efNames			=	(ArrayList<String>)		parameters[3];
				shNames			=	(ArrayList<String>)		parameters[4];
				state			=	(String)				parameters[5];
				maxHealth		=	(Integer)				parameters[6];
				shieldHealth	=	(Integer)				parameters[7];
				
				return new EntityInfoItem(name, speed, abNames, efNames, shNames, state, maxHealth, shieldHealth);
		}
		return null;
	}

}
