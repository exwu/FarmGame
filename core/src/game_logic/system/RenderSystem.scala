package game_logic.system

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.{Sprite, SpriteBatch}
import game_logic.{C, Entity, GameMap}

/**
  * Created by Emily on 8/25/2016.
  */
object RenderSystem {

  def draw(batch : SpriteBatch, entities: Iterable[Entity]): Unit = {
    for (entity <- entities) {
      if (entity.getInt(C.VISIBLE).getOrElse(0) == 1) {
        val (x, y) = GameMap.getPOS(entity)
        // TODO: Load in texture once
        // TODO: One sprite per entity (id -> sprite)
        val sprite = new Sprite(new Texture(entity.getString(C.SPRITE_FILE).get))
        sprite.setPosition(x, y)
        sprite.setSize(1, entity.getFloat(C.SPRITE_ASPECT_RATIO).getOrElse(1))
        sprite.draw(batch)
      }
    }
  }
}
