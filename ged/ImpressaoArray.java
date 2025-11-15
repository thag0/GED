package ged;

import java.lang.reflect.Array;

class ImpressaoArray {
	
	/**
	 * Utilitário de impressão.
	 */
	public ImpressaoArray() {}

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

}
