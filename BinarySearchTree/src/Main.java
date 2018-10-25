
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
		
		Integer p3 = tree.position(12);
		System.out.println("The 12 position is: " +p3);	
		Integer p5 = tree.position(14);
		System.out.println("The 14 position is: " +p5);
		Integer p6 = tree.position(15);
		System.out.println("The 15 position is: " +p6);	
		Integer p8 = tree.position(18);
		System.out.println("The 18 position is: " +p8);	
		Integer p9 = tree.position(19);
		System.out.println("The 19 position is: " +p9);			
		Integer p10 = tree.position(25);
		System.out.println("The 25 position is: " +p10);	
		Integer p11 = tree.position(29);
		System.out.println("The 29 position is: " +p11);	
		Integer p12 = tree.position(30);
		System.out.println("The 30 position is: " +p12);
		Integer p13 = tree.position(32);
		System.out.println("The 32 position is: " +p13);
		Integer p14 = tree.position(34);
		System.out.println("The 34 position is: " +p14);	
		Integer p15 = tree.position(35);
		System.out.println("The 35 position is: " +p15);
		Integer p16 = tree.position(37);
		System.out.println("The 37 position is: " +p16);
		Integer p17 = tree.position(38);
		System.out.println("The 38 position is: " +p17);	
		Integer p18 = tree.position(39);
		System.out.println("The 39 position is: " +p18);
		Integer p19 = tree.position(40);
		System.out.println("The 40 position is: " +p19);	
		
//		Integer n = tree.nthElement(15);
//		System.out.println(n);
		
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
