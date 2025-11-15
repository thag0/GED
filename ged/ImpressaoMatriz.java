package ged;

import java.lang.reflect.Array;

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
        if (!matriz.getClass().isArray() || 
            !matriz.getClass().getComponentType().isArray()) {
            throw new IllegalArgumentException("\nObjeto informado não é uma matriz.");
        }

        String pad = "  ";

        if (nome.isEmpty()) {
            System.out.println("Matriz = [");
        } else {
            System.out.println(nome + " = [");
        }

        int linhas = Array.getLength(matriz);

        for (int i = 0; i < linhas; i++) {
            Object linha = Array.get(matriz, i);
            int colunas = Array.getLength(linha);

            System.out.print(pad);

            for (int j = 0; j < colunas; j++) {
                System.out.print(Array.get(linha, j) + "  ");
            }

            System.out.println();
        }

        System.out.println("]");
    }
}
