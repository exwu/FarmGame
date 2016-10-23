package game_logic.system

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import game_logic.{Entity, GameMap, C}

/**
  * Created by Emily on 8/25/2016.
  */
object RenderSystem {

  val grid_size = 64

  def draw(batch : SpriteBatch, entities: Iterable[Entity]): Unit = {
    for (entity <- entities) {
      if (entity.getInt(C.VISIBLE).getOrElse(0) == 1) {
        val (x, y) = GameMap.getPOS(entity)
        val sprite = new Texture(entity.getString(C.SPRITE_FILE).get)
        if (entity.getInt(C.STAGE).isDefined) {
          val stage = entity.getInt(C.STAGE).get
          batch.draw(sprite, x * grid_size, y * grid_size, getSpriteBounds(stage), 0, 64, 64)
        } else {
          batch.draw(sprite, x * grid_size, y * grid_size)
        }
      }
    }
  }


  def getSpriteBounds(index: Int): Int = {
    index * grid_size
  }
}
