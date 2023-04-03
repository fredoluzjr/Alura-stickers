import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // String url = "https://imdb-api.com/en/API/TOP250Movies/k_0ojt0yvm";
        // String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";

        //API api = API.NASA;

        // String url = api.getUrl();
        // ExtratorDeConteudo extrator = api.getExtrator();

        String url = "http://localhost:8080/linguagens";
        // String url = "https://alura-linguagens-alfredo.fly.dev/linguagens"; // Configurar fly.io
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        System.out.println(json);

        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            /*String nomeArquivo = "C:/Users/Henrique/Documents/Estudos_Pessoais/DIO/Repository-vscode/alura-stickers/imersao-java-alura-stickers/saida/" + conteudo.getTitulo() + ".png";*/
            String nomeArquivo = "D:/Users/Alfredo/Repository-vscode/Alura-stickers/imersao-java-alura-stickers/alura-stickers/saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}