package game_logic.system

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import game_logic.{Entity, GameMap, C}

/**
  * Created by Emily on 8/25/2016.
  */
object RenderSystem {

  val robot_sprite = new Texture(Gdx.files.internal("robot_sprite.png"))
  val grid_size = 64

  def draw(batch : SpriteBatch, entities: Iterable[Entity]): Unit = {
    for (entity <- entities) {
      if (entity.getInt(C.VISIBLE).getOrElse(0) == 1) {
        val (x, y, z) = GameMap.getPOS(entity)
        batch.draw(robot_sprite, x * grid_size, z * grid_size)
      }
    }
  }
}
