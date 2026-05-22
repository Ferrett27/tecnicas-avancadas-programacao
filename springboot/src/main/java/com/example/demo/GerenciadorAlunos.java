package com.example.demo;

import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service // Indica ao Spring que esta é uma classe de serviço
public class GerenciadorAlunos {

    String jdbcUrl = "jdbc:mysql://localhost:3306/alunos";
    String user = "root";
    String password = "admin";

    public Connection conexao() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, user, password);
    }

    public void insercao(String matriculav, String nomev) {
        // Usando try-with-resources para garantir que a conexão será fechada
        try (Connection connection = conexao();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO alunos (matricula, nome) VALUES (?, ?)")) {

            preparedStatement.setString(1, matriculav);
            preparedStatement.setString(2, nomev);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir aluno", e);
        }
    }

    public List<Aluno> resultado() {
        List<Aluno> listaAlunos = new ArrayList<>();

        try (Connection connection = conexao();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT matricula, nome FROM alunos")) {

            // Preenche a lista com os resultados do banco
            while (rs.next()) {
                listaAlunos.add(new Aluno(rs.getString("matricula"), rs.getString("nome")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar alunos", e);
        }

        return listaAlunos; // Retorna os dados ao invés de imprimir
    }

    public Aluno buscarPorMatricula(String matricula) {
        Aluno alunoEncontrado = null;

        try (Connection connection = conexao();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT matricula, nome FROM alunos WHERE matricula = ?")) {

            preparedStatement.setString(1, matricula);

            try (ResultSet resultado = preparedStatement.executeQuery()) {
                if (resultado.next()) {
                    alunoEncontrado = new Aluno(resultado.getString("matricula"), resultado.getString("nome"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar o aluno por matrícula", e);
        }

        return alunoEncontrado; // Retorna o aluno (ou null se não encontrar)
    }

    public void editar(String matricula, String novoNome) {
        // Usando try-with-resources para garantir que a conexão será fechada
        try (Connection connection = conexao();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE alunos SET nome = ? WHERE matricula = ?")) {

            // A ordem aqui é importante: primeiro o nome (SET), depois a matrícula (WHERE)
            preparedStatement.setString(1, novoNome);
            preparedStatement.setString(2, matricula);

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas == 0) {
                System.out.println("Nenhum aluno encontrado com a matrícula: " + matricula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao editar aluno", e);
        }
    }

    public void deletar(String matricula) {
        try (Connection connection = conexao();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM alunos WHERE matricula = ?")) {

            preparedStatement.setString(1, matricula);

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas == 0) {
                System.out.println("Aviso: Nenhum aluno encontrado com a matrícula " + matricula + " para deletar.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar aluno", e);
        }
    }
}