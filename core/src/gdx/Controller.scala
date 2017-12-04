package gdx


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{OrthographicCamera, Texture}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.{Image, Table}
import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener, Stage}
import com.badlogic.gdx.utils.viewport.{FitViewport, ScreenViewport}

class Controller(batch: SpriteBatch) {
  // Draws and handles logic for the on screen controller

  val BUTTON_SIZE = 200

  val viewport = new ScreenViewport()
  val stage = new Stage(viewport, batch)
  Gdx.input.setInputProcessor(stage)
  val table = new Table()
  table.setSize(Gdx.graphics.getWidth, Gdx.graphics.getWidth)
  table.setPosition(0, 0)


  // Draw the controller
  val leftBox = new Image(new Texture("flatDark23.png"))
  leftBox.setSize(BUTTON_SIZE, BUTTON_SIZE)
  val leftController = new ControllerListener
  leftBox.addListener(leftController)

  val rightBox = new Image(new Texture("flatDark23.png"))
  rightBox.setSize(BUTTON_SIZE, BUTTON_SIZE)
  val rightController = new ControllerListener
  rightBox.addListener(rightController)

  val upBox = new Image(new Texture("flatDark23.png"))
  upBox.setSize(BUTTON_SIZE, BUTTON_SIZE)
  val upController = new ControllerListener
  upBox.addListener(upController)

  val downBox = new Image(new Texture("flatDark23.png"))
  downBox.setSize(BUTTON_SIZE, BUTTON_SIZE)
  val downController = new ControllerListener
  downBox.addListener(downController)

  table.add()
  table.add(upBox).size(upBox.getWidth, upBox.getHeight)
  table.add()
  table.row().pad(5, 5, 5, 5)
  table.add(leftBox).size(leftBox.getWidth, leftBox.getHeight)
  table.add()
  table.add(rightBox).size(rightBox.getWidth, rightBox.getHeight)
  table.row()
  table.add()
  table.add(downBox).size(downBox.getWidth, downBox.getHeight)
  table.add()
  stage.addActor(table)


  // Translate touch events to controls

  def left(): Boolean = {
    check(leftController)
  }
  def right(): Boolean = {
    check(rightController)
  }
  def up(): Boolean = {
    check(upController)
  }
  def down(): Boolean = {
    check(downController)
  }

  def check(controller: ControllerListener): Boolean = {
    val doSend = controller.pressSent && !controller.pressRead
    if (doSend) controller.pressRead = true
    return doSend
  }

  def render(): Unit = {
    viewport.apply()
    stage.act()
    stage.draw()
  }

  def dispose(): Unit = {
    stage.dispose()
  }

}

class ControllerListener extends InputListener{
  var isPressed = false

  var pressSent = false
  var pressRead = false

  override def touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
    isPressed = true
    pressSent = true
    return true
  }
  override def touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Unit = {
    isPressed = false
    pressRead = false
    pressSent = false
  }
}
