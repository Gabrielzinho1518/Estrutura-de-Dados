package AtividadeGrafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<String> vertices;
    private int[][] matriz;
    private int qtdVertices;
    private int qtdArestas;

    // Construtor do grafo
    public Grafo(List<String> vertices){
        this.vertices = new ArrayList<>(vertices);
        this.qtdVertices = vertices.size();
        this.matriz = new int[qtdVertices][qtdVertices];
        this.qtdArestas = 0;

        // Inicializa a matriz de adjacência com zeros
        for(int i = 0; i < this.qtdVertices; i++){
            for(int j = 0; j < this.qtdVertices; j++){
                this.matriz[i][j] = 0;
            }
        }
    }

    // Método para montar conexões simétricas (bidirecionais) no grafo
    public void montarConexoesSimetricas(List<String> linhas){
        for (String linha : linhas) {
            String[] campos = linha.split("@");
            String nomeOrigem = campos[0];
            String nomeDestino = campos[1];
            int custo = Integer.parseInt(campos[2]);

            int indiceOrigem = this.vertices.indexOf(nomeOrigem);
            int indiceDestino = this.vertices.indexOf(nomeDestino);

            // Adiciona a conexão se os índices são válidos e ainda não existe uma aresta
            if (indiceOrigem != -1 && indiceDestino != -1 && this.matriz[indiceOrigem][indiceDestino] == 0) {
                this.matriz[indiceOrigem][indiceDestino] = custo;
                this.matriz[indiceDestino][indiceOrigem] = custo;
                this.qtdArestas++;
            }
        }
    }

    // Método para ler o arquivo e montar o grafo
    public static void lerArquivo_montarGrafo(String nomeArquivo, List<String> vertices, List<String> linhas){
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while((linha = leitor.readLine()) != null){
                linha = linha.toUpperCase();
                linhas.add(linha);

                String[] campos = linha.split("@");
                if(!vertices.contains(campos[0])){
                    vertices.add(campos[0]);
                }
                if(!vertices.contains(campos[1])){
                    vertices.add(campos[1]);
                }
            }
            vertices.sort(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para exibir a matriz de adjacência
    public void mostrarMatriz(){
        System.out.print("\t");
        for(int i = 0; i < this.qtdVertices; i++){
            System.out.print(i + "\t");
        }
        System.out.println();

        for(int i = 0; i < this.qtdVertices; i++){
            System.out.print(i + ":\t");
            for(int j = 0; j < this.qtdVertices; j++){
                System.out.print(this.matriz[i][j] + "\t");
            }
            System.out.println();
        }

        for(int i = 0; i < this.qtdVertices; i++){
            System.out.println(i + ": " + vertices.get(i));
        }
    }

    // Método para exibir o grafo
    public void mostrarGrafo(){
        for(int i = 0; i < this.qtdVertices; i++){
            System.out.print(this.vertices.get(i) + ":\t");
            for(int j = 0; j < this.qtdVertices; j++){
                if(this.matriz[i][j] != 0){
                    System.out.print(this.vertices.get(j) + "\t");
                }
            }
            System.out.println();
        }
    }


    
    // Método para verificar se existe um caminho entre dois vértices usando busca em profundidade
    public boolean temCaminhoProfundidade(String origem, String destino){
        int indiceOrigem = this.vertices.indexOf(origem);
        int indiceDestino = this.vertices.indexOf(destino);

        if (indiceOrigem == -1 || indiceDestino == -1) return false;

        List<String> visitados = new ArrayList<>();
        visitados.add(origem);

        return testaCaminhoProfundidade(indiceOrigem, indiceDestino, visitados);
    }

    // Método recursivo para busca em profundidade
    private boolean testaCaminhoProfundidade(int no, int destino, List<String> visitados) {
        for(int col = 0; col < this.qtdVertices; col++){
            if(this.matriz[no][col] != 0 && !visitados.contains(this.vertices.get(col))) {
                if(col == destino) return true;
                visitados.add(this.vertices.get(col));
                if (testaCaminhoProfundidade(col, destino, visitados)) {
                    return true;
                }
            }
        }
        return false;
    }
}
