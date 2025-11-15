package ged;

import java.util.Random;


/**
 * Gerenciador de treino e teste do Ged
 */
class TreinoTeste{ 

   /**
    * Implementações relacionadas a manipulação de dados de treino e teste da rede neural.
    */
   public TreinoTeste() {}

   //embaralhar

   /**
    * Embaralha as linhas do conjunto de dados.
    * @param dados conjunto de dados.
    */
   public void embaralharDados(Object dados) {
      if (dados instanceof int[][]) {
         int[][] d = (int[][]) dados;
         embaralharDados(d);
         dados = (Object) d;
      
      } else if (dados instanceof float[][]) {
         float[][] d = (float[][]) dados;
         embaralharDados(d);
         dados = (Object) d;
      
      } else if (dados instanceof double[][]) {
         double[][] d = (double[][]) dados;
         embaralharDados(d);
         dados = (Object) d;
      
      } else {
         throw new IllegalArgumentException("Tipo de dados não suportado.");
      }
   }

   /**
    * Embaralha as linhas do conjunto de dados.
    * @param dados conjunto de dados.
    */
   private void embaralharDados(int[][] dados) {
      Random random = new Random();
      int linhas = dados.length;

      int[] temp = new int[dados[0].length];
      int j;
      for (int i = linhas - 1; i > 0; i--) {
         j = random.nextInt(i + 1);

         System.arraycopy(dados[i], 0, temp, 0, temp.length);
         System.arraycopy(dados[j], 0, dados[i], 0, dados[i].length);
         System.arraycopy(temp, 0, dados[j], 0, dados[j].length);
      }
   }

   /**
    * Embaralha as linhas do conjunto de dados.
    * @param dados conjunto de dados.
    */
   private void embaralharDados(float[][] dados) {
      Random random = new Random();
      int linhas = dados.length;

      float[] temp = new float[dados[0].length];
      int j;
      for (int i = linhas - 1; i > 0; i--) {
         j = random.nextInt(i + 1);

         System.arraycopy(dados[i], 0, temp, 0, temp.length);
         System.arraycopy(dados[j], 0, dados[i], 0, dados[i].length);
         System.arraycopy(temp, 0, dados[j], 0, dados[j].length);
      }
   }

   /**
    * Embaralha as linhas do conjunto de dados.
    * @param dados conjunto de dados.
    */
   private void embaralharDados(double[][] dados) {
      Random random = new Random();
      int linhas = dados.length;

      double[] temp = new double[dados[0].length];
      int j;
      for (int i = linhas - 1; i > 0; i--) {
         j = random.nextInt(i + 1);

         System.arraycopy(dados[i], 0, temp, 0, temp.length);
         System.arraycopy(dados[j], 0, dados[i], 0, dados[i].length);
         System.arraycopy(temp, 0, dados[j], 0, dados[j].length);
      }
   }

   //separar entrada

   /**
    * Divide os dados de entrada a partir de um conjunto de dados base.
    * @param dados conjunto de dados.
    * @param cols quantidade de colunas desejadas para entrada.
    * @return dados de entrada.
    */
   public Object separarDadosEntrada(Object dados, int cols) {
      if (dados instanceof int[][]) {
         int[][] m = (int[][]) dados;
         return separarDadosEntrada(m, cols);
      
      } else if (dados instanceof float[][]) {
         float[][] m = (float[][]) dados;
         return separarDadosEntrada(m, cols);
      
      } else if (dados instanceof double[][]) {
         double[][] m = (double[][]) dados;
         return separarDadosEntrada(m, cols);
      
      } else {
         throw new IllegalArgumentException(
            "Tipo de dado (" + dados.getClass().getSimpleName() +") não suportado."
         );
      }  
   }

   /**
    * Divide os dados de entrada a partir de um conjunto de dados base.
    * @param dados conjunto de dados.
    * @param cols quantidade de colunas desejadas para entrada.
    * @return dados de entrada.
    */
   private int[][] separarDadosEntrada(int[][] dados, int cols) {
      if (cols > dados[0].length) {
         throw new IllegalArgumentException("O número de colunas fornecido é maior do que o número de colunas disponíveis nos dados.");
      }
      if (cols < 1) {
         throw new IllegalArgumentException("A quantidade de colunas extraídas não pode ser menor que um");
      }

      int[][] dadosEntrada = new int[dados.length][cols];
      for (int i = 0; i < dadosEntrada.length; i++) {
         System.arraycopy(dados[i], 0, dadosEntrada[i], 0, cols);
      }
      
      return dadosEntrada;
   }

