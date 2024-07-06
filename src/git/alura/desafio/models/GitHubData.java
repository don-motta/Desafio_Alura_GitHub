package git.alura.desafio.models;

public record GitHubData(String login, String name, String location, String bio) {
    @Override
    public String toString(){
        return "\n***************\nNickname: "+login+"\nNome: "+name+"\nBio: "+bio+"\nLocation: "+location+"\n***************\n";
    }
}
