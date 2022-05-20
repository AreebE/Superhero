package battlesystem.infoItemImpls;

import org.json.*;
import java.lang.Comparable;

public abstract class MoveItem<T> implements Comparable<MoveItem<T>>
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

    public abstract void adjustPriority(T conditions);

    public int compareTo(MoveItem<T> other)
    {
        return this.getPriority() - other.getPriority();
    }

    public JSONObject toJson()
    {
        JSONObject json = new JSONObject();
        json.put(PRIORITY_KEY, priority);
        json.put(MOVE_KEY, move);
        json.put(CATEGORY_KEY, category);
        return json;
    }
}