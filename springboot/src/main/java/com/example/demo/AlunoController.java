package com.example.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@Tag(name = "Gerenciamento de Alunos", description = "Endpoints para criar, listar(especifico e geral), modificar e deletar alunos")
public class AlunoController {

    private final GerenciadorAlunos gerenciadorAlunos;

    // Injeção de dependência do Spring
    public AlunoController(GerenciadorAlunos gerenciadorAlunos) {
        this.gerenciadorAlunos = gerenciadorAlunos;
    }

    @PostMapping
    @Operation(summary = "Inserir novo aluno", description = "Adiciona um novo aluno.")
    public String inserirAluno(
            @RequestParam String matricula,
            @RequestParam String nome) {
        gerenciadorAlunos.insercao(matricula, nome);
        return "Aluno inserido com sucesso!";
    }

    @GetMapping
    @Operation(summary = "Listar alunos", description = "Retorna uma lista de todos os alunos.")
    public List<Aluno> listarAlunos() {
        return gerenciadorAlunos.resultado();
    }

    @GetMapping("/{matricula}")
    @Operation(summary = "Buscar aluno específico", description = "Retorna os dados de apenas um aluno usando a sua matrícula.")
    public Aluno buscarAluno(
            @PathVariable String matricula) {

        return gerenciadorAlunos.buscarPorMatricula(matricula);
    }

    @PutMapping
    @Operation(summary = "Modificar Aluno", description = "Altera dados de um aluno existente usando a sua matrícula.")
    public void alterarAluno(
            @RequestParam String matricula,
            @RequestParam String novoNome) {

        gerenciadorAlunos.editar(matricula, novoNome);
    }

    @DeleteMapping
    @Operation(summary = "Deletar Aluno", description = "Remove um aluno do banco de dados utilizando a matrícula.")
    public void deletarAluno(
            @RequestParam String matricula) {

        gerenciadorAlunos.deletar(matricula);
    }
}