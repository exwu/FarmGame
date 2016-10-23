package game_logic.system

import game_logic.{GameMap, Entity, C, GameInstance}

import scala.util.Random


object AISystem {

  val AI_STATE_WANDERING = 0
  val AI_STATE_FOLLOWING = 1
  val AI_STATE_AVOIDING = 2


  def update(entities: Iterable[Entity]): Unit = {
    for (entity <- entities) {
      entity.getInt(C.AI_STATE) match {
        case Some(AI_STATE_WANDERING) => wander(entity)
        case Some(AI_STATE_FOLLOWING) => follow(entity)
        case Some(AI_STATE_AVOIDING) => avoid(entity)
        case None => {}
      }
    }
  }


  def wander(entity: Entity): Unit = {
    val newX = entity.getInt(C.POS_X).get + (if (Random.nextBoolean()) 1 else -1)
    val newY = entity.getInt(C.POS_Y).get + (if (Random.nextBoolean()) 1 else -1)
    GameMap.move(entity, newX, newY)
  }

  def follow(entity: Entity): Unit = {
    val target = GameInstance.ids(entity.getInt(C.AI_TARGET).get)
    val (tx, ty) = GameMap.getPOS(target)
    val (x, y) = GameMap.getPOS(entity)
    val dx = if (tx - x > 0) 1 else if (tx == x) 0 else -1
    val dy = if (ty - y > 0) 1 else if (ty == y) 0 else -1
    GameMap.move(entity, x + dx, y + dy)
  }

  def avoid(entity: Entity): Unit = {
    val target = GameInstance.ids(entity.getInt(C.AI_TARGET).get)
    val (tx, ty) = GameMap.getPOS(target)
    val (x, y) = GameMap.getPOS(entity)
    val dx = if (tx - x > 0) -1 else 1
    val dy = if (ty - y > 0) -1 else 1
    GameMap.move(entity, x + dx, y + dy)

  }
}
