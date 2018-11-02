package com.fatec.sce;

import com.fatec.sce.model.Emprestimo;
import com.fatec.sce.model.Livro;
import com.fatec.sce.model.Usuario;
import com.fatec.sce.servico.ServicoEmprestimo;

public class ObtemEmprestimo {
	public static Emprestimo comDadosValidos() {
		Livro livro = ObtemLivro.comDadosValidos();
		Usuario aluno = ObtemUsuario.comDadosValidos();
		ServicoEmprestimo servico = new ServicoEmprestimo();
		return servico.empresta(livro, aluno);
	}
}
