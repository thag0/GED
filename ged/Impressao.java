package ged;

import java.lang.reflect.Array;

public class Impressao {
    
    public Impressao() {}

	/**
	 * Exibe o conteúdo do array.
	 * @param arr array base.
	 */
	public void printArray(Object arr) {
		printArray(arr, "");
	}

	/**
	 * Exibe o conteúdo do array.
	 * @param arr array base.
	 * @param nome nome para impressão.
	 */
	public void printArray(Object arr, String nome) {
        if (!arr.getClass().isArray()) {
			throw new IllegalArgumentException("\nObjeto informado não é um array.");
        }
		
        if (nome.isEmpty()) {
			System.out.println("Array = [");
        } else {
			System.out.println(nome + " = [");
        }
		
		String pad = "  ";
        int tam = Array.getLength(arr);

        if (tam > 0) {
            System.out.print(pad + Array.get(arr, 0));

            for (int i = 1; i < tam; i++) {
                System.out.print(", " + Array.get(arr, i));
            }
        }

        System.out.println();
        System.out.println("]");
	}

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
