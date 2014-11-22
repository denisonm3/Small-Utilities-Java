/*
 * Copyright 2014 Denison.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package socket;

/**
 * Interface de escrita no terminal com cores ANSI
 * @author Denison
 */
public class Print {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BACK_BLACK = "\u001B[40m";
    public static final String ANSI_BACK_RED = "\u001B[41m";
    public static final String ANSI_BACK_GREEN = "\u001B[42m";
    public static final String ANSI_BACK_YELLOW = "\u001B[43m";
    public static final String ANSI_BACK_BLUE = "\u001B[44m";
    public static final String ANSI_BACK_PURPLE = "\u001B[45m";
    public static final String ANSI_BACK_CYAN = "\u001B[46m";
    public static final String ANSI_BACK_WHITE = "\u001B[47m";
    private static String Foreground = "\u001B[39m";
    private static String Background = "\u001B[49m";
    
    public static void ln(String text) {
        System.out.println(text);
    }
    
    public static void ln(String text, String ansiColor) {
        System.out.println(ansiColor + text + ANSI_RESET);
    }
    
    public static void ln(String text, String ansiForeground, String ansiBackground) {
        System.out.println(ansiBackground + ansiForeground + text + ANSI_RESET);
    }
    
    public static void str(String text) {
        System.out.print(text);
    }
    
    public static void str(String text, String ansiColor) {
        System.out.print(ansiColor + text + ANSI_RESET);
    }
    
    public static void str(String text, String ansiForeground, String ansiBackground) {
        System.out.print(ansiBackground + ansiForeground + text + ANSI_RESET);
    }
    
    public static void blue(String text) {
        System.out.print(ANSI_BLUE + text + ANSI_RESET);
    }
    
    public static void red(String text) {
        System.out.print(ANSI_RED + text + ANSI_RESET);
    }
    
    public static void green(String text) {
        System.out.print(ANSI_GREEN + text + ANSI_RESET);
    }
    
    public static void setForeground(String ANSI_COLOR) {
        Foreground = ANSI_COLOR;
    }

    public static void setBackground(String ANSI_BLACK) {
        Background = ANSI_BLACK;
    }
    /**
     * Imprime texto com fundo e cor setados, pulando para próxima linha
     * @param text String a ser apresentada
     */
    public static void textln(String text) {
        System.out.println(Background + Foreground + text + ANSI_RESET);
    }

    /**
     * Imprime texto com fundo e cor setados
     * @param text String a ser apresentada
     */
    public static void text(String text) {
        System.out.print(Background + Foreground + text + ANSI_RESET);
    }
    
}
