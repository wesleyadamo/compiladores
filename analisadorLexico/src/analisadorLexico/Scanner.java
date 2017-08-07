package analisadorLexico;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Scanner {

	static Map<String, String> example = new HashMap<String, String>();

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

	}

	public static void tabelaSimbolos(String str) {

		System.out.println("aqui:" + str);

		if (example.containsKey(str)) {
			System.out.println(example.get(str));
		} else {
			example.put(str, str);
			System.out.println("Adicionando na tabela: " + str);
		}

	}

	public static void main(String[] args) {
		try {
			
			adicionarTabela();

			File arquivo = new File("teste.txt");
			FileReader fr = new FileReader(arquivo);
			char[] b = new char[(int)arquivo.length()];

			int i = 0;
			while ((fr.read(b)) != -1) {
				System.out.println(b);

			}

			StringBuffer str = new StringBuffer();

			for (i = 0; i < b.length; i++) {
				

				if ((int) b[i] == 32 || i+1 == b.length) {

				
					if(str.toString().length() > 0) {
					tabelaSimbolos(str.toString());
					str.delete(0, str.length());
					continue;
					}
					

				} else {
					str.append(b[i]);
				}
			}


		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.getMessage());
		}
	}

}
