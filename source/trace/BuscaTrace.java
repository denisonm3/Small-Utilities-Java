/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abre e extrai informações de um arquivo de trace no formato GWF
 * @author Denison
 */
public class BuscaTrace {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //GWF
            String linha, temp;
            String trace = BuscaTrace.class.getResource("GWA-T-1.gwf").getPath();
            int numTask = 0, numErros = 0;
            int dias, horas, minutos, segundos;
            FileReader reader = new FileReader(trace);
            BufferedReader leitor = new BufferedReader(reader);
            // loop que percorrerá todas as  linhas do arquivo.txt que eu quero ler
            while ((linha = leitor.readLine()) != null && linha.charAt(0) == '#') {
                //Descarta comentários
            }

            String[] campos = linha.split("\t");
            horas = Integer.parseInt(campos[1]);
            do {
                numTask++;
                campos = linha.split("\t");
                temp = campos[1];
                if (campos[10].equals("0")) {
                    numErros++;
                    System.out.print(linha);
                    System.out.println("");
                }
            } while ((linha = leitor.readLine()) != null);
            leitor.close();
            reader.close();

            segundos = Integer.parseInt(temp);
            segundos = segundos - horas;
            minutos = segundos / 60;
            segundos = segundos % 60;
            horas = minutos / 60;
            minutos = minutos % 60;
            dias = horas / 24;
            horas = horas % 24;
            int anos = dias / 365;
            dias = anos % 365;

            System.out.println("Trace: " + trace);
            System.out.println("Número de tarefas: " + numTask);
            System.out.println("Número de erros: " + numErros);
            System.out.println("Tempo de execução: " + anos + " anos " + dias + " dias " + horas + " horas " + minutos + " minutos " + segundos + " segundos ");

        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(BuscaTrace.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
