package game_logic.entity

import game_logic.{GameMap, GameInstance, C, Entity}

import scala.util.Random

/**
  * Created by Emily on 8/25/2016.
  */

object RobotFactory {
  def makeRobot(): Entity = {
    val id = Random.nextInt()
    val ent = new Entity(id)
    ent.add(C.NAME, "Robo-" + id)
    ent.add(C.COLOR, "blue")
    ent.add(C.HEALTH, 100)
    ent.add(C.SPEED, 10.0f)
    ent.add(C.SENTIENCE, 0.5f)
    ent.add(C.VISIBLE, 1)

    //ent.add(Components.AI_GOAL, -1)

    val x = 5
    val y = 5
    val z = 5
    ent.add(C.POS_X, x)
    ent.add(C.POS_Y, y)
    ent.add(C.POS_Z, z)

    GameMap.addToMap(ent, x, y, z)
    ent
  }

  def makeDeadRobot(): Entity = {
    val ent = makeRobot()
    ent.add(C.HEALTH, 0)
    ent
  }
}
