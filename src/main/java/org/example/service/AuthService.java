package org.example.service;

import org.example.repository.UserRepository;
import org.example.user.User;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {

    private final UserRepository repo = new UserRepository();

    public void register(String username, String password){
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        repo.save(new User(username, hashed));
        System.out.println("Usuário cadastrado!");
    }

    public boolean login(String username, String password){
        User u = repo.findByUsername(username);

        if(u == null){
            System.out.println("Usuário não encontrado");
            return false;
        }

        if(BCrypt.checkpw(password, u.getPassword())){
            System.out.println("Login bem-sucedido");
            return true;
        }

        System.out.println("Senha incorreta");
        return false;
    }
}
