import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class KDTree {
	
	
	public Node root;

	KDTree buildKDtree(String fileName){
		try {
			ArrayList<Point2D> list=new ArrayList<Point2D>();
			Scanner scan=new Scanner(new File(fileName));
			String s;
			while(scan.hasNextLine()) {
				s=scan.nextLine();
				String[] str=s.split("\t");
				double x=Double.parseDouble(str[0]);
				double y=Double.parseDouble(str[1]);
				list.add(new Point2D(x, y));
			}
			Collections.shuffle(list);
			for(Point2D p:list)
				this.insert(p);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return this;
	}

	void insert(Point2D point) {		//inserting new node
		Node newNode=new Node(point);
		if(root==null) {
			root=newNode;
			newNode.setParent(null);
			newNode.setLeft(null);
			newNode.setRight(null);
		}else
			insertR(root,newNode, 0);
	}


	void insertR(Node node, Node newNode, int depth) {		//insert recursively
		if(depth%2==0) {									//x
			if(node.point.x>newNode.point.x)
				if(node.left==null) {
					node.setLeft(newNode);
					newNode.setParent(node);
				}else {
					insertR(node.left,newNode,depth+1);
				}
			else {
				if(node.right==null) {
					node.setRight(newNode);
					newNode.setParent(node);
				}else {
					insertR(node.right,newNode,depth+1);
				}
			}
		}else {												//y
			if(node.point.y>newNode.point.y)
				if(node.left==null) {
					node.setLeft(newNode);
					newNode.setParent(node);
				}else {
					insertR(node.left,newNode,depth+1);
				}
			else {
				if(node.right==null) {
					node.setRight(newNode);
					newNode.setParent(node);
				}else {
					insertR(node.right,newNode,depth+1);
				}
			}
		}
	}
	
	void remove(Point2D point) {
		removeR(root,point,0,0);
	}
	void removeR(Node node,Point2D point, int depth, int lastOperation) {		//remove point recursively
		if(node!=null) {														//lastOperation is -1 if called from left, 1 if called from right, 0 if it is root
			if(depth%2==0) {
				if(node.point.x==point.x) {
					//code to delete
					if(node.left==null&&node.right==null) {
						if(lastOperation==-1)	//means this node is the left child of its parent
							node.parent.left=null;
						else if(lastOperation==1)	//means this node is the right child of its parent
							node.parent.right=null;
						else 
							root=null;
					}else if(node.left==null) {
						if(lastOperation==-1) {	//means this node is the left child of its parent
							node.parent.left=node.right;
							node.right.parent=node.parent;
							
						}else if(lastOperation==1) {	//means this node is the right child of its parent
							node.parent.right=node.right;
							node.right.parent=node.parent;
						}else {
							root=node.right;
							node.right.parent=null;
						}
					}else if(node.right==null) {	
						if(lastOperation==-1) {	//means this node is the left child of its parent
							node.parent.left=node.left;
							node.left.parent=node.parent;
							
						}else if(lastOperation==1) {	//means this node is the right child of its parent
							node.parent.right=node.left;
							node.left.parent=node.parent;
						}else {
							root=node.left;
							node.left.parent=null;
						}
					}else {//removed node has two children
						Node newNode;
						if(depth%2==0) {										//depth is even
							newNode=findMinR(0,node.right,0);
						}else {													//depth is odd
							newNode=findMinR(1,node.right,1);
						}
						
						boolean left=false;
						if(newNode==newNode.parent.left)
							left=true;
						if(newNode.left!=null) {
							newNode.left.parent=newNode.parent;
							if(left) {
								newNode.parent.left=newNode.left;
								
							}else {
								newNode.parent.right=newNode.left;
							}	
						}else if(newNode.right!=null){
							newNode.right.parent=newNode.parent;
							if(left) {
								newNode.parent.left=newNode.right;
							}else {
								newNode.parent.right=newNode.right;
							}
						}
						else {
							if(left) {
								newNode.parent.left=null;
							}else {
								newNode.parent.right=null;
							}
						}
							
							
						if(node.parent==null) {
							newNode.left=node.left;
							newNode.right=node.right;
							newNode.parent=null;
						}else if(node.parent.left==node) {
							node.parent.left=newNode;
							newNode.parent=node.parent;
						}else {
							node.parent.right=newNode;
							newNode.parent=node.parent;
						}
						
						newNode.left=node.left;
						newNode.right=node.right;
						
					}
				}else if(node.point.x>point.x) {
					removeR(node.left,point, depth+1,-1);
				}
				else
					removeR(node.right, point, depth+1,1);
			}else {												//depth is odd
				if(node.point.y==point.y) {
					//code to delete
					if(node.left==null&&node.right==null) {
						if(lastOperation==-1)	//means this node is the left child of its parent
							node.parent.left=null;
						else if(lastOperation==1)	//means this node is the right child of its parent
							node.parent.right=null;
						else 
							root=null;
					}else if(node.left==null) {
						if(lastOperation==-1) {	//means this node is the left child of its parent
							node.parent.left=node.right;
							node.right.parent=node.parent;
							
						}else if(lastOperation==1) {	//means this node is the right child of its parent
							node.parent.right=node.right;
							node.right.parent=node.parent;
						}else {
							root=node.right;
							node.right.parent=null;
						}
					}else if(node.right==null) {	
						if(lastOperation==-1) {	//means this node is the left child of its parent
							node.parent.left=node.left;
							node.left.parent=node.parent;
							
						}else if(lastOperation==1) {	//means this node is the right child of its parent
							node.parent.right=node.left;
							node.left.parent=node.parent;
						}else {
							root=node.left;
							node.left.parent=null;
						}
					}else {//removed node has two children
						Node newNode;
						if(depth%2==0) {										//depth is even
							newNode=findMinR(0,node.right,0);
						}else {													//depth is odd
							newNode=findMinR(1,node.right,1);
						}
						
						boolean left=false;
						if(newNode==newNode.parent.left)
							left=true;
						if(newNode.left!=null) {
							newNode.left.parent=newNode.parent;
							if(left) {
								newNode.parent.left=newNode.left;
								
							}else {
								newNode.parent.right=newNode.left;
							}	
						}else {
							newNode.right.parent=newNode.parent;
							if(left) {
								newNode.parent.left=newNode.right;
							}else {
								newNode.parent.right=newNode.right;
							}
						}
						if(node.parent==null) {
							newNode.left=node.left;
							newNode.right=node.right;
							newNode.parent=null;
						}else if(node.parent.left==node) {
							node.parent.left=newNode;
							newNode.parent=node.parent;
						}else {
							node.parent.right=newNode;
							newNode.parent=node.parent;
						}
						
						newNode.left=node.left;
						newNode.right=node.right;
					
					}
				}else if(node.point.y>point.y) {
					removeR(node.left,point, depth+1,-1);
				}
				else
					removeR(node.right, point, depth+1,1);
			}
		}
	}
	
	Point2D search(Point2D point) {
		Node ans=searchR(point, root, 0);
		
		if(ans==null)
			return null;
		else
			return ans.point;
	}
	Node searchR(Point2D point, Node node, int depth) {
		if(depth%2==0) {
			if(node.point.x==point.x) {	//assuming general position. (no need to check y's)
				return node;
			}
			else if(node.point.x>point.x)
				if(node.left!=null)
					return searchR(point, node.left, depth+1);
				else 
					return null;
			else
				if (node.right!=null)
					return searchR(point, node.right,depth+1);
				else
					return null;
		}else {
			if(node.point.y==point.y) {	//assuming general position. (no need to check x's)
				return node;
			}
			else if(node.point.y>point.y)
				if(node.left!=null)
					return searchR(point, node.left, depth+1);
				else 
					return null;
			else
				if (node.right!=null)
					return searchR(point, node.right,depth+1);
				else
					return null;
			
		}
	}
	
	void displayTree() {

		displayTreeR(root,0);
		
	}
	void displayTreeR(Node node, int depth) {

		
	}
	
	
	void displayPoints() {
		displayPointsR(root);
	}
	void displayPointsR(Node node) {
		if(node.left!=null)
			displayPointsR(node.left);
		System.out.println(node.point);
		if(node.right!=null)
			displayPointsR(node.right);
	}
	
	Point2D findMin(int d) {
		return findMinR(d,root,0).point;	
	}
	
	Node findMinR(int d,Node node,int depth) {
		if(d==0) {
			if(depth%2==0)
				if(node.left!=null)
					return findMinR(d,node.left,depth+1);
				else
					return node;
			else {
				Node minNode=node.right;
				if(node.left!=null)
					if(minNode==null)
						minNode=node.left;
					else if(minNode.point.x>node.left.point.x)
						minNode=node.left;
				
				if(minNode!=null)
					if(minNode.point.x>node.point.x)
						return node;
					else
						return findMinR(d,minNode,depth+1);	
				else 
					return node;
			}

		}else {
			if(depth%2==1)
				if(node.left!=null)
					return findMinR(d,node.left,depth+1);
				else
					return node;
			else {
				Node minNode=node.right;
				if(node.left!=null)
					if(minNode==null)
						minNode=node.left;
					else if(minNode.point.y>node.left.point.y)
						minNode=node.left;
				
				if(minNode!=null)
					if(minNode.point.y>node.point.y)
						return node;
					else
						return findMinR(d,minNode,depth+1);	
				else 
					return node;
			}
		}
	}
	
	Point2D findMax(int d) {
		return findMaxR(d,root,0);
	}
	Point2D findMaxR(int d,Node node,int depth) {
		if(d==0) {
			if(depth%2==0)
				if(node.right!=null)
					return findMaxR(d,node.right,depth+1);
				else
					return node.point;
			else {
				Node maxNode=node.right;
				if(node.left!=null)
					if(maxNode==null)
						maxNode=node.left;
					else if(maxNode.point.x<node.left.point.x)
						maxNode=node.left;
				
				if(maxNode!=null)
					if(maxNode.point.x<node.point.x)
						return node.point;
					else
						return findMaxR(d,maxNode,depth+1);	
				else 
					return node.point;
			}

		}else {
			if(depth%2==1)
				if(node.left!=null)
					return findMaxR(d,node.right,depth+1);
				else
					return node.point;
			else {
				Node maxNode=node.right;
				if(node.left!=null)
					if(maxNode==null)
						maxNode=node.left;
					else if(maxNode.point.y<node.left.point.y)
						maxNode=node.left;
				
				if(maxNode!=null)
					if(maxNode.point.y<node.point.y)
						return node.point;
					else
						return findMaxR(d,maxNode,depth+1);	
				else 
					return node.point;
			}
		}
	}
	
	void printRange(Point2D lowerleftcorner, Point2D upperrightcorner) {
		this.insert(lowerleftcorner);
		this.insert(upperrightcorner);
		Node llc=this.searchR(lowerleftcorner, root, 0);
		Node urc=this.searchR(upperrightcorner, root, 0);
		
		Node split=findSplitNode(llc,urc,root, 0);
		Node node=llc;
		while (node!= split) {
			if(node.right!=null)
				printSubtree(node.right);
			node=node.parent;
		}
		node=urc;
		while (node!= split) {
			if(node.left!=null)
				printSubtree(node.left);
			node=node.parent;
		}
		
	}

	Node findSplitNode(Node leftNode,Node rightNode,Node node,int depth) {
		if(depth==0) {
			if(leftNode.point.x<node.point.x && rightNode.point.x<node.point.x)
				if(node.left!=null)
					return findSplitNode(leftNode,rightNode,node.left,depth+1);
				else
					return node;
			else if(leftNode.point.x>node.point.x && rightNode.point.x>node.point.x)
				if(node.right!=null)
					return findSplitNode(leftNode,rightNode,node.right,depth+1);
				else
					return node;
			else
				return node;
		}
		else {
			if(leftNode.point.y<node.point.y && rightNode.point.y<node.point.y)
				if(node.left!=null)
					return findSplitNode(leftNode,rightNode,node.left,depth+1);
				else
					return node;
			else if(leftNode.point.y>node.point.y && rightNode.point.y>node.point.y)
				if(node.right!=null)
					return findSplitNode(leftNode,rightNode,node.right,depth+1);
				else
					return node;
			else
				return node;
		}
	}
	void printSubtree( Node node) {
		if(node.left!=null)
			printSubtree(node.left);
		System.out.println(node.point);
		if(node.right!=null)
			printSubtree(node.right);
		
	}
	
}
