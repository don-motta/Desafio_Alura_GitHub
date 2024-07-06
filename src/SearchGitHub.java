import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.Gson;
import git.alura.desafio.models.GitHubData;
import git.alura.desafio.models.UserData;
import git.alura.desafio.models.exceptions.GitHubSearchErrorException;
import git.alura.desafio.models.exceptions.InvalidPasswordException;

public class SearchGitHub {
    public static void main(String[] args) throws IOException, InterruptedException {
        String inputId;
        String inputPassword;
        boolean validPassword=false;
        String inputUser;
        String json;
        char keepLoop;
        Scanner sc=new Scanner(System.in);
        System.out.println("Olá! Seja bem vindo! Primeiramente crie sua conta.\nDigite seu login:");
        inputId=sc.nextLine();
        while (!validPassword){
            try {
                System.out.println("Digite sua senha: ");
                inputPassword = sc.nextLine();
                UserData user = new UserData(inputId, inputPassword);
                validPassword=true;

                System.out.println("Sua conta foi criada com sucesso!");
                do{
                    System.out.println("Efetue seu login para acesso:\nLogin: ");
                    inputId=sc.nextLine();
                    System.out.println("Senha: ");
                    inputPassword=sc.nextLine();
                    if (!user.verifyAccess(inputId, inputPassword)){
                        System.out.println("Login ou senha invalidos!");
                    }
                }while(!user.verifyAccess(inputId, inputPassword));
                System.out.println("Acesso autorizado!");
                do {
                    try {
                        System.out.println("Digite o usuario que deseja encontrar no GitHub:");
                        inputUser = sc.nextLine();
                        String addressLink = "https://api.github.com/users/" + inputUser;
                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(addressLink))
                                .header("Accept", "application/vnd.github.v3+json")
                                .build();
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 404) {
                            throw new GitHubSearchErrorException("Usuário não encontrado no GitHub.");
                        }
                        json = response.body();
                        Gson gson = new Gson();
                        GitHubData data = gson.fromJson(json, GitHubData.class);
                        System.out.println(data);
                    } catch (GitHubSearchErrorException e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Deseja fazer uma nova buscar? s/n");
                    keepLoop = sc.nextLine().charAt(0);
                }while(keepLoop=='s');
            } catch (InvalidPasswordException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
