//Program to sort a Linked list in ascending order
public class LinkedList {
	Node head;
	  
	  static class Node{
	    int data;
	    Node next;

	    Node(int d){
	      data=d;
	      next=null;

	    }
	  }

	  //The function inserts a new value passed to linkedlist.
	  public static LinkedList insertValue(LinkedList list,int data)
	  {
	    Node new_node = new Node(data);
	    new_node.next = null;

	    if (list.head==null)
	    {
	      list.head=new_node;
	    }
	    else{
	      Node temp = list.head;
	      while(temp.next!=null){
	        temp=temp.next;
	      }
	      temp.next= new_node;

	    }
	    return list;

	  }
	  
	  //Function traverses through each node and prints the value
	  public static void traverse(LinkedList list)
	  {
		  Node temp = list.head;
		  if (temp==null)
		  {
			  System.out.print("List is empty");
			  return;
		  }
		  else
		  {
			  while(temp!=null)
			  {
				  System.out.print(temp.data +" ");
				  temp=temp.next;
			  }
		  
		  }
		  return;
	  }
	  
	  //Function sorts the unsorted list nodes into sorted nodes. Here we sort the list in
	  //ascending order
	  public static void sortList(LinkedList list)
	  {
		 Node temp1=list.head;
		 Node temp2=temp1.next;
		 Node ptemp2=temp1;
		 Node small=temp1;
		 Node first=temp1;
		 Node temp3=temp2;
		 
		 while(temp1.next!=null)
		 {
			 while(temp2!=null)
			 {
				 if(small.data>temp2.data)
				 {
					 if (small.next==temp2 && small!=list.head)
					 {
						 ptemp2=small;
					 }
					 else if(small.next!=temp2 || small!=list.head)
					 {
						 ptemp2=temp3;
					 }
					 small=temp2;
					 temp2=temp2.next;
				 }
				 else
				 {
					 if(temp2.next!=null) 
					 {
						 temp3=temp2;
					 }
					 temp2=temp2.next;
				 }
			 }
			 
			 if (temp1==list.head)
			 {
				 if (ptemp2.next!=small.next) 
				 {
					 ptemp2.next=small.next;
					 small.next=temp1;
					 list.head=small;
				 }
				 first=small;
			 }
			 else
			 {
				 if (ptemp2.next!=small.next) 
				 {
					 ptemp2.next=small.next;
					 first.next=small;
					 small.next=temp1;
				 }
				 
				 first=small;
			 }
			 temp1=small.next;
			 temp2=temp1.next;
			 ptemp2=temp1;
			 small=temp1;
		 }
		 return;
		  
	  }
	  
	  public static void main(String[] args)
	  {
	    LinkedList list = new LinkedList();
	    
	    list = insertValue(list, 5);
	    list = insertValue(list, 8);
	    list = insertValue(list, 4);
	    list = insertValue(list, 3);
	    list = insertValue(list, 6);
	    list = insertValue(list, 7);
	    list = insertValue(list, 2);
	    list = insertValue(list, 1);
	    list = insertValue(list, 9);
	    list = insertValue(list, 20);
	    list = insertValue(list, 25);
	    list = insertValue(list, 10);
	    list = insertValue(list, 12);
	    list = insertValue(list, 13);
	    list = insertValue(list, 11);
	    System.out.print("LinkedList before sorting is :"+"\n");
	    traverse(list);
		sortList(list);
		System.out.print("\nLinkedList after sorting is :\n");
		traverse(list);

	  }
}
