package structures

import structures.BinaryTree.ModifiableBinaryTree.{MBranch, MLeaf}

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
  case class Branch(prev: Option[BinaryTree], zero: Option[BinaryTree], one: Option[BinaryTree]) extends BinaryTree
  case class Leaf(prev: Option[BinaryTree]) extends BinaryTree {
    override def zero: Option[BinaryTree] = None
    override def one: Option[BinaryTree] = None
  }

  def addLayer(tree: BinaryTree): BinaryTree = ???



  object ModifiableBinaryTree {

    trait ModifiableBinaryTree extends BinaryTree {
      private var _prev: Option[BinaryTree] = None
      private var _zero: Option[BinaryTree] = None
      private var _one: Option[BinaryTree] = None

      override def prev: Option[BinaryTree] = _prev

      override def zero: Option[BinaryTree] = _zero

      override def one: Option[BinaryTree] = _one

      def setPrev(prev: BinaryTree): Unit = {_prev = Some(prev)}

      def setZero(zero: BinaryTree): Unit = {_zero = Some(zero)}

      def setOne(one: BinaryTree): Unit = {_one = Some(one)}

      def toBinaryTree: BinaryTree
    }

    /** Makes a modifiable balanced tree
     *
     * @param height of the tree
     * @return the modifiable tree
     */
    def apply(height: Int): BinaryTree =
      if (height == 0) MLeaf() else MBranch(Some(apply(height - 1)), Some(apply(height - 1)))

    case class MRoot() extends ModifiableBinaryTree {
      override def toBinaryTree: BinaryTree = Root(this.zero, this.one)
    }
    case class MBranch(override val zero: Option[BinaryTree], override val one: Option[BinaryTree]) extends ModifiableBinaryTree {
      def this() = {
        this(None, None)
      }
      override def toBinaryTree: BinaryTree = Branch(this.prev, this.zero, this.one)
    }
    case class MLeaf() extends ModifiableBinaryTree {
      override def toBinaryTree: BinaryTree = Leaf(this.prev)
    }

  }

}




