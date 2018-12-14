public class GraphAdjMatrix implements Graph {

//    private int Parent[];
//
//    class Edge{
//        int neighbor;
//        int cost;
//        Edge next;
//    }

    //Creates a disjoint set of edges//
    void CreateSets(int n){
        Edges = new int[n];
        for(int i =0;i<n;i++){
            Edges[i]=-1;
        }
    }
    //Graph matrix and Edge array, edge set to -1 in create sets.
    private int[][] Matrix;
    private int [] Edges;

    //CreateSets in GraphAdjMAtrix to create a disjoint set with Edges array
    GraphAdjMatrix(int vertices){
        CreateSets(vertices);
        Matrix = new int[vertices][vertices];
        int i,j;
        for(i=0;i<vertices;i++){
            for(j=0;j<Matrix[i].length;j++){
                Matrix[i][j]=0;
            }
        }

    }
    @Override
    public void addEdge(int v1, int v2) {
        Matrix[v1][v2]=1;
       // Matrix[v2][v1]=1;
        //check if edge has been added to edge array already
        if(Edges[v2] == -1) {
            Edges[v2] = v1;
        }
        //compare to see if new edge is smaller
        else if (Matrix[v1][v2] < Matrix[Edges[v2]][v2]) {
            Edges[v2]=v1;
        }

    }

    @Override
    public void topologicalSort() {

    }
//Add edge also adds the minimum cost edge to a node.
    @Override
    public void addEdge(int v1, int v2, int weight) {
        Matrix[v1][v2]= weight;
       // Matrix[v2][v1]= weight;
        //check if edge has been added to edge array already
        if(Edges[v2] == -1) {
            Edges[v2] = v1;
        }
        //compare to see if new edge is smaller
        else if (Matrix[v1][v2] < Matrix[Edges[v2]][v2]) {
            Edges[v2]=v1;
        }
    }

    @Override
    public int getEdge(int v1, int v2) {
        if(Matrix[v1][v2] > 0){
            return Matrix[v1][v2];
        }
        return 0;
    }
/*compares the graph to the disjoint set array called Edges,
 *  k = the Edge index, only goes up if we are at a parent or if we found a minimum edge
  *  All Matrix edges are set to 0 unless they match a min pair, found in Disjoint set array 'Edges'
  *  Total = final weight of MST*/
    @Override
    public int createSpanningTree() {
        int i,j;
        int k =0;
        int total =0;
        for(i=0;i<Edges.length;i++){
            System.out.println();
            //Fills the graph with min spanning tree
            for(j=0;j<Matrix[i].length;j++) {
                if(k ==Edges.length){
                    Matrix[i][j] = 0;                }
                else if (Edges[k] < 0) {
                    Matrix[i][j] = 0;
                    k++;
                } else if (Matrix[Edges[k]][j] != Matrix[i][j]) {
                    Matrix[i][j] = 0;
                } else if (Matrix[i][k] == Matrix[i][j]) {
                    if(k <Edges.length){
                        k++;
                    }
                }
                total += Matrix[i][j];
                System.out.print(Matrix[i][j]);

            }
        }
        return total;
    }
}
