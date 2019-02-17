package sample;


import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Kmeans {
    private int pointsCount;
    private List<Point2D> points;
    private List<Cluster> clusters;
    private int clusterCount;

    public Kmeans(int pointsCount, int clusterCount) {
        this.pointsCount = pointsCount;
        this.clusterCount = clusterCount;
        this.points = new ArrayList<>(pointsCount);
        this.clusters = new ArrayList<>(clusterCount);
    }

    public List<Cluster> calculate() {
        generatePoints();
        generateClusters();
        boolean centroidHasChanged;
        do {
            centroidHasChanged = false;
            assignPointsToCluster();
            for (Cluster cluster : clusters) {
                Point2D newCentroid = recalculateCentroid(cluster);
                if (newCentroid.equals(cluster.getCentroid())) {
                    cluster.setCentroid(newCentroid);
                    centroidHasChanged = true;
                }
            }
        } while (centroidHasChanged);
        return clusters;
    }

    private void generatePoints() {
        int a = 0, x2 = 900, y2 = 500;
        for (int i = 0; i < pointsCount; ++i) {
            points.add(new Point2D(a + Math.random() * x2, a + Math.random() * y2));
        }
    }

    private void generateClusters() {
        for (int i = 0; i < clusterCount; ++i) {
            clusters.add(new Cluster(points.get((int) (Math.random() * pointsCount))));
        }
    }

    private void assignPointsToCluster() {
        for (Point2D point : points) {
            double minDistance = Double.MAX_VALUE;
            Cluster minCluster = clusters.get(0);
            for (Cluster cluster : clusters) {
                double distance = euclideanDistance(cluster.getCentroid(), point);
                if (distance < minDistance) {
                    minDistance = distance;
                    minCluster = cluster;
                }
            }
            minCluster.addPointToCluster(point);
        }
    }

    private double euclideanDistance(Point2D point1, Point2D point2) {
        double x = point1.getX() - point2.getX();
        double y = point1.getY() - point2.getY();
        return Math.hypot(x, y);
    }

    private Point2D recalculateCentroid(Cluster cluster) {
        Point2D newCentroid = new Point2D(-1, -1.0);

        double xSum = 0, ySum = 0;
        for (Point2D point : cluster.getPointsInCluster()) {
            xSum += point.getX();
            ySum += point.getY();
        }
        double xMassCenter = xSum / cluster.getPointsInCluster().size();
        double yMassCenter = ySum / cluster.getPointsInCluster().size();
        Point2D bestCentroid = new Point2D(xMassCenter, yMassCenter);

        double minDistance = Double.MAX_VALUE;

        for (Point2D point : cluster.getPointsInCluster()) {
            double distance = euclideanDistance(bestCentroid, point);
            if (distance < minDistance) {
                minDistance = distance;
                newCentroid = point;
            }
        }

        return newCentroid;
    }

}
