import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class LLex {

	public static void main(String[] args) {
		LinkedList<Integer> C;
		LinkedList<Integer> A = new LinkedList<Integer>(Arrays.asList(1,2,3));
		LinkedList<Integer> B = new LinkedList<Integer>(Arrays.asList(0,5,6));
		
		C = sumList(A,B);

	}
	
	public LinkedList<Integer> sumList(LinkedList<Integer> A, LinkedList<Integer> B){
	LinkedList<Integer> C = new LinkedList<Integer>();
	
	
	for(int i = 0; i<A.size(); i++){
		C.add(A.get(i) + B.get(i));
	}

	for(int i = 0; i<C.size(); i++){
		System.out.println(C.get(i));
	}
	
	return C;
	}
}