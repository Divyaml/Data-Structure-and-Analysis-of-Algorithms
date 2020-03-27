
public class HeapSort {
    
    //Function to heapify
    public void heapify(int[] tmp)
    {
        int par;
        int swappedIndex;
        
        for(int i=tmp[0]/2;i>0;i--)
        {
            par=i;
            swappedIndex=perculate(i,tmp);
            while(swappedIndex!=par)
            {
                par=swappedIndex;
                swappedIndex=perculate(par,tmp);
            }
        }   
    }
    
    //function to sort
    public void sort(int[] tmp) 
    {
        int temp;
        int count=tmp[0];
        while(tmp[0]>1)
        {
            temp=tmp[1];
            tmp[1]=tmp[tmp[0]];
            tmp[tmp[0]]=temp;
            tmp[0]--;
            heapify(tmp);
        }
        tmp[0]=count;
    }
    
    //function to perculate
    public int perculate(int parindex, int[] tmp)
    {
        int lchild=parindex*2;
        int rchild=parindex*2+1;
        int largest=parindex;
        int temp;
        
        if (lchild<=tmp[0]  && tmp[lchild]>tmp[largest] )
        {
            largest=lchild;
        }
        if (rchild<=tmp[0]  && tmp[rchild]>tmp[largest] )
        {
            largest=rchild;
        }
        
        if (largest!=parindex)
        {
            temp=tmp[parindex];
            tmp[parindex]=tmp[largest];
            tmp[largest]=temp;
        }
        
        return largest;
            
    }
    
    //function to print
    public static void printHeap(int[] tmp)
    {
        for(int i=1; i<tmp[0]+1; i++)
            System.out.print(tmp[i]+" ");
        System.out.println();
        return;
    }

    public static void main(String args[])
    {
        HeapSort array=new HeapSort();
        int[] heapArray= new int[] {20,5,7,11,20,3,32,9,12,6,1,29,33,4,13,17,45,24,60,73,18};
        System.out.println("\nArray before sort:");
        printHeap(heapArray);
        
        for(int i=1;i<heapArray.length;i++)
        {
            if(heapArray[i]<0)
            {
                for(int j=i;j<heapArray.length-1;j++)
                {
                    heapArray[j]=heapArray[j+1];
                    
                }
                heapArray[0]--;
            }
        }
        
        
        array.heapify(heapArray);
        System.out.println("\nArray after heapify:");
        printHeap(heapArray);
        
        array.sort(heapArray);
        System.out.println("\nArray after sort:");
        printHeap(heapArray);
    }
}
