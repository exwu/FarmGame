package game_logic

import com.badlogic.gdx.graphics.g2d.SpriteBatch


/**
  * Created by Emily on 8/25/2016.
  */
object GameMap {
  var map: Map[(Int, Int, Int),List[Entity]] = Map()

  def addToMap(entity: Entity, x: Int, y: Int, z:Int): Unit = {
    val entList = entity :: map.getOrElse((x, y, z), List())
    map += ((x, y, z) -> entList)
  }

  def removeFromMap(entity: Entity): Unit = {
    val pos = getPOS(entity)
    // get old list, remove entity, update map with new list
    map += (pos -> map.getOrElse(pos, List())
      .filterNot(x => x.getInt(C.ID) == entity.getInt(C.ID)))
  }

  def getPOS(entity: Entity): (Int, Int, Int) = {
    val x = entity.getInt(C.POS_X).get
    val y = entity.getInt(C.POS_Y).get
    val z = entity.getInt(C.POS_Z).get
    assert(map.get((x, y, z)).get.contains(entity))
    (x, y, z)
  }

  def move(entity: Entity, newX: Int, newZ: Int): Unit = {
    removeFromMap(entity)
    addToMap(entity, newX, entity.getInt(C.POS_Y).get, newZ)
    entity.add(C.POS_X, newX)
    entity.add(C.POS_Z, newZ)
  }
}
