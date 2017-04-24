import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

	private LineSegment[] linesegs;
	private int linesegnumbers = 0;
	
	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		ArrayList<LineSegment> linSegList = new ArrayList<LineSegment>();
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				for (int k = j + 1; k <points.length && 
						(points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])); k++) {
					for (int l = k + 1; l < points.length && 
							(points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])); l++) {
						Point largest = points[i];
						Point smallest = points[l];
						if(largest.compareTo(points[j]) < 0) {
							largest = points[j];
						}
						if(largest.compareTo(points[k]) < 0) {
							largest = points[k];
						}
						if(largest.compareTo(points[l]) < 0) {
							largest = points[l];
						}
						if(smallest.compareTo(points[i]) > 0) {
							smallest = points[j];
						}
						if(smallest.compareTo(points[j]) > 0) {
							smallest = points[k];
						}
						if(smallest.compareTo(points[k]) > 0) {
							smallest = points[l];
						}
						linSegList.add(new LineSegment(smallest, largest));
						linesegnumbers++;
					}
				}
			}
		}
		linesegs = new LineSegment[linSegList.size()];
		linSegList.toArray(linesegs);
	}
	   
	// the number of line segments
	public int numberOfSegments() {
		return linesegnumbers;
	}
	   
	// the line segments
	public LineSegment[] segments() {
		return linesegs;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}

}
