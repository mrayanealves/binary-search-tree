/**
 * Classe que representa um nó de uma árvore
 * 
 * @author Maria Rayane Alves
 * @version 2018.10.20
 */ 
public class Node {
    // Valor armazenado no nó
	private Integer value;
	// Conta a quantidade de filhos a esquerda
	private Integer countLeftNodes;
	// Conta a quantidade de filhos a direita
	private Integer countRigthNodes;
	
    /**
     * Construtor
     * 
     * @param value valor Valor a ser armazenado no nó
     * @param countLeftNodes total de filhos a esquerda
     * @param countRigthNodes total de filhos a direita
     */ 
	public Node(Integer value, Integer countLeftNodes, Integer coutRigthNodes) {
		this.value = value;
		this.countLeftNodes = countLeftNodes;
		this.countRigthNodes = coutRigthNodes;
	}
	
    /**
     * Getters e Setters
     */ 
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Integer getCountLeftNodes() {
		return countLeftNodes;
	}
	public void setCountLeftNodes(Integer countLeftNodes) {
		this.countLeftNodes = countLeftNodes;
	}
	public Integer getCountRigthNodes() {
		return countRigthNodes;
	}
	public void setCountRigthNodes(Integer countRigthNodes) {
		this.countRigthNodes = countRigthNodes;
	}	
}
