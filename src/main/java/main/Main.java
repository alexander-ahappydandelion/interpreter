package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static interpreter.Interpreter.interpret;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        System.out.println(interpret(lines));
    }
}
