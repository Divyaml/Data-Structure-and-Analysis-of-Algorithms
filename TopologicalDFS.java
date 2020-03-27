
public class TopologicalDFS {
    
    int vertex;
    int index=0;
    int cyclicFlag=0;
    public TopologicalDFS(int vertex) {
        this.vertex=vertex;
    }
    
    public void printGraph(int[][] tmp) {
        for(int i=0;i<vertex;i++)
        {
            for(int j=0;j<vertex;j++)
            {
                System.out.print(tmp[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    //function to sort the graph
    public void sort(int i, boolean[] visited,int[] order,int[][] graph)
    {
        visited[i]=true;
        int tmp=-1;
        for(int j=0;j<vertex;j++) 
        {
            if (graph[i][j]!=0) {
                if (visited[j]==false && cyclicFlag!=1)
                {
                    sort(j,visited,order,graph);
                    if(cyclicFlag==1) {
                        return;
                    }
                    
                }
                else
                {
                    for(int k=0;k<vertex;k++)
                    {
                        if(order[k]==j && order[k]!=-1)
                        {
                            tmp=order[k];
                            break;
                        }
                    }
                    if(tmp!=j)
                    {
                        System.out.println("Found a cycle in the graph hence exiting program. The order of Graph sorted by far is :");
                        cyclicFlag=1;
                        return;
                    }
                }
            }
        }
        order[index]=i;
        index=index+1;
    }
    
    public void sortDFS(int[][] tmp) 
    {
        boolean visited[]=new boolean[vertex];
        int order[]=new int[vertex];
        
        for(int i=0;i<vertex;i++) 
        {
            visited[i]=false;
            order[i]=-1;
        }
        
        for (int j=0; j<vertex;j++)
        {
            if (visited[j]==false)
            {
                sort(j,visited,order,tmp);
                if(cyclicFlag==1)
                {break;}
            }

        }
        
        for(int k=vertex-1;k>-1;k--)
        {
            if(order[k]!=-1) 
            {
                System.out.print(order[k]+" ");
            }
        }

    }
    
    public static void main(String args[])
    {
        int[][] acyclicGraph= {{0,0,1,1,1,0,0,0,0,0,0,0},
                               {0,0,0,0,0,0,0,0,0,0,0,0},
                               {0,1,0,0,0,1,0,0,0,0,0,0},
                               {0,0,0,0,0,0,0,0,0,0,0,0},
                               {0,0,0,1,0,0,0,0,0,0,0,0},
                               {0,0,0,0,0,0,0,0,0,0,1,1},
                               {0,0,0,1,0,0,0,0,0,0,0,1},
                               {0,0,0,0,0,0,1,0,0,0,0,0},
                               {0,0,0,0,0,0,1,0,0,1,0,0},
                               {0,0,0,0,0,0,0,0,0,0,0,0},
                               {0,0,0,0,0,0,0,0,0,1,0,1},
                               {0,0,0,0,0,0,0,0,0,0,0,0}};
        int[][] cyclicGraph=  {{0,0,1,0,1,0,0,0,0,0,0,0},
                               {0,0,0,0,0,0,0,0,0,0,0,0},
                               {0,1,0,0,0,1,0,0,0,0,0,0},
                               {1,0,0,0,0,0,0,0,0,0,0,0},
                               {0,0,0,1,0,0,0,0,0,0,0,0},
                               {0,0,0,0,0,0,0,0,0,0,1,1},
                               {0,0,0,1,0,0,0,0,0,0,0,1},
                               {0,0,0,0,0,0,1,0,0,0,0,0},
                               {0,0,0,0,0,0,1,0,0,1,0,0},
                               {0,0,0,0,0,0,0,0,0,0,0,0},
                               {0,0,0,0,0,0,0,0,0,1,0,1},
                               {0,0,0,0,0,0,0,0,0,0,0,0}};
        TopologicalDFS tdfs = new TopologicalDFS(12);
        System.out.println("\nGraph without cyclic vertex: ");
        tdfs.printGraph(acyclicGraph);
        System.out.println("\nGraph sorted using topological DFS:");
        tdfs.sortDFS(acyclicGraph);
        TopologicalDFS ctdfs = new TopologicalDFS(12);
        System.out.println("\n\nGraph with cyclic vertex:");
        ctdfs.printGraph(cyclicGraph);
        ctdfs.sortDFS(cyclicGraph);
        
        
        
        
    }

}
