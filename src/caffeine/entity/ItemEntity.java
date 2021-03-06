package caffeine.entity;

import caffeine.items.Item;


public class ItemEntity extends Entity{
  long spawnTime = System.currentTimeMillis();
  Item item;

  public ItemEntity(Item item) {
    this.item = item;
    sprite = item.getSprite();
  }


  public boolean touchedBy(Entity entity){
    long now = System.currentTimeMillis();
    if (now - spawnTime > 100){
      entity.takeItem(this);
    }
    return true;
  }

  public void take(Entity entity){
    item.onTake(entity);
    item.playPickupSound();
    remove();
  }
}
