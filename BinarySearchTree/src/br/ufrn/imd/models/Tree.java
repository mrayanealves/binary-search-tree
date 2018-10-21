package br.ufrn.imd.models;

public class Tree {
	private Node root;
	private Tree leftTree;
	private Tree rigthTree;
	
	public Tree(Node root, Tree leftTree, Tree rigthTree) {
		this.root = root;
		this.leftTree = leftTree;
		this.rigthTree = rigthTree;
	}
	
	public Node getRoot() {
		return root;
	}
	
	public void setRoot(Node root) {
		this.root = root;
	}
	
	public Tree getLeftTree() {
		return leftTree;
	}
	
	public void setLeftTree(Tree leftTree) {
		this.leftTree = leftTree;
	}
	
	public Tree getRigthTree() {
		return rigthTree;
	}
	
	public void setRigthTree(Tree rigthTree) {
		this.rigthTree = rigthTree;
	}
}
