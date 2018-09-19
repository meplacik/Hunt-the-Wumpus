import java.util.Comparator;
import java.util.ArrayList;


//stores the heap, number of elements in the heap, and a comparator
public class PQHeap<T>{
	
	private ArrayList<T> heap;
	//number of objects in list
	private int size;
	private Comparator<T> comp;
	
	//initializes empty heap, sets size to zero, stores comparator
	public PQHeap(Comparator<T> comparator){
		this.comp = comparator;
		this.heap = new ArrayList<T>();
		this.size = 0;
		this.heap.add(null);
	}
	
	//returns number of elements in heap
	public int size(){
		return this.size;
	}
	
	//adds the value to the heap and increments the size
	//use comparator to reshape the heap
	//can add private methods to handle reshaping the heap
	public void add(T obj){
		this.heap.add( obj );
		this.size++;
		this.moveUp();
	}
	
	// Re-heapify after adding a new last element.
	// Take the last element of the pq's list and move up the tree, making
	// sure the parent is bigger than the kid.
	private void moveUp() {
		int childIdx = this.size;
		int parentIdx;
		while (childIdx > 1) {
			parentIdx = childIdx/2;
			// if the child is higher priority than the parent, if compare
			// returns a positive number
			if (this.comp.compare( this.heap.get(parentIdx),
			        this.heap.get( childIdx ) ) < 0) {
			    // swap the parent and child so that the parent has 
			    // a higher priority
			    T tmp = this.heap.get( childIdx );
			    this.heap.set( childIdx, this.heap.get(parentIdx) );
			    this.heap.set( parentIdx, tmp );
			    // head up the tree
			    childIdx = parentIdx;
			}
			else { break; }
		}
	}
	
	private void moveDown() {
	    int parentIdx = 1;
	    int lChildIdx = 2*parentIdx;
	    int rChildIdx= lChildIdx + 1;
	    while (lChildIdx <= this.size) {
            if (this.comp.compare( this.heap.get( parentIdx ), this.heap.get( lChildIdx )) < 0) {
                // The left child is higher priority than the parent.
                // but maybe the right is even higher.
                if (rChildIdx <= this.size && this.comp.compare( this.heap.get( lChildIdx ), this.heap.get( rChildIdx ) )< 0) {
                    // The right child has the highest priority
                    T tmp = this.heap.get( rChildIdx );
                    this.heap.set( rChildIdx, this.heap.get(parentIdx ) );
                    this.heap.set( parentIdx, tmp );
                    // head down the tree to the right.
                    parentIdx = rChildIdx;                    
                }
                else {
                    // The left child has the highest priority
                    T tmp = this.heap.get( lChildIdx );
                    this.heap.set( lChildIdx, this.heap.get(parentIdx ) );
                    this.heap.set( parentIdx, tmp );
                    // head down the tree to the left.
                    parentIdx = lChildIdx;                    
                }
            }
            else if (rChildIdx <= this.size && this.comp.compare( this.heap.get( parentIdx ), this.heap.get( rChildIdx )) < 0) {
                // the right child is higher priority than the parent
                // the left child is lower priority than the parent.
                // therefore, the right child has the highest priority and should be swapped with the parent.
			    T tmp = this.heap.get( rChildIdx );
			    this.heap.set( rChildIdx, this.heap.get(parentIdx ) );
			    this.heap.set( parentIdx, tmp );
			    // head down the tree to the right.
			    parentIdx = rChildIdx;
            }
            else { break; } // the parent is higher priority than both kids
            // update the indices of the children.
            lChildIdx = 2*parentIdx;
            rChildIdx= lChildIdx + 1;
	    }
	}
	
	private void modMoveDown(ArrayList<T> temp) {
	
	    int parentIdx = 1;
	    int lChildIdx = 2*parentIdx;
	    int rChildIdx= lChildIdx + 1;
	    while (lChildIdx <= this.size) {
            if (this.comp.compare( temp.get( parentIdx ), temp.get( lChildIdx )) < 0) {
                // The left child is higher priority than the parent.
                // but maybe the right is even higher.
                if (rChildIdx <= this.size && this.comp.compare( temp.get( lChildIdx ), temp.get( rChildIdx ) )< 0) {
                    // The right child has the highest priority
                    T tmp = temp.get( rChildIdx );
                    temp.set( rChildIdx, temp.get(parentIdx ) );
                    temp.set( parentIdx, tmp );
                    // head down the tree to the right.
                    parentIdx = rChildIdx;                    
                }
                else {
                    // The left child has the highest priority
                    T tmp = temp.get( lChildIdx );
                    temp.set( lChildIdx, temp.get(parentIdx ) );
                    temp.set( parentIdx, tmp );
                    // head down the tree to the left.
                    parentIdx = lChildIdx;                    
                }
            }
            else if (rChildIdx <= this.size && this.comp.compare( temp.get( parentIdx ), temp.get( rChildIdx )) < 0) {
                // the right child is higher priority than the parent
                // the left child is lower priority than the parent.
                // therefore, the right child has the highest priority and should be swapped with the parent.
			    T tmp = temp.get( rChildIdx );
			    temp.set( rChildIdx, temp.get(parentIdx ) );
			    temp.set( parentIdx, tmp );
			    // head down the tree to the right.
			    parentIdx = rChildIdx;
            }
            else { break; } // the parent is higher priority than both kids
            // update the indices of the children.
            lChildIdx = 2*parentIdx;
            rChildIdx= lChildIdx + 1;
	    }
	}
	
	//removes and returns the highest priority element from the heap
	//use comparator to reshape the heap
	//can add private methods to handle reshaping heap
	public T remove(){
		if (this.size == 0){
			return null;
		}
		T t = this.heap.get(1);
		if (this.size>1){
			//puts last element in heap as root
			this.heap.set(1,this.heap.remove(this.size));
		}
		else{
			this.heap.remove(1);
		}
		this.size--;
		this.moveDown();
		return t;
	}
// 	
// 	public T getTopItem() {		
// 		ArrayList<T> temp = new ArrayList<T>(this.heap);
// 		
// 		if (this.size == 0){
// 			return null;
// 		}
// 		T t = temp.get(1);
// 		if (this.size>1){
// 			temp.set(1,temp.remove(this.size));
// 		
// 		}
// 		else{
// 			temp.remove(1);
// 		}
// // 		this.size--;
// 		this.modMoveDown(temp);
// 			
// 		return t;
// 	}

}







