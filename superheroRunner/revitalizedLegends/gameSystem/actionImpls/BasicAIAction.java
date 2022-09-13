package revitalizedLegends.gameSystem.actionImpls;

import java.util.List;

import revitalizedLegends.gameSystem.Action;
import revitalizedLegends.gameSystem.Entity;
import revitalizedLegends.gameSystem.InputSystem;
import revitalizedLegends.gameSystem.entityImpls.BasicAIEntity;

public class BasicAIAction extends Action{

	public BasicAIAction(BasicAIEntity caster, List<Entity> allHeros, BasicAIEntity.TargettingSystem input) {
		super(input.getSingleTarget(), caster, input.getAbilityName(), allHeros, input);
        
		// TODO Auto-generated constructor stub
	}

}
