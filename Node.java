
public class Node {
	public Node left;
	public Node right;
	public Node parent;
	public Point2D point;
	
	public Node(Node parent, Node left, Node right) {

	}
	public Node(Point2D point) {
		this.point=point;
	}
	
	public void setParent(Node parent) {
		this.parent=parent;
	}
	public void setLeft(Node left) {
		this.left=left;
	}
	public void setRight(Node right) {
		this.right=right;
	}
	
}
