package sample;


import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private Point2D centroid;
    private List<Point2D> pointsInCluster = new ArrayList<>();

    public void setCentroid(Point2D centroid) {
        this.centroid = centroid;
    }

    public List<Point2D> getPointsInCluster() {
        return pointsInCluster;
    }

    public Cluster(Point2D centroid) {
        this.centroid = centroid;
    }

    public Point2D getCentroid() {
        return centroid;
    }

    public void addPointToCluster(Point2D point) {
        pointsInCluster.add(point);
    }
}
