package game_logic.entity

import game_logic.{GameMap, C, Entity}

import scala.util.Random

object PlayerFactory {
  def makePlayer(): Entity = {
    val id = Random.nextInt()
    val ent = new Entity(id)
    ent.add(C.NAME, "Player")

    val x = 10
    val y = 10
    ent.add(C.POS_X, x)
    ent.add(C.POS_Y, y)


    ent.add(C.VISIBLE, 1)
    ent.add(C.SPRITE_FILE, "robot_sprite.png")
    GameMap.addToMap(ent)
    ent
  }

}
