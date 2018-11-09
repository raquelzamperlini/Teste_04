package com.fatec.sce.servico;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import com.fatec.sce.model.Emprestimo;
import com.fatec.sce.model.Livro;
import com.fatec.sce.model.Usuario;

public class ServicoEmprestimo {
	public Emprestimo empresta(Livro livro, Usuario usuario) {
		
		if (livro == null | usuario == null) {
			throw new RuntimeException("Dados inválidos.");
		} else {
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setLivro(livro);
			emprestimo.setUsuario(usuario);
			// data do emprestimo - data atual do sistema
			DateTime dataAtual = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
			emprestimo.setDataEmprestimo(dataAtual.toString(fmt));
			// prazo de devolucao 8 dias
			DateTime dataDevolucao = fmt.parseDateTime(emprestimo.getDataEmprestimo());
			emprestimo.setDataDevolucao(dataDevolucao.plusDays(8).toString(fmt));
			
			return emprestimo;
			
		}
	}

	/**
	 * Objetivo - verifica se a devolucao esta atrasada
	 *
	 * @param umEmprestimo
	 * @return int < 0 se estiver atrasado e > 0 se estive no prazo
	 */
	public int devolucao(Emprestimo umEmprestimo) {
		DateTime dataAtual = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
		DateTime dataDevolucao = fmt.parseDateTime(umEmprestimo.getDataDevolucao());
		int dias = Days.daysBetween(dataAtual, dataDevolucao).getDays();
		
		return dias;
		
	}
}
