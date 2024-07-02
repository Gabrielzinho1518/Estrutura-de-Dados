package AtividadeGrafo;

import java.util.ArrayList;


public class Principal {
    public static void main(String[] args) {
        
        // Lista para armazenar os vértices do grafo
        ArrayList<String> vertices = new ArrayList<>();
        
        // Lista para armazenar as linhas do arquivo que representam as conexões
        ArrayList<String> linhasArquivo = new ArrayList<>();
        
        // Caminho do arquivo que contém os dados do grafo
        String nomeArquivo = "main/java/AtividadeGrafo/grafo/mapaCentral.txt";

        // Método para ler o arquivo e montar o grafo
        Grafo.lerArquivo_montarGrafo(nomeArquivo, vertices, linhasArquivo);

        // Criação do grafo com os vértices lidos
        Grafo g = new Grafo(vertices);

        // Montagem das conexões simétricas no grafo
        g.montarConexoesSimetricas(linhasArquivo);

        // Exibição do grafo
        g.mostrarGrafo();
      
        // Verificação de caminho entre dois vértices
        System.out.println("Tem caminho entre AGUDO e JULIO DE CASTILHOS? " + g.temCaminhoProfundidade("AGUDO", "JULIO DE CASTILHOS"));
    }
}
