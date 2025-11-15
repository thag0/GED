package ged;

/**
 * Conversor de dados para o Ged
 */
class ConversorDados {
	
	/**
	 * Objeto responsável converter o conteúdo dos dados em
	 * arrays do tipo int, float e double.
	 */
	public ConversorDados() {}

	/**
	 * Converte o conteúdo de dados para o tipo {@code int}
	 * @param dados conjunto de dados.
	 * @return array 2D contendo o conteúdo convertido.
	 */
	public int[][] dadosParaInt(Dados dados) {
		int[] shape = dados.shape();

		int[][] convertido = new int[shape[0]][shape[1]];

		for (int i = 0; i < shape[0]; i++) {
			for (int j = 0; j < shape[1]; j++) { 
				try {
					convertido[i][j] = Integer.parseInt(dados.getItem(i, j));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	
		return convertido;
	}

	/**
	 * Converte o conteúdo de dados para o tipo {@code float}
	 * @param dados conjunto de dados.
	 * @return array 2D contendo o conteúdo convertido.
	 */	
	public float[][] dadosParaFloat(Dados dados) {
		int[] shape = dados.shape();

		float[][] convertido = new float[shape[0]][shape[1]];

		for (int i = 0; i < shape[0]; i++) {
			for (int j = 0; j < shape[1]; j++) { 
				try {
					convertido[i][j] = Float.parseFloat(dados.getItem(i, j));
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	
		return convertido;
	}

	/**
	 * Converte o conteúdo de dados para o tipo {@code double}
	 * @param dados conjunto de dados.
	 * @return array 2D contendo o conteúdo convertido.
	 */	
	public double[][] dadosParaDouble(Dados dados) {
		int[] shape = dados.shape();

		double[][] convertido = new double[shape[0]][shape[1]];

		for (int i = 0; i < shape[0]; i++) {
			for (int j = 0; j < shape[1]; j++) { 
				try {
					convertido[i][j] = Double.parseDouble(dados.getItem(i, j));
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	
		return convertido;
	}

	/**
	 * Converte o conteúdo de dados para o tipo {@code String}
	 * @param dados conjunto de dados.
	 * @return array 2D contendo o conteúdo convertido.
	 */
	public String[][] dadosParaString(Dados dados) {
		int[] shape = dados.shape();

		String[][] convertido = new String[shape[0]][shape[1]];

		for (int i = 0; i < shape[0]; i++) {
			for (int j = 0; j < shape[1]; j++) { 
				try {
					convertido[i][j] = dados.getItem(i, j);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	
		return convertido;
	}

}
