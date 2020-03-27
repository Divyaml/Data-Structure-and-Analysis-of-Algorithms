
public class Graphs {

    int vertex;
    int adjMatrix[][];
    
    public Graphs(int vertex) {
        this.vertex=vertex;
        adjMatrix=new int[vertex][vertex];
    }
    
    public void edgeValue(int src,int dest,int edge)
    {
        adjMatrix[src][dest]=edge;
    }
    
    //Function to print Graph
    public void printValue() {
        for (int i=0; i<vertex;i++)
            for(int j=0;j<vertex;j++) {
                if (adjMatrix[i][j]!=0)
                {
                    System.out.println("The edge from vertices ("+i+" ,"+j+") is: "+adjMatrix[i][j]);
                }
            }
    }

    //Function to find shortest path from given source
    public void shortPath(int src)
    {
        int vertexArr[]= new int[vertex+1];
        int edgeArr[]=new int[vertex+1];
        int parentArr[]=new int[vertex+1];
        int finalGraph[][]=new int[vertex][vertex];
        edgeArr[0]=vertex;
        vertexArr[0]=vertex;
        parentArr[0]=vertex;
        int minValue=0;
        int dist,shortDist;
        int i,tmp,index=0;
        for (i=1;i<vertex+1;i++)
        {
            if (i-1==src)
            {
                edgeArr[i]=0;
                vertexArr[i]=i-1;
            }
            else
            {
                edgeArr[i]=Integer.MAX_VALUE;
                vertexArr[i]=i-1;
            }
        }
        
        heapify(edgeArr,vertexArr,parentArr);
        
        while(edgeArr[0]>0) 
        {
            minValue=edgeArr[1];
            i=vertexArr[1];
            for(int j=0;j<vertex;j++)
            {
                if(adjMatrix[i][j]!=0) 
                {
                    for(int k=0;k<=vertex;k++)
                    {
                        if (j==vertexArr[k]) {
                            index=k;
                        }
                    }
                    shortDist=edgeArr[index];
                    dist=minValue+adjMatrix[i][j];
                    if (shortDist>dist) 
                    {
                        edgeArr[index]=dist;
                        parentArr[index]=i;
                    }
                    else
                    {
                        edgeArr[index]=shortDist;
                    }
                }
        
            }
            tmp=edgeArr[1];
            edgeArr[1]=edgeArr[edgeArr[0]];
            edgeArr[edgeArr[0]]=tmp;
            tmp=vertexArr[1];
            vertexArr[1]=vertexArr[edgeArr[0]];
            vertexArr[edgeArr[0]]=tmp;
            tmp=parentArr[1];
            parentArr[1]=parentArr[edgeArr[0]];
            parentArr[edgeArr[0]]=tmp;
            edgeArr[0]--;
            heapify(edgeArr,vertexArr,parentArr);           
        }
        edgeArr[0]=vertex;

        for(i=1;i<vertex+1;i++)
        {
            finalGraph[parentArr[i]][vertexArr[i]]=adjMatrix[parentArr[i]][vertexArr[i]];
        }
        
        adjMatrix=finalGraph;
        
    }
    
    //to heapify the distance heap
    public void heapify(int[] tmpe, int[] tmpv,int[] tmpp)
    {
        int par;
        int swappedIndex;
        
        for(int i=tmpe[0]/2;i>0;i--)
        {
            par=i;
            swappedIndex=perculate(i,tmpe,tmpv,tmpp);
            while(swappedIndex!=par)
            {
                par=swappedIndex;
                swappedIndex=perculate(par,tmpe,tmpv,tmpp);
            }
        }   
    }
    
    public int perculate(int parindex, int[] tmpe,int[] tmpv, int[] tmpp)
    {
        int lchild=parindex*2;
        int rchild=parindex*2+1;
        int smallest=parindex;
        int temp,tempv,tempp;
        
        if (lchild<=tmpe[0]  && tmpe[lchild]<tmpe[smallest] )
        {
            smallest=lchild;
        }
        if (rchild<=tmpe[0]  && tmpe[rchild]<tmpe[smallest] )
        {
            smallest=rchild;
        }
        
        if (smallest!=parindex)
        {
            temp=tmpe[parindex];
            tempv=tmpv[parindex];
            tempp=tmpp[parindex];
            tmpe[parindex]=tmpe[smallest];
            tmpv[parindex]=tmpv[smallest];
            tmpp[parindex]=tmpp[smallest];
            tmpe[smallest]=temp;
            tmpv[smallest]=tempv;
            tmpp[smallest]=tempp;
        }
        
        return smallest;
            
    }
    
    
    public static void main(String[] args) {
        Graphs graph=new Graphs(10);
        graph.edgeValue(0, 1, 8);
        graph.edgeValue(0, 7, 7);
        graph.edgeValue(0, 9, 5);
        graph.edgeValue(1, 2, 7);
        graph.edgeValue(1, 4, 2);
        graph.edgeValue(1, 7, 1);
        graph.edgeValue(2, 1, 10);
        graph.edgeValue(2, 5, 2);
        graph.edgeValue(3, 2, 9);
        graph.edgeValue(4, 2, 7);
        graph.edgeValue(4, 6, 6);
        graph.edgeValue(5, 2, 4);
        graph.edgeValue(5, 3, 3);
        graph.edgeValue(6, 5, 8);
        graph.edgeValue(6, 8, 8);
        graph.edgeValue(7, 0, 6);
        graph.edgeValue(7, 4, 9);
        graph.edgeValue(7, 6, 9);
        graph.edgeValue(7, 8, 3);
        graph.edgeValue(7, 9, 4);
        graph.edgeValue(8, 9, 6);
        graph.edgeValue(9, 0, 2);
        System.out.println("Graph before finding shortest path using Dijkstra's Algorithm: ");
        graph.printValue();
        graph.shortPath(0);
        System.out.println("\nGraph after finding shortest path:");
        graph.printValue();
    }
}
