package ged.utils;

import java.lang.reflect.Array;

/**
 * Gerenciador de treino e teste do Ged
 */
public class TreinoTeste { 

	/**
	 * Implementações relacionadas a manipulação de dados de treino e teste da rede neural.
	 */
	public TreinoTeste() {}

	/**
	 * Verifica se um objeto genérico é uma matriz 2D.
	 * @param obj objeto base.
	 * @return resultado da verificação.
	 */
	private boolean isMatriz(Object obj) {
		return obj.getClass().isArray() && obj.getClass().getComponentType().isArray();
	}

	/**
	 * Divide os dados de entrada a partir de um conjunto de dados base.
	 * @param <T> tipo do dado.
	 * @param mat conjunto de dados.
	 * @param cols quantidade de colunas desejadas para entrada.
	 * @return dados de entrada.
	 */
	@SuppressWarnings("unchecked")
	public <T> T separarDadosEntrada(Object mat, int cols) {
		if (!isMatriz(mat)) {
			throw new IllegalArgumentException("\nO parâmetro não é uma matriz 2D.");
		}

		int linhas = Array.getLength(mat);
		if (linhas == 0) {
			throw new IllegalArgumentException("\nA matriz está vazia.");
		}

		Object primeiraLinha = Array.get(mat, 0);
		int colunasOriginais = Array.getLength(primeiraLinha);

		if (cols < 1) {
			throw new IllegalArgumentException("\nA quantidade de colunas extraídas deve ser >= 1.");
		}
		if (cols > colunasOriginais) {
			throw new IllegalArgumentException("\nO número de colunas " + cols + " fora de alcance.");
		}

		Class<?> tipoInterno = mat.getClass().getComponentType().getComponentType();
		Object entradas = Array.newInstance(tipoInterno, linhas, cols);

		for (int i = 0; i < linhas; i++) {
			Object linhaOriginal = Array.get(mat, i);
			Object novaLinha = Array.get(entradas, i);
			System.arraycopy(linhaOriginal, 0, novaLinha, 0, cols);
		}

		return (T) entradas;
	}

	/**
	 * Divide os dados de saída a partir de um conjunto de dados base.
	 * @param <T> tipo do dado.
	 * @param mat conjunto de dados.
	 * @param cols quantidade de colunas desejadas para saída.
	 * @return dados de saída.
	 */
	@SuppressWarnings("unchecked")
	public <T> T separarDadosSaida(Object mat, int cols) {
		if (!isMatriz(mat)) {
			throw new IllegalArgumentException("\nO parâmetro não é uma matriz 2D.");
		}

		int linhas = Array.getLength(mat);

		if (linhas == 0) {
			throw new IllegalArgumentException("\nA matriz está vazia.");
		}

		Object primeiraLinha = Array.get(mat, 0);
		int totalCols = Array.getLength(primeiraLinha);

		if (cols > totalCols) {
			throw new IllegalArgumentException("\nO número de colunas " + cols + " fora de alcance.");
		}

		if (cols < 1) {
			throw new IllegalArgumentException(
				"\nA quantidade de colunas extraídas deve ser >= 1."
			);
		}

		Class<?> tipo = mat.getClass().getComponentType().getComponentType();
		Object saidas = Array.newInstance(tipo, linhas, cols);
		int idInicial = totalCols - cols;
		for (int i = 0; i < linhas; i++) {
			Object linhaOrig = Array.get(mat, i);
			Object linhaNova  = Array.get(saidas, i);
			System.arraycopy(linhaOrig, idInicial, linhaNova, 0, cols);
		}

		return (T) saidas;
	}

	/**
	 * Separa os dados em um conjunto treino e conjunto de teste.
	 * @param <T> tipo do dado.
	 * @param mat conjunto de dados.
	 * @param tamTeste tamanho dos dados de teste.
	 * @return dados separados.
	 */
	@SuppressWarnings("unchecked")
	public <T> T separarTreinoTeste(Object mat, double tamTeste) {
		if (!isMatriz(mat)) {
			throw new IllegalArgumentException("\nO parâmetro não é uma matriz 2D.");
		}
		if (tamTeste < 0 || tamTeste > 1) {
			throw new IllegalArgumentException("\nO tamanho de teste deve ser entre 0 e 1.");
		}

		Class<?> tipoLinha = mat.getClass().getComponentType();
		Class<?> tipoElm = tipoLinha.getComponentType();

		int linhas = Array.getLength(mat);
		int colunas = Array.getLength(Array.get(mat, 0));

		int linhasTeste = (int) (linhas * tamTeste);
		int linhasTreino = linhas - linhasTeste;

		Object treino = Array.newInstance(tipoElm, linhasTreino, colunas);
		Object teste  = Array.newInstance(tipoElm, linhasTeste, colunas);

		System.arraycopy(mat, 0, treino, 0, linhasTreino);
		System.arraycopy(mat, linhasTreino, teste, 0, linhasTeste);

		Object res = Array.newInstance(tipoElm, 2, 0, 0);

		Array.set(res, 0, treino);
		Array.set(res, 1, teste);

		return (T) res;
	}

}
