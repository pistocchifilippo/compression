package structures

import structures.BinaryTree.{BinaryTree, Branch, Leaf, Root}


object BinaryTree {

  sealed trait BinaryTree {
    def prev: Option[BinaryTree]
    def zero: Option[BinaryTree]
    def one: Option[BinaryTree]

    def toPrint(space: String): String = if (zero.nonEmpty && one.nonEmpty) {
        space + "Zero\n" + zero.get.toPrint(space + "  ") +
        space + "One\n" + one.get.toPrint(space + "  ")
    } else ""
  }
  case class Root(zero: Option[BinaryTree], one: Option[BinaryTree]) extends BinaryTree {
    override def prev: Option[BinaryTree] = None
  }
  case class Branch(prev: Option[BinaryTree], zero: Option[BinaryTree], one: Option[BinaryTree]) extends BinaryTree // modificabile solo questo
  case class Leaf(prev: Option[BinaryTree]) extends BinaryTree {
    override def zero: Option[BinaryTree] = None
    override def one: Option[BinaryTree] = None
  }

  def apply(height: Int, prev: Option[BinaryTree]): BinaryTree = if (height == 0) Leaf(prev) else Branch(apply(height - 1, questo), apply(height - 1, questo))

  def addLayer(tree: BinaryTree): BinaryTree = ???


}

object ModifiableBinaryTree {

  trait ModifiableBinaryTree extends BinaryTree {
    private var _prev: Option[ModifiableBinaryTree] = None
    private var _zero: Option[ModifiableBinaryTree] = None
    private var _one: Option[ModifiableBinaryTree] = None

    override def prev: Option[ModifiableBinaryTree] = _prev

    override def zero: Option[ModifiableBinaryTree] = _zero

    override def one: Option[ModifiableBinaryTree] = _one

    def setPrev(prev: ModifiableBinaryTree): Unit = {_prev = Some(prev)}

    def setZero(zero: ModifiableBinaryTree): Unit = {_zero = Some(zero)}

    def setOne(one: ModifiableBinaryTree): Unit = {_one = Some(one)}
  }

  /** Makes a modifiable balanced tree
   *
   * @param height of the tree
   * @return the modifiable tree
   */
  def apply(height: Int): ModifiableBinaryTree =
    if (height == 0) MLeaf() else MBranch(Some(apply(height - 1)), Some(apply(height - 1)))
  def linkAll(binaryTree: ModifiableBinaryTree): Unit =
    map(binaryTree, link)

  case class MRoot() extends ModifiableBinaryTree
  case class MBranch(override val zero: Option[ModifiableBinaryTree], override val one: Option[ModifiableBinaryTree]) extends ModifiableBinaryTree {
    def this() = {
      this(None, None)
    }
  }
  case class MLeaf() extends ModifiableBinaryTree

  def map(binaryTree: ModifiableBinaryTree, f: ModifiableBinaryTree => ()): Unit = binaryTree match {
    case root: MRoot =>
      if (root.zero.nonEmpty && root.one.nonEmpty) {
        f(root)
        map(root.zero.get, f)
        map(root.one.get, f)
      }

    case branch: MBranch =>
      if (branch.zero.nonEmpty && branch.one.nonEmpty) {
        f(branch)
        map(branch.zero.get, f)
        map(branch.one.get, f)
      }

    case leaf: MLeaf => f(leaf)
  }

  def link: ModifiableBinaryTree => Unit = {
    case branch: MBranch =>
      branch.zero.get.setPrev(branch)
      branch.one.get.setPrev(branch)
    case _ =>
  }

  def purify: ModifiableBinaryTree => BinaryTree = {
    case root: MRoot => Root(root.zero, root.one)
    case branch: MBranch => Branch(branch.prev, branch.zero, branch.one)
    case leaf: MLeaf => Leaf(leaf.prev)
  }

}




