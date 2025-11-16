package ged.utils;

import java.lang.reflect.Array;

import ged.Dados;

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

	/**
	 * Exibe as linhas iniciais do conjunto de dados.
	 * @param dados conjunto de dados.
	 */
	public void printIncioDados(Dados dados) {
		if (dados.vazio()) {
			dados.print();
			return;
		}
		
		String espacamento = " ".repeat(4);
		int[] shape = dados.shape();
		int linPadrao = (shape[0] < 5) ? shape[0] : 5;

		if (linPadrao < 5) {
			dados.print();
			return;
		}

		System.out.println("Início " + "\"" + dados.nome() + "\" (" + shape[0] + ", " + shape[1] + ")" +" = [");

		//comprimento máximo de cada coluna
		int[] comprimentoMaximo = new int[dados.conteudo().get(0).length];
		for (int i = 0; i < linPadrao; i++) {
			String[] linha = dados.conteudo().get(i);

			for (int j = 0; j < linha.length; j++) {
				int comprimento = linha[j].length();

				if (comprimento > comprimentoMaximo[j]) {
					comprimentoMaximo[j] = comprimento;
				}
			}
		}

		for (int i = 0; i < linPadrao; i++) {
			String[] linha = dados.conteudo().get(i);
			for (int j = 0; j < linha.length; j++) {
				String valor = linha[j];
				int distancia = comprimentoMaximo[j] - (valor.length()-1);
				String espacos = " ".repeat(distancia);
				System.out.print(espacamento + valor + espacos);
			}
			
			System.out.println();
		}

		System.out.println("]");
	}

}
