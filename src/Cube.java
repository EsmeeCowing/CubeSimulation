import java.awt.*;

public class Cube {
    double length;
    double assumedDistanceFromScreen;
    double pixelsPerFakeInch; //not cube based
    Matrix[] vertecies;
    int[][] edges;
    Color[] edgeColors;

    public Cube(int length) {
        this.length = length;
        this.assumedDistanceFromScreen = 20;
        pixelsPerFakeInch = 100;
        this.vertecies = new Matrix[]{
                new Matrix(new double[][]{{0-(length/2)},{0+(length/2)},{0+(length/2)},{1}}),
                new Matrix(new double[][]{{0+(length/2)},{0+(length/2)},{0+(length/2)},{1}}),
                new Matrix(new double[][]{{0-(length/2)},{0-(length/2)},{0+(length/2)},{1}}),
                new Matrix(new double[][]{{0+(length/2)},{0-(length/2)},{0+(length/2)},{1}}),
                new Matrix(new double[][]{{0-(length/2)},{0+(length/2)},{0-(length/2)},{1}}),
                new Matrix(new double[][]{{0+(length/2)},{0+(length/2)},{0-(length/2)},{1}}),
                new Matrix(new double[][]{{0-(length/2)},{0-(length/2)},{0-(length/2)},{1}}),
                new Matrix(new double[][]{{0+(length/2)},{0-(length/2)},{0-(length/2)},{1}})};
        this.edges = new int[][] {{0,1},{0,2},{0,4},{2,3},{2,6},{3,7},{3,1},{4,5},{4,6},{5,1},{5,7},{7,6}};
        this.edgeColors = new Color[]{Color.RED, Color.ORANGE, Color.BLUE, Color.GREEN, Color.BLUE, Color.CYAN, Color.PINK, Color.MAGENTA, Color.GRAY, Color.GREEN, Color.DARK_GRAY, Color.BLACK};
    }


    //THIS FUNCTION TAKES RADIANS NOT DEGREES
    //This is just a helper function, so if I have time after debugging I should just merge it with draw.
    //ax is the rotation around the x axis, ay around the y axis, and az around the z axis
    public Matrix getTransformationMatrix(double tx, double ty, double tz, double s, double ax, double ay, double az){ //not cube based
        Matrix translationMatrix = new Matrix(new double[][]{{1, 0, 0, tx},{0, 1, 0, ty},{0, 0, 1, tz},{0,0,0,1}});
        Matrix scaleMatrix = new Matrix(new double[][]{{s,0,0,0}, {0,s,0,0}, {0,0,s,0}, {0,0,0,1}});
        Matrix rotateXMatrix = new Matrix(new double[][]{{1,0,0,0},{0, Math.cos(ax), -Math.sin(ax), 0},{0, Math.sin(ax), Math.cos(ax), 0},{0, 0, 0, 1}});
        Matrix rotateYMatrix = new Matrix(new double[][]{{Math.cos(ay), 0, Math.sin(ay), 0},{0, 1, 0, 0} ,{-Math.sin(ay), 0, Math.cos(ay), 0},{0, 0, 0, 1}});
        Matrix rotateZMatrix = new Matrix(new double[][]{{Math.cos(az), -Math.sin(az), 0, 0},{Math.sin(az), Math.cos(az), 0, 0} ,{0, 0, 1, 0},{0, 0, 0, 1}});
        Matrix perspectiveMatrix = new Matrix(new double[][]{{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,1/assumedDistanceFromScreen,0}});
        Matrix screenScaleMatrix = new Matrix(new double[][]{{pixelsPerFakeInch,0,0,0},{0,-pixelsPerFakeInch,0,0},{0,0,pixelsPerFakeInch,0},{0,0,0,1}});//check here
        Matrix screenTranslationMatrix = new Matrix(new double[][]{{1,0,0,300},{0,1,0,300},{0,0,1,300},{0,0,0,1}});//todo: make this screen/2

        return screenTranslationMatrix.multiply(screenScaleMatrix).multiply(perspectiveMatrix).multiply(translationMatrix).multiply(scaleMatrix).multiply(rotateZMatrix).multiply(rotateXMatrix).multiply(rotateYMatrix);
    }

    public void draw(Graphics2D pen, double tx, double ty, double tz, double s, double ax, double ay, double az) {
        Matrix transformationMatrix = getTransformationMatrix(tx, ty, tz, s, ax, ay, az);

        for (int i = 0; i < edges.length; i++) {
            pen.setColor(edgeColors[i]);

            Matrix coordinateMatrix1 = transformationMatrix.multiply(vertecies[edges[i][0]]);
            Matrix coordinateMatrix2 = transformationMatrix.multiply(vertecies[edges[i][1]]);
            pen.drawLine(
                    (int) (coordinateMatrix1.getElement(0, 0) / coordinateMatrix1.getElement(3, 0)),
                    (int) (coordinateMatrix1.getElement(1, 0) / coordinateMatrix1.getElement(3, 0)),

                    (int) (coordinateMatrix2.getElement(0, 0) / coordinateMatrix2.getElement(3, 0)),
                    (int) (coordinateMatrix2.getElement(1, 0) / coordinateMatrix2.getElement(3, 0))
            );
        }
    }
}
