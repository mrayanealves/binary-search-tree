
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
				if (leftTree.getRoot() != null) {
					// Atualizar contagem de filhos
					root.setCountLeftNodes(root.getCountLeftNodes() + 1);
					leftTree.insert(value);
				} else {
					// Se sub-arvore a esquerda é vazia
					Node node = new Node(value, 0, 0);
					Tree leftTree = new Tree(null, null, null);
					Tree rigthTree = new Tree(null, null, null);
					this.leftTree = new Tree(node, leftTree, rigthTree);
					// Atualizar contagem de filhos
					root.setCountLeftNodes(root.getCountLeftNodes() + 1);
					System.out.println("O valor " + value + " foi inserido com sucesso!");
				}
			// Se for maior
			} else if (value > root.getValue()) {
				// Se tiver sub-árvore não vazia a direita
				if (rigthTree.getRoot() != null) {
					// Atualizar contagem de filhos
					root.setCountRigthNodes(root.getCountRigthNodes() + 1);
					rigthTree.insert(value);
				} else {
					// Se a sub-árvore a direita é vazia
					Node node = new Node(value, 0, 0);
					Tree leftTree = new Tree(null, null, null);
					Tree rigthTree = new Tree(null, null, null);
					this.rigthTree = new Tree(node, leftTree, rigthTree);
					// Atualizar contagem de filhos
					root.setCountRigthNodes(root.getCountRigthNodes() + 1);
					System.out.println("O valor " + value + " foi inserido com sucesso!");
				}
			}
			// Impedir inserção de valores duplicados
			else if (value == root.getValue()) {
				System.out.println("O valor " + value + " já existe na árvore e não pode ser inserido novamente!");
			}
		}
		else { // Caso a árvore seja vazia
			this.root = new Node(value, 0, 0);
			this.leftTree = new Tree(null, null, null);
			this.rigthTree = new Tree(null, null, null);
			System.out.println("O valor " + value + " foi inserido com sucesso e é a raiz!");
		}
	}

	// Remover Elemento
	public boolean remove(Integer value) {
		if (root != null) {
			if (leftTree != null && rigthTree != null) {
				// Se for menor
				if (value < root.getValue()) {
					if (leftTree.getRoot() != null) {
						if (leftTree.getRoot().getValue() == value) {
							boolean remove = leftTree.remove(value);
							return remove;
						}

						else {
							boolean remove = leftTree.remove(value);
							if (remove) {
								Integer new_count = root.getCountLeftNodes() - 1;
								root.setCountLeftNodes(new_count);
							}
							return remove;
						}
					}
					else {
						return false;
					}
				}
				// Se for Maior
				else if (value > root.getValue()) {
					if (rigthTree.getRoot() != null) {
						// Vejamos se o filho é o valor
						if (rigthTree.getRoot().getValue() == value) {
							boolean remove = rigthTree.remove(value);
							return remove;
						} else {
							boolean remove = rigthTree.remove(value);
							if (remove) {
								Integer new_count = root.getCountRigthNodes() - 1;
								root.setCountRigthNodes(new_count);
							}
							return remove;
						}
					} else {
						return false;
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
		return false;
	}

	// Buscar Elemento
	public boolean search(Integer value) {
		boolean found = false;
		if (root != null && found == false) {
			if (root.getValue() == value) {
				found = true;
			} 
			else if (value < root.getValue()) {
				found = leftTree.search(value);
			} else {
				found = leftTree.search(value);
			}
		}
		return found;
	}

	/**
	 * Busca o n-ésimo termo na árvore em ordem simétrica
	 * @param n Integer que representa a posição que se deseja buscar
	 * @param tree Tree que representa a árvore que possuirá a raíz inicial
	 * @return meet Integer que representa o número encontado na posição n
	 * @author Maria Rayane Alves
	 */
	Integer nthElement(Integer n) {
		boolean isMeet = false;
		Integer meet = 0;
		Tree aux = this;
		// O contador inicialmente é igual a soma dos filhos à esquerda com os filhos à direita mais 1 da raiz
		int cout = 1 + aux.getRoot().getCountLeftNodes() + aux.getRoot().getCountRigthNodes();
		
		// Se o n for menor que 1 ou o n for maior que o contador inicial 
		// (ou seja, maior que a quantidade de elementos no total)
		if ((n < 1) || (cout < n)) {
			meet = -1;
			return meet;
		}
		
		// O contador agora é igual a quantidade dos filhos à esquerda do nó raiz
		cout = aux.getRoot().getCountLeftNodes();
		// Se o contador for maior que 0 e maior ou igual a n, então o n está entre os filhos à esquerda do nó raiz
		if (cout > 0 && cout >= n) {
			// Enquanto o valor não for encontrado
			while (!isMeet) {
				// O contador é igual ao número de filhos à esquerda do nó
				cout = aux.getRoot().getCountLeftNodes();
				// Se esse número for maior que 0 e maior que n, então ele está entre os filhos à esquerda do nó
				if (cout > 0 && cout >= n) {
					// Se o filho a esqueda do nó não for nulo
					if (aux.getLeftTree().getRoot() != null) {
						// Contador é igual a ele menos a quantidade de filhos à esquerda do nó raiz
						cout = cout - aux.getRoot().getCountLeftNodes();
						// O novo nó raíz é igual ao filho à esquerda do atual nó raiz
						aux = aux.getLeftTree();
					}
				}
				// Se esse número não for igual
				else {
					// Soma 1 ao contador, que agora representará o número de filhos à esquerda mais 1 da raíz a qual está
					cout++;
					// Se esse número for igual ao n
					if (cout == n) {
						// O número encontrado será o valor que está no nó raiz da árvore
						meet = aux.getRoot().getValue();
						isMeet = true;
					}
					// Se o número não for igual ao n
					else {
						// O contador será ele (filhos a esquerda mais 1 da raiz) somado com os filhos à direita da raiz
						cout += aux.getRoot().getCountRigthNodes();
						// Se o filho à direita não for nulo
						if (aux.getRigthTree().getRoot() != null) {
							// A nova raíz será o filho à direira da raíz atual
							aux = aux.getRigthTree();
							// Se o contador for igual a n
							if (cout == n) {
								// O número encontrado será o valor que está no nó raiz da árvore
								meet = aux.getRoot().getValue();
								isMeet = true;
							}
						}
					}
				}
			}
		}
		
		// Se ele não entrar no if anterior, o valor do contador será igual a 1 da raíz mais os filhos à sua esquerda  
		cout = aux.getRoot().getCountLeftNodes() + 1;
		// Se esse valor for igual ao n, então o número procurado é a raíz
		if (cout == n) {
			// O número encontrado será o valor que está no nó raiz da árvore
			meet = aux.getRoot().getValue();
			isMeet = true;
		}
		
		// Se o cout anterior, porém, não entrar no if, criamos uma variavel auxiliar para somar o valor dos filhos à esquerda
		// da raíz mais o 1 da própria raíz. Ou seja, atribuímos à essa nova variável o valor de count
		Integer coutLeftRoot = cout;
		// A nova raíz, agora, será o filho à direita da raíz atual
		aux = aux.getRigthTree();
		// Enquanto o número não for encontrado
		while (!isMeet) {
			// O contador será o que foi guardado na coutLeftRoot mais os filhos à esquerda do nó
			cout = coutLeftRoot + aux.getRoot().getCountLeftNodes();
			// Se o valor do contador for maior que o contador até a raíz e maior que o n, então o n estará entre os filhos 
			// à esquerda da raíz
			if (cout > coutLeftRoot && cout >= n) {
				// Se esse filho a esquerda existe
				if (aux.getLeftTree().getRoot() != null) {
					// O contador será ele menos o valor de filhos da raíz
					cout = cout - aux.getRoot().getCountLeftNodes();
					// E a nova raíz será o filho à esquerda da raíz atual
					aux = aux.getLeftTree();
				}
			} 
			// Se o contador não atender ao if anterior
			else {
				// Somando 1 à ele para representar a raíz atual
				cout++;
				// Se esse contador for igual ao n, então a raíz é o valor buscado
				if (cout == n) {
					// O número encontrado será o valor que está no nó raiz da árvore
					meet = aux.getRoot().getValue();
					isMeet = true;
				} 
				// Se não for
				else {
					// O contador será ele mais os filhos à direita do nó
					cout += aux.getRoot().getCountRigthNodes();
					// Se existirem um filho à direita
					if (aux.getRigthTree().getRoot() != null) {
						// Atribui à uma variável x a quantidade dos filhos à esquerda da raíz
						Integer x = aux.getRoot().getCountLeftNodes();
						// A nova raíz será o da árvore à direita da atual
						aux = aux.getRigthTree();
						// Cria-se um novo valor para n que será o antigo n menos a quantidade de filhos à esquerda do nó raíz
						// anterior menos 1 da raíz atual
						n = n - coutLeftRoot - x - 1;
						coutLeftRoot = 0;
					}
				}
			}
		}
		// Retorne o valor encontrado
		return meet;
	}
}
