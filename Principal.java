package AtividadeGrafo;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        
        // ler o arquivo e montar o grafo
        ArrayList<String> vertices = new ArrayList<>();
        ArrayList<String> linhasArquivo = new ArrayList<>();
        String nomeArquivo = "main/java/AtividadeGrafo/grafo/mapaCentral.txt";

        Grafo.lerArquivo_montarGrafo(nomeArquivo, vertices, linhasArquivo);

        Grafo g = new Grafo(vertices);

        g.montarConexoesSimetricas(linhasArquivo);

    
        g.mostrarGrafo();
      
        System.out.println("Tem caminho entre agudo e Julio de Castilhos? " + g.temCaminhoProfundidade("AGUDO", "JULIO DE CASTILHOS"));
    }
}
