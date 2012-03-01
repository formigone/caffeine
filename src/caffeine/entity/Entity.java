package caffeine.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Scanner;

import caffeine.view.Animation;
import caffeine.world.Location;
import caffeine.world.Tile;

/**
 * base class representing objects in the world. Entities have a location
 * 
 * @author srwaggon
 * 
 */
public class Entity {
  protected static int numCharacters = 0;
  protected int id = 0;
  protected int size = 24;
  protected Location loc;
  private Rectangle frame;
  protected int spriteID;
  protected Animation walkAnim;
  protected String name;
  
  public Entity() {
    this(new Location());
  }
  
  public Entity(Location l) {
    id = Entity.numCharacters++;
    loc = l.copy();
    spriteID = 3;
    
    int[] walkSprites = { 3, 4 };
    walkAnim = new Animation(walkSprites, 200, true);
    name = "" + id;
    System.out.println("Spawning Entity " + name + " at " + loc);
  }
  
  public int getID() {
    return id;
  }
  
  public Rectangle hitbox() {
    return frame;
  }
  
  public Location loc() {
    return loc;
  }
  
  public void loc(Location loc) {
    this.loc = loc;
  }
  
  public int radius() {
    return size;
  }
  
  public int spriteID() {
    return spriteID;
  }
  
  public void tick() {
  }
  
  public Tile tile() {
    return loc.tile();
  }
  
  public static Entity newEntity(String data) {
    Scanner scans = new Scanner(data);
    
    int id = scans.nextInt();
    int mapID = scans.nextInt();
    int x = scans.nextInt();
    int y = scans.nextInt();
    Location l = new Location(mapID, x, y);
    Entity entity = new Entity(l);
    entity.id = id;
    return entity;
  }
  
  public ArrayList<Location> vertices() {
    ArrayList<Location> vertices = new ArrayList<Location>();
    int mapID = loc.mapID();
    int x = loc.x();
    int y = loc.y();
    int ts = loc.map().tileSize();
    int padding = ts - size;
    
    // topleft, topright, bottomleft, bottomright
    vertices.add(new Location(mapID, x + padding, y + padding));
    vertices.add(new Location(mapID, x + padding, y + ts - padding - 1));
    vertices.add(new Location(mapID, x + ts - padding - 1, y + padding));
    vertices
        .add(new Location(mapID, x + ts - padding - 1, y + ts - padding - 1));
    return vertices;
  }
  
  public void render(Graphics2D g2) {
    walkAnim.render(g2, loc.x(), loc.y());
  }
  
  @Override
  public String toString() {
    return "entity" + " " + id + " " + loc.toString();
  }
}