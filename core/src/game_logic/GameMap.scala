package game_logic

import com.badlogic.gdx.graphics.g2d.SpriteBatch


/**
  * Created by Emily on 8/25/2016.
  */
object GameMap {
  var map: Map[(Int, Int),List[Entity]] = Map()

  def addToMap(entity: Entity): Unit = {
    val x = entity.getInt(C.POS_X).get
    val y = entity.getInt(C.POS_Y).get
    val entList = entity :: map.getOrElse((x, y), List())
    map += ((x, y) -> entList)
  }

  def removeFromMap(entity: Entity): Unit = {
    val pos = getPOS(entity)
    // get old list, remove entity, update map with new list
    map += (pos -> map.getOrElse(pos, List())
      .filterNot(x => x.getInt(C.ID) == entity.getInt(C.ID)))
  }

  def getPOS(entity: Entity): (Int, Int) = {
    val x = entity.getInt(C.POS_X).get
    val y = entity.getInt(C.POS_Y).get
    assert(map.get((x, y)).get.contains(entity))
    (x, y)
  }

  def move(entity: Entity, newX: Int, newY: Int): Unit = {
    removeFromMap(entity)
    entity.add(C.POS_X, newX)
    entity.add(C.POS_Y, newY)
    addToMap(entity)
  }
}
