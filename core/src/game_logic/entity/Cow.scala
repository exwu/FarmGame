package game_logic.entity

import game_logic._

import scala.util.Random

/**
  * Created by Emily on 8/25/2016.
  */
object CowFactory {
  def makeCow() : Entity = {
    val id = Random.nextInt()
    val ent = new Entity(id)
    ent.add(C.VISIBLE, 1)
    ent.add(C.NAME, "Cow")

    val x = 15
    val y = 15
    val z = 15
    ent.add(C.POS_X, x)
    ent.add(C.POS_Y, y)
    ent.add(C.POS_Z, z)

    GameMap.addToMap(ent, x, y, z)
    ent
  }
}

