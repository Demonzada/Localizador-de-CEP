import br.com.alura.cepfinder.BaseCep;
import br.com.alura.cepfinder.BaseSalvar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<BaseCep> lista = new ArrayList<>();
        Scanner leitura = new Scanner(System.in);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String busca = "";
        while (!busca.equalsIgnoreCase("sair")){
            System.out.println("Sistema de CEP, digite um cep para buscar");
            busca = leitura.nextLine();

            if (busca.equalsIgnoreCase("sair")) {
                break;
            }

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://viacep.com.br/ws/" + busca.replace("-", "") + "/json/"))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 400) {
                System.out.println("CEP Incorreto, desligando sistemas");
                break;
            }

            String json = response.body();
            System.out.println(json);

            BaseSalvar cepfind = gson.fromJson(json, BaseSalvar.class);
            BaseCep baseCep = new BaseCep(cepfind);

            lista.add(baseCep);

        }

        System.out.println("Busca de CEP feita com sucesso!");
        FileWriter escrita = new FileWriter("CEPS.json");

        escrita.write(gson.toJson(lista));
        escrita.close();
        System.out.println(lista);


    }
}