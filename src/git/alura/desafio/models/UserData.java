package git.alura.desafio.models;

import git.alura.desafio.models.exceptions.InvalidPasswordException;

public class UserData {
    private String id;
    private String password;

    public UserData(String id, String password){
        this.id=id;
        if (passwordRequiriments(password) && password.length() >=8 ){
            this.password=password;
        }else {
            throw new InvalidPasswordException("Senha deve conter no mínimo 8 caracteres, com pelo menos uma letra maiúscula, uma letra minúscula, um número e um caracter expecial.");
        }


    }

    public boolean verifyAccess(String inputId, String inputPassword){
        if (inputId.equals(this.id) && inputPassword.equals(this.password)){
            return true;
        }
        return false;
    }

    public boolean passwordRequiriments(String password){
        boolean hasUpperLetters = false;
        boolean hasLowerLetters = false;
        boolean hasDigits = false;
        boolean hasSpecialChars = false;
        for (char c : password.toCharArray()){
            if (Character.isUpperCase(c)){
                hasUpperLetters = true;
            } else if (Character.isLowerCase(c)){
                hasLowerLetters = true;
            } else if (Character.isDigit(c)){
                hasDigits = true;
            } else {
                hasSpecialChars = true;
            }
        }
        if (hasDigits && hasLowerLetters && hasUpperLetters && hasSpecialChars){
            return true;
        }
        return false;
    }

}
