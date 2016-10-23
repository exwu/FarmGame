package gdx

import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.input.GestureDetector.{GestureAdapter, GestureListener}
import com.badlogic.gdx.math.{Vector3, Rectangle}
import com.badlogic.gdx.{InputAdapter, InputProcessor, Gdx, Screen}
import com.badlogic.gdx.graphics.{Texture, GL20, OrthographicCamera}
import game_logic.{GameMap, C, Terrain, GameInstance}
import game_logic.system.RenderSystem

/**
  * Created by emily on 4/29/16.
  */
class WorldScreen(game: FarmGame) extends Screen {
  val camera = new OrthographicCamera()
  camera.setToOrtho(false)

  val controller = new Controller(game.batch)


  /*
  val inputProcessor = new MapGestures
  Gdx.input.setInputProcessor(new GestureDetector(inputProcessor))
  */

  //val gameInstance = GameInstance

  override def hide(): Unit = {}


  override def resize(width: Int, height: Int): Unit = {
    controller.resize(width, height)
  }

  override def dispose(): Unit = {}

  override def pause(): Unit = {}


  var lastUpdated = System.currentTimeMillis()
  def update(): Unit = {
    handleInput()
    val time = System.currentTimeMillis()
    if (time - lastUpdated > 500) {
      GameInstance.tick()
      lastUpdated = time
    }

  }

  def handleInput(): Unit = {
    val playerX = GameInstance.player.getInt(C.POS_X).get
    val playerY = GameInstance.player.getInt(C.POS_Y).get
    if (controller.down()) {
      GameMap.move(GameInstance.player, playerX, playerY - 1)
    } else if (controller.up()) {
      GameMap.move(GameInstance.player, playerX, playerY + 1)
    } else if (controller.left()) {
      GameMap.move(GameInstance.player, playerX - 1, playerY)
    } else if (controller.right()) {
      GameMap.move(GameInstance.player, playerX + 1, playerY)
    }
  }



  override def render(delta: Float): Unit = {
    update()

    //Gdx.gl.glClearColor(0.1f, .8f, .3f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    camera.update()
    game.batch.setProjectionMatrix(camera.combined)
    game.batch.begin()
    Terrain.render(game.batch)
    RenderSystem.draw(game.batch, GameInstance.entities())
    game.batch.end()
    controller.render()
  }


  override def show(): Unit = {}

  override def resume(): Unit = {}

  /*
  class MapGestures extends GestureAdapter {

    var dragLastX = -1
    var dragLastY = -1

    var initialZoom = 1f

    override def touchDown(x : Float, y : Float, pointer : Int, button : Int) : Boolean = {
      initialZoom = camera.zoom
      false
    }

    override def pan(x : Float, y : Float, deltaX : Float, deltaY : Float) : Boolean = {
      camera.translate(-deltaX * camera.zoom, deltaY * camera.zoom)
      true
    }

    override def zoom(originalDistance : Float, newDistance : Float): Boolean = {
      val ratio = originalDistance / newDistance
      camera.zoom = initialZoom * ratio
      true
    }
  }
*/

}

