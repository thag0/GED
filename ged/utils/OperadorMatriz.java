package ged.utils;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * Operador de matrizes do Ged.
 */
public class OperadorMatriz {

	/**
	 * Contém implementações de operações matriciais para dados
	 * int, float e double.
	 */
	public OperadorMatriz() {}

	/**
	 * Verifica se um objeto genérico é uma matriz 2D.
	 * @param obj objeto base.
	 * @return resultado da verificação.
	 */
	private boolean isMatriz(Object obj) {
		return obj.getClass().isArray() && obj.getClass().getComponentType().isArray();
	}
   
    /**
     * Transforma os dados da matriz em um array.
     * @param <T> tipo do dado.
     * @param matriz matriz base.
     * @return dados convertidos.
     */
   	@SuppressWarnings("unchecked")
	public <T> T vetorizar(Object matriz) {
		if (!isMatriz(matriz)) {
			throw new IllegalArgumentException("\nO parâmetro não é uma matriz 2D.");
		}

		int linhas = Array.getLength(matriz);
		int colunas = Array.getLength(Array.get(matriz, 0));
		int tamanho = linhas * colunas;

		Class<?> tipoBase = matriz.getClass().getComponentType().getComponentType();

		Object vetor = Array.newInstance(tipoBase, tamanho);

		int id = 0;
		for (int i = 0; i < linhas; i++) {
			Object linha = Array.get(matriz, i);
			for (int j = 0; j < colunas; j++) {
				Object valor = Array.get(linha, j);
				Array.set(vetor, id++, valor);
			}
		}

		return (T) vetor;
	}

