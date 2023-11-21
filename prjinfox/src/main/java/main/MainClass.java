package main;

import com.mycompany.infox.telas.TelaLogin;


/**
 * Classe principal do projeto
 * @author romenik
 */
public class MainClass {

 public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }
}
