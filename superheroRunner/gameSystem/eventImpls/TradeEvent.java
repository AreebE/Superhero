package gameSystem.eventImpls;

import java.util.ArrayList;
import java.util.InputMismatchException;

import org.json.JSONArray;
import org.json.JSONObject;

import gameSystem.BattleLog;
import gameSystem.EntityInfoItem;
import gameSystem.Event;
import gameSystem.InputSystem;
import gameSystem.OutputSystem;
import gameSystem.Storage;

public class TradeEvent extends Event {

	public static final int GIVING = 1;
	public static final int GIVING_CATEGORY = 2;
	public static final int RECIEVING = 3;
	public static final int RECIEVING_CATEGORY = 4;
	
	private static final String CATEGORY_KEY = "category";
	
	public TradeEvent(JSONObject json)
	{
		super(json);
		JSONArray categoriesJson = json.getJSONArray(CATEGORY_KEY);
	}
	
	public TradeEvent(
			String title, 
			ArrayList<String> preludeLines, 
			ArrayList<ArrayList<String>> postludeLines,
			ArrayList<String[]> choices, 
			String prompt,
			int[] categories) {
		super(title, preludeLines, postludeLines, choices, prompt);
	}

	@Override
	public boolean verifyValidity(Storage s) 
	{
		ArrayList<String[]> choices = super.getChoices();
		for (int i = 0; i < choices.size(); i++)
		{
			try
			{
				String[] currentChoice = choices.get(i);
				int recievingCategory = EntityInfoItem.getCategory(Integer.parseInt(currentChoice[RECIEVING_CATEGORY]));
				if (recievingCategory == -1)
				{
					return false;
				}
				else if (recievingCategory == Integer.MAX_VALUE)
				{
					Integer.parseInt(currentChoice[RECIEVING]);
				}
				else 
				{
					if (
							!s.getSaveables(recievingCategory)
							.containsKey(currentChoice[RECIEVING]))
					{
						return false;
					}
				}
				
				int sendingCategory = EntityInfoItem.getCategory(Integer.parseInt(currentChoice[GIVING_CATEGORY]));
				if (sendingCategory == -1)
				{
					return false;
				}
				else if (sendingCategory == Integer.MAX_VALUE)
				{
					Integer.parseInt(currentChoice[GIVING]);
				}
				else 
				{
					if (
							!s.getSaveables(sendingCategory)
							.containsKey(currentChoice[GIVING]))
					{
						return false;
					}
				}
			}
			catch (InputMismatchException ime)
			{
				return false;
			}
			
		}
		return true;
	}

	@Override
	protected boolean executeAction(
			String[] choice, 
			BattleLog log, 
			Storage s, 
			EntityInfoItem protag, 
			InputSystem input,
			OutputSystem output) 
	{
//		protag.
		int recievingCategory = EntityInfoItem.getCategory(Integer.parseInt(choice[RECIEVING_CATEGORY]));
		String recieved = choice[RECIEVING];
		protag.adjustItems(recieved, recievingCategory, false);
		int sendingCategory = EntityInfoItem.getCategory(Integer.parseInt(choice[GIVING_CATEGORY]));
		String sending = choice[GIVING];
		protag.adjustItems(sending, sendingCategory, true);
		return false;
	}
	
	@Override 
	public JSONObject toJson()
	{
		JSONObject json = super.toJson();
		json.put(TYPE_KEY, EventLoader.TRADE);
		return json;
	}

}
