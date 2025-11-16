package ged.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ged.Dados;


/**
 * Manipulador de dados do Ged
 */
public class ManipuladorDados {

	/**
	 * Contém as implementações das manipulações no conjunto de dados.
	 */
	public ManipuladorDados() {}
 
	/**
	 * Adiciona uma nova coluna vazia no final do conjunto de dados.
	 * @param dados conjunto de dados.
	 */
	public void addCol(Dados dados) {
		ArrayList<String[]> conteudo = dados.conteudo();

		int nColunas = conteudo.get(0).length;
  
		for (int i = 0; i < conteudo.size(); i++) {
			String[] linhaAtual = conteudo.get(i);
			String[] novaLinha = new String[nColunas + 1];

			System.arraycopy(linhaAtual, 0, novaLinha, 0, nColunas);

			novaLinha[nColunas] = "-";

			conteudo.set(i, novaLinha);
		}

		dados.atribuir(conteudo);
  	}  

	/**
	 * Adiciona uma nova coluna vazia no conjunto de dados.
	 * @param dados conjunto de dados.
	 * @param id índice da coluna nova.
	 */
	public void addCol(Dados dados, int id) {
		if (!dados.simetrico()) {
			throw new IllegalArgumentException("O conteúdo dos dados deve ser simético.");
		}

		ArrayList<String[]> conteudo = dados.conteudo();
		if ((id < 0) || (id >= conteudo.get(0).length)) {
			throw new IllegalArgumentException("O índice fornecido é inválido.");
		}
	
		int nColunas = conteudo.get(0).length;
	
		for (int i = 0; i < conteudo.size(); i++) {
			String[] linhaAtual = conteudo.get(i);
			String[] novaLinha = new String[nColunas + 1];
	
			//copiando valores antigos e deslocando a partir
			//do novo indice
			System.arraycopy(linhaAtual, 0, novaLinha, 0, id);
	
			novaLinha[id] = "";
	
			//copiando valores restantes
			System.arraycopy(linhaAtual, id, novaLinha, id + 1, nColunas - id);
	
			conteudo.set(i, novaLinha);
		}

		dados.atribuir(conteudo);
	}

	/**
	 * Adiciona uma nova linha vazia no final do conjunto de dados.
	 * @param dados conjunto de dados.
	 */
	public void addLin(Dados dados) {
		if (!dados.simetrico()) {
			throw new IllegalArgumentException("O conteúdo dos dados deve ser simético.");
		}

		ArrayList<String[]> conteudo = dados.conteudo();
		int colunas = conteudo.get(0).length;

		String[] novaLinha = new String[colunas];
		for (int i = 0; i < novaLinha.length; i++) {
			novaLinha[i] = "";
		}
		conteudo.add(novaLinha);

		dados.atribuir(conteudo);
	}

	/**
	 * Adiciona uma nova linha vazia no conjunto de dados.
	 * @param dados conjunto de dados.
	 * @param id índice da linha nova.
	 */
	public void addLin(Dados dados, int id) {
		if (!dados.simetrico()) {
			throw new IllegalArgumentException("O conteúdo dos dados deve ser simético.");
		}
		
		ArrayList<String[]> conteudo = dados.conteudo();
		if (id < 0 || id >= conteudo.size()) {
			throw new IllegalArgumentException("O índice fornecido é inválido.");
		}

		int colunas = conteudo.get(0).length;

		String[] novaLinha = new String[colunas];
		for (int i = 0; i < novaLinha.length; i++) {
			novaLinha[i] = "";
		}
		conteudo.add(id, novaLinha);

		dados.atribuir(conteudo);
	}

	/**
	 * Remove uma linha do conjunto de dados.
	 * @param dados conjunto de dados.
	 * @param id índice da linha desejada,
	 */
	public void dropLin(Dados dados, int id) {
		ArrayList<String[]> conteudo = dados.conteudo();

		if (conteudo == null) throw new IllegalArgumentException("O conteúdo dos dados é nulo.");
		if ((id < 0) || (id > conteudo.get(0).length-1)) {
			throw new IllegalArgumentException("Índice fornecido para remoção é inválido");
		}

		conteudo.remove(id);
		dados.atribuir(conteudo);
	}

