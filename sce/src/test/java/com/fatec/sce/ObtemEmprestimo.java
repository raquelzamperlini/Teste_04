package com.fatec.sce;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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

	public static Emprestimo comDataDeDevolucaoVencida() {
		Livro livro = ObtemLivro.comDadosValidos();
		Usuario aluno = ObtemUsuario.comDadosValidos();
		ServicoEmprestimo servico = new ServicoEmprestimo();
		Emprestimo umEmprestimo = servico.empresta(livro, aluno);
		DateTime dataAtual = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");

		umEmprestimo.setDataEmprestimo(dataAtual.minusDays(12).toString(fmt));
		umEmprestimo.setDataDevolucao(dataAtual.minusDays(4).toString(fmt));

		return umEmprestimo;
		
	}
	
	public static Emprestimo comDataDeDevolucaoIgualDataIgualDeEmprestimo() {
		
		Livro livro = ObtemLivro.comDadosValidos();
		Usuario usuario = ObtemUsuario.comDadosValidos();
		ServicoEmprestimo servico = new ServicoEmprestimo();
		Emprestimo umEmprestimo = servico.empresta(livro, usuario);
		DateTime dataAtual = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
		
		//umEmprestimo.setDataEmprestimo(dataAtual.toString(fmt));
		umEmprestimo.setDataDevolucao(dataAtual.toString(fmt));
		
		return umEmprestimo;
		
	}
}
