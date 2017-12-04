package game_logic

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.{Sprite, SpriteBatch}
import game_logic.system.RenderSystem

/**
  * Created by Emily on 8/27/2016.
  */
object Terrain {
  val width = 20
  val height = 20
  var terrainMap: Array[Array[TerrainCell]] = Array.ofDim(width, height)

  val desertTexture = new Texture(Gdx.files.internal("tile.png"))


  for (i <- 0 until width) {
    for (j <- 0 until height) {
      terrainMap(i)(j) = new TerrainCell
    }
  }


  def render(batch: SpriteBatch): Unit = {
    for (i <- 0 until width) {
      for (j <- 0 until height) {
        //batch.draw(getSprite(terrainMap(i)(j)), i * RenderSystem.grid_size, j * RenderSystem.grid_size)
        val sprite = new Sprite(desertTexture)
        sprite.setSize(1, 1)
        sprite.setPosition(i, j)
        sprite.draw(batch)
      }
    }
  }

  /*
  def getSprite(terrainCell: TerrainCell): Texture = {
    terrainCell.biome match {
      case BiomeTypes.Desert => desertSprite
      case BiomeTypes.Grass => desertSprite
      case BiomeTypes.Forest => desertSprite
    }
  }
  */
}

class TerrainCell {
  var elevation: Int = 0
  var biome: BiomeTypes.Value = BiomeTypes.Desert
}

object BiomeTypes extends Enumeration {
  val Desert, Grass, Forest = Value
}
