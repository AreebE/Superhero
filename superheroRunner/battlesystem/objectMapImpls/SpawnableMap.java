package battlesystem.objectMapImpls;

import java.util.ArrayList;

import battlesystem.ObjectMap;
import battlesystem.infoItemImpls.AIInfoItem;

public class SpawnableMap extends ObjectMap<AIInfoItem> {
	
	public SpawnableMap(String pathway) {
		super(pathway);
		// TODO Auto-generated constructor stub
	}

	public static final int SIMPLE_AI = 0;
	
	@Override
	public AIInfoItem constructObject(Integer type, Object[] parameters) {
		// TODO Auto-generated method stub
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
		case SIMPLE_AI:
			name	 		=	(String)				parameters[0];
			speed			=	(Integer)				parameters[1];
			abNames			=	(ArrayList<String>)		parameters[2];
			efNames			=	(ArrayList<String>)		parameters[3];
			shNames			=	(ArrayList<String>)		parameters[4];
			state			=	(String)				parameters[5];
			maxHealth		=	(Integer)				parameters[6];
			shieldHealth	=	(Integer)				parameters[7];
			attackPattern	=	(ArrayList<String>)		parameters[8];
			targettable		=	(Boolean)				parameters[9];
			
			return new AIInfoItem(name, speed, abNames, efNames, shNames, state, maxHealth, shieldHealth, attackPattern, targettable);
		}
		return null;
	}

}
