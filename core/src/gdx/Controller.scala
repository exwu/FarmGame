package gdx


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{Texture, OrthographicCamera}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.{Image, Table}
import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener, Stage}
import com.badlogic.gdx.utils.viewport.FitViewport

class Controller(batch: SpriteBatch) {
  val camera = new OrthographicCamera()
  val viewport = new FitViewport(800, 400, camera)
  val stage = new Stage(viewport, batch)
  Gdx.input.setInputProcessor(stage)
  val table = new Table()
  table.left().bottom()

  val leftBox = new Image(new Texture("flatDark23.png"))
  leftBox.setSize(50, 50)
  val leftController = new ControllerListener
  leftBox.addListener(leftController)

  val rightBox = new Image(new Texture("flatDark23.png"))
  rightBox.setSize(50, 50)
  val rightController = new ControllerListener
  rightBox.addListener(rightController)

  val upBox = new Image(new Texture("flatDark23.png"))
  upBox.setSize(50, 50)
  val upController = new ControllerListener
  upBox.addListener(upController)

  val downBox = new Image(new Texture("flatDark23.png"))
  downBox.setSize(50, 50)
  val downController = new ControllerListener
  downBox.addListener(downController)

  table.add()
  table.add(upBox).size(upBox.getWidth, upBox.getHeight)
  table.add()
  table.row().pad(5, 5, 5, 5)
  table.add(leftBox).size(leftBox.getWidth, leftBox.getHeight)
  table.add()
  table.add(rightBox).size(rightBox.getWidth, rightBox.getHeight)
  table.row().padBottom(5)
  table.add()
  table.add(downBox).size(downBox.getWidth, downBox.getHeight)
  table.add()
  stage.addActor(table);


  def left(): Boolean = {
    leftController.isPressed
  }
  def right(): Boolean = {
    rightController.isPressed
  }
  def up(): Boolean = {
    upController.isPressed
  }
  def down(): Boolean = {
    downController.isPressed
  }

  def resize(width: Int, height: Int): Unit = {
    viewport.update(width, height)
  }

  def render(): Unit = {
    stage.draw()
  }

}

class ControllerListener extends InputListener{
  var isPressed = false


  override def touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
    isPressed = true
    return true
  }
  override def touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Unit = {
    isPressed = false
  }
}
