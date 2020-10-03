public class Matrix {
    private double[][] matrixValues;
    private int rowLength;
    private int colLength;

    //add, multiply (scalar), multiply (other matrix), subtract

    public Matrix(double[][]matrixValues) {
        this.matrixValues = matrixValues;
        this.rowLength = matrixValues[0].length;
        this.colLength = matrixValues.length;

        for (int r  = 1; r < this.colLength; r++){
            if (this.rowLength != this.matrixValues[r].length){
                throw new RuntimeException("This matrix doesn't have all of its positions filled, so you can't initialize it.");
            }
        }
    }

    public int getRowLength(){
        return rowLength;
    }

    public int getColLength(){
        return colLength;
    }

    public double[][] getMatrixValues(){
        return matrixValues;
    }

    public Matrix add(Matrix otherMatrix){
        if (otherMatrix.getRowLength() != this.rowLength || otherMatrix.getColLength() != this.colLength){
            throw new RuntimeException("These matrices don't have the same dimension, so you can't add them");
        }
        else {
            double[][] newMatrixValues = matrixValues;
            for (int r = 0; r < this.colLength; r++) {
                for (int c = 0; c < this.rowLength; c++){
                    newMatrixValues[r][c] += otherMatrix.getMatrixValues()[r][c];
                }
            }
            return new Matrix(newMatrixValues);
        }
    }

    //subtracts otherMatrix from this matrix
    public Matrix subtract(Matrix otherMatrix){
        if (otherMatrix.getRowLength() != this.rowLength || otherMatrix.getColLength() != this.colLength){
            throw new RuntimeException("These matrices don't have the same dimension, so you can't subtract them");
        }
        else {
            double[][] newMatrixValues = matrixValues;
            for (int r = 0; r < this.colLength; r++) {
                for (int c = 0; c < this.rowLength; c++){
                    newMatrixValues[r][c] -= otherMatrix.getMatrixValues()[r][c];
                }
            }
            return new Matrix(newMatrixValues);
        }
    }

    //If this matrix is A and the other is B, this returns A*B
    public Matrix multiply(Matrix otherMatrix) {
        if (this.rowLength != otherMatrix.colLength){
            throw new RuntimeException("These matrices don't have the correct dimensions, so you can't multiply them");
        }
        else {
            double[][] newMatrixValues = new double[this.colLength][otherMatrix.getRowLength()];
            for (int ourRow = 0; ourRow < this.colLength; ourRow++) {
                for (int theirCol = 0; theirCol < otherMatrix.getRowLength(); theirCol++){
                    for (int ourColAndTheirRow = 0; ourColAndTheirRow < this.rowLength; ourColAndTheirRow++){
                        newMatrixValues[ourRow][theirCol] += matrixValues[ourRow][ourColAndTheirRow]*otherMatrix.getMatrixValues()[ourColAndTheirRow][theirCol];
                    }
                }
            }
            return new Matrix(newMatrixValues);
        }
    }

    public double getElement(int row, int col){
        return matrixValues[row][col];
    }

    @Override
    public String toString() {
        String returnString = "";
        for (int r = 0; r < this.colLength; r++) {
            for (int c = 0; c < this.rowLength; c++) {
                returnString += matrixValues[r][c]+ " ";
            }
            returnString +="\n";
        }
        return returnString;
    }
}
