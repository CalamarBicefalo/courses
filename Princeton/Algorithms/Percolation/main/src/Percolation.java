import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final static int TOP_VIRTUAL_NODE_INDEX = 0;
    private final static int BOTTOM_VIRTUAL_NODE_INDEX = 1;

    final int N;
    final WeightedQuickUnionUF weightedQuickUnionUF;
    final boolean[][] open;

    public Percolation(int N) {
        if(N <= 0){
            throw new IllegalArgumentException("A percolation grid size must be between 1 and Integer.MAX");
        }
        this.N = N;
        weightedQuickUnionUF = new WeightedQuickUnionUF(N*N + 2);
        for (int j = 1; j <= N; j++) {
            weightedQuickUnionUF.union(TOP_VIRTUAL_NODE_INDEX,indexOf(1,j));
            weightedQuickUnionUF.union(BOTTOM_VIRTUAL_NODE_INDEX,indexOf(N,j));
        }
        open = new boolean[N][N];
    }

    private int indexOf(int i, int j) {
        return j + (i - 1)*N + 1; //Nodes 0 and 1 are reserved for virtual nodes
    }

    public void open(int i, int j) {
        checkCoordinates(i,j);
        open[i-1][j-1] = true;

        if(i > 1 && isOpen(i-1,j)){
            weightedQuickUnionUF.union(indexOf(i,j),indexOf(i-1,j));
        }
        if(i < N && isOpen(i+1,j)){
            weightedQuickUnionUF.union(indexOf(i,j),indexOf(i+1,j));
        }

        if(j > 1 && isOpen(i,j-1)){
            weightedQuickUnionUF.union(indexOf(i,j),indexOf(i,j-1));
        }
        if(j < N && isOpen(i,j+1)){
            weightedQuickUnionUF.union(indexOf(i,j),indexOf(i,j+1));
        }
    }

    public boolean isOpen(int i, int j) {
        checkCoordinates(i,j);
        return open[i-1][j-1];
    }

    public boolean isFull(int i, int j) {
        checkCoordinates(i, j);
        return isOpen(i,j) && weightedQuickUnionUF.connected(indexOf(i,j),TOP_VIRTUAL_NODE_INDEX);
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(TOP_VIRTUAL_NODE_INDEX,BOTTOM_VIRTUAL_NODE_INDEX);
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