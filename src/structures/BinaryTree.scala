package structures

object BinaryTree {

  sealed trait BinaryTree {
    def prev: Option[BinaryTree]
    def zero: Option[BinaryTree]
    def one: Option[BinaryTree]
    def toPrint(space: String): String = if (zero.nonEmpty && one.nonEmpty) {
        space + "Zero\n" + zero.get.toPrint(space + "  ") +
        space + "One\n" + one.get.toPrint(space + "  ")
      }
      else ""
  }
  case class Branch(z: BinaryTree, o: BinaryTree) extends BinaryTree {
    override def prev: Option[BinaryTree] = None
    override def zero: Option[BinaryTree] = Some(z)
    override def one: Option[BinaryTree] = Some(o)
  }
  case class Leaf() extends BinaryTree {
    override def prev: Option[BinaryTree] = None
    override def zero: Option[BinaryTree] = None
    override def one: Option[BinaryTree] = None
  }

  /** Makes a balanced tree
   *
   * @param height
   * @return
   */
  def apply(height: Int): BinaryTree =
    if (height == 0) Leaf() else Branch(apply(height - 1), apply(height - 1))












}
