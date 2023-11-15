package com.mycompany.infox.dal;

import java.sql.*;

/**
 *
 * @author Romenik
 */
public class ModuloConexao {

    //método responsável por estabelecer a conexao com o banco
    public static Connection conector() {
        Connection conexao = null;
        // a linha abaixo chama o driver
        String driver = "com.mysql.cj.jdbc.Driver";
        // Armazenando informações referente ao banco
        String url = "jdbc:mysql://localhost:3306/dbinfox?characterEncoding=utf-8";
        String user = "dba";
        String password = "infox123";
        // estabelecendo a conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            //a linha abaixo serve de apoio para esclarecer o erro
           // System.out.println(e);
            return null;
        }
    }
}