   /**
    * Divide os dados de entrada a partir de um conjunto de dados base.
    * @param dados conjunto de dados.
    * @param cols quantidade de colunas desejadas para entrada.
    * @return dados de entrada.
    */
   private float[][] separarDadosEntrada(float[][] dados, int cols) {
      if (cols > dados[0].length) {
         throw new IllegalArgumentException("O número de colunas fornecido é maior do que o número de colunas disponíveis nos dados.");
      }
      if (cols < 1) {
         throw new IllegalArgumentException("A quantidade de colunas extraídas não pode ser menor que um");
      }

      float[][] dadosEntrada = new float[dados.length][cols];
      for (int i = 0; i < dadosEntrada.length; i++) {
         System.arraycopy(dados[i], 0, dadosEntrada[i], 0, cols);
      }
      
      return dadosEntrada;
   }

   /**
    * Divide os dados de entrada a partir de um conjunto de dados base.
    * @param dados conjunto de dados.
    * @param cols quantidade de colunas desejadas para entrada.
    * @return dados de entrada.
    */
   private double[][] separarDadosEntrada(double[][] dados, int cols) {
      if (cols > dados[0].length) {
         throw new IllegalArgumentException("O número de colunas fornecido é maior do que o número de colunas disponíveis nos dados.");
      }
      if (cols < 1) {
         throw new IllegalArgumentException("A quantidade de colunas extraídas não pode ser menor que um");
      }

      double[][] dadosEntrada = new double[dados.length][cols];
      for (int i = 0; i < dadosEntrada.length; i++) {
         System.arraycopy(dados[i], 0, dadosEntrada[i], 0, cols);
      }
      
      return dadosEntrada;
   }

   //separar saida

   /**
    * Divide os dados de saída a partir de um conjunto de dados base.
    * @param dados conjunto de dados.
    * @param cols quantidade de colunas desejadas para saída.
    * @return dados de saída.
    */
   public Object separarDadosSaida(Object dados, int cols) {
      if (dados instanceof int[][]) {
         int[][] m = (int[][]) dados;
         return separarDadosSaida(m, cols);
      
      } else if (dados instanceof float[][]) {
         float[][] m = (float[][]) dados;
         return separarDadosSaida(m, cols);
      
      } else if (dados instanceof double[][]) {
         double[][] m = (double[][]) dados;
         return separarDadosSaida(m, cols);
      
      } else {
         throw new IllegalArgumentException(
            "Tipo de dado (" + dados.getClass().getSimpleName() +") não suportado."
         );
      }  
   }

   /**
    * Divide os dados de saída a partir de um conjunto de dados base.
    * @param dados conjunto de dados.
    * @param cols quantidade de colunas desejadas para saída.
    * @return dados de saída.
    */
   private int[][] separarDadosSaida(int[][] dados, int cols) {
      if (cols > dados[0].length) {
         throw new IllegalArgumentException("O número de colunas fornecido é maior do que o número de colunas disponíveis nos dados.");
      }
      if (cols < 1) {
         throw new IllegalArgumentException("A quantidade de colunas extraídas não pode ser menor que um");
      }

      int[][] dadosSaida = new int[dados.length][cols];
      int indiceInicial = dados[0].length - cols;

      for (int i = 0; i < dados.length; i++) {
         System.arraycopy(dados[i], indiceInicial, dadosSaida[i], 0, cols);
      }

      return dadosSaida;
   }

   /**
    * Divide os dados de saída a partir de um conjunto de dados base.
    * @param dados conjunto de dados.
    * @param cols quantidade de colunas desejadas para saída.
    * @return dados de saída.
    */
   private float[][] separarDadosSaida(float[][] dados, int cols) {
      if (cols > dados[0].length) {
         throw new IllegalArgumentException("O número de colunas fornecido é maior do que o número de colunas disponíveis nos dados.");
      }
      if (cols < 1) {
         throw new IllegalArgumentException("A quantidade de colunas extraídas não pode ser menor que um");
      }

      float[][] dadosSaida = new float[dados.length][cols];
      int indiceInicial = dados[0].length - cols;

      for (int i = 0; i < dados.length; i++) {
         System.arraycopy(dados[i], indiceInicial, dadosSaida[i], 0, cols);
      }

      return dadosSaida;
   }

   /**
    * Divide os dados de saída a partir de um conjunto de dados base.
    * @param dados conjunto de dados.
    * @param cols quantidade de colunas desejadas para saída.
    * @return dados de saída.
    */
   private double[][] separarDadosSaida(double[][] dados, int cols) {
      if (cols > dados[0].length) {
         throw new IllegalArgumentException("O número de colunas fornecido é maior do que o número de colunas disponíveis nos dados.");
      }
      if (cols < 1) {
         throw new IllegalArgumentException("A quantidade de colunas extraídas não pode ser menor que um");
      }

      double[][] dadosSaida = new double[dados.length][cols];
      int indiceInicial = dados[0].length - cols;

      for (int i = 0; i < dados.length; i++) {
         System.arraycopy(dados[i], indiceInicial, dadosSaida[i], 0, cols);
      }

      return dadosSaida;
   }