	/**
	 * Remove uma coluna do conjunto de dados.
	 * @param dados conjunto de dados.
	 * @param id índice da coluna desejada,
	 */
	public void dropCol(Dados dados, int indice) {
		ArrayList<String[]> conteudo = dados.conteudo();

		if (conteudo == null) throw new IllegalArgumentException("O conteúdo dos dados é nulo.");
		if ((indice < 0) || (indice > conteudo.get(0).length-1)) {
			throw new IllegalArgumentException("Índice fornecido para remoção é inválido");
		}

		for (int i = 0; i < conteudo.size(); i++) {
			String[] linha = conteudo.get(i);
			//remover coluna original e substituir pelas novas categorias
			String[] novaLinha = new String[linha.length - 1];

			int contador1 = 0;
			int contador2 = 0;

			while(contador2 < linha.length) {
				if (contador2 == indice) {
					contador2++;
					
				} else {
					novaLinha[contador1] = linha[contador2];
					contador1++;
					contador2++;
				}
			}

			conteudo.set(i, novaLinha);
		}

		dados.atribuir(conteudo);
	}

	/**
	 * Altera um valor no conjunto de dados.
	 * @param dados conjunto de dados.
	 * @param lin índice da linha desejada.
	 * @param col índice da coluna desejada.
	 * @param val novo valor.
	 */
	public void set(Dados dados, int lin, int col, String val) {
		ArrayList<String[]> conteudo = dados.conteudo();

		if (conteudo == null) throw new IllegalArgumentException("O conteúdo dos dados é nulo.");
		if (!dadosSimetricos(dados)) throw new IllegalArgumentException("O conteúdo dos dados deve ser simétrico para a operação.");

		if (lin < 0 || (lin >= conteudo.size())) {
			throw new IllegalArgumentException("Valor do índice de linha é inválido");
		}
		if (col < 0 || (col >= conteudo.get(0).length)) {
			throw new IllegalArgumentException("Valor do índice de coluna é inválido.");
		}

		if (val == null) throw new IllegalArgumentException("O novo valor para substituição é nulo");

		dados.set(val, lin, col);
	}

	/**
	 * Altera um valor de uma coluna do conjunto de dados com base em um valor de busca.
	 * @param dados conjunto de dados.
	 * @param col índice da coluna desejada.
	 * @param val novo valor.
	 */
	public void set(Dados dados, int col, String busca, String val) {
		if (!dadosSimetricos(dados)) {
			throw new IllegalArgumentException("O conteúdo dos dados deve ser simétrico para a operação.");
		}
		if (col < 0 || (col >= dados.conteudo().get(0).length)) {
			throw new IllegalArgumentException("Valor do índice de coluna é inválido.");
		}
		if (busca == null) {
			throw new IllegalArgumentException("O valor de busca é nulo");
		}
		if (val == null) {
			throw new IllegalArgumentException("O novo valor para substituição é nulo");
		}

		dados.set(val, col, busca);
	}

	/**
	 * Troca o conteúdo de dados entre duas colunas.
	 * @param dados conjunto de dados.
	 * @param col1 índice da coluna 1.
	 * @param col2 índice da coluna 2.
	 */
	public void trocarColunas(Dados dados, int col1, int col2) {
		ArrayList<String[]> conteudo = dados.conteudo();

		if (conteudo == null) {
			throw new IllegalArgumentException("O conteúdo dos dados fornicido está nulo.");
		}
		if (conteudo.size() < 2) {
			throw new IllegalArgumentException("É necessário que o conteúdo dos dados tenha pelo menos duas colunas.");
		}
		if (!(dadosSimetricos(dados))) {
			throw new IllegalArgumentException("O conteúdo dos dados deve ser simétrica.");
		}

		if (col1 < 0 || col1 >= conteudo.get(0).length) {
			throw new IllegalArgumentException("O índice fornecido da coluna 1 é inválido.");
		}
		if (col2 < 0 || col2 >= conteudo.get(0).length) {
			throw new IllegalArgumentException("O índice fornecido da coluna 2 é inválido.");
		}
		if (col1 == col2) {
			throw new IllegalArgumentException("Os índices fornecidos devem ser diferentes.");
		}

		for (String[] linha : conteudo) {
			String intermediario = linha[col1];
			linha[col1] = linha[col2];
			linha[col2] = intermediario;
		}

		dados.atribuir(conteudo);
	}

	/**
	 * Remove linha que contenham dados não numéricos.
	 * @param dados conjunto de dados.
	 */
	public void dropNaoNumericos(Dados dados) {
		ArrayList<String[]> conteudo = dados.conteudo();

		int indiceInicial = 0;
		boolean removerLinha = false;
	
		while(indiceInicial < conteudo.size()) {
			removerLinha = false;

			for (int j = 0; j < conteudo.get(indiceInicial).length; j++) {
				//verificar se existe algum valor que não possa ser convertido para número.
				if ((valorInt(conteudo.get(indiceInicial)[j]) == false) || 
					(valorFloat(conteudo.get(indiceInicial)[j]) == false) || 
					(valorDouble(conteudo.get(indiceInicial)[j]) == false)
				) {
					removerLinha = true;
					break;
				}
			}

			if (removerLinha) conteudo.remove(indiceInicial);
			else indiceInicial++; 
		}

		dados.atribuir(conteudo);
	}

