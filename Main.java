import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		KDTree tree =new KDTree();
		/*String[] arg= {
				"build-kdtree	points-file.txt",
				"display-tree",
				"insert	15.0	15.0",
				"display-tree",
				"search	15.0	15.0",
				"remove	15.0	15.0",
				"display-tree",
				"display-points",
				"remove	90.0	80.0",
				"search	90.0	80.0",
				"display-tree",
				"display-points",
				"find-min-x",
				"find-min-y",
				"find-max-x",
				"find-max-y",
				"print-range	0.0	0.0	90.0	100.0",
				"quit"
		};*/
		Scanner scan=new Scanner(System.in);
		String s;
		/*for(String s:arg){*/
		while(scan.hasNextLine()) {
			s=scan.nextLine();
			if(s.startsWith("build-kdtree"))
				tree.buildKDtree(s.split("\t")[1]);
			else if(s.startsWith("display-tree"))
				tree.displayTree();
			else if(s.startsWith("insert")) {
				String[] str=s.split("\t");
				double x=Double.parseDouble(str[1]);
				double y=Double.parseDouble(str[2]);

				tree.insert(new Point2D(x,y));
				System.out.println("Inserted ("+x+","+y+")");
			}else if(s.startsWith("search")) {
				String str[]=s.split("\t");
				double x=Double.parseDouble(str[1]);
				double y=Double.parseDouble(str[2]);
				Point2D ret=tree.search(new Point2D(x,y));
				if(ret==null)
					System.out.println("Not found ("+str[1]+","+str[2]+")");
				else
					System.out.println("Found ("+ret.x+","+ret.y+")");
			}else if(s.startsWith("display-points")) {
				tree.displayPoints();
			}else if(s.startsWith("remove")) {
				String[] str=s.split("\t");
				double x=Double.parseDouble(str[1]);
				double y=Double.parseDouble(str[2]);
				Point2D p=tree.search(new Point2D(x, y));
				if(p!=null) {
					tree.remove(new Point2D(x, y));
					System.out.println("Removed ("+x+","+y+")");
				}else
					System.out.println("Not found ("+x+","+y+")");
			}else if(s.startsWith("find-min-x")) {
				Point2D p=tree.findMin(0);
				System.out.println("minimum-x is ("+p.x+","+p.y+")");
			
			}
			else if(s.startsWith("find-min-y")) {
				Point2D p=tree.findMin(1);
				System.out.println("minimum-y is ("+p.x+","+p.y+")");
			
			}
			else if(s.startsWith("find-max-x")) {
				Point2D p=tree.findMax(0);
				System.out.println("maximum-x is ("+p.x+","+p.y+")");
			
			}
			else if(s.startsWith("find-max-y")) {
				Point2D p=tree.findMax(1);
				System.out.println("maximum-y is ("+p.x+","+p.y+")");
			
			}else if(s.startsWith("quit")) {
				System.exit(0);
			}else if(s.startsWith("print-range"))	{
				String[] str=s.split("\t");
				double llx=Double.parseDouble(str[1]);
				double lly=Double.parseDouble(str[2]);
				double urx=Double.parseDouble(str[3]);
				double ury=Double.parseDouble(str[4]);
				tree.printRange(new Point2D(llx, lly), new Point2D(urx, ury));
			}
				
		}
		
		/*Point2D p1=new Point2D(1.0,1.0);
		Point2D p2=new Point2D(2.0,2.0);
		Point2D p3=new Point2D(3.0,3.0);
		Point2D p4=new Point2D(4.0,4.0);
		Point2D p5=new Point2D(5.0,5.0);
		
		tree.insert(p1);
		tree.insert(p2);
		tree.insert(p4);
		tree.insert(p3);
		tree.insert(p5);
		
		tree.displayPoints();
		
		tree.remove(p3);
		System.out.println();
		tree.displayPoints();*/
	}

}
