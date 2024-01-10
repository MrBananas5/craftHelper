package org.example;
import java.util.Scanner;

public class Main {
    private static Guide guide;
    public static void main(String[] args) {
        guide = new Guide();
        menu();

    }
    private static void menu(){
        Scanner scan = new Scanner(System.in);
        char ch = 0;
        do {
        System.out.println("""
                1: Add Material
                2: Base Query Material
                3: Full Query Material
                4: List Materials
                Q: Quit
                """);
        ch = scan.nextLine().charAt(0);
        switch (ch){
            case '1' -> guide.newMaterial(scan);
            case '2' -> guide.query(scan);
            case '3' -> guide.fullQuery(scan);
            case '4' -> guide.list();
        }
        }while(ch != 'Q');
    }
}