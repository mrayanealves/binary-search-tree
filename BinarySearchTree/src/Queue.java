import java.util.LinkedList;
import java.util.List;

/**
 * Classe que representa uma fila
 *
 * @author Maria Rayane Alves
 * @version 2018.10.23
 */ 
public class Queue {
    // Lista de Árvores
	private List<Tree> trees;

    /**
     * Construtor
     */ 
	public Queue() {
		trees = new LinkedList<Tree>();
	}

	/**
     * Adiciona elementos na fila
     */ 
	public void enqueue(Tree tree) {
		this.trees.add(tree);
	}

	/**
     * Remove elementos da fila
     */ 
	public Tree dequeue() {
		return this.trees.remove(0);
	}

	/**
     * Verifica se a fila está vazia
     *
     * @return boolean que indica True se é vazia e
     * False se não for
     */ 
	public boolean isEmpty() {
		return this.trees.size() == 0;
	}
}
