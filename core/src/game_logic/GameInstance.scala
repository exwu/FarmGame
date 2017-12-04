package game_logic

import game_logic.system._
import game_logic.entity._

import scala.util.Random

/**
  * Created by Emily on 8/21/2016.
  */


object GameInstance {
  var ids: Map[Int, Entity] = Map()


  val player = PlayerFactory.makePlayer()
  //val crop = CropFactory.makeCrop()
  for (_ <- 1 to 10) {
    val chicken = ChickenFactory.makeChicken(x=Random.nextInt(20), y=Random.nextInt(20))
    chicken.add(C.AI_STATE, AISystem.AI_STATE_FOLLOWING)
    chicken.add(C.AI_TARGET, player.id)
  }



  def entities(): Iterable[Entity] = {
    ids.values
  }

  def tick(): Unit = {
    val entities = ids.values
    AISystem.update(entities)
    TimeSystem.update(entities)
  }
}





