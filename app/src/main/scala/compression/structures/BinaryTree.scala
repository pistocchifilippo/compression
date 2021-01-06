package compression.structures

object BinaryTree {

  sealed trait BitTree {
    def prev: Option[BitTree]
    def zero: Option[BitTree]
    def one: Option[BitTree]

    def setPrev(prev: BitTree): Unit
    def setZero(zero: BitTree): Unit
    def setOne(one: BitTree): Unit

//    def leaves: Map[Leaf, Int]

    def toPrint(space: String): String = if (zero.nonEmpty && one.nonEmpty) {
        space + "Zero\n" + zero.get.toPrint(space + "  ") +
        space + "One\n" + one.get.toPrint(space + "  ")
    } else ""
  }

  class BinaryTreeImpl extends BitTree {
    private var _prev: Option[BitTree] = None
    private var _zero: Option[BitTree] = None
    private var _one: Option[BitTree] = None

    override def prev: Option[BitTree] = _prev
    override def zero: Option[BitTree] = _zero
    override def one: Option[BitTree] = _one

    def setPrev(prev: BitTree): Unit = {_prev = Some(prev)}
    def setZero(zero: BitTree): Unit = {_zero = Some(zero)}
    def setOne(one: BitTree): Unit = {_one = Some(one)}
  }

  case class Branch(override val prev: Option[BitTree]) extends BinaryTreeImpl
  case class Leaf(override val prev: Option[BitTree]) extends BinaryTreeImpl

  def apply(height: Int, root: BitTree): BitTree =
    if (height <= 0) {
      Leaf(Some(root))
    } else {
      val branch = Branch(Some(root))
      val child1 = apply(height - 1, branch) // crate 2 in order to have different objects in memory
      val child2 = apply(height - 1, branch) // maybe in future ca try to create just one
      branch.setZero(child1)
      branch.setOne(child2)
      branch
    }

}