	/**
	 * Transforma o conteúdo de uma coluna em one hot encoding.
	 * @param dados conjunto de dados.
	 * @param col índice da coluna desejada.
	 */
	public void categorizar(Dados dados, int col) {
		ArrayList<String[]> conteudo = dados.conteudo();

		if (!dadosSimetricos(dados)) {
			throw new IllegalArgumentException("O conteúdo dos dados deve ser simétrico para categorizar.");
		}
		if ((col < 0) || (col >= conteudo.get(0).length)) {
			throw new IllegalArgumentException("O índice fornecido é inválido.");
		}

		//objeto que recebe apenas valores únicos
		HashSet<String> categoriasUnicas = new HashSet<>();
		for (String[] linha : conteudo) {
			categoriasUnicas.add(linha[col]);
		}

		//definindo indice de cada categoria
		int nCategorias = categoriasUnicas.size();
		String[] listaCategorias = categoriasUnicas.toArray(new String[0]);
		int[] indiceCategorias = new int[nCategorias];
		for (int i = 0; i < indiceCategorias.length; i++) {
			indiceCategorias[i] = i;
		}

		//nova lista para armazenar as linhas atualizadas
		ArrayList<String[]> novaLista = new ArrayList<>();

		//reestruturando a lista com as novas categorias
		for (String[] linha : conteudo) {
			//remover coluna original e substituir pelas novas categorias
			String[] novaLinha = new String[(linha.length - 1) + nCategorias];

			//copiando valores existentes até o índice original
			for (int j = 0; j < col; j++) {
				novaLinha[j] = linha[j];
			}

			//novos valores para as categorias
			for (int j = 0; j < nCategorias; j++) {
				if (listaCategorias[j].equals(linha[col])) novaLinha[col + j] = "1";
				else novaLinha[col + j] = "0";
			}

			//copiando valores existentes após o índice original
			for (int j = col + 1; j < linha.length; j++) {
				novaLinha[j + nCategorias - 1] = linha[j];
			}

			novaLista.add(novaLinha);
		}

		//substituindo a lista original pela nova lista modificada
		conteudo.clear();
		conteudo.addAll(novaLista);
		dados.atribuir(novaLista);
	}

	/**
	 * Contacena por linhas o conteúdo de A e B.
	 * @param a primeiro conjunto de dados.
	 * @param b segundo conjunto de dados.
	 * @return conjunto concatenado.
	 */
	public Dados concatenar(Dados a, Dados b) {
		//simetria
		if (!a.simetrico()) {
			throw new IllegalArgumentException("O conteúdo de A deve ser simétrico.");
		}
		if (!b.simetrico()) {
			throw new IllegalArgumentException("O conteúdo de B deve ser simétrico.");
		}

		//dimensionalidade
		int[] shapeA = a.shape();
		int[] shapeB = b.shape();
		if (shapeA[1] != shapeB[1]) {
			throw new IllegalArgumentException("O contéudo de A e B deve conter a mesma quantidade de colunas.");
		}

		//é seguro trabalhar com os dados agora
		ArrayList<String[]> conteudoA = a.conteudo();
		ArrayList<String[]> conteudoB = b.conteudo();

		ArrayList<String[]> conteudoNovo = new ArrayList<>();
		conteudoNovo.addAll(conteudoA);
		conteudoNovo.addAll(conteudoB);

		Dados dados = new Dados();
		dados.atribuir(conteudoNovo);

		return dados;
	}

	/**
	 * Contacena por colunas o conteúdo de A e B.
	 * @param a primeiro conjunto de dados.
	 * @param b segundo conjunto de dados.
	 * @return conjunto concatenado.
	 */
	public Dados concatenarColunas(Dados a, Dados b) {
		int[] shapeA = a.shape();
		int[] shapeB = b.shape();

		if (shapeA[0] != shapeB[0]) {
			throw new IllegalArgumentException(
				"A quantidade de linhas de A (" + shapeA[0] + 
				") deve ser igual a quantidade de linhas de B (" + shapeB[0] +")"
			);
		}

		int qLinhas = shapeA[0];

		ArrayList<String[]> conteudoA = a.conteudo();
		ArrayList<String[]> conteudoB = b.conteudo();
		
		ArrayList<String[]> conteudoNovo = new ArrayList<>();
		for (int i = 0; i < qLinhas; i++) {
			String[] linhaA = conteudoA.get(i);
			String[] linhaB = conteudoB.get(i);
			String[] novaLinha = new String[linhaA.length + linhaB.length];

			System.arraycopy(linhaA, 0, novaLinha, 0, linhaA.length);
			System.arraycopy(linhaB, 0, novaLinha, linhaA.length, linhaB.length);

			conteudoNovo.add(novaLinha);
		}
		
		Dados dados = new Dados();
		dados.atribuir(conteudoNovo);
		
		return dados;
	}

