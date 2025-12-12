package org.example;

import org.example.service.AuthService;
import org.example.util.Database;
import org.h2.tools.Server;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Database.init();
        Server webServer = null;
        // Inicia console web do H2
        try {
            webServer = Server.createWebServer("-webAllowOthers").start();
        } catch (Exception e){
            e.printStackTrace();
        }

        AuthService auth = new AuthService();
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("Digite o número correspondente a seguir.");
            System.out.println("1 - Registrar");
            System.out.println("2 - Login");
            System.out.println("0 - Sair");
            String line = sc.nextLine();
            int op = Integer.parseInt(line);

            if (op == 1) {
                System.out.print("Usuário: ");
                String u = sc.nextLine();
                System.out.print("Senha: ");
                String p = sc.nextLine();
                auth.register(u, p);
            } else if (op == 2) {
                System.out.print("Usuário: ");
                String u = sc.nextLine();
                System.out.print("Senha: ");
                String p = sc.nextLine();
                auth.login(u, p);
            } else if (op == 0) {
                if(webServer != null) webServer.stop();
                break;
            }
        }
    }
}