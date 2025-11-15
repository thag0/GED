package ged;

class ImpressaoMatriz{
	
	/**
	 * Implementações das impressões de matrizes.
	 */
	public ImpressaoMatriz() {}

	/**
	 * Exive o conteúdo da matriz.
	 * @param matriz matriz base.
	 */
	public void printMatiz(Object matriz) {
		printMatriz(matriz, "");
	}
	
	/**
	 * Exibe o conteúdo da martiz.
	 * @param matriz matriz base.
	 * @param nome nome para exibição.
	 */
	public void printMatriz(Object matriz, String nome) {
		String espacamento = "  ";

		if (nome.isEmpty()) {
			System.out.println("Matriz = [");
		}else{
			System.out.println(nome + " = [");
		}

		if (matriz instanceof int[][]) {
			int[][] mat = (int[][]) matriz;

			for (int i = 0; i < mat.length; i++) {
				System.out.print(espacamento);
				for (int j = 0; j < mat[i].length; j++) {
					System.out.print(mat[i][j] + "  ");
				}
				System.out.println();
			}
			System.out.println("]");
		
		}else if (matriz instanceof float[][]) {
			float[][] mat = (float[][]) matriz;

			for (int i = 0; i < mat.length; i++) {
				System.out.print(espacamento);
				for (int j = 0; j < mat[i].length; j++) {
					System.out.print(mat[i][j] + "  ");
				}
				System.out.println();
			}
			System.out.println("]");
		
		}else if (matriz instanceof double[][]) {
			double[][] mat = (double[][]) matriz;

			for (int i = 0; i < mat.length; i++) {
				System.out.print(espacamento);
				for (int j = 0; j < mat[i].length; j++) {
					System.out.print(mat[i][j] + "  ");
				}
				System.out.println();
			}
			System.out.println("]");
		
		}else if (matriz instanceof String[][]) {
			String[][] mat = (String[][]) matriz;

			for (int i = 0; i < mat.length; i++) {
				System.out.print(espacamento);
				for (int j = 0; j < mat[i].length; j++) {
					System.out.print(mat[i][j] + "  ");
				}
				System.out.println();
			}
			System.out.println("]");
		
		} else {
			throw new IllegalArgumentException("Tipo de matriz não suportado.");
		}
	}
}
