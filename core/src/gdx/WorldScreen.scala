package gdx

import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}
import com.badlogic.gdx.utils.Scaling
import com.badlogic.gdx.utils.viewport.{ExtendViewport, FillViewport, ScalingViewport, Viewport}
import game_logic.{C, GameInstance, GameMap, Terrain}
import game_logic.system.RenderSystem

/**
  * Created by emily on 4/29/16.
  */
class WorldScreen(game: FarmGame) extends Screen {
  val camera = new OrthographicCamera(20, 20)
  camera.position.set(10, 10, 0)

  val viewport = new FillViewport(20, 20, camera)

  val controller = new Controller(game.batch)



  //val gameInstance = GameInstance

  override def hide(): Unit = {}


  override def resize(width: Int, height: Int): Unit = {
    viewport.update(width, height)
    controller.viewport.update(width, height, true)
  }

  override def dispose(): Unit = {
    controller.dispose()
  }

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
    var dX = 0
    var dY = 0
    if (controller.down()) {
      dY = -1
    } else if (controller.up()) {
      dY = 1
    } else if (controller.left()) {
      dX = -1
    } else if (controller.right()) {
      dX = 1
    }
    GameMap.move(GameInstance.player, playerX + dX, playerY + dY)
    camera.translate(dX, dY)
  }



  override def render(delta: Float): Unit = {
    viewport.apply()
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

