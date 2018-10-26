import java.util.LinkedList;
import java.util.List;

public class Queue {
	private List<Tree> trees;
	public Queue() {
		trees = new LinkedList<Tree>();
	}
	
	public void enqueue(Tree tree) {
		this.trees.add(tree);
	}
	
	public Tree dequeue() {
		return this.trees.remove(0);
	}
	
	public boolean isEmpty() {
		return this.trees.size() == 0;
	}
}
