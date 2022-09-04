package gameSystem;

import org.json.JSONObject;

public class ItemStack implements Saveable{
	
	public static final String MIN_KEY = "min";
	public static final String MAX_KEY = "max";
	public static final String LABEL_KEY = "additional label";
	public static final String NAME_KEY = "name";
	public static final String INITIAL_KEY = "initial";
	
	private final int min;
	private final int max;
	private final String label;
	private final String name;
	private final int startingAmount;
	private int currentAmount;
	
	public ItemStack(JSONObject json)
	{
		name = json.getString(NAME_KEY);
		min = json.getInt(MIN_KEY);
		max = json.getInt(MAX_KEY);
		label = json.getString(LABEL_KEY);
		startingAmount = json.getInt(INITIAL_KEY);
		currentAmount = startingAmount;
	}
	
	public ItemStack(int min, int max, String label, String name, int startingAmount)
	{
		this.min = min;
		this.max = max;
		this.label = label;
		this.name = name;
		this.startingAmount = startingAmount;
		this.currentAmount = startingAmount;
	}

	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put(LABEL_KEY, label);
		json.put(MIN_KEY, min);
		json.put(MAX_KEY, max);
		json.put(NAME_KEY, name);
		json.put(INITIAL_KEY, startingAmount);
		return json;
	}

	@Override
	public boolean verifyValidity(Storage s) {
		return true;
	}
	
	public String getName()
	{
		return label;
	}
	
	public String getDisplay()
	{
		return currentAmount + label + " " + name;
	}
	
	public int getCount()
	{
		return currentAmount;
	}
	
	public void updateAmount(int change)
	{
		currentAmount += change;
		if (currentAmount > max)
		{
			currentAmount = max;
		}
		else if (currentAmount < min)
		{
			currentAmount = min;
		}
	}
}
