
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

	// Inserir Elemento
	public void insert(Integer value) {
		if (root != null) {
			// Se for menor
			if (value < root.getValue()) {

				// Se tiver sub-arvore a esquerda
				if (leftTree != null && leftTree.getRoot() != null) {

					// Atualizar contagem de filhos
					Node new_root = leftTree.getRoot();
					Integer new_count = new_root.getCountLeftNodes() + 1;

					new_root.setCountLeftNodes(new_count);
					leftTree.setRoot(new_root);

					leftTree.insert(value);

				} else {// Se sub-arvore a esquerda é vazia

					Node node = new Node(value, 0, 0);
					Tree leftTree = new Tree(null, null, null);
					Tree rigthTree = new Tree(null, null, null);

					this.leftTree = new Tree(node, leftTree, rigthTree);

					System.out.println("O valor " + value + " foi inserido com sucesso");

				}

				// Se for maior
			} else if (value > root.getValue()) {

				// Se tiver sub-árvore não vazia a direita
				if (rigthTree != null && rigthTree.getRoot() != null) {

					// Atualizar contagem de filhos
					Node new_root = rigthTree.getRoot();
					Integer new_count = new_root.getCountRigthNodes() + 1;

					new_root.setCountRigthNodes(new_count);
					leftTree.setRoot(new_root);

					rigthTree.insert(value);

				} else {

					// Se a sub-árvore a direita é vazia
					Node node = new Node(value, 0, 0);

					Tree leftTree = new Tree(null, null, null);
					Tree rigthTree = new Tree(null, null, null);

					this.rigthTree = new Tree(node, leftTree, rigthTree);

					System.out.println("O valor " + value + " foi inserido com sucesso");

				}
			}
			// Impedir inserção de valures duplicados
			else if (value == root.getValue()) {
				System.out.println("O valor já existe na árvore e não pode ser inserido novamente!");
			}

		}

		else { // Caso a árvore seja vazia
			Node root = new Node(value, 0, 0);
			Tree leftTree = new Tree(null, null, null);
			Tree rigthTree = new Tree(null, null, null);

			this.root = root;
			this.leftTree = leftTree;
			this.rigthTree = rigthTree;

			System.out.println("O valor " + value + " foi inserido com sucesso e é a raiz!");

		}
	}

	// Remover Elemento
	public boolean remove(Integer value) {

		if (root != null) {

			if (leftTree != null && rigthTree != null) {

				if (value < root.getValue()) {

					if (leftTree.getRoot().getValue() == value) {

						// Se nao tem filhos
						if (leftTree.getLeftTree().getRoot() == null && leftTree.getRigthTree().getRoot() == null) {

							leftTree.setRoot(null);
							leftTree.setRigthTree(null);
							leftTree.setLeftTree(null);

							return true;

						}

						// Se tem um filho e é o da direita
						else if (leftTree.getLeftTree().getRoot() == null) {
							leftTree = leftTree.getRigthTree();
							return true;
						}

						// Se tem um filho e é o da esquerda
						else if (leftTree.getRigthTree().getRoot() == null) {
							leftTree = leftTree.getRigthTree();
							return true;
						}

						// Se tem dois filhos
						else {

							// Vai para a direita
							// Pega o da esquerda

							Tree auxiliar = leftTree.getRigthTree();

							while (auxiliar.getLeftTree() != null && auxiliar.getLeftTree().getRoot() != null) {
								auxiliar = auxiliar.getLeftTree();
							}

							Integer new_value = auxiliar.getRoot().getValue();

							leftTree.getRoot().setValue(new_value);

							boolean remove = leftTree.getRigthTree().remove(new_value);

							return remove;

						}

					} else {

						boolean remove = leftTree.remove(value);

						if (remove) {

							Integer new_count = root.getCountLeftNodes() - 1;

							root.setCountLeftNodes(new_count);

						}

						return remove;

					}
				}

				else if (value > root.getValue()) {

					// Vejamos se o filho é o valor

					if (rigthTree.getRoot().getValue() == value) {

						// Se nao tem filhos
						if (rigthTree.getLeftTree() == null && rigthTree.getRigthTree() == null) {

							rigthTree.setRoot(null);
							rigthTree.setRigthTree(null);
							rigthTree.setLeftTree(null);

							return true;
						}

						// Se tem um filho e é o da direita
						else if (rigthTree.getLeftTree().getRoot() == null) {
							rigthTree = rigthTree.getRigthTree();

							return true;
						}

						// Se tem um filho e é o da esquerda
						else if (rigthTree.getRigthTree().getRoot() == null) {
							rigthTree = leftTree.getRigthTree();

							return true;
						}

						// Se tem dois filhos
						else {

							// Vai para a direita
							// Pega o mais a esquerda

							Tree auxiliar = rigthTree.getRigthTree();

							while (auxiliar.getLeftTree() != null && auxiliar.getLeftTree().getRoot() != null) {
								auxiliar = auxiliar.getLeftTree();
							}

							Integer new_value = auxiliar.getRoot().getValue();

							rigthTree.getRoot().setValue(new_value);

							boolean remove = rigthTree.getRigthTree().remove(new_value);

							return remove;
						}
					} else {

						boolean remove = rigthTree.remove(value);

						if (remove) {

							Integer new_count = root.getCountRigthNodes() - 1;

							root.setCountRigthNodes(new_count);

						}

						return remove;
					}

				}

				else if (value == root.getValue()) {

					// Se nao tem filhos
					if (rigthTree.getRoot() == null && leftTree.getRoot() == null) {
						root = null;
						rigthTree = null;
						leftTree = null;

						return true;

					}

					// Se tem um filho e é o da direita
					else if (leftTree.getRoot() == null) {
						root = rigthTree.getRoot();
						rigthTree = rigthTree.getRigthTree();
						leftTree = rigthTree.getLeftTree();

						return true;
					}

					// Se tem um filho e é o da esquerda
					else if (rigthTree.getRoot() == null) {
						root = leftTree.getRoot();
						rigthTree = leftTree.getRigthTree();
						leftTree = leftTree.getLeftTree();

						return true;
					}

					// Se tem dois filhos
					else {

						// Vai para a direita
						// Pega o mais a esquerda

						Tree auxiliar = rigthTree;

						while (auxiliar.getLeftTree() != null && auxiliar.getLeftTree().getRoot() != null) {
							auxiliar = auxiliar.getLeftTree();
						}

						Integer new_value = auxiliar.getRoot().getValue();

						root.setValue(new_value);

						boolean remove = rigthTree.remove(new_value);

						return remove;
					}
				}

			}
		}

		else {

			System.out.println("O valor " + value + " não se encontra na árvore, logo não pode ser removido!");

		}

		return false;
	}

	// Buscar Elemento
	public boolean search(Integer value) {
		boolean found = false;

		if (root != null && found == false) {

			if (root.getValue() == value) {
				found = true;
			} else if (value < root.getValue()) {
				found = leftTree.search(value);
			} else {
				found = leftTree.search(value);
			}
		}
		return found;
	}
}
