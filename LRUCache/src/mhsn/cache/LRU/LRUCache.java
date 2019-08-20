package mhsn.cache.LRU;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	
	Map<Integer, DNode> cacheMap; 
	int size;
	DNode front; // to hold the position of front in the double linked list 
	DNode rear; // to hold the Position of end in the double linked list
	
	public LRUCache(int size) {
		this.size = size;
		cacheMap = new HashMap<Integer, DNode>(size); //initializing the constructor with cache size
	}
	
	//Put entry in the cache. check method put2() for more clear logic understanding
	public void put(int value){
		System.out.println("\nPUT: "+value);
		//if entry already exists then make it rear
		if(cacheMap.containsKey(value)){
			DNode n = cacheMap.get(value);
			// If there is not only one element and given entry is not rear
			if(front != rear && n != rear){
				if(n == front){ // if given entry is on the front
					front = front.next;
					front.prev = null; 
				}else{
					n.next.prev = n.prev;
					n.prev.next= n.next;
				}
				//make it rear 
				n.next = null;
				n.prev = rear;
				rear.next = n;
				rear = n;
			}
						
		}//if cache is already full and key doesn't exist in the cacheMap
		else if(cacheMap.size() == size){
			cacheMap.remove(front.value); // remove the front element
			front = front.next; // move front to next
			front.prev = null;
			DNode n = new DNode(rear,null, value); // creat a new node and add it to rear.
			rear.next = n;
			rear = n;
			cacheMap.put(value, n);
			
		}else{// ELse entry is not already existing and cache limit is not reached.
			
			DNode n = null;
			if(front == null){ // if caches is empty
				n = new DNode(null, null, value);
				front = n;
				rear = n;
			}else{
				n = new DNode(rear, null, value);
				rear.next = n;
				rear = n;
			}
			cacheMap.put(value, n);
		}
		
		
		printCache(front);
	}
	
	
	public void put2(int value){
		
		DNode n = new DNode(null, null, value);
		
		//1. First check if the cache is empty
		if(front == null){
			//put n in the cache and assign to the front and rear
			n = new DNode(null, null, value);
			front = n;
			rear = n;
			return;
		}
		
		//2. If key is already existing in the cache map.
		if(cacheMap.containsKey(value)){
			 n = cacheMap.get(value);
			//assign the corresponding node to rear 
			//below condition checks
			//If front != rear :: there is not only one element
			// and n != rear :: if n is rear then no need to modify
			if(front != rear && n != rear){
				if(n == front){
					// move front to next and make n rear
				}
				else{ // n is some in the middle of link list
					// this node rear
				}
			}
		}
		//3. new value does not exist in the cache.
		else{
			//3.1 If cache is full
			if(cacheMap.size() == size){
				// Remove the front
				// put new node in the cache 
				// and assign it to rear.
			}
			//3.2 else cache is not full and limit is not reached 
			else{
				// put new node in the cache 
				// assign it to rear
			}
			
		}
	}
	
	private void printCache(DNode front2) {
		
		DNode temp = front2;
		
		System.out.println("front: "+ front.value+"  Rear: "+rear.value);
		System.out.print("Cache Storage:");
		while(temp != null){
			System.out.print(" "+temp.value);
			temp = temp.next;
		}
		System.out.println();
	}

	public int get(int num){
		System.out.println("\nGET: "+num);
		if(cacheMap.containsKey(num)){
			put(num);
			return cacheMap.get(num).value;	
		}else{
			// TODO: Get value from the database and cache it in the LRU cache
			System.out.println("GET: Not in the cache, getting it from the db");
			put(num);
			
		}
				
		return -1;
		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LRUCache lruCache = new LRUCache(4);
		
		lruCache.put(10);
		lruCache.put(3);
		lruCache.put(4);
		
		lruCache.put(2);
		lruCache.put(3);
		lruCache.put(5);
		lruCache.get(10);
		lruCache.get(2);
		
		
	}

}

class DNode{
	
	public DNode prev;
	public DNode next;
	
	int value; 
	
	public DNode(DNode prev, DNode next, int value){
		this.prev = prev;
		this.next = next;
		this.value = value;
	}
	
} 