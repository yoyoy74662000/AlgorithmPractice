/******************************************************************************
 *  Compilation:  javac BruteCollinearPoints.java
 *  Execution:    java BruteCollinearPoints
 *  Dependencies: Point.java LineSegment.java
 *  
 *  This class examines 4 points at a time and checks whether 
 *  they all lie on the same line segment, returning all such line segments.
 *
 ******************************************************************************/
import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
	private LineSegment[] segments;
    /**
     * Constructor: finds all line segments containing 4 points
     *
     * @param  array of Points that need to be checked
     * 
     */
    public BruteCollinearPoints(Point[] points) {
        checkDuplicatedPoints(points);
        ArrayList<LineSegment> foundSegments = new ArrayList<>();

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);

        for (int p = 0; p < pointsCopy.length - 3; p++) {
            for (int q = p + 1; q < pointsCopy.length - 2; q++) {
                for (int r = q + 1; r < pointsCopy.length - 1; r++) {
                    for (int s = r + 1; s < pointsCopy.length; s++) {
                        if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[r]) &&
                                pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s])) {
                            foundSegments.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
                        }
                    }
                }
            }
        }

        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }

    /**
     * Check if the argument contains a repeated point
     * Throw a java.lang.IllegalArgumentException if the argument to the constructor contains a repeated point.
     */
    private void checkDuplicatedPoints(Point[] p){
    	for (int i = 0; i < p.length - 1; i++) {
    		for (int j = i + 1; j < p.length; j++) {
    			if (p[i].compareTo(p[j]) == 0) {
    				throw new java.lang.IllegalArgumentException("the argument to the constructor contains a repeated point");
    			}
    		}
    	}

    }

    /**
     * @return the number of segments containing 4 points;
     */
    public int numberOfSegments() {
   		return segments.length;
    }

    /**
     * @return the LineSegment Array in which all segments containing 4 points;
     */
    public LineSegment[] segments() {
   		return Arrays.copyOf(segments, numberOfSegments());
    }
}