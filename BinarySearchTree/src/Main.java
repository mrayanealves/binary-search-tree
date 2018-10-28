import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A classe que contém a função estática main
 *
 * a Main permite a leitura dos arquivos e execução dos comandos
 * 
 * @author Julia Ferreira
 * @version 2018.10.20
 */ 
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
			// Enquanto o arquivo não chegar ao fim
			while (line != null) {
				// Transforma o valor da linha para tipo inteiro
				Integer value = Integer.valueOf(line);
				// Insere o valor na árvore
				tree.insert(value);
				// Lê a próxima linha
				line = readFile.readLine();
			}
			// Fecha o arquivo
			file.close();
		// Caso dê algum erro na abertura do aqruivo	
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		
		// Leitura e Execução dos Comandos
		try {
			FileReader file = new FileReader(file_commands);
			BufferedReader readFile = new BufferedReader(file);
			String line = readFile.readLine();
			// Enquanto o arquivo não chegar ao fim
			while (line != null) {
				// Divide a string dos comandos que informam valores pelo caractere "-" 
				String[] comando = line.split("-");
				// Compara a string inicial do comando
				switch (comando[0]) {
					// Caso o comando seja "INSIRA"
					case "INSIRA": {
						Integer value = Integer.valueOf(comando[1]);
						// Insere o elemento com o valor passado na árvore
						tree.insert(value);
					} break;
					// Caso o comando seja "REMOVA"
					case "REMOVA": {
						Integer value = Integer.valueOf(comando[1]);
						// Remove o elemento com o valor passado da árvore
						boolean remove = tree.remove(value);
						// Se o elemento foi removido
						if (remove) {
							System.out.println("O elemento de valor " + value + " foi removido com sucesso!");
						}
						// Caso o elemento não tenha sido removido
						else {
							System.out.println("Não foi possível remover o elemento de valor " + value + "!");
						}
					} break;
					// Caso o comando seja "BUSQUE"
					case "BUSQUE": {
						Integer value = Integer.valueOf(comando[1]);
						// Procura o elemento com valor passado na árvore
				    	boolean found = tree.search(value);
				    	// Se o elemento se encontra na árvore
				    	if(found) {
				    			System.out.println("O elemento de valor " + value + " encontra-se na árvore!" );
				    	}
				    	// Caso o elemento não se encontre na árvore	
				    	else {
				    			System.out.println("O elemento de valor " + value + ", pois ele não se encontra na árvore!");
				    	}
					} break;
					// Caso o comando seja "CHEIA"
					case "CHEIA": {
						// Verifica se a árvore é cheia
						boolean isPerfect= tree.isPerfect();
						// Se for cheia
						if(isPerfect) {
							System.out.println("A árvore é cheia!");
						}
						// Se não for
						else {
							System.out.println("A árvore não é cheia!");
						}

					} break;
					// Caso o comando seja "COMPLETA"
					case "COMPLETA": {
						// Verifica se a árvore é completa
						boolean isComplete = tree.isComplete();
						// Se for completa
						if(isComplete) {
							System.out.println("A árvore é completa!");
						}
						// Se não for
						else {
							System.out.println("A árvore não é completa!");
						}

					} break;
					// Caso o comando seja "MEDIANA"
					case "MEDIANA": {
						//Busca o elemento mediano da árvore
						Integer element = tree.median();
						Integer position = tree.position(element);
						// Se a árvore for vazia
						if(element == null) {
							System.out.println("Não foi possível encontrar um elemento mediano, pois a árvore é vazia!");
						}
						// Se não for
						else {
							System.out.println("O elemento mediano é " + element + " e ocupa a posição " + position+"!");
						}

					} break;
					// Caso o comando seja "IMPRIMA"
					case "IMPRIMA": {
						// Realiza o percurso em nível na árvore
						String result = tree.toString();
						// Se  a árvore for vazia 
						if(result.equals("")) {
							System.out.println("Não foi possível realizar o percurso pois a árvore se encontra vazia!");
						}
						// Se não for
						else {
							System.out.println("Percurso em nível: " + result);
						}
					} break;
					// Caso o comando seja "POSICAO"
					case "POSICAO": {
						Integer value = Integer.valueOf(comando[1]);
						// Procura a posição do elemento com o valor passado
						Integer position = tree.position(value);
						// Se não foi possível encontrar a posição
						if (position == -1) {
							System.out.println("Não foi possível encontrar a posição, pois o elemento de valor " + value + " não se encontra na árvore!");
						}
						// Se foi possível 
						else {
							System.out.println("A posição do elemento de valor " + value + " em ordem simétrica é " + position + "!");
						}

					} break;
					// Caso o comando seja "ENESIMO"
					case "ENESIMO": {
						Integer position = Integer.valueOf(comando[1]);
						// Busca o valor do elemento na posição passada
						Integer value = tree.nthElement(position);
						// Se não foi possível encontrar
						if(value == 0) {
							System.out.println("Não foi possível encontrar o elemento nesta posição!");
						}
						// Se foi possível
						else {
							System.out.println("O elemento na posiçao " + position + " da árvore em ordem simétrica tem como valor " + value + "!");
						}
					} break;
				}
			// Lê a próxima linha
			line = readFile.readLine();
			}
			// Fecha o arquivo
			file.close();
		// Caso dê algum erro na abertura do aqruivo	
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
	}
}
