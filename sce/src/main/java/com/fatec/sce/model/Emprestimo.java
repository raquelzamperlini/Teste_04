package com.fatec.sce.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Emprestimo {
	private Livro livro;
	private Usuario usuario;
	private String dataEmprestimo;
	private String dataDevolucao;

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		if (livro != null) {
			this.livro = livro;
		} else {
			throw new RuntimeException("Dados invalidos.");
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(String dataEmprestimo) {
		if (validaData(dataEmprestimo) == true) {
			this.dataEmprestimo = dataEmprestimo;
		} else {
			throw new RuntimeException("Data invalida.");
		}
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	/**
	 * valida o formato da data
	 * 
	 * @param data no formato yyyy/MM/dd
	 * @return true se a data estiver no formato valido e false para formato
	 *         invalido
	 */
	public boolean validaData(String data) {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		df.setLenient(false); //
		try {
			df.parse(data); // data válida
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	public boolean ehDomingo(String data) {
		boolean isValida = false;
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
		if (validaData(data) == true) {
			DateTime umaData = fmt.parseDateTime(data);
			if (umaData.dayOfWeek().getAsText().equals("Domingo")) {
				isValida = true;
			}
		}
		return isValida;
	}
}