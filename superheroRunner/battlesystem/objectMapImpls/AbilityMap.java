package battlesystem.objectMapImpls;

import battlesystem.Ability;
import battlesystem.abilityImpls.*;
import battlesystem.ObjectMap;
import battlesystem.Effect;
import battlesystem.Shield;
import battlesystem.State;
import battlesystem.infoItemImpls.AIInfoItem;
import battlesystem.Elements;
import battlesystem.Element;

public class AbilityMap extends ObjectMap<Ability> {
	
	public AbilityMap(String pathway) {
		super(pathway);
		// TODO Auto-generated constructor stub
	}

	public static final int ATTACK = 0;
	public static final int ATTACK_STATUS = 1;
	public static final int CLEANSE = 2;
	public static final int DEFENSE = 3;
	public static final int PASS = 4;
	public static final int SPAWNABLE = 5;
	public static final int STATE_CHANGE = 6;
	public static final int SUPPORT = 7;
	
	private ObjectMap<Shield> shields;
	private ObjectMap<Effect> effects;
	private ObjectMap<State> states;
	private ObjectMap<AIInfoItem> ais;
	
	public void setShields(ObjectMap<Shield> shields)
	{
		this.shields = shields;
	}
	
	public void setEffects(ObjectMap<Effect> effects)
	{
		this.effects = effects;
	}
	
	public void setStates(ObjectMap<State> states)
	{
		this.states = states;
	}
	
	public void setAIs(ObjectMap<AIInfoItem> ais)
	{
		this.ais = ais;
	}
	
	@Override
	public Ability constructObject(Integer type, Object[] parameters) {
		String name;
		String desc;
		int strength;
		int cooldown;
		Element em;
		boolean piercesDefense;
		boolean piercesAttack;
		
		switch(type)
		{
			case ATTACK:
				
//				return new AttackAbility(name, desc, 0, 0, null, false, false, null);
		
		}
		return null;
	}

}
