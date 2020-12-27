import structures.BinaryTree
import structures.BinaryTree._

object Application extends App {

  val tree = BinaryTree(1)
  println(tree)
  println(
    tree.toPrint("")
  )

}
