import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe que representa uma árvore binária de busca
 *
 * @author Maria Rayane Alves
 * @author Julia Ferreira
 * @version 2018.10.20
 */ 
public class Tree {
    // Raiz da árvore
	private Node root;
    // Sub-árvore a esquerda
	private Tree leftTree;
    // Sub-árvore a direita
	private Tree rigthTree;

    /**
     * Construtor
     *
     * @param root Node que indica a raiz da árvore
     * @param leftTree Tree que indica a sub-árvore a esquerda
     * @param rigthTree Tree que indica a sub-árvore a direita
     */ 
	public Tree(Node root, Tree leftTree, Tree rigthTree) {
		this.root = root;
		this.leftTree = leftTree;
		this.rigthTree = rigthTree;
	}

    /**
     * Getters e Setters
     */ 
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

    /**
	 * Insere um elemento na árvore
	 * 
	 * @param value Integer que representa o valor do elemento a ser inserido
	 * @author Júlia Ferreira
	 */
	public void insert(Integer value) {
		// Se a raiz não for vazia
		if (root != null) {
			// Se o valor for menor que a raiz
			if (value < root.getValue()) {
				// Se a raiz tiver sub-árvore não vazia a esquerda
				if (leftTree.getRoot() != null) {
					// Atualiza contagem de filhos a , incrementando
					root.setCountLeftNodes(root.getCountLeftNodes() + 1);
					// Insere o valor na sub-árvore a esquerda
					leftTree.insert(value);
				} 
				// Se a sub-árvore a esquerda da raiz é vazia
				else {
					Node node = new Node(value, 0, 0);
					Tree leftTree = new Tree(null, null, null);
					Tree rigthTree = new Tree(null, null, null);
					// A árvore a esquerda recebe o value como raiz e terá duas sub-árvores vazias
					this.leftTree = new Tree(node, leftTree, rigthTree);
					// Atualiza contagem de filhos a esquerda, incrementando
					root.setCountLeftNodes(root.getCountLeftNodes() + 1);
					// System.out.println("O valor " + value + " foi inserido com sucesso!");
				}
			} 
			// Se o valor for maior que a raiz
			else if (value > root.getValue()) {
				// Se a raiz tiver sub-árvore não vazia a direita
				if (rigthTree.getRoot() != null) {
					// Atualiza contagem de filhos, incrementando
					root.setCountRigthNodes(root.getCountRigthNodes() + 1);
					// Insere o valor na sub-árvore a direita
					rigthTree.insert(value);
				} 
				// Se a sub-árvore a direita da raiz é vazia 
				else {
					Node node = new Node(value, 0, 0);
					Tree leftTree = new Tree(null, null, null);
					Tree rigthTree = new Tree(null, null, null);
					// A árvore a direita recebe o value como raiz e terá duas sub-árvores vazias
					this.rigthTree = new Tree(node, leftTree, rigthTree);
					// Atualizar contagem de filhos, incrementando
					root.setCountRigthNodes(root.getCountRigthNodes() + 1);
					//System.out.println("O valor " + value + " foi inserido com sucesso!"
				}
			}
			// Impede inserção de valores duplicados
			else if (value == root.getValue()) {
				System.out.println("O valor " + value + " já existe na árvore e não pode ser inserido novamente!");
			}
		} 
		// Caso a árvore seja vazia
		else { 
			// Cria uma raiz com de valor = value e adiciona a ela duas sub-árvores vazias
			this.root = new Node(value, 0, 0);
			this.leftTree = new Tree(null, null, null);
			this.rigthTree = new Tree(null, null, null);
			// System.out.println("O valor " + value + " foi inserido com sucesso e é a raiz!");
		}
	}

