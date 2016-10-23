package game_logic.entity

import game_logic.system.TimeSystem
import game_logic.{GameMap, C, Entity}

import scala.util.Random

object CropFactory {
  def makeCrop(): Entity = {
    val id = Random.nextInt()
    val ent = new Entity(id)
    ent.add(C.NAME, "Generic Crop")

    ent.add(C.VISIBLE, 1)
    ent.add(C.SPRITE_FILE, "strawberry.png")

    val x = 9
    val y = 10

    ent.add(C.POS_X, x)
    ent.add(C.POS_Y, y)
    GameMap.addToMap(ent)

    ent.add(C.AGE, 0)
    ent.add(C.NUM_STAGES, 4)
    ent.add(C.STAGE_LENGTH, 10)
    ent.add(C.STAGE, 0)

    ent
  }

}
