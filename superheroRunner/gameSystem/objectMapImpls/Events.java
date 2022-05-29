package gameSystem.objectMapImpls;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;

import gameSystem.Event;
import gameSystem.eventImpls.BattleEvent;

public class Events {
	private static final ArrayList<Event> EVENTS = new ArrayList<Event>()
			{{
				add
				(
						new BattleEvent
							(
									"Stupid King...",
									new ArrayList<String>()
									{{
										add("Have you ever been tired of a meme?");
										add("An ordinary Joe decided he had enough, so he started walking towards the birthplace -- ");
										add("Sawcon.");
										add("First, he would have to prove he was worthy of meeting the manager.");
										add("Who better than a king?");
										add("Off to a new world, where he found himself on the king\'s battleground.");
										add("He had just finished his fight, where he then sees you.");
									}},
									new ArrayList<ArrayList<String>>()
									{{
										add(new ArrayList<String>()
										{{
											add("Upon his defeat, the king laughs no more.");
											add("Finally, one meme down.");
											add("Proceed.");
										}});
									}},
									new ArrayList<String[]>()
									{{
										add(new String[] {"The king points his scepter, all full of elixir.", "The Laughing King"});
									}},
									"He challenges you directly, seeing you as a challenge to his status. No way out of this.",
									"Upon your loss, the king laughs at your grave. Really... what hope did you have?"
									
							)
				);
				add
				(
						new BattleEvent
						(
								"Friday Night Out",
								new ArrayList<String>()
								{{
									add("You continue to walk.");
									add("As you do so, a portal opens out of the sky.");
									add("Out comes a blue haired kid with what looks to be a stop sign on his shirt.");
									add("He gets up, then immediately throws a microphone at you.");
									add("Hesitantly, you accept the gift.");
								}},
								new ArrayList<ArrayList<String>>()
								{{
									add(new ArrayList<String>()
									{{
										add("The kid loses, but seems to take it... somewhat well.");
										add("The portal then arrives, taking him away once more.");
										add("You wonder for a moment how he came here, but no point dwelling on that.");
									}});
								}},
								new ArrayList<String[]>()
								{{
									add(new String[] {"The kid pulls out his own, looking eager to rap battle with you.", "A Funky Battle"});
								}},
								"You're not sure if your rapping skills are the best, so maybe it's better to sing with attacks?",
								"You lose, which causes you to consider the fact that a child beat you. Maybe this wasn't such a good idea..."
						)
				);
				add
				(
						new BattleEvent
						(
								"Engineering a Meme",
								new ArrayList<String>()
								{{
									add("As you continue to walk, you find yourself in a dustbowl.");
									add("A few shotgun pellets fly by your left and you turn to see a lone man in a construction helmet.");
									add("His shotgun is still smoking and he aims it right at you..");
									add("You ask that this be resolved peacefully.");
								}},
								new ArrayList<ArrayList<String>>()
								{{
									add(new ArrayList<String>()
									{{
										add("The engineer's buildings self-destruct and, he cowers before you.");
										add("He pleads for his life, even offering to build you a spaceship to get to the final destination.");
										add("You oblige, since you weren't too keen on being that drastic.");
									}});
								}},
								new ArrayList<String[]>()
								{{
									add(new String[] {"The engineer simply laughs before pulling out his wrench and aiming it at you.", "Texan Showdown"});
								}},
								"You prepare for the Texan\'s showdown.",
								"The engineer laughs before aiming the sentry, then firing."
						)
				);
				add
				(
						new BattleEvent
						(
								"Imposter",
								new ArrayList<String>()
								{{
									add("You board the ship the Engineer constructed.");
									add("While you haven't boarded a spaceship before, the controls are fairly intuitive.");
									add("As you start to fly in distant space, you get the feeling something is watching you.");
									add("There are little walking beans in the spaceship you hadn't noticed earlier.");
									add("The reddest among them goes into the electrical, so you decided to follow them in.");
									add("Soon enough, the lights turn off, so you go to fix the wiring.");
									add("You then hear a vent open.");
								}},
								new ArrayList<ArrayList<String>>()
								{{
									add(new ArrayList<String>()
									{{
										add("With that, your ship has become decontaminated.");
										add("You inspect the imposter's belongings to discover that he was sent to sabotage your mission.");
										add("Seems like they failed.");
										add("Now... one left.");
									}});
								}},
								new ArrayList<String[]>()
								{{
									add(new String[] {"You turn around to see the imposter pull out a knife.", "So, so Sussy"});
								}},
								"You narrowly dodge the first stab, but you suspect there will be more to come.",
								"You get stabbed in the back."
						)
				);
				add
				(
						new BattleEvent
						(
								"Intervention at Sawcon",
								new ArrayList<String>()
								{{
									add("It\'s time to end this.");
									add("With the imposter gone, you land at Sawcon.");
									add("\"Hello, can I help you?\" the manager asks.");
									add("You simply point to them.");
								}},
								new ArrayList<ArrayList<String>>()
								{{
									add(new ArrayList<String>()
									{{
										add("Now, you ARE the manager.");
										add("Against all odds, you proved that an unstoppable force beats an immovable wall.");
										add("No one will treat your name as a joke.");
										add("You will be known...");
										add("As the one who memes.");
										add("Those who wish to publish anything on the internet must pass it by you.");
									}});
								}},
								new ArrayList<String[]>()
								{{
									add(new String[] {"The manager sighs, then starts to levitate.", "Talking to the Manager"});
								}},
								"You wrap your hands, prepared for your last battle.",
								"Your battle was well fought, but no one can win against the power of the manager."
						)
				);
				/*
				 * 
				 * String title,
				 * ArrayList<String> preludeLines,
				 * ArrayList<ArrayList<String>> postludeLines,
				 * ArrayList<String[]> choices,
				 * String prompt
				 */
			}};
			
	
	public static JSONArray saveEvents()
	{
		JSONArray json = new JSONArray();
    	for (int i = 0; i < EVENTS.size(); i++)
    	{
    		json.put(EVENTS.get(i).toJson());
    		
    	}
		return json;
	}
}
