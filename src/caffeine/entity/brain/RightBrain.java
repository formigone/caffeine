package caffeine.entity.brain;

import java.util.List;

import caffeine.action.Action;
import caffeine.action.Face;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Map;
import caffeine.world.World;

public class RightBrain extends Brain {
  Direction dir = Direction.EAST;

  public RightBrain(Entity self) {
    super(self);
  }

  public static Entity embody(World world) {
    return new RightBrain(new Entity(world)).getEntity();
  }

  @Override
  public List<Action> getActionPlan() {

    actionPlan.clear();
    Map map = self.getWorld().getMap(self.getLoc().mapID);
    Move move = new Move(dir);

    actionPlan.add(new Face(dir));
    actionPlan.add(move);

    return actionPlan;
  }
}
