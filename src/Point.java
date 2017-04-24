import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
	
	private final int xcord;
	private final int ycord;
	
	// constructs the point (x, y)
	public Point(int x, int y) {
	     this.xcord = x;
	     this.ycord = y;  
	}

	// draws this point   
	public void draw() {
		StdDraw.point(xcord, ycord);
	}
	// draws the line segment from this point to that point
	public void drawTo(Point that) {
		StdDraw.line (this.xcord, this.ycord, that.xcord, that.ycord);
	}
	// string representation
	public String toString() {
		return ("("+xcord+",("+ycord+")");
	}
	// compare two points by y-coordinates, breaking ties by x-coordinates   
	public int compareTo(Point that) {
		if ((this.ycord < that.ycord) || ((this.ycord == that.ycord) && (this.xcord < that.xcord)))
			return -1;
		else if ((this.ycord == that.ycord) && (this.xcord == that.xcord))
			return 0;
		else
			return 1;
	}
	// the slope between this point and that point
	public double slopeTo(Point that) {
		double slope;
		if ((that.ycord - this.ycord) == 0)
			return 0.0;
		if ((that.xcord - this.xcord) == 0)
			return Double.POSITIVE_INFINITY;
		if ((this.ycord == that.ycord) && (this.xcord == that.xcord))
			return Double.NEGATIVE_INFINITY;
		slope = (double) ((that.ycord - this.ycord)/(that.xcord - this.xcord));
		return slope;
	}
	// compare two points by slopes they make with this point
	public Comparator<Point> slopeOrder() {
		return new Comparator<Point>() {
			@Override
			public int compare (Point p1, Point p2) {
				return Double.compare(Point.this.slopeTo(p1), Point.this.slopeTo(p2));
			}
		};
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
