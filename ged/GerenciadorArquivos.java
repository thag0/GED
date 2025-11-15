package ged;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Gerenciador de arquivos do Ged
 */
class GerenciadorArquivos {
	
	/**
	 * Responsável por manuseio de arquivos externos
	 */
	public GerenciadorArquivos() {}

	/**
	 * Lê o conteúdo de um arquivo {@code .csv}
	 * @param caminho caminho do arquivo desejado, deve conter a extensão .csv.
	 * @return conjunto de dados a partir do arquivo lido.
	 */
	public Dados lerCsv(String caminho) {
		return lerCsv(caminho, ",");
	}

	/**
	 * Lê o conteúdo de um arquivo {@code .csv}
	 * @param caminho caminho do arquivo desejado, deve conter a extensão .csv.
	 * @param sep separador entre os dados, normalmente sendo vírgula.
	 * @return conjunto de dados a partir do arquivo lido.
	 */
	public Dados lerCsv(String caminho, String sep) {
		if (!(new File(caminho).exists())) {
			throw new IllegalArgumentException(
				"\nO caminho \'" + caminho + "\'" + 
				"\n não foi encontrado."
			);
		}

		//extensão não é .csv
		if (new File(caminho).getClass().getName().toLowerCase().endsWith(".csv")) {
			throw new IllegalArgumentException(
				"\nO arquivo especificado não contém as extensão .csv"
			);
		}

		ArrayList<String[]> linhas = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			String linha = "";
			
			while ((linha = br.readLine()) != null) {
				String linhaDados[] = linha.split(sep);

				for (int i = 0; i < linhaDados.length; i++) {
					linhaDados[i] = linhaDados[i].replaceAll(" ", "");
				}
				
				linhas.add(linhaDados);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		Dados dados = new Dados(linhas);

		return dados;
	}

	/**
	 * Lê o conteúdo de um arquivo {@code .txt}
	 * @param caminho caminho do arquivo desejado, deve conter a extensão .txt.
	 * @return conjunto de dados a partir do arquivo lido.
	 */
	public Dados lerTxt(String caminho) {
		if (!(new File(caminho).exists())) {
			throw new IllegalArgumentException(
				"\nO caminho \'" + caminho + "\'" + 
				"\n não foi encontrado."
			);
		}

		//extensão não é .txt
		if (new File(caminho).getClass().getName().toLowerCase().endsWith(".txt")) {
			throw new IllegalArgumentException("O arquivo especificado não contém as extensão .txt");
		}

		ArrayList<String[]> linhas = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			String linha = "";
			
			while ((linha = br.readLine()) != null) {
				String linhaDados[] = linha.trim().split("\\s+");//dividir por espaços em branco
				linhas.add(linhaDados);
			}

		} catch(IOException e) {
			e.printStackTrace();
		}

		Dados dados = new Dados(linhas);

		return dados;
	}

	/**
	 * Salva o conteudo de dados no formato {@code .csv}
	 * @param dados conjunto de dados.
	 * @param caminho caminho desejado.
	 */
	public void paraCsv(Dados dados, String caminho) {
		String separador = ",";

		ArrayList<String[]> conteudo = dados.conteudo();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho + ".csv"))) {			
			for (String[] linha : conteudo) {
				for (int i = 0; i < linha.length; i++) {

					bw.write(linha[i]);
					if (i < linha.length - 1) bw.write(separador);
				}
				bw.newLine();
			}
		} catch(Exception e) {
			System.out.println("\nErro ao exportar o arquivo.");
			e.printStackTrace();
		}
	}

	/**
	 * Salva o conteudo de dados no formato {@code .txt}
	 * @param dados conjunto de dados.
	 * @param caminho caminho desejado.
	 */
	public void paraTxt(Dados dados, String caminho) {
		String separador = " ";

		ArrayList<String[]> conteudo = dados.conteudo();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho + ".txt"))) {			
			for (String[] linha : conteudo) {
				for (int i = 0; i < linha.length; i++) {

					bw.write(linha[i]);
					if (i < linha.length - 1) {
						bw.write(separador);
					}
				}
				bw.newLine();
			}
		} catch(Exception e) {
			System.out.println("\nErro ao exportar o arquivo.");
			e.printStackTrace();
		}
	}

}
