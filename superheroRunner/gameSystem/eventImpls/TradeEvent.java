package gameSystem.eventImpls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import gameSystem.BattleLog;
import gameSystem.EntityInfoItem;
import gameSystem.Event;
import gameSystem.InputSystem;
import gameSystem.OutputSystem;
import gameSystem.Saveable;
import gameSystem.Storage;

public class TradeEvent extends Event {

	public class TradeItem implements Saveable
	{
		private static final String CATEGORY_KEY = "category";
		private static final String IS_NEGATIVE_KEY = "is negative";
		private static final String NAME_KEY = "name";
		private static final String AMOUNT_TO_REDUCE_KEY = "amount to reduce"; 
		
		private boolean isNegative;
		private int category;
		private String name;
		private int amountToReduce;
		
		public TradeItem(String line)
		{
			 this((JSONObject) new JSONTokener(line).nextValue());
		}
		
		public TradeItem(JSONObject json)
		{
			this.isNegative = json.getBoolean(IS_NEGATIVE_KEY);
			this.category = json.getInt(CATEGORY_KEY);
			this.name = json.getString(NAME_KEY);
			this.amountToReduce = json.getInt(AMOUNT_TO_REDUCE_KEY);
		}
		
		@Override
		public JSONObject toJson() {
			JSONObject json = new JSONObject();
			json.put(CATEGORY_KEY, category);
			json.put(IS_NEGATIVE_KEY, isNegative);
			json.put(NAME_KEY, name);
			json.put(AMOUNT_TO_REDUCE_KEY, amountToReduce);
			return json;
		}

		@Override
		public boolean verifyValidity(Storage s) {
			try
			{
				int recievingCategory = EntityInfoItem.getCategory(category);
				if (recievingCategory == -1)
				{
					return false;
				}
				else if (recievingCategory == Integer.MAX_VALUE)
				{
					return true;
				}
				else 
				{
					if (
							!s.getSaveables(recievingCategory)
							.containsKey(name))
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
			
			return true;
		}

		public void changeProtag(EntityInfoItem protag) {
			if (amountToReduce == 0)
			{
				protag.adjustItems(name, category, isNegative);
			}
			else 
			{
				protag.adjustItems(amountToReduce + "", category, isNegative);
			}
		}
		
	}
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
				String[] choiceSet = choices.get(i);
				boolean isValid = true;
				for (int j = 1; j < choiceSet.length; j++)
				{
					System.out.println(choiceSet[j]);
					isValid = new TradeItem(choiceSet[j]).verifyValidity(s);
					if (!isValid)
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
		for (int i = 1; i < choice.length; i++)
		{
			new TradeItem(choice[i]).changeProtag(protag);
		}
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