   //separar treino teste

   /**
    * Separa os dados em um conjunto treino e conjunto de teste. 
    * @param dados conjunto de dados.
    * @param tamTeste tamanho dos dados de teste.
    * @return dados separados.
    */
   public Object separarTreinoTeste(Object dados, double tamTeste) {
      if (dados instanceof int[][]) {
         int[][] m = (int[][]) dados;
         return separarTreinoTesteI(m, tamTeste);
      
      } else if (dados instanceof float[][]) {
         float[][] m = (float[][]) dados;
         return separarTreinoTesteF(m, tamTeste);
      
      } else if (dados instanceof double[][]) {
         double[][] m = (double[][]) dados;
         return separarTreinoTesteD(m, tamTeste);
      
      } else {
         throw new IllegalArgumentException(
            "Tipo de dado (" + dados.getClass().getSimpleName() +") não suportado."
         );
      }  
   }

   /**
    * Separa os dados em um conjunto treino e conjunto de teste. 
    * @param dados conjunto de dados.
    * @param tamTeste tamanho dos dados de teste.
    * @return dados separados.
    */
   private int[][][] separarTreinoTesteI(int[][] dados, double tamTeste) {
      if (dados == null) throw new IllegalArgumentException("O conjunto de dados é nulo.");
      if (tamTeste < 0 || tamTeste > 1) {
         throw new IllegalArgumentException("O tamanho dos dados de teste deve ser maior que zero e menor que um.");
      }

      int linhasTeste = (int) (dados.length*tamTeste);
      int linhasTreino = dados.length - linhasTeste;
      int colunas = dados[0].length;

      int[][] treino = new int[linhasTreino][colunas];
      int[][] teste = new int[linhasTeste][colunas];

      //método nativo, parece ser mais rápido que uma cópia manual
      System.arraycopy(dados, 0, treino, 0, linhasTreino);// copiar treino
      System.arraycopy(dados, linhasTreino, teste, 0, linhasTeste);// copiar teste

      return new int[][][]{treino, teste};
   }

   /**
    * Separa os dados em um conjunto treino e conjunto de teste. 
    * @param dados conjunto de dados.
    * @param tamTeste tamanho dos dados de teste.
    * @return dados separados.
    */
   private float[][][] separarTreinoTesteF(float[][] dados, double tamTeste) {
      if (dados == null) throw new IllegalArgumentException("O conjunto de dados é nulo.");
      if (tamTeste < 0 || tamTeste > 1) {
         throw new IllegalArgumentException("O tamanho dos dados de teste deve ser maior que zero e menor que um.");
      }

      int linhasTeste = (int) (dados.length*tamTeste);
      int linhasTreino = dados.length - linhasTeste;
      int colunas = dados[0].length;

      float[][] treino = new float[linhasTreino][colunas];
      float[][] teste = new float[linhasTeste][colunas];

      //método nativo, parece ser mais rápido que uma cópia manual
      System.arraycopy(dados, 0, treino, 0, linhasTreino);// copiar treino
      System.arraycopy(dados, linhasTreino, teste, 0, linhasTeste);// copiar teste

      return new float[][][]{treino, teste};
   }

   /**
    * Separa os dados em um conjunto treino e conjunto de teste. 
    * @param dados conjunto de dados.
    * @param tamTeste tamanho dos dados de teste.
    * @return dados separados.
    */
   private double[][][] separarTreinoTesteD(double[][] dados, double tamTeste) {
      if (dados == null) throw new IllegalArgumentException("O conjunto de dados é nulo.");
      if (tamTeste < 0 || tamTeste > 1) {
         throw new IllegalArgumentException("O tamanho dos dados de teste deve ser maior que zero e menor que um.");
      }

      int linhasTeste = (int) (dados.length*tamTeste);
      int linhasTreino = dados.length - linhasTeste;
      int colunas = dados[0].length;

      double[][] treino = new double[linhasTreino][colunas];
      double[][] teste = new double[linhasTeste][colunas];

      //método nativo, parece ser mais rápido que uma cópia manual
      System.arraycopy(dados, 0, treino, 0, linhasTreino);// copiar treino
      System.arraycopy(dados, linhasTreino, teste, 0, linhasTeste);// copiar teste

      return new double[][][]{treino, teste};
   }
}
