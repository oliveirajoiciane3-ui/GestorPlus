package sistema;

import java.sql.Connection;

public class TesteConexao {
    public static void main(String[] args) {
        try {
            Connection conn = Conexao.conectar();
            System.out.println("Conectou com sucesso!");
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}