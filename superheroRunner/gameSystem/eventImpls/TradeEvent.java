package gameSystem.eventImpls;

import java.util.ArrayList;
import java.util.Arrays;
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

	public static final int REMOVING = 1;
	public static final int REMOVING_CATEGORY = 2;
	public static final int RECIEVING = 3;
	public static final int RECIEVING_CATEGORY = 4;
	
	
	public TradeEvent(JSONObject json)
	{
		super(json);
	}
	
	public TradeEvent(
			String title, 
			ArrayList<String> preludeLines, 
			ArrayList<ArrayList<String>> postludeLines,
			ArrayList<String[]> choices, 
			String prompt) {
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
//				System.out.println(Arrays.toString(currentChoice));
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
				
				int sendingCategory = EntityInfoItem.getCategory(Integer.parseInt(currentChoice[REMOVING_CATEGORY]));
				if (sendingCategory == -1)
				{
					return false;
				}
				else if (sendingCategory == Integer.MAX_VALUE)
				{
					Integer.parseInt(currentChoice[REMOVING]);
				}
				else 
				{
					if (
							!s.getSaveables(sendingCategory)
							.containsKey(currentChoice[REMOVING]))
					{
						return false;
					}
				}
			}
			catch (InputMismatchException|NumberFormatException ime)
			{
				System.out.println(ime);
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
		int recievingCategory = Integer.parseInt(choice[RECIEVING_CATEGORY]);
		String recieved = choice[RECIEVING];
		protag.adjustItems(recieved, recievingCategory, false);
		int removingCategory = Integer.parseInt(choice[REMOVING_CATEGORY]);
		String removing = choice[REMOVING];
		protag.adjustItems(removing, removingCategory, true);
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
