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

	}

	public static void tabelaSimbolos(String str) {

		if (example.containsKey(str)) {
			strFinal.append("<'" + str + "' , " + example.get(str) + " >\n");

		} else {
			example.put(str, "id");
			strFinal.append("< id , " + str + " >");

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
			
			System.out.println(b.length);
			for (i = 0; i < b.length-1; i++) {

				System.out.println("aqui b " + i);

				switch (estado) {
				case 1: // identificador ou palavra reservada
					if (Character.isLetter(b[i])) {

						System.out.println("entrou com: " + b[i]);

						// enquanto caracter ou número
						while (Character.isLetterOrDigit(b[i])) {
							str.append(b[i]);
							System.out.println(b[i]);
							i++;
						}

						i--;
						// System.out.println("Valor lido: "+str.toString());
						tabelaSimbolos(str.toString()); // pesquisa na tabela de símbolos
						str.delete(0, str.length()); // limpa a string auxiliar
						estado = 1;

					} else {
						System.out.println("entrou aqui");
						estado = 2;
						i--;
					} // ativa próximo diagrama

					break;
				case 3:
					if ((int) b[i] == 32) {
						System.out.println("entrou no if 3" + b[i] + "  " + i);
						// diagrama de espaço
						while ((int) b[i] == ' ' || (int) b[i] == 10 || (int) b[i] == 12) {
							i++;
						}
						i--;
						estado = 1;
					}
					break;

				case 2: // atributos relacionais
					
					boolean found = false;
					if (b[i] == ';' || b[i] == '{' || b[i] == '}' || b[i] == '(' || b[i] == ')') {

						tabelaSimbolos(str.append(b[i]).toString());
						str.delete(0, str.length());
						found = true;

					} else 
						if (b[i] == '=') 
						{
							str.append("=");

							i++;
							if(b[i] == '=') str.append("=");
							if(b[i] == '>') str.append(">");
							
							
							
							tabelaSimbolos(str.toString());
							System.out.println(str.toString());
							if(str.length()==1) i--;  // caso de = 
							System.out.println( i);
							str.delete(0, str.length());
							estado = 1;
							found = true;
							break;
						}
					
						if(b[i]=='!')
							if(b[++i]=='=') 
							{
								str.append("!=");
								tabelaSimbolos(str.toString());
								str.delete(0, str.length());
								estado = 1;
								found = true;
								break;
							}   else 
								{
									strFinal.append("Erro léxico em !");
									i--;
								}	
							
						
						if(b[i]=='<') 
							if (b[++i]=='=')
							{
								str.append("<=");
								tabelaSimbolos(str.toString());
								str.delete(0, str.length());
								estado = 1;
								found = true;
								break;
							} else  {	
								str.append('<');
								tabelaSimbolos(str.toString());
								str.delete(0, str.length());
								--i;
								found = true;
							
							}						
						
						if(b[i]=='>' ) if( b[++i]=='=')
						{
							str.append(">=");
							tabelaSimbolos(str.toString());
							str.delete(0, str.length());
							estado = 1;
							found = true;
							break;
						} else   {
							str.append('>');
							tabelaSimbolos(str.toString());
							str.delete(0, str.length());
							--i;
							found = true;
							
						}
						
						

						if(!found) {
							estado++;
							i--;
							
						}
						
					
					
					break;

				default:
					break;
				}

				/*
				 * if ((int) b[i] == 32 || i+1 == b.length) {
				 * 
				 * 
				 * if(str.toString().length() > 0) { tabelaSimbolos(str.toString());
				 * str.delete(0, str.length()); continue; }
				 * 
				 * 
				 * } else { str.append(b[i]);
				 * 
				 * }
				 */

			}

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.getMessage());
		}

		System.out.println(strFinal.toString());

	}

}
