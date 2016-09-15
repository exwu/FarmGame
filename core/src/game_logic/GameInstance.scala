package game_logic

import game_logic.system._
import game_logic.entity._

/**
  * Created by Emily on 8/21/2016.
  */


object GameInstance {
  var ids: Map[Int, Entity] = Map()

  val cow = CowFactory.makeCow()
  val robot = RobotFactory.makeRobot()
  robot.add(C.AI_STATE, AISystem.AI_STATE_FOLLOWING)
  robot.add(C.AI_TARGET, cow.id)
  cow.add(C.AI_STATE, AISystem.AI_STATE_AVOIDING)
  cow.add(C.AI_TARGET, robot.id)





  def entities(): Iterable[Entity] = {
    return ids.values
  }

  def tick(): Unit = {
    val entities = ids.values
    AISystem.update(entities)
  }
}