	/**
	 * Remove um elemento da árvore
	 * 
	 * @param value Integer que representa o valor do elemento a ser removido
	 * @return remove boolean que indica se a remoção foi realizada ou não
	 * @author Júlia Ferreira
	 */
	public boolean remove(Integer value) {
		// Se a raiz não for vazia
		if (root != null) {
			// Se as sub-árvores não forem nulas
			if (leftTree != null && rigthTree != null) {
				// Se o valor for menor do que a raiz
				if (value < root.getValue()) {
					// Se a sub-árvore a esquerda não for vazia
					if (leftTree.getRoot() != null) {
						// Remove value da sub-árvore a esquerda
						boolean remove = leftTree.remove(value);
						// Se foi possível remover
						if (remove) {
							// Atualiza contagem de filhos a esquerda decrementando
							Integer new_count = root.getCountLeftNodes() - 1;
							root.setCountLeftNodes(new_count);
						}
						return remove;
					} 
					// Se a sub-árvore a esquerda for vazia
					else {
						// Estamos em uma folha e não encontramos o elemento para remover
						return false;
					}
				}
				// Se o valor for maior do que a raiz
				else if (value > root.getValue()) {
					// Se a sub-árvore a direita não for vazia
					if (rigthTree.getRoot() != null) {
						// Remove value da sub-árvore a direita
						boolean remove = rigthTree.remove(value);
						// Se foi possível remover
						if (remove) {
							// Atualiza a contagem de filhos a direita decrementando
							Integer new_count = root.getCountRigthNodes() - 1;
							root.setCountRigthNodes(new_count);
						}
						return remove;
					} 
					// Se a sub-árvore for vazia
					else {
						// Estamos em uma folha e não encontramos o elemento para remover
						return false;
					}					
				} 
				// Se o valor a ser removido for igual a raiz
				else if (value == root.getValue()) {
					// Se a raiz não tem filhos, então é folha
					if (rigthTree.getRoot() == null && leftTree.getRoot() == null) {
						// Torna seus campos nulos
						root = null;
						rigthTree = null;
						leftTree = null;
						// Retorna que foi possível remover
						return true;
					}
					// Se a raiz possui sub-árvore vazia a esquerda
					else if (leftTree.getRoot() == null) {
						// A raiz da sub-árvore a direita passa a ser a raiz da árvore
						root = rigthTree.getRoot();
						// As sub-árvores do filho da direita passam a ser as sub-árvores da raiz
						rigthTree = rigthTree.getRigthTree();
						leftTree = rigthTree.getLeftTree();
						// Retorna que foi possível remover
						return true;
					}
					// Se a raiz possui sub-árvore vazia a direita
					else if (rigthTree.getRoot() == null) {
						// A raiz da sub-árvore a esquerda passa a ser a raiz da árvore
						root = leftTree.getRoot();
						// As sub-árvores do filho a esquerda passam a ser  as sub-árvores da raiz
						rigthTree = leftTree.getRigthTree();
						leftTree = leftTree.getLeftTree();
						// Retorna que foi possível remover
						return true;
					}
					// Se tem duas sub-árvores não vazias
					else {
						// Acessa a sub-árvore a direita
						Tree auxiliar = rigthTree;
						// Enquanto a sub-árvore a esquerda não for vazia
						while (auxiliar.getLeftTree() != null && auxiliar.getLeftTree().getRoot() != null) {
							// Acessa o elemento mais a esquerda
							auxiliar = auxiliar.getLeftTree();
						}
						// O novo valor da raiz será o elemento da direita ou o mais a esquerda da sub-árvore a direita
						Integer new_value = auxiliar.getRoot().getValue();
						root.setValue(new_value);
						// Removemos o valor que agora está na raiz
						boolean remove = rigthTree.remove(new_value);
						return remove;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Busca um elemento na árvore
	 * 
	 * @param value Integer que representa o valor do elemento a ser removido
	 * @return found boolean que indica se o elemento foi encontrado ou não
	 * @author Júlia Ferreira
	 */
	public boolean search(Integer value) {
		boolean found = false;
		// Enquanto a raiz não for vazia e o elemento não tiver sido encontrado
		if (root != null && found == false) {
			// Se a raiz for o valor procurado
			if (root.getValue() == value) {
				// Retorna que está na árvore
				found = true;
			} 
			// Se o valor for menor que a raiz
			else if (value < root.getValue()) {
				// Procura na sub-árvore a esquerda
				found = leftTree.search(value);
			} 
			// Se o valor for maior
			else if (value > root.getValue()) {
				// Procura na sub-árvore a direita
				found = rigthTree.search(value);
			}
		}
		return found;
	}

	/**
	 * Busca o elemento mediano da árvore
	 * Se a ABB possui um número par de elementos, 
	 * retorne o menor dentre os dois elementos medianos
	 * @return element Integer que representa o valor mediano
	 * @author Júlia Ferreira
	 */
	public Integer median() {
		Integer element = null;
		// Soma os filhos a direita com os filhos a esquerda da raiz para obter o 
		// total de posições que existem
		Integer total_nodes = root.getCountLeftNodes() + root.getCountRigthNodes()+1;
		// Se a árvore não for vazia
		if (root!= null) {
			// o elemento mediano será o elemento que ocupa a posicao central do percurso em ordem
			element = this.nthElement((1+total_nodes)/2);
		}
		return element;
	}

	/**
	 * Busca o n-ésimo termo na árvore em ordem simétrica
	 * 
	 * @param n Integer que representa a posição que se deseja buscar
	 * @return meet Integer que representa o número encontado na posição n
	 * @author Maria Rayane Alves
	 */
	public Integer nthElement(Integer n) {
		boolean isMeet = false;
		Integer meet = 0;
		Tree aux = this;
		// O contador inicialmente é igual a soma dos filhos à esquerda com os filhos à
		// direita mais 1 da raiz
		int cout = 1 + aux.getRoot().getCountLeftNodes() + aux.getRoot().getCountRigthNodes();

		// Se o n for menor que 1 ou o n for maior que o contador inicial
		// (ou seja, maior que a quantidade de elementos no total)
		if ((n < 1) || (cout < n)) {
			meet = -1;
			return meet;
		}

		// O contador agora é igual a quantidade dos filhos à esquerda do nó raiz
		cout = aux.getRoot().getCountLeftNodes();
		// Se o contador for maior que 0 e maior ou igual a n, então o n está entre os
		// filhos à esquerda do nó raiz
		if (cout > 0 && cout >= n) {
			// Enquanto o valor não for encontrado
			while (!isMeet) {
				// O contador é igual ao número de filhos à esquerda do nó
				cout = aux.getRoot().getCountLeftNodes();
				// Se esse número for maior que 0 e maior que n, então ele está entre os filhos
				// à esquerda do nó
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
					// Soma 1 ao contador, que agora representará o número de filhos à esquerda mais
					// 1 da raíz a qual está
					cout++;
					// Se esse número for igual ao n
					if (cout == n) {
						// O número encontrado será o valor que está no nó raiz da árvore
						meet = aux.getRoot().getValue();
						isMeet = true;
					}
					// Se o número não for igual ao n
					else {
						// O contador será ele (filhos a esquerda mais 1 da raiz) somado com os filhos à
						// direita da raiz
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

		// Se ele não entrar no if anterior, o valor do contador será igual a 1 da raíz
		// mais os filhos à sua esquerda
		cout = aux.getRoot().getCountLeftNodes() + 1;
		// Se esse valor for igual ao n, então o número procurado é a raíz
		if (cout == n) {
			// O número encontrado será o valor que está no nó raiz da árvore
			meet = aux.getRoot().getValue();
			isMeet = true;
		}

		// Se o cout anterior, porém, não entrar no if, criamos uma variavel auxiliar
		// para somar o valor dos filhos à esquerda
		// da raíz mais o 1 da própria raíz. Ou seja, atribuímos à essa nova variável o
		// valor de count
		Integer coutLeftRoot = cout;
		// A nova raíz, agora, será o filho à direita da raíz atual
		aux = aux.getRigthTree();
		// Enquanto o número não for encontrado
		while (!isMeet) {
			// O contador será o que foi guardado na coutLeftRoot mais os filhos à esquerda
			// do nó
			cout = coutLeftRoot + aux.getRoot().getCountLeftNodes();
			// Se o valor do contador for maior que o contador até a raíz e maior que o n,
			// então o n estará entre os filhos
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
						// Cria-se um novo valor para n que será o antigo n menos a quantidade de filhos
						// à esquerda do nó raíz
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

	/**
	 * Busca a posiçao do elemento na árvore em ordem simétrica
	 * 
	 * @param element Integer que representa o elemento do qual se deseja a posição
	 * @return position Integer que representa a posição encontado do elemento
	 * @author Maria Rayane Alves
	 */
	public Integer position(Integer element) {
		boolean meetPosition = false;
		Integer position = -1;
		Integer cout = 0;
		Integer leftRigthPosition = 0;
		Tree aux = this;
		Integer root = aux.getRoot().getCountLeftNodes() + 1;

		// Se o elemento estiver na árvore
		if (search(element)) {
			// Se o valor na raiz for maior que o elemento, então o elemento estará na
			// árvore à esquerda da raíz
			if (aux.getRoot().getValue() > element) {
				// Enquanto a posição não for encontrada
				while (!meetPosition) {
					// Se o valor da raíz for maior que o elemento
					if (aux.getRoot().getValue() > element) {
						// Se existir uma árvore à esquerda
						if (aux.getLeftTree().getRoot() != null) {
							// Acesse a árvore a esquerda
							aux = aux.getLeftTree();
						}
					}
					// Se o valor da raíz for igual ao elemento
					else if (aux.getRoot().getValue() == element) {
						// Posição será o valor dos filhos à esquerda do nó somado com 1 dele mesmo
						position = aux.getRoot().getCountLeftNodes() + 1;
						;
						// Encontrou a posição
						meetPosition = true;
					}
					// Se o valor da raíz for menor que o elemento
					else if (aux.getRoot().getValue() < element) {
						// O contador será o valor dos filhos à esquerda do nó raíz somado com 1 dele
						// mesmo
						cout = aux.getRoot().getCountLeftNodes() + 1;
						// Se existir uma árvore à direita
						if (aux.getRigthTree().getRoot() != null) {
							// A nova árvore raíz será a árvore à direita da antiga
							aux = aux.getRigthTree();
							// o contador será o valor dele somado com o valor da posição calculada da raíz
							// direita anterior
							// a essa
							cout += leftRigthPosition;
							// Atualizando o valor da posição calculada da anterior com a soma do contador
							// mais 1 da atual
							// árvore (que é uma árvore direita)
							leftRigthPosition = cout++;
							// Se o valor da raiz dessa árvore atual for igual ao elemento
							if (aux.getRoot().getValue() == element) {
								// A posição é o contador e atualiza o valor da avariavel para dizer que
								// encontrou
								position = cout;
								meetPosition = true;
							}
						}
					}
				}
			}
			// Se o valor da raíz for igual ao elemento, a posição será a quatidade de
			// filhos à esquerda mais 1 dela mesma
			else if (aux.getRoot().getValue() == element) {
				position = root;
			}

			// Se o valor da raíz for menor que o elemento, então o elemento está à direita
			// da raíz
			else if (aux.getRoot().getValue() < element) {
				// A nova raíz será a subárvore à direita
				aux = aux.getRigthTree();
				// Enquanto não encontrar a posiçãp
				while (!meetPosition) {
					// Se o valor na raíz for maior que o elemento, esse valor estará à esquerda da
					// raíz
					if (aux.getRoot().getValue() > element) {
						// Se houver subárvore à esquerda
						if (aux.getLeftTree().getRoot() != null) {
							// A nova árvore raíz será a da esquerda da raiz
							aux = aux.getLeftTree();
						}
					}
					// Se o valor na raíz for o elemento
					else if (aux.getRoot().getValue() == element) {
						// O contador será ele somado com os filhos à esquerda mais 1 da raiz atual
						cout += aux.getRoot().getCountLeftNodes() + 1;
						// A posição será o valor do contador mais o valor dos nós até a raíz da árvore
						// original
						position = cout + root;
						// Atualiza que achou a posição
						meetPosition = true;
					}
					// Se o valor na raíz for menor que o elemento, então o valor estará à direita
					// da raíz
					else if (aux.getRoot().getValue() < element) {
						// O contador será os filhos à esquerda desse nó raiz mais 1 dele mesmo
						cout += aux.getRoot().getCountLeftNodes() + 1;
						// Se houver árvore à direita
						if (aux.getRigthTree().getRoot() != null) {
							// A nova raíz será a árvore à direita da atual
							aux = aux.getRigthTree();
						}
					}
				}
			}
		}
		// Retorne a posição
		return position;
	}

	/**
	 * Retorna o valor da raíz de uma árvore
	 * 
	 * @param tree Tree árvore a ter o valor da raíz retornado
	 * @return tree.getRoot().getValue() Integer que representa o valor da raíz
	 * @author Maria Rayane Alves
	 */
	private Integer visit(Tree tree) {
		return tree.getRoot().getValue();
	}

	/**
	 * Retorna um array que representa a árvore em percurso de nível
	 * 
	 * @return result ArrayList<Tree> que representa o resultado do percurso de
	 *         nível da árvore
	 * @author Maria Rayane Alves
	 */
	private ArrayList<Tree> levelOrder() {
		ArrayList<Tree> result = new ArrayList<Tree>();
		Tree aux = this;
		Queue queue = new Queue();
		// Adiciona a raiz à fila
		queue.enqueue(aux);

		// Enquanto a fila não for vazia
		while (!queue.isEmpty()) {
			// Recebe o primeiro da fila
			aux = queue.dequeue();
			// Adiciona ao ArrayList
			result.add(aux);
			// Se a árvore à esquerda não for nula, adicione à fila
			if (aux.getLeftTree().getRoot() != null) {
				queue.enqueue(aux.getLeftTree());
			}
			// Se a árvore à direita não for nula, adicione à fila
			if (aux.getRigthTree().getRoot() != null) {
				queue.enqueue(aux.getRigthTree());
			}
		}
		// Retorne o ArrayList
		return result;
	}

	/**
	 * Retorna uma String que representa a árvore em percurso de nível
	 * 
	 * @return result String Representa o resultado do percurso de nível da árvore
	 * @author Maria Rayane Alves
	 */
	public String toString() {
		String result = "";
		ArrayList<Tree> trees = levelOrder();
		Iterator<Tree> it = trees.iterator();

		// Enquanto houver próximo, adicione ao resultado a visita à árvore
		while (it.hasNext()) {
			Tree tree = (Tree) it.next();
			result += visit(tree) + " ";
		}
		// Retorne o resultado
		return result;
	}
	
	/**
	 * Retorna a profundidade da árvore
     *
	 * @return depth Integer profundidade da árvore
	 * @author Maria Rayane Alves
	 */
	private Integer findADepth() {
		Tree aux = this;
		Integer depth = 0;
		while (aux.getRoot() != null) {
			depth++;
			aux = aux.getLeftTree();
		}
		return depth;
	}
	
	/**
	 * Calcula recursivamente se a árvore é perfeita
     *
	 * @param tree Tree árvore para se calcular se é perfeita ou não
	 * @param depth Integer profundidade da árvore
	 * @param level Integer nível do nó
	 * @return boolean true se ela for perfeita e false se não
	 * @author Maria Rayane Alves
	 */
	private boolean isPerfectRec(Tree tree, Integer depth, int level) { 
	    if (tree.getRoot() == null) {
	    	return true; 
	    }	  

	    if ((tree.getLeftTree().getRoot() == null) && (tree.getRigthTree().getRoot() == null)) {
	    	return (depth == level+1);
	    }
	    
	    if ((tree.getLeftTree().getRoot() == null) || (tree.getRigthTree().getRoot() == null)) {
	    	return false;
	    } 
	  
	    return isPerfectRec(tree.getLeftTree(), depth, level+1) && 
	           isPerfectRec(tree.getRigthTree(), depth, level+1); 
	} 
	  
	/**
	 * Calcula se a árvore é perfeita
     *
	 * @return boolean resultado do método isPerfect
	 * @author Maria Rayane Alves
	 */
	public boolean isPerfect() { 
	   Integer depth = findADepth(); 
	   return isPerfectRec(this, depth, 0); 
	}
	
	/**
	 * Calcula o nível de um determinado valor de nó
     *
	 * @param tree Tree árvore a qual o nó pertence
	 * @param value Integer valor do nó
	 * @param  level Integer nível do nó
	 * @return downlevel Integer nível do nó
	 * @author Maria Rayane Alves
	 */
	private Integer getLevelUtil(Tree tree, Integer value, Integer level) {
		Integer downlevel = 0;
		if (search(value)) {
			if (tree.getRoot().getValue() == null) 
		        return 0; 
		  
		    if (tree.getRoot().getValue()== value) 
		        return level; 
		  
		    if (tree.getLeftTree().getRoot() != null) {
		    	downlevel = getLevelUtil(tree.getLeftTree(), value, level+1); 
			    if (downlevel != 0) 
			        return downlevel; 
			}
		    
		    if (tree.getRigthTree().getRoot() != null) {
		    	downlevel = getLevelUtil(tree.getRigthTree(), value, level+1);
			}
		    
		}
	    return downlevel; 
	} 
	  
	/**
	 * Calcula o nível de um determinado valor de nó
     *
	 * @param value Integer valor do nó a ser calculado
	 * @return getLevelUtil
	 * @author Maria Rayane Alves
	 */
	private Integer getLevel(Integer value)  { 
	    return getLevelUtil(this,value,1); 
	} 
	
	/**
	 * Retorna os nós do ultimo nível  
     *
	 * @return results ArrayList<Integer> array de inteiros que contém os valores dos nós do último nível
	 * @author Maria Rayane Alves
	 */
	private ArrayList<Integer> getNodesOfLastLevel(){
		ArrayList<Tree> levelsOrderTree = this.levelOrder();
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		Integer valueLastNode = levelsOrderTree.get(levelsOrderTree.size() - 1).getRoot().getValue();
		Integer lastNodeLevel = getLevel(valueLastNode);
		
		for (int i = levelsOrderTree.size()-1; i >= 0; i--) {
			Integer levelValue = getLevel(levelsOrderTree.get(i).getRoot().getValue());
			if (levelValue == lastNodeLevel) {
				results.add(levelsOrderTree.get(i).getRoot().getValue());
			}
		}
		return results;
	}
	
	/**
	 * Retorna uma nova árvore sem os nós do último nível 
     *
	 * @return aux Tree árvore sem os nós do último nível 
	 * @author Maria Rayane Alves
	 */
	private Tree removeNodesOfLastLevels() {
		Tree aux = new Tree(null, null, null);
		ArrayList<Tree> thisTree = levelOrder();
		Iterator<Tree> it = thisTree.iterator();
		while (it.hasNext()) {
			Tree tree = (Tree) it.next();
			aux.insert(tree.getRoot().getValue());
		}	
		ArrayList<Integer> nodesOfLastLevels = getNodesOfLastLevel();
		Iterator<Integer> it2 = nodesOfLastLevels.iterator();
		while (it2.hasNext()) {
			Integer value = (Integer) it2.next();
			aux.remove(value);
		}	
		return aux;
	}
	
	/**
	 * Verifica se a árvore é completa ou não
     *
	 * @return isComplete boolean true se for completa ou false se não for
	 * @author Maria Rayane Alves
	 */
	public boolean isComplete() {
		boolean isComplete = false;
		Tree aux = removeNodesOfLastLevels();
		if (aux.isPerfect()) {
			isComplete = true;
		}
		
		return isComplete;
	}
}
