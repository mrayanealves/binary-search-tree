
public class Node {
	private Integer value;
	// Conta a quantidade de filhos a esquerda
	private Integer countLeftNodes;
	// Conta a quantidade de filhos a direita
	private Integer countRigthNodes;
	
	public Node(Integer value, Integer countLeftNodes, Integer coutRigthNodes) {
		this.value = value;
		this.countLeftNodes = countLeftNodes;
		this.countRigthNodes = coutRigthNodes;
	}
	
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