package caffeine.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import caffeine.action.Action;
import caffeine.entity.brain.Brain;
import caffeine.view.Animation;
import caffeine.world.Direction;
import caffeine.world.Loc;
import caffeine.world.Map;
import caffeine.world.Tile;

/**
 * base class representing objects in the world. Entities have a location
 * 
 * @author srwaggon
 * 
 */
public class Entity {
  /* static fields */
  protected static int numEntities = 0;

  /* primitive fields */
  protected int id = Entity.numEntities++;
  protected boolean isAlive = true;
  protected int size = 32;
  protected int speed = 2;

  /* object fields */
  protected LinkedList<Action> actionPlans = new LinkedList<Action>();
  protected Animation anim;
  protected Brain brain = null;
  protected Direction dir = Direction.SOUTH;
  protected Loc loc;
  protected String name = "Entity-" + id;

  public Entity() {
    this(new Loc(0, 48, 48));
  }

  /* CONSTRUCTORS */
  public Entity(Loc loc) {
    this.loc = loc;
    int[] walkSprites = { 3, 4 };

    Animation walkAnim = new Animation(walkSprites, 200, true);
    anim = walkAnim;
  }

  /* ACCESSORS */
  public boolean isAlive() {
    return isAlive;
  }

  public Brain getBrain() {
    return brain;
  }

  /**
   * Returns the number of currently existing entities. This number is
   * incremented each time an entity is created and reduced each time an entity
   * is destroyed.
   * 
   * @return The number of entities.
   */
  public static int getPopulation() {
    return Entity.numEntities;
  }

  /**
   * Returns a unique numerical digit representing the entity's identification.
   * 
   * @return unique id as digit
   */
  public int getID() {
    return id;
  }

  /**
   * Returns a Rectangle object of which the entity is considered to occupy in
   * space. Objects which fall within the rectangle or overlap with the
   * rectangle are considered to be inside of the entity. This rectangle serves
   * for basic hit detection purposes.
   * 
   * @return rectangle of which this entity occupies
   */
  public Rectangle getHitbox() {
    return new Rectangle((int)loc.x - size / 2, (int)loc.y - size / 2, size, size);
  }

  /**
   * Returns the current Location of the entity.
   * 
   * @return location of entity
   */
  public Loc getLoc() {
    return loc;
  }

  /**
   * 
   * @return speed that this entity can move
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * Takes a tile and determines if the tile is a validly accessible location
   * according to this entity for movement purposes.
   * 
   * @param tile
   * @return boolean representing whether or not this tile is a validly
   *         accessible location.
   */
  public boolean isValidLoc(Tile tile) {
    return tile.canPass();
  }

  /* MUTATORS */
  public void act() {
    while (!actionPlans.isEmpty()) {
      actionPlans.poll().performBy(this);
    }
  }

  public void die() {
    isAlive = false;
  }

  public void setBrain(Brain b) {
    brain = b;
  }

  public boolean collideWith(Entity e) {
    // e.die();
    System.out.println(this + " colliding with " + e);
    return false;
  }

  public void setFacing(Direction dir) {
    this.dir = dir;
  }

  /**
   * Takes a graphics object and draws the representation of this entity.
   * 
   * @param g
   *          Graphics used to draw this entity
   */
  public final void render(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    anim.render(g2, (int)loc.x, (int)loc.y);
    g2.draw(getHitbox());
  }

  /**
   * Enact the regularly scheduled routines this entity must perform.
   */
  public void tick(Map map) {
    if (isAlive) {
      if (brain != null) {
        actionPlans.addAll(brain.next());
      }
      act();
    }
  }

  @Override
  public String toString() {
    return name + " @ " + loc.toString();
  }

  @Override
  public void finalize() {
    try {
      Entity.numEntities--;
      super.finalize();
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

}