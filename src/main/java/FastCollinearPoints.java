import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        // Checks argument for null
        if (points == null)
            throw new IllegalArgumentException();

        // Checks array indices for null, otherwise copies to myPoints
        Point[] myPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            } else {
                myPoints[i] = points[i];
            }
        }

        // Check for Duplicate Points
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i != j && points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }

        int lineSegmentCount = 0;
        LineSegment[] tempLineSegments = new LineSegment[points.length * points.length];

        for (int p = 0; p < points.length; p++) {
            Point pPoint = points[p];
            Arrays.sort(myPoints);
            Arrays.sort(myPoints, pPoint.slopeOrder());
            int count = 1;

            for (int q = 0; q < myPoints.length - 1; q++) {
                Point curPoint = myPoints[q];
                if ((pPoint.compareTo(curPoint) < 0
                        && pPoint.slopeTo(curPoint) == pPoint.slopeTo(myPoints[q + 1]))) {
                    count++;
                } else {
                    if (count >= 3) {
                        tempLineSegments[lineSegmentCount] = new LineSegment(pPoint, myPoints[q]);
                        lineSegmentCount++;
                    }
                    count = 1;
                }
            }

            if (count >= 3) {
                tempLineSegments[lineSegmentCount] = new LineSegment(pPoint, myPoints[myPoints.length - 1]);
                lineSegmentCount++;
            }
        }

        // Copies over line segments
        lineSegments = new LineSegment[lineSegmentCount];
        for (int i = 0; i < lineSegmentCount; i++) {
            lineSegments[i] = tempLineSegments[i];
        }

    }

    private static LineSegment[] deepCopy(LineSegment[] arr) {
        LineSegment[] temp = new LineSegment[arr.length];

        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }

        return temp;
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return deepCopy(lineSegments);
    }

    public static void main(String[] args) {
        Point point0 = new Point(-1, 6);
        Point point1 = new Point(0, 5);
        Point point2 = new Point(1, 4);
        Point point3 = new Point(2, 3);
        Point point4 = new Point(3, 2);
        Point point5 = new Point(4, 1);

        Point[] points = { point0, point1, point5, point2, point3, point4 };
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        System.out.println(point4.slopeTo(point5));
        System.out.println(Arrays.deepToString(fastCollinearPoints.segments()));
    }
}
