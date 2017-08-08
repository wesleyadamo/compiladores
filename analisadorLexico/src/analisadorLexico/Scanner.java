package analisadorLexico;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Scanner {

	static Map<String, String> example = new HashMap<String, String>();
	static StringBuffer strFinal = new StringBuffer();

	public static void adicionarTabela() {
		example.put("int", "int");
		example.put("{", "pr {");
		example.put(";", "pr ;");
		example.put("}", "pr }");
		example.put("(", "pr (");
		example.put(")", "pr )");
		example.put("while", "pr while");
		example.put("for", "pr for");
		example.put("if", "pr if");
		example.put("else", "pr else");
		example.put("=", "operador de atribuição");
		example.put("==", "operador de igualdade");
		example.put(">", "operador maior que");
		example.put("<", "menor que");
		example.put("<=", "operador menor ou igual");
		example.put(">=", "operador maior ou igual");
		example.put("!=", "operador diferente ");
		example.put("+", "operador de soma");
		example.put("-", "operador de subtração");
		example.put("/", "operador de divisão");
		example.put("*", "operador de multiplicação");
		example.put("**", "operador de exponeciação");
		example.put("++", "operador de incremento ");
		example.put("--", "operador de decremento");
	}

	public static void tabelaSimbolos(String str) {

		if (example.containsKey(str)) {
			strFinal.append("<'" + str + "' , " + example.get(str) + " >\n");

		} else {
			example.put(str, "id");
			strFinal.append("< id , " + str + " >\n");

		}

	}

	public static void main(String[] args) {
		try {

			adicionarTabela();

			File arquivo = new File("teste.txt");
			FileReader fr = new FileReader(arquivo);
			char[] b = new char[(int) arquivo.length()];

			int i = 0;
			while ((fr.read(b)) != -1) {
				// System.out.println(b);

			}

			StringBuffer str = new StringBuffer();
			int estado = 1;

			for (i = 0; i < b.length - 1; i++) {


				switch (estado) {
				case 1: // identificador ou palavra reservada
					if (Character.isLetter(b[i])) {


						// enquanto caracter ou número
						while (Character.isLetterOrDigit(b[i])) {
							str.append(b[i]);
							i++;
						}

						i--;
						// System.out.println("Valor lido: "+str.toString());
						tabelaSimbolos(str.toString()); // pesquisa na tabela de símbolos
						str.delete(0, str.length()); // limpa a string auxiliar
						estado = 1;

					} else {
						estado = 2;
						i--;
					} // ativa próximo diagrama

					break;
				case 3:
					
					boolean found = false;
					if(b[i] == '+') {
						str.append('+');
						if(b[++i] == '+') str.append('+');
						else --i;
						
						tabelaSimbolos(str.toString());
						str.delete(0, str.length());
						estado = 1;
						found = true;
						break;
						
							
					}
					
					
					if(b[i] == '-') {
						str.append('-');
						if(b[++i] == '-') str.append('-');
						else --i;
						
						tabelaSimbolos(str.toString());
						str.delete(0, str.length());
						estado = 1;
						found = true;
						break;
						
							
					}
					
					
					if(b[i] == '*') {
						str.append('*');
						if(b[++i] == '*') str.append('*');
						else --i;
						
						tabelaSimbolos(str.toString());
						str.delete(0, str.length());
						estado = 1;
						found = true;
						break;
						
							
					}
						
					if(b[i] == '/') {
						
						
						if(b[++i]=='/') {
							while((int)b[i]!=10) {
								i++;
							    
							} 
						} else {
						
							tabelaSimbolos(str.toString());
							str.delete(0, str.length());
							estado = 1;
							found = true;
							--i;
							break;
						}
						
							
					}
					
					
					
					if(!found) {
						--i;
						estado++;
					}
					
					
					break;
					
				case 4:
						if(Character.isDigit(b[i])) {
							while(Character.isDigit(b[i])) {
								str.append(b[i]);
								i++;
							}
							
							strFinal.append("< num , " + str.toString()+ " >\n");
							str.delete(0, str.length());
						    estado = 1;
						    i--;
						    break;
						}
						
						estado++;
						--i;
						break;
			
				case 5:
					if ((int) b[i] == 32) {
						// diagrama de espaço
						while ((int) b[i] == ' ' || (int) b[i] == 10 || (int) b[i] == 12) {
							i++;
						}
						i--;
						estado = 1;
					}

					break;

				case 2: // atributos relacionais

				    found = false;
					if (b[i] == ';' || b[i] == '{' || b[i] == '}' || b[i] == '(' || b[i] == ')') {

						tabelaSimbolos(str.append(b[i]).toString());
						str.delete(0, str.length());
						found = true;

					} else if (b[i] == '=') {
						str.append("=");

						i++;
						if (b[i] == '=')
							str.append("=");
						if (b[i] == '>')
							str.append(">");

						tabelaSimbolos(str.toString());
						if (str.length() == 1)
							i--; // caso de =
						str.delete(0, str.length());
						estado = 1;
						found = true;
						break;
					}

					if (b[i] == '!')
						if (b[++i] == '=') {
							str.append("!=");
							tabelaSimbolos(str.toString());
							str.delete(0, str.length());
							estado = 1;
							found = true;
							break;
						} else {
							strFinal.append("Erro léxico em !");
							i--;
						}

					if (b[i] == '<')
						if (b[++i] == '=') {
							str.append("<=");
							tabelaSimbolos(str.toString());
							str.delete(0, str.length());
							estado = 1;
							found = true;
							break;
						} else {
							str.append('<');
							tabelaSimbolos(str.toString());
							str.delete(0, str.length());
							--i;
							found = true;

						}

					if (b[i] == '>')
						if (b[++i] == '=') {
							str.append(">=");
							tabelaSimbolos(str.toString());
							str.delete(0, str.length());
							estado = 1;
							found = true;
							break;
						} else {
							str.append('>');
							tabelaSimbolos(str.toString());
							str.delete(0, str.length());
							--i;
							found = true;

						}

					if (!found) {
						estado++;
						i--;

					}

					break;

				default:
					break;
				}

			}

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.getMessage());
		}

		System.out.println(strFinal.toString());

	}

}
