package game_logic.entity

import game_logic._
import game_logic.system.AISystem

import scala.util.Random

/**
  * Created by Emily on 8/25/2016.
  */
object ChickenFactory {
  def makeChicken(x:Int = 0, y:Int = 0) : Entity = {
    val id = Random.nextInt()
    val ent = new Entity(id)
    ent.add(C.VISIBLE, 1)
    ent.add(C.NAME, "Chicken")

    ent.add(C.POS_X, x)
    ent.add(C.POS_Y, y)

    ent.add(C.VISIBLE, 1)
    ent.add(C.SPRITE_FILE, "chicken.png")

    GameMap.addToMap(ent)
    ent
  }
}

