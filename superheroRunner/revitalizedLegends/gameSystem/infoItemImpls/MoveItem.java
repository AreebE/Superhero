package revitalizedLegends.gameSystem.infoItemImpls;

import org.json.*;

import revitalizedLegends.gameSystem.Game;

import java.lang.Comparable;

public class MoveItem implements Comparable<MoveItem>
{
    public static final String TYPE_KEY = "type";
    
    private static final String PRIORITY_KEY = "priority";
    private static final String MOVE_KEY = "move";
    private static final String CATEGORY_KEY = "category";
    
    public static final int POSITIVE = 1;
    public static final int NEGATIVE = -1;
    public static final int NEUTRAL = 0;
    
    private int priority;
    private String move;
    private int category;

    public MoveItem(JSONObject json) throws JSONException
    {
        priority = json.getInt(PRIORITY_KEY);
        move = json.getString(MOVE_KEY);
        category = json.getInt(CATEGORY_KEY);
    }
    
    public MoveItem(int priority, int category, String move)
    {
        this.priority = priority;
        this.category = category;
        this.move = move;
    }

    public String getMove()
    {
        return move;
    }

    public int getCategory()
    {
        return category;
    }

    public int getPriority()
    {
        return priority;
    }

    public void adjustPriority(Game g)
    {
    	
    }

    public int compareTo(MoveItem other)
    {
        return this.getPriority() - other.getPriority();
    }

    public JSONObject toJson()
    {
        JSONObject json = new JSONObject();
        json.put(TYPE_KEY, "basic");
        json.put(PRIORITY_KEY, priority);
        json.put(MOVE_KEY, move);
        json.put(CATEGORY_KEY, category);
        return json;
    }
}