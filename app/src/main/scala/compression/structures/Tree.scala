package compression.structures

object Tree {

  trait Tree {
    def zero: Option[Tree]
    def one: Option[Tree]
    def father: Option[Tree]

    def setZero(tree: Tree): Unit
    def setOne(tree: Tree): Unit

    def toPrint(space: String): String = if (zero.nonEmpty && one.nonEmpty) {
      space + "Zero\n" + zero.get.toPrint(space + "  ") +
        space + "One\n" + one.get.toPrint(space + "  ")
    } else ""
  }

  class NodeImplementation(var zero: Option[Tree], var one: Option[Tree],val father: Option[Tree]) extends Tree {
    override def setZero(tree: Tree): Unit = {zero = Some(tree)}
    override def setOne(tree: Tree): Unit = {one = Some(tree)}
  }
  case class EmptyNode() extends NodeImplementation(None, None, None)

  def apply(height: Int): Tree = {
    val root = EmptyNode()
    (0 until height).foreach(_ => addLayer(leaves(root)))
    root
  }

  def leaves(node: Tree): Array[Tree] = (node.zero, node.one) match {
    case (Some(zero), Some(one)) => leaves(zero) concat leaves(one)
    case (None, None) => Array(node)
  }

  // Side effects
  def addLayer(leaves: Array[Tree]): Unit =
    leaves.foreach(l => {
      val zero = new NodeImplementation(None, None, Some(l))
      val one = new NodeImplementation(None, None, Some(l))
      l.setZero(zero)
      l.setOne(one)
      l
    })



}

object POC extends App {
  import Tree._

  var root: Tree = Tree(3)
  println(leaves(root).length)
  val leaf6 = leaves(root)(5)
  println(leaves(root).indexOf(leaf6))

}
