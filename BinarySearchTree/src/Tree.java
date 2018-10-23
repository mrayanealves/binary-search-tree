
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

    //Inserir Elemento
	public void insert(Integer value) {
		if(root !=null) {
			//Se for menor
			if (value < root.getValue()) {
			
		        //Se tiver sub-arvore a esquerda
                 if (leftTree.getRoot() != null) {
		        	
		        	//Atualizar contagem de filhos  	
		        	root.setCountLeftNodes(root.getCountLeftNodes()+1);
		        	
		            leftTree.insert(value);

		      
		        } else  {//Se sub-arvore a esquerda é vazia
		        	
		        	Node node = new Node(value, 0, 0);
		        	Tree leftTree = new Tree(null,null,null);
					Tree rigthTree = new Tree(null, null, null);
		        	
		        	this.leftTree = new Tree(node,leftTree,rigthTree);

                    //Atualizar contagem de filhos
		        	root.setCountLeftNodes(root.getCountLeftNodes()+1);

                    System.out.println("O valor " + value + " foi inserido com sucesso!");

		        }
		        
		    //Se for maior
		    } else if (value > root.getValue()) {
		   
		        //Se tiver sub-árvore não vazia a direita
		        if (rigthTree.getRoot() != null) {
		        	
		        	//Atualizar contagem de filhos		        		        	
		        	root.setCountRigthNodes(root.getCountRigthNodes()+1);
		        
		            rigthTree.insert(value);

		        } else {
		        	
                    
		            //Se a sub-árvore a direita é vazia
		        	Node node = new Node(value, 0, 0);
		        	
		        	Tree leftTree = new Tree(null,null,null);
					Tree rigthTree = new Tree(null, null, null);
		        	
		        	this.rigthTree = new Tree(node,leftTree,rigthTree);

                    //Atualizar contagem de filhos
		        	root.setCountRigthNodes(root.getCountRigthNodes()+1);

		        	System.out.println("O valor " + value + " foi inserido com sucesso!");
		            
		        }
		    } 
			//Impedir inserção de valores duplicados
		    else if(value == root.getValue()) {
		    	System.out.println("O valor " + value + " já existe na árvore e não pode ser inserido novamente!");
		    }

		}
		
		else { //Caso a árvore seja vazia
			this.root = new Node(value, 0, 0);
			this.leftTree = new Tree(null,null,null);
			this.rigthTree = new Tree(null, null, null);


            System.out.println("O valor " + value + " foi inserido com sucesso e é a raiz!");
		            
			
		}
	}

    //Remover Elemento
	public boolean remove(Integer value) {
		
		if(root != null) {

           if(leftTree !=null && rigthTree!=null){
			
                //Se for menor
                if(value < root.getValue()) {
				    
				    if(leftTree.getRoot()!=null) {

                        if( leftTree.getRoot().getValue() == value) {
					    
                            boolean remove = leftTree.remove(value);

                            return remove;
						}				    
				    

				        else {
					        
					        boolean remove = leftTree.remove(value);
					        
					        if(remove) {
						        
						        Integer new_count = root.getCountLeftNodes() - 1;
			                	
			                	root.setCountLeftNodes(new_count);
			                	
					        }
					        
					        return remove;
				        
				        }
                    }

                    else{

                        return false;
                        }
			}
			
            //Se for Maior
			else if(value > root.getValue()) {
				
				
				if(rigthTree.getRoot()!= null){
                        
                   //Vejamos se o filho é o valor
				   if(rigthTree.getRoot().getValue() == value) {
                            
                            boolean remove = rigthTree.remove(value);  

                            return remove;       
                            
				         }

				    else {
					    
				    boolean remove = rigthTree.remove(value);
				    
					    if(remove) {
						    
			            	Integer new_count = root.getCountRigthNodes() - 1;
			            	
			            	root.setCountRigthNodes(new_count);
			            	
					    }
					    
				    return remove;	
				    }
			    }

                else{return false;}
			}
			
			else if(value == root.getValue()){
				
				//Se nao tem filhos
				if(rigthTree.getRoot() == null && leftTree.getRoot() == null) {
					root = null ;
					rigthTree = null;
					leftTree = null;
					
					return true;
					
				}
				
				//Se tem um filho e é o da direita
				else if(leftTree.getRoot() == null) {
					root = rigthTree.getRoot();
					rigthTree = rigthTree.getRigthTree();
					leftTree = rigthTree.getLeftTree();
					
					return true;
				}
				
				//Se tem um filho e é o da esquerda
				else if(rigthTree.getRoot() == null) {
					root = leftTree.getRoot();
					rigthTree= leftTree.getRigthTree();
					leftTree = leftTree.getLeftTree();
					
					return true;
				}
				
				//Se tem dois filhos
				else{
					
					//Vai para a direita
					//Pega o mais a esquerda
					
					Tree auxiliar = rigthTree;
					
					while(auxiliar.getLeftTree() != null && auxiliar.getLeftTree().getRoot() !=null) {
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
			} else if (value < root.getValue()) {
				found = leftTree.search(value);
			} else {
				found = leftTree.search(value);
			}
		}
		return found;
	}
	
	// Retorna o n-ésimo elemento em ordem simétrica
	Integer nthElement(Integer n) {
		Integer meet = 0;
		Tree aux = this;
		int cout = 1 + aux.getRoot().getCountLeftNodes() + aux.getRoot().getCountRigthNodes();
		
		if ((n < 1) || (cout < n)) {
			meet = -1;
		} else {
			cout = 0;
		}
		
		while(meet == 0) {
			cout = aux.getRoot().getCountLeftNodes();
			if (aux.getLeftTree() != null && cout >= n) {
				cout = cout - aux.getRoot().getCountLeftNodes();
				aux = aux.getLeftTree();
			} else {
				cout++;
				if (cout == n) {
					meet = aux.getRoot().getValue();
				} else {
					cout += aux.getRoot().getCountRigthNodes();
					if (aux.getRigthTree() != null && cout <= n) {
						aux = aux.rigthTree;
					}
				}
			}
		}
		return meet;
	}
}
