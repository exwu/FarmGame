package game_logic

import game_logic.system._
import game_logic.entity._

/**
  * Created by Emily on 8/21/2016.
  */


object GameInstance {
  var ids: Map[Int, Entity] = Map()


  val player = PlayerFactory.makePlayer()
  val crop = CropFactory.makeCrop()




  def entities(): Iterable[Entity] = {
    ids.values
  }

  def tick(): Unit = {
    val entities = ids.values
    AISystem.update(entities)
    TimeSystem.update(entities)
  }
}





