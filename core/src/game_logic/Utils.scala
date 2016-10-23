package game_logic

/**
 * Created by exwu on 10/9/16.
 */
class Utils {

  /**
   * Verifies that all invariants are held
   * @param entity
   */
  def verify(entity: Entity): Boolean = {
    // if the sprite is visible, make sure it has a sprite file
    val vis = (entity.getInt(C.VISIBLE).getOrElse(0) > 0)  && entity.getString(C.SPRITE_FILE).isDefined
    val stages = entity.getInt(C.STAGE).isDefined && entity.getInt(C.NUM_STAGES).isDefined && entity.getInt(C.STAGE_LENGTH).isDefined
    return vis && stages
  }

}
