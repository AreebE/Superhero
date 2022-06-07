package gameSystem;

import org.json.JSONObject;

public class ConditionItem
    implements Saveable
{

	private static final String NAME_NEEDED = "name needed";
	private static final String CATEGORY_KEY = "category";
	private static final String TYPE_OF_COMPARISON_KEY = "type";
	private static final String NUMBER_REQUIREMENT_KEY = "number requirement";
	public static final String TYPE_KEY = "type";

	public static final String CATEGORY_EFFECT = "effect";
	public static final String CATEGORY_EFFECT_NUMBER = "effect #";
	public static final String CATEGORY_STACK = "stack";
	public static final String CATEGORY_HEALTH = "health";
	public static final String CATEGORY_SHIELD = "shield";
	public static final String CATEGORY_SPEED = "speed";
	public static final String CATEGORY_ATTACK = "attack";
	public static final String CATEGORY_DEFENSE = "defense";
	
	public static final String COMPARE_LESS_THAN = "less";
	public static final String COMPARE_LESS_OR_EQUAL = "less or equal";
	public static final String COMPARE_EQUAL = "equal";
	public static final String COMPARE_GREATER_OR_EQUAL = "greater or equal";
	public static final String COMPARE_GREATER_THAN = "greater";
	
	private String nameNeeded;
	private String categoryName;
	private String comparisonType;
	private int numberReq;
	
	public ConditionItem(
			JSONObject json)
	{
		nameNeeded = json.getString(NAME_NEEDED);
		categoryName = json.getString(CATEGORY_KEY);
		comparisonType = json.getString(TYPE_OF_COMPARISON_KEY);
		numberReq = json.getInt(NUMBER_REQUIREMENT_KEY);
	}
	
	public ConditionItem(
			String nameNeeded,
			String categoryName,
			String comparisonType,
			int numberReq)
	{
		this.nameNeeded = nameNeeded;
		this.categoryName = categoryName;
		this.comparisonType = comparisonType;
		this.numberReq = numberReq;
	}

	public boolean meetsRequirements(Entity target)
	{
		int number = 0;
		switch(categoryName)
		{
			case CATEGORY_EFFECT:
				number = target.getNumOfEffects();
				break;
			case CATEGORY_EFFECT_NUMBER:
				number = target.getNumOfEffects(nameNeeded);
				break;
			case CATEGORY_STACK:
				number = target.getStackNumber(nameNeeded);
				break;
			case CATEGORY_HEALTH:
				number = target.getStatistic(Entity.Statistic.HEALTH);
				break;
			case CATEGORY_SHIELD:	
				number = target.getStatistic(Entity.Statistic.SHIELD);
				break;
			case CATEGORY_SPEED:
				number = target.getStatistic(Entity.Statistic.SPEED);
				break;
			case CATEGORY_ATTACK:
				number = target.getStatistic(Entity.Statistic.BASE_ATTACK);
				break;
			case CATEGORY_DEFENSE:
				number = target.getStatistic(Entity.Statistic.BASE_DEFENSE);
				break;		
		}
		switch (comparisonType)
		{
			case COMPARE_LESS_THAN:
				return number < numberReq;
			case COMPARE_LESS_OR_EQUAL:
				return number <= numberReq;
			case COMPARE_EQUAL:
				return number == numberReq;
			case COMPARE_GREATER_OR_EQUAL:
				return number >=  numberReq;
			case COMPARE_GREATER_THAN:
				return number > numberReq;
		}
		return false;
	}
	
	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put(TYPE_KEY, "basic");
		json.put(CATEGORY_KEY, categoryName);
		json.put(NAME_NEEDED, nameNeeded);
		json.put(NUMBER_REQUIREMENT_KEY, numberReq);
		json.put(TYPE_OF_COMPARISON_KEY, comparisonType);
		return json;
	}

	@Override
	public boolean verifyValidity(Storage s) {
		return true;
	}

	public int getNumRequired() {
		return numberReq;
	}

	public String getName() {
		return nameNeeded;
	}

	public void changeEntity(Entity caster) {
		switch (categoryName)
		{
			case CATEGORY_STACK:
				caster.changeStack(nameNeeded, -numberReq);
				break;
			case CATEGORY_HEALTH:
				caster.dealEffectDamage(numberReq, true, true);;
				break;
			case CATEGORY_SHIELD:	
				caster.addShieldHealth(-numberReq);
				break;
			case CATEGORY_SPEED:
				caster.addSpeed(-numberReq);
				break;
			case CATEGORY_ATTACK:
				caster.addAttack(-numberReq);
				break;
			case CATEGORY_DEFENSE:
				caster.addDefense(-numberReq);
				break;		
		}
	}
    
}