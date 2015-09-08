public class Percolation {

    final int N;

    public Percolation(int N) {
        if(N <= 0){
            throw new IllegalArgumentException("A percolation grid size must be between 1 and Integer.MAX");
        }
        this.N = N;
    }

    public void open(int i, int j) {
        checkCoordinates(i,j);
    }

    public boolean isOpen(int i, int j) {
        checkCoordinates(i,j);
        return false;
    }

    public boolean isFull(int i, int j) {
        checkCoordinates(i,j);
        return false;
    }

    public boolean percolates() {
        return false;
    }

    public static void main(String[] args) {
    }


    private void checkCoordinates(int i, int j) {
        if(i > N || j > N || i < 1 || j < 1){
            throw new IndexOutOfBoundsException(
                    String.format("The specified coordinates (%s,%s) are out of the grid, " +
                            "both should be between 1 and %s",i,j,N));
        }
    }
}