	/**
	 * Remove linhas com dados duplicados.
	 * @param dados conjunto de dados.
	 */
	public void dropDuplicadas(Dados dados) {
		if (!dados.simetrico()) {
			throw new IllegalArgumentException("O conjunto de dados deve ser simétrico.");
		}

		ArrayList<String[]> conteudo = dados.conteudo();
		Set<String> linhasVistas = new HashSet<>();
		ArrayList<String[]> novoConteudo = new ArrayList<>();

		for (String[] linha : conteudo) {
			
			String linhaComoString = String.join(",", linha);//transforma a linha em uma única string
			if (!linhasVistas.contains(linhaComoString)) {
				novoConteudo.add(linha);
				linhasVistas.add(linhaComoString);
			}
		}

		dados.atribuir(novoConteudo);
	}

	/**
	 * Capitaliza o conteúdo do conjunto de dados.
	 * @param dados conjunto de dados.
	 */
	public void capitalizar(Dados dados) {
		if (dados.vazio()) {
			throw new IllegalArgumentException("O conteúdo dos dados está vazio.");
		}
		if (!dados.simetrico()) {
			throw new IllegalArgumentException("O conteúdo dos dados deve ser simétrico.");
		}

		int nColunas = dados.conteudo().get(0).length;
		for (int i = 0; i < nColunas; i++) {
			dados.capitalizar(i);
		}
	}

	/**
	 * Troca o valor contindo no conjunto de dados baseado em um valor de busca.
	 * @param dados conjunto de dados.
	 * @param busca valor de busca.
	 * @param valor novo valor.
	 */
	public void substituir(Dados dados, String busca, String valor) {
		if (dados.vazio()) {
			throw new IllegalArgumentException("O conteúdo dos dados está vazio.");
		}
		if (!dados.simetrico()) {
			throw new IllegalArgumentException("O conteúdo dos dados deve ser simétrico.");
		}

		int[] shape = dados.shape();
		for (int i = 0; i < shape[1]; i++) {
			dados.substituir(i, busca, valor);
		}
	}
 
	/**
	 * Normaliza o conteúdo do conjunto de dados.
	 * @param dados conjunto de dados.
	 */
	public void normalizar(Dados dados) {
		if (dados.vazio()) {
			throw new IllegalArgumentException("O conteúdo dos dados está vazio.");
		}
		if (!dados.simetrico()) {
			throw new IllegalArgumentException("O conteúdo dos dados deve ser simétrico.");
		}

		int nColunas = dados.shape()[1];

		for (int i = 0; i < nColunas; i++) {
			dados.normalizar(nColunas);
		}
	}

	/**
	 * Filtra o conteúdo do conjunto de dados.
	 * @param dados conjunto de dados.
	 * @param col índice da coluna.
	 * @param busca valor de busca.
	 * @return dados filtrados.
	 */
	public Dados filtrar(Dados dados, int col, String busca) {
		dadosSimetricos(dados);
		if (dados.vazio()) {
			throw new IllegalArgumentException("O conteúdo dos dados está vazio.");
		}

		ArrayList<String[]> conteudo = dados.conteudo();
		if (col < 0 || col > conteudo.get(0).length) {
			throw new IllegalArgumentException("Índice da coluna fornecido é invalido.");
		}

		ArrayList<String[]> conteudoFiltrado = new ArrayList<>();
		//percorre a lista e procura o valor desejado
		for (String[] linha : conteudo) {
			if (linha[col].equals(busca)) {
				conteudoFiltrado.add(linha);
			}
		}

		//salvar numa estrutura separada
		Dados dadosFiltados = new Dados();
		dadosFiltados.atribuir(conteudoFiltrado);
		dadosFiltados.setNome(dados.nome());

		return dadosFiltados;
	}

