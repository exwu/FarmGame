package game_logic.system

import game_logic.{GameMap, C, Entity}

object TimeSystem {

  def update(entities: Iterable[Entity]): Unit = {
    for (entity <- entities) {
      entity.getInt(C.AGE) match {
        case Some(age) => entity.add(C.AGE, age+1)
        case None => {}
      }
      entity.getInt(C.STAGE) match {
        case Some(_) => stage(entity)
        case None => {}
      }
    }
  }

  def stage(entity: Entity): Unit = {
    val age = entity.getInt(C.AGE).get
    val stage_length = entity.getInt(C.STAGE_LENGTH).get
    val stage = entity.getInt(C.STAGE).get
    val num_stages = entity.getInt(C.NUM_STAGES).get
    if (age % stage_length == 0) {
      entity.add(C.STAGE, stage + 1)
      if (stage + 1 > num_stages) {
        GameMap.removeFromMap(entity)
        entity.destroy()
      }
    }
  }

}