	/**
	 * Embaralha as linhas do conjunto de dados.
	 * @param mat conjunto de dados.
	 */
	public void embaralharDados(Object mat) {
		if (!isMatriz(mat)) {
			throw new IllegalArgumentException("\nO parâmetro não é uma matriz 2D.");
		}

		Random random = new Random();
		int linhas = Array.getLength(mat);

		Object tempLinha = null;
		for (int i = linhas - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);

			Object linhaI = Array.get(mat, i);
			Object linhaJ = Array.get(mat, j);

			if (tempLinha == null) {
				tempLinha = Array.newInstance(
						linhaI.getClass().getComponentType(),
						Array.getLength(linhaI)
				);
			}

			System.arraycopy(linhaI, 0, tempLinha, 0, Array.getLength(linhaI));
			System.arraycopy(linhaJ, 0, linhaI, 0, Array.getLength(linhaJ));
			System.arraycopy(tempLinha, 0, linhaJ, 0, Array.getLength(tempLinha));
		}
	}

    /**
     * Retorna um subconjunto de dados contidos na matriz.
     * @param matriz matriz base.
     * @param inicio índice de início,
     * @param fim índice de fim.
     * @return subconjunto.
     */
   	@SuppressWarnings("unchecked")
	public <T> T obterSubLinhas(Object matriz, int inicio, int fim) {
		if (!isMatriz(matriz)) {
			throw new IllegalArgumentException("\nO parâmetro não é uma matriz 2D.");
		}

		int linhasTotais = Array.getLength(matriz);

		if (inicio < 0 || fim > linhasTotais || inicio >= fim) {
			throw new IllegalArgumentException("Índices de início ou fim inválidos.");
		}

		Class<?> tipoLinha = matriz.getClass().getComponentType();

		int lin = fim - inicio;
		int col = Array.getLength(Array.get(matriz, 0));

		Object subMatriz = Array.newInstance(tipoLinha, lin);

		for (int i = 0; i < lin; i++) {
			Object linhaOriginal = Array.get(matriz, inicio + i);
			Object novaLinha = Array.newInstance(tipoLinha.getComponentType(), col);
			System.arraycopy(linhaOriginal, 0, novaLinha, 0, col);
			Array.set(subMatriz, i, novaLinha);
		}

		return (T) subMatriz;
	}

    /**
     * Retorna um subconjunto de colunas de dados contidos na matriz.
     * @param matriz matriz base.
     * @param inicio índice de início,
     * @param fim índice de fim.
     * @return subconjunto.
     */
   	@SuppressWarnings("unchecked")
	public <T> T obterSubCols(Object matriz, int inicio, int fim) {
		if (!isMatriz(matriz)) {
			throw new IllegalArgumentException("\nO parâmetro não é uma matriz 2D.");
		}

		int linhas = Array.getLength(matriz);
		int colunasOrig = Array.getLength(Array.get(matriz, 0));

		if (inicio < 0 || fim > colunasOrig || inicio >= fim) {
			throw new IllegalArgumentException("Índices inválidos para colunas.");
		}

		Class<?> tipo = matriz.getClass().getComponentType().getComponentType();
		int novasColunas = fim - inicio;
		Object novaMatriz = Array.newInstance(tipo, linhas, novasColunas);
		
		for (int i = 0; i < linhas; i++) {
			Object linhaOrig = Array.get(matriz, i);
			Object linhaNova = Array.get(novaMatriz, i);
			System.arraycopy(linhaOrig, inicio, linhaNova, 0, novasColunas);
		}

		return (T) novaMatriz;
	}
   
	/**
     * Preenche o conteúdo da matriz.
     * @param matriz matriz base.
     * @param val valor desejado.
     */
	public void preencherMatriz(Object matriz, Object valor) {
		if (!isMatriz(matriz)) {
			throw new IllegalArgumentException("\nO parâmetro não é uma matriz 2D.");
		}

		int linhas = Array.getLength(matriz);

		for (int i = 0; i < linhas; i++) {
			Object linha = Array.get(matriz, i);
			int colunas = Array.getLength(linha);
			for (int j = 0; j < colunas; j++) {
				Array.set(linha, j, valor);
			}
		}
	}

   	/**
     * Transforma a matriz no formato identidade.
     * @param matriz matriz base.
     */
	public void matId(Object matriz) {
		if (!isMatriz(matriz)) {
			throw new IllegalArgumentException("\nO parâmetro não é uma matriz 2D.");
		}

		int linhas = Array.getLength(matriz);
		for (int i = 0; i < linhas; i++) {
			Object linha = Array.get(matriz, i);
			int colunas = Array.getLength(linha);

			for (int j = 0; j < colunas; j++) {
				Object valor = (i == j) ? valor1(linha) : valor0(linha);
				Array.set(linha, j, valor);
			}
		}
	}

	/**
	 * Auxiliar na matriz identidade
	 * @param linha linha de dados de uma matriz.
	 * @return valor convertido para o mesmo tipo da linha.
	 */
	private Object valor0(Object linha) {
		Class<?> tipo = linha.getClass().getComponentType();
		if (tipo == int.class)     return 0;
		if (tipo == float.class)   return 0f;
		if (tipo == double.class)  return 0d;
		if (tipo == long.class)    return 0L;
		throw new IllegalArgumentException("\nTipo não suportado: " + tipo);
	}

	/**
	 * Auxiliar na matriz identidade
	 * @param linha linha de dados de uma matriz.
	 * @return valor convertido para o mesmo tipo da linha.
	 */
	private Object valor1(Object linha) {
		Class<?> tipo = linha.getClass().getComponentType();
		if (tipo == int.class)     return 1;
		if (tipo == float.class)   return 1f;
		if (tipo == double.class)  return 1d;
		if (tipo == long.class)    return 1L;
		throw new IllegalArgumentException("\nTipo não suportado: " + tipo);
	}

	/**
	 * Transpôe o conteúdo da matriz.
	 * @param <T> tipo do dado.
	 * @param matriz matriz base.
	 * @return matriz transposta.
	 */
	@SuppressWarnings("unchecked")
	public <T> T matTransp(Object matriz) {
		if (!isMatriz(matriz)) {
			throw new IllegalArgumentException("\nO parâmetro não é uma matriz 2D.");
		}

		int linhas = Array.getLength(matriz);
		int colunas = Array.getLength(Array.get(matriz, 0));

		Class<?> tipoBase = matriz.getClass().getComponentType().getComponentType();

		Object transposta = Array.newInstance(
			tipoBase,
			colunas,
			linhas
		);

		for (int i = 0; i < linhas; i++) {
			Object linha = Array.get(matriz, i);
			for (int j = 0; j < colunas; j++) {
				Object valor = Array.get(linha, j);
				Object linhaT = Array.get(transposta, j);
				Array.set(linhaT, i, valor);
			}
		}

		return (T) transposta;
	}
   
   	//verificação de dimensionalidade pra soma, subtração, hadamard
   
    /**
	 * Verifica se as matrizes possuem a mesma dimensão.
	 * @param a matriz A.
	 * @param b matriz B.
	 * @param c matriz C.
     */
	private void dimensoesIguais(Object a, Object b, Object c) {
		if (!isMatriz(a) || !isMatriz(b) || !isMatriz(c)) {
			throw new IllegalArgumentException("Um dos parâmetros não é uma matriz 2D.");
		}

		int aLin = Array.getLength(a);
		int aCol = Array.getLength(Array.get(a, 0));

		int bLin = Array.getLength(b);
		int bCol = Array.getLength(Array.get(b, 0));

		int rLin = Array.getLength(c);
		int rCol = Array.getLength(Array.get(c, 0));
	
		if (aLin != bLin || aCol != bCol || aLin != rLin || aCol != rCol) {
			throw new IllegalArgumentException(
				"As dimensões de A, B e C não são iguais. " +
				"A = (" + aLin + "x" + aCol + "), " +
				"B = (" + bLin + "x" + bCol + "), " +
				"R = (" + rLin + "x" + rCol + ")."
			);
		}
	}

    /**
	 * Verifica se as matrizes possuem dimensões compatíveis para o produto matricial.
	 * @param a matriz A.
	 * @param b matriz B.
	 * @param c matriz C.
     */
	private void dimensoesMatMul(Object a, Object b, Object dest) {
		if (!isMatriz(a) || !isMatriz(b) || !isMatriz(dest)) {
			throw new IllegalArgumentException("Um dos parâmetros não é uma matriz 2D.");
		}

		int aLin = Array.getLength(a);
		int aCol = Array.getLength(Array.get(a, 0));

		int bLin = Array.getLength(b);
		int bCol = Array.getLength(Array.get(b, 0));

		int rLin = Array.getLength(dest);
		int rCol = Array.getLength(Array.get(dest, 0));

		if (aCol != bLin) {
			throw new IllegalArgumentException("\nDimensões de A e B incompatíveis para multiplicação");
		}
		if (rLin != aLin || rCol != bCol) {
			throw new IllegalArgumentException("\nDimensões de R incompatíveis com o resultado da multiplicação");
		}
	}

	/**
	 * Realiza a operação {@code A + B = R}
	 * @param a matriz A.
	 * @param b matriz B.
	 * @param r matriz resultado.
	 */
	public void matAdd(Object a, Object b, Object r) {
		dimensoesIguais(a, b, r);

		int linhas = Array.getLength(a);
		int colunas = Array.getLength(Array.get(a, 0));

		Class<?> tipo = a.getClass().getComponentType().getComponentType();

		for (int i = 0; i < linhas; i++) {
			Object linhaA = Array.get(a, i);
			Object linhaB = Array.get(b, i);
			Object linhaR = Array.get(r, i);

			for (int j = 0; j < colunas; j++) {
				Object va = Array.get(linhaA, j);
				Object vb = Array.get(linhaB, j);

				Array.set(linhaR, j, somarValores(va, vb, tipo));
			}
		}
	}
	
	/**
	 * Realiza a operação {@code A - B = R}
	 * @param a matriz A.
	 * @param b matriz B.
	 * @param r matriz resultado.
	 */
	public void matSub(Object a, Object b, Object r) {
		dimensoesIguais(a, b, r);

		int linhas = Array.getLength(a);
		int colunas = Array.getLength(Array.get(a, 0));

		Class<?> tipo = a.getClass().getComponentType().getComponentType();

		for (int i = 0; i < linhas; i++) {
			Object linhaA = Array.get(a, i);
			Object linhaB = Array.get(b, i);
			Object linhaR = Array.get(r, i);

			for (int j = 0; j < colunas; j++) {
				Object va = Array.get(linhaA, j);
				Object vb = Array.get(linhaB, j);

				Array.set(linhaR, j, subtrairValores(va, vb, tipo));
			}
		}
	}

	// TODO melhorar a generalização do mamult
   	//multiplicação

	public void matMult(Object a, Object b, Object r) {
		if (a == null || b == null || r == null) {
			throw new IllegalArgumentException("As matrizes fornecidas não podem ser nulas.");
		}

		if ((a instanceof int[][]) && (b instanceof int[][]) && (r instanceof int[][])) {
			int[][] m1 = (int[][]) a;
			int[][] m2 = (int[][]) b;
			int[][] mr = (int[][]) r;
			multiplicarMatrizes(m1, m2, mr);
			
		} else if ((a instanceof float[][]) && (b instanceof float[][]) && (r instanceof float[][])) {
			float[][] m1 = (float[][]) a;
			float[][] m2 = (float[][]) b;
			float[][] mr = (float[][]) r;
			multiplicarMatrizes(m1, m2, mr);
			
		} else if ((a instanceof double[][]) && (b instanceof double[][]) && (r instanceof double[][])) {
			double[][] m1 = (double[][]) a;
			double[][] m2 = (double[][]) b;
			double[][] mr = (double[][]) r;
			multiplicarMatrizes(m1, m2, mr);
			
		} else {
			throw new IllegalArgumentException("Tipos de matrizes fornecidas não suportados.");   
		}
	}

	private void multiplicarMatrizes(int[][] a, int[][] b, int[][] r) {
		dimensoesMatMul(a, b, r);

		int tamInterno = a[0].length;

		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < r[i].length; j++) {

				r[i][j] = 0;
				for (int k = 0; k < tamInterno; k++) {
				r[i][j] += a[i][k] * b[k][j];
				}
			}
		}
	}

	private void multiplicarMatrizes(float[][] a, float[][] b, float[][] r) {
		dimensoesMatMul(a, b, r);

		int tamInterno = a[0].length;

		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < r[i].length; j++) {

				r[i][j] = 0;
				for (int k = 0; k < tamInterno; k++) {
				r[i][j] += a[i][k] * b[k][j];
				}
			}
		}
	}

	private void multiplicarMatrizes(double[][] a, double[][] b, double[][] r) {
		dimensoesMatMul(a, b, r);

		int tamInterno = a[0].length;

		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < r[i].length; j++) {

				r[i][j] = 0;
				for (int k = 0; k < tamInterno; k++) {
				r[i][j] += a[i][k] * b[k][j];
				}
			}
		}
	}

	/**
	 * Multiplica o conteúdo da matriz por um escalar.
	 * @param matriz matriz base.
	 * @param escalar valor multiplicativo.
	 */
	public void matMultEscalar(Object matriz, Number escalar) {
		if (!isMatriz(matriz)) {
			throw new IllegalArgumentException("\nO parâmetro não é uma matriz 2D.");
		}
		
		Class<?> tipo = matriz.getClass().getComponentType().getComponentType();

		int linhas = Array.getLength(matriz);
		for (int i = 0; i < linhas; i++) {
			Object linha = Array.get(matriz, i);
			int colunas = Array.getLength(linha);

			for (int j = 0; j < colunas; j++) {
				Object valor = Array.get(linha, j);
				Object res = multiplicarEscalar((Number) valor, escalar, tipo);
				Array.set(linha, j, res);
			}
		}
	}

	/**
	 * Realiza a multiplicação elemento a elemento entre A e B.
	 * @param a matriz A.
	 * @param b matriz B.
	 * @param r matriz de destino do resultado.
	 */
	public void hadamard(Object a, Object b, Object r) {
		dimensoesIguais(a, b, r);

		Class<?> tipo = a.getClass().getComponentType().getComponentType();

		int linhas = Array.getLength(a);
		for (int i = 0; i < linhas; i++) {
			Object linhaA = Array.get(a, i);
			Object linhaB = Array.get(b, i);
			Object linhaR = Array.get(r, i);

			int colunas = Array.getLength(linhaA);

			for (int j = 0; j < colunas; j++) {
				Number vA = (Number) Array.get(linhaA, j);
				Number vB = (Number) Array.get(linhaB, j);

				Object res = multiplicarValores(vA, vB, tipo);
				Array.set(linhaR, j, res);
			}
		}
	}

	// utilitários

	/**
	 * Utilitário para operação de adição.
	 * @param a primeiro valor.
	 * @param b primeiro valor.
	 * @param tipo tipo de dados dos valores.
	 * @return resultado da soma.
	 */
	private Object somarValores(Object a, Object b, Class<?> tipo) {
		if (tipo == int.class) 		return (int) a + (int) b;
		if (tipo == float.class) 	return (float) a + (float) b;
		if (tipo == double.class) 	return (double) a + (double) b;
		if (tipo == long.class) 	return (long) a + (long) b;
		if (tipo == short.class) 	return (short) ((short) a + (short) b);
		if (tipo == byte.class) 	return (byte) ((byte) a + (byte) b);
		throw new IllegalArgumentException("Tipo não numérico: " + tipo.getSimpleName());
	}

	/**
	 * Utilitário para operação de subtração.
	 * @param a primeiro valor.
	 * @param b primeiro valor.
	 * @param tipo tipo de dados dos valores.
	 * @return resultado da soma.
	 */
	private Object subtrairValores(Object a, Object b, Class<?> tipo) {
		if (tipo == int.class) 		return (int) a - (int) b;
		if (tipo == float.class) 	return (float) a - (float) b;
		if (tipo == double.class) 	return (double) a - (double) b;
		if (tipo == long.class) 	return (long) a - (long) b;
		if (tipo == short.class) 	return (short) ((short) a - (short) b);
		if (tipo == byte.class) 	return (byte) ((byte) a - (byte) b);
		throw new IllegalArgumentException("\nTipo não numérico: " + tipo.getSimpleName());
	}

	/**
	 * Utilitário para operação de multiplicação escalar.
	 * @param a primeiro valor.
	 * @param b primeiro valor.
	 * @param tipo tipo de dados dos valores.
	 * @return resultado da soma.
	 */
	private Object multiplicarEscalar(Number a, Number b, Class<?> tipo) {
		if (tipo == int.class) 		return a.intValue() * b.intValue();
		if (tipo == float.class) 	return a.floatValue() * b.floatValue();
		if (tipo == double.class) 	return a.doubleValue() * b.doubleValue();
		if (tipo == long.class) 	return a.longValue() * b.longValue();
		if (tipo == short.class) 	return (short) (a.shortValue() * b.shortValue());
		if (tipo == byte.class) 	return (byte) (a.byteValue() * b.byteValue());
		throw new IllegalArgumentException("Tipo não suportado: " + tipo.getSimpleName());
	}

	/**
	 * Utilitário para operação de multiplicação hadamard.
	 * @param a primeiro valor.
	 * @param b primeiro valor.
	 * @param tipo tipo de dados dos valores.
	 * @return resultado da multiplicação.
	 */
	private Object multiplicarValores(Number a, Number b, Class<?> tipo) {
		if (tipo == int.class)   return a.intValue() * b.intValue();
		if (tipo == float.class) return a.floatValue() * b.floatValue();
		if (tipo == double.class) return a.doubleValue() * b.doubleValue();
		if (tipo == long.class)  return a.longValue() * b.longValue();
		if (tipo == short.class) return (short) (a.shortValue() * b.shortValue());
		if (tipo == byte.class)  return (byte) (a.byteValue() * b.byteValue());
		
		throw new IllegalArgumentException("Tipo não suportado: " + tipo.getSimpleName());
	}
}