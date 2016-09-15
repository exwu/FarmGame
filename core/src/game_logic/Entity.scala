package game_logic

/**
  * Created by Emily on 8/20/2016.
  */
class Entity (val id: Int) {

  var stringComponents: Map[String, String] = Map()
  var intComponents: Map[String, Int] = Map()
  var floatComponents: Map[String, Float] = Map()

  intComponents = intComponents + (C.ID -> id)

  create()

  def create(): Unit = {
    GameInstance.ids += id -> this
  }

  def destroy(): Unit = {
    GameInstance.ids -= id
  }

  def apply(component: String): Any = {
    if (stringComponents contains component) return stringComponents(component)
    if (floatComponents contains component) return floatComponents(component)
    if (intComponents contains component) return intComponents(component)
    return null
  }

  def getString(component: String): Option[String] = {
    stringComponents get component
  }

  def getFloat(component: String): Option[Float] = {
    floatComponents get component
  }

  def getInt(component: String): Option[Int] = {
    intComponents get component
  }

  def add(name: String, component: String): Unit = {
    stringComponents = stringComponents + (name -> component)
  }

  def add(name: String, component: Float): Unit = {
    floatComponents = floatComponents + (name -> component)
  }

  def add(name: String, component: Int): Unit = {
    intComponents = intComponents + (name -> component)
  }

  def remove(name: String): Unit = {
    if (stringComponents contains name) stringComponents = stringComponents - name
    if (intComponents contains name) intComponents = intComponents - name
    if (floatComponents contains name) floatComponents = floatComponents - name
  }

  override def toString(): String = {
    "<<<" + id + ">>>" + "\n" + stringComponents.toString + "\n" + intComponents.toString + "\n" + floatComponents.toString()
  }

  override def equals(other: Any): Boolean = {
    other.isInstanceOf[Entity] && other.asInstanceOf[Entity].id == this.id
  }

}
