package gameSystem;

import org.json.JSONObject;

public class Stack implements Saveable{
	
	public static final String MIN_KEY = "min";
	public static final String MAX_KEY = "max";
	public static final String LABEL_KEY = "additional label";
	public static final String NAME_KEY = "name";
	
	private final int min;
	private final int max;
	private final String label;
	private final String name;
	
	public Stack(int min, int max, String label, String name)
	{
		this.min = min;
		this.max = max;
		this.label = label;
		this.name = name;
	}

	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put(LABEL_KEY, label);
		json.put(MIN_KEY, min);
		json.put(MAX_KEY, max);
		json.put(NAME_KEY, name);
		return null;
	}

	@Override
	public boolean verifyValidity(Storage s) {
		return true;
	}
	

}
