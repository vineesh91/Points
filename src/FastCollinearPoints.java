import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	
	private LineSegment[] linesegs;
	private int linesegcount;
	
	private void swap (int i, int j, Point[] array) {
		Point temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	private void sort (Point[] array) {
		int lo = 1;
		int hi = array.length - 1;
		quicksort (lo, hi, array);
	}
	
	private void quicksort (int lo, int hi, Point[] array) {
		while (hi > lo) {
			int mid = partition (lo, hi, array);
			quicksort (lo, mid, array);
			quicksort (mid + 1, hi, array);
		}
	}
	
	private int partition (int lo, int hi, Point[] array) {
		int index = lo + 1;	
		int closed = lo;
		for (int i = lo + 1; i < array.length; i++) {
			if (array[0].slopeOrder().compare(array[lo], array[i]) < 0) {
				swap(i, index, array);
				closed = index;
				index++;
			}
		}
		swap (lo, closed, array);
		return closed;
	}
	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
		ArrayList<LineSegment> linSegList = new ArrayList<LineSegment>();
		linesegcount = 0;
		for (int i = 0; i < points.length; i++) {
			//to sort the points based on their slopes with point at i we will
			//place the ith point at 0 for reference
			swap(0, i, points);
			sort (points);
			Point refPoint = points[1];
			int slopeCount = 0;
			for (int k = 1; k < points.length; k++) {
				int j = 2;				
				while (j < points.length - 1) {
					if (points[0].slopeOrder().compare(refPoint, points[j]) == 0) {
						slopeCount++;
					}
					else {
						break;
					}
					j++;
				}
				if (slopeCount > 2) {
					linSegList.add(new LineSegment(points[0], points [k+slopeCount]));
					linesegcount ++;					
				}
			}
		}
		
		linesegs = new LineSegment[linSegList.size()];
		linSegList.toArray(linesegs);
	}
	   
	
	// the number of line segments
	public int numberOfSegments() {
	    return linesegcount;	
	}
	   
	
	// the line segments
	public LineSegment[] segments() {
		return linesegs;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}

}
