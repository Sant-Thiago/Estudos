package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        clear();
        Scanner scn = new Scanner(System.in);

        System.out.println("-".repeat(14));
        System.out.println("Olá Prof, como vai?");
        System.out.println("-".repeat(14));

        int res = 420;

        Vector vector = new Vector();
        do {
            showOptions();
            res = scn.nextInt();
            switch (res) {
                clear();
                case 0: {
                    scn.close();
                    System.exit(0);
                } case 1: {
                    messageADD(vector); 
                    break;
                } case 2: {
                    vector.show(); 
                    break;
                } default: System.out.println("[ERRO] valor inválido!");
            }
        } while (!isValid(res));
        
        
        vector.add(7);
        vector.show();
        vector.add(9);
        vector.show();
    }

    private static void showOptions() {
        System.out.println("Digite [1] para adicionar");
        System.out.println("Digite [2] para mostrar");
        System.out.println("Digite [0] sair e ver o código");
    }

    private static void messageADD(Vector vector) {
        System.out.println("Digite um número a ser adicionado:: ");
        Scanner scn = new Scanner(System.in);
        int num = scn.nextInt();
        vector.add(num);
    }

    private static boolean isValid(int response) {
        return response == 420; 
    }

    private static void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}