	/**
	 * Filtra o conteúdo do conjunto de dados baseado em um opeerador.
	 * @param dados conjunto de dados.
	 * @param col índice da coluna.
	 * @param op operador para busca.
	 * @param valor novo valor.
	 * @return dados filtrados.
	 */
	public Dados filtrar(Dados dados, int col, String op, String valor) {
		dadosSimetricos(dados);
		if (dados.vazio()) {
			throw new IllegalArgumentException("O conteúdo dos dados está vazio.");
		}

		ArrayList<String[]> conteudo = dados.conteudo();
		if (col < 0 || col > conteudo.get(0).length) {
			throw new IllegalArgumentException("Índice da coluna fornecido é invalido.");
		}

		ArrayList<String[]> conteudoFiltrado = new ArrayList<>();
		for (String[] linha : conteudo) {
			if (compararValores(linha[col], op, valor)) {
				conteudoFiltrado.add(linha);
			}
		}

		Dados dadosFiltados = new Dados();
		dadosFiltados.atribuir(conteudoFiltrado);
		dadosFiltados.setNome(dados.nome());

		return dadosFiltados;
	}

	/**
	 * Preenche o conteúdo de dados onde existirem valores inválidos ou ausentes.
	 * @param dados conjunto de dados.
	 * @param val novo valor
	 */
	public void preencherAusentes(Dados dados, String val) {
		ArrayList<String[]> conteudo = dados.conteudo();

		for (String[] linha : conteudo) {
			for (int j = 0; j < linha.length; j++) {
				if (linha[j].equals("?") || linha[j].isBlank() || linha[j].isEmpty()) {
					linha[j] = val.trim();
				}
			}
		}

		dados.atribuir(conteudo);
	}

	/**
	 * Preenche o conteúdo de dados onde existirem valores inválidos ou ausentes.
	 * @param dados conjunto de dados.
	 * @param col índice da coluna desejada.
	 * @param val novo valor
	 */
	public void preencherAusentes(Dados dados, int col, String val) {
		ArrayList<String[]> conteudo = dados.conteudo();

		for (String[] linha : conteudo) {
			if (linha[col].equals("?") || linha[col].isBlank() || linha[col].isEmpty()) {
				linha[col] = val.trim();
			}
		}

		dados.atribuir(conteudo);
	}

	/**
	 * Clona um conjunto de dados.
	 * @param dados conjunto de dados.
	 * @return clone dos dados.
	 */
	public Dados clonar(Dados dados) {
		return dados.clone();
	}

	/**
	 * Tenta converter o valor para um numérico do tipo int
	 * @param valor valor que será testado.
	 * @return resultado da verificação, verdadeiro se foi convertido ou false se não
	 */
	private boolean valorInt(String valor) {
		try{
			Integer.parseInt(valor);
			return true;
		
		}catch(Exception e) {
			return false;
		}
	}

	/**
	 * Tenta converter o valor para um numérico do tipo float.
	 * @param valor valor que será testado.
	 * @return resultado da verificação, verdadeiro se foi convertido ou false se não
	 */
	private boolean valorFloat(String valor) {
		try{
			Float.parseFloat(valor);
			return true;
		
		}catch(Exception e) {
			return false;
		}
	}

	/**
	 * Tenta converter o valor para um numérico do tipo double
	 * @param valor valor que será testado.
	 * @return resultado da verificação, verdadeiro se foi convertido ou false se não
	 */
	private boolean valorDouble(String valor) {
		try{
			Double.parseDouble(valor);
			return true;
		
		}catch(Exception e) {
			return false;
		}
	}

	/**
	 * Verifica se a operação entre os dois valores é válida de acordo com a expressão.
	 * v1 (operador) v2.
	 * @param v1 primeiro valor.
	 * @param op operador esperado.
	 * @param v2 segundo valor.
	 * @return resultado da operação, valores que não possam ser convertidos serão desconsiderados.
	 */
	private boolean compararValores(String v1, String op, String v2) {
		double val1;
		double val2;

		try {
			val1 = Double.parseDouble(v1);
			val2 = Double.parseDouble(v2);

		} catch(Exception e) {
			return false;
		}

		switch(op) {
			case ">":  return (val1 > val2);
			case ">=": return (val1 >= val2);
			case "<":  return (val1 < val2);
			case "<=": return (val1 <= val2);
			case "==": return (val1 == val2);
			case "!=": return (val1 != val2);
			default: throw new IllegalArgumentException("Operador não suportado.");
		}

	}

	/**
	 * Verifica se o conjunto de dados é simétrico.
	 * @param dados conjunto de dados.
	 * @return resultado da comparação.
	 */
	public boolean dadosSimetricos(Dados dados) {
		return dados.simetrico();
	}
}