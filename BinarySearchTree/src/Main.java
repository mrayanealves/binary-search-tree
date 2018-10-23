
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		// Inicialização dos Elementos
		Tree tree = new Tree(null, null, null);

		// Leitura dos Arquivos
		String file_tree = args[0];
		String file_commands = args[1];

		// Leitura e Construção da árvore
		try {
			FileReader file = new FileReader(file_tree);
			BufferedReader readFile = new BufferedReader(file);

			String line = readFile.readLine();

			while (line != null) {

				Integer value = Integer.valueOf(line);
				tree.insert(value);

				line = readFile.readLine();

			}
			file.close();

		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		
		for (int i = 1; i <= 9; i++) {
			Integer n = tree.nthElement(i);
			System.out.println(i +": " +n);
		}

//		// Execução da Ações
//		try {
//			FileReader file = new FileReader(file_commands);
//			BufferedReader readFile = new BufferedReader(file);
//
//			String line = readFile.readLine();
//			
//			while (line != null) {
//
//				String[] comando = line.split("-");
//
//				switch (comando[0]) {
//
//				case "INSIRA": {
//					Integer value = Integer.valueOf(comando[1]);
//					tree.insert(value);
//				}
//					break;
//
//				case "REMOVA": {
//					Integer value = Integer.valueOf(comando[1]);
//					boolean remove = tree.remove(value);
//
//					if (remove) {
//
//						System.out.println("O valor " + value + " foi removido com sucesso!");
//					}
//
//					else {
//						System.out.println("Não foi possível remover o valor " + value + " !");
//					}
//				}
//					break;
//
//				case "BUSCA": {
//					Integer value = Integer.valueOf(comando[1]);
//			    	boolean found = tree.search(value);
//			    		if(found) {
//			    			System.out.println("O valor " + value + " encontra-se na árvore!" );
//			    		}
//			    		else {
//			    			System.out.println("O valor " + value + ", pois ele não se encontra na árvore!");
//			    		}
//				}
//					break;
//
//				case "CHEIA": {
//
//				}
//					break;
//
//				case "COMPLETA": {
//
//				}
//					break;
//
//				case "MEDIANA": {
//
//				}
//					break;
//
//				case "IMPRIMA": {
//
//                    //Só para verificar se a checagem está correta
//                    System.out.println("O numero de filhos da raiz a esquerda é: " + tree.getRoot().getCountLeftNodes() + " e a direita é: " + tree.getRoot().getCountRigthNodes());
//                    System.out.println("O valor da raiz é " + tree.getRoot().getValue());
//
//				}
//					break;
//
//				case "POSICAO": {
//
//				}
//					break;
//
//				case "ENESIMO": {
//
//				}
//					break;
//				}
//
//				line = readFile.readLine();
//			}
//
//			file.close();
//
//		} catch (IOException e) {
//			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
//		}
	}
}
