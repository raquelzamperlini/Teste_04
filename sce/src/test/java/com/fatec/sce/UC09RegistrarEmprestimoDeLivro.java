package com.fatec.sce;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import com.fatec.sce.model.Emprestimo;
import com.fatec.sce.model.Livro;
import com.fatec.sce.model.Usuario;
import com.fatec.sce.servico.ServicoEmprestimo;

public class UC09RegistrarEmprestimoDeLivro {
	static private Livro livro;
	static private Usuario usuario;
	static private ServicoEmprestimo servico;

	@Test
	public void CT01RegistraEmprestimoDeLivro_com_sucesso() {
		// cenario
		livro = new Livro();
		livro.setIsbn("121212");
		livro.setTitulo("Engenharia de Software");
		livro.setAutor("Pressman");
		usuario = new Usuario();
		usuario.setRa("1111");
		usuario.setNome("Jose da Silva");
		servico = new ServicoEmprestimo();
		// acao
		Emprestimo resultadoEsperado = servico.empresta(livro, usuario);
		// verificação
		assertNotNull(resultadoEsperado);
	}

	@Test
	public void CT02RegistraEmprestimoDeLivro_com_sucesso() {
		// cenario
		Emprestimo emprestimo = null;
		// acao
		emprestimo = ObtemEmprestimo.comDadosValidos();
		// verificação
		assertNotNull(emprestimo);
	}

	@Test
	public void CT03UC01FB_registra_emprestimo_com_sucesso_validacao_da_data() {
		// cenario
		DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
		String dataEsperada = new DateTime().plusDays(8).toString(fmt);
		ServicoEmprestimo servico = null;
		
		// acao
		Emprestimo emprestimo = ObtemEmprestimo.comDadosValidos();
		String dataObtida = emprestimo.getDataDevolucao();
		
		// verificacao
		assertTrue(dataEsperada.equals(dataObtida));
	}

	@Test
	public void CT04RegistraEmprestimoDeLivro_dados_invalidos_livro_null() {
		// cenario
		Livro livro = null;
		Usuario usuario = ObtemUsuario.comDadosValidos();
		ServicoEmprestimo servico = new ServicoEmprestimo();
		Emprestimo emprestimo = null;
		
		// acao
		try {
			emprestimo = servico.empresta(livro, usuario);
		} catch (Exception e) {
			// verificação
			assertNull(emprestimo);
		}
	}

	@Test
	public void CT05RegistraEmprestimoDeLivro_dados_invalidos_livro_null() {
		// cenario
		Livro livro = null;
		Emprestimo emprestimo = new Emprestimo();
		// acao
		try {
			emprestimo.setLivro(livro);
		} catch (Exception e) {
			// verificação
			System.out.println(e.getMessage());
			assertEquals("Dados invalidos.", e.getMessage());
		}
	}

	/**
	 * Objetivo - verificar o comportamento do metodo ehDomigo() para uma data com
	 * formato valido dia invalido (domingo).
	 */
	@Test
	public void CT06SeDataDevolucaoForDomingoRetornaTrue() {
		// cenario
		String data = "02/09/2018"; // domingo
		// acao
		Emprestimo umEmprestimo = new Emprestimo();
		// verificacao
		assertTrue(umEmprestimo.ehDomingo(data));
	}

	@Test
	public void CT07QuandoEntregaAtrasadoQuantDiasNegativo() {
		// cenario
		Emprestimo umEmprestimo = ObtemEmprestimo.comDataDeDevolucaoVencida();
		ServicoEmprestimo servico = new ServicoEmprestimo();
		// acao
		int quantDias = servico.devolucao(umEmprestimo);
		// verificacao
		assertTrue(quantDias < 0); // quant de dias entre a data de emprestimo e a de devolucao
	}
	
	/**
	 * Objetivo - verificar o comportamento do método validaData()
	 * com data com formato invalido.
	 */
	@Test
	public void CT08SeDataFormatoInvalidoRetornaTrue() {
		// cenario
		String data = "01/12/2018"; // formato invalido
		// acao
		Emprestimo umEmprestimo = new Emprestimo();
		// verificacao
		assertTrue(umEmprestimo.validaData(data));
	}
	
	/**
	 * Objetivo - testar data emprestimo igual a data devolucao
	 */
	@Test
	public void CT09QuandoDataEmprestimoIgualDataDevolucaoRetornaTrue() {
		// cenario
		Emprestimo umEmprestimo = ObtemEmprestimo.comDataDeDevolucaoIgualDataIgualDeEmprestimo();
		ServicoEmprestimo servico = new ServicoEmprestimo();
		
		// acao
		int quantDias = servico.devolucao(umEmprestimo);
		
		// verificacao
		assertTrue(quantDias == 0); // quant de dias entre a data de emprestimo e a de devolucao
	}
	
}