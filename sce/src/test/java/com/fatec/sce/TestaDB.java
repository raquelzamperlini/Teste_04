package com.fatec.sce;

import static org.junit.Assert.*;
import com.mysql.jdbc.Connection;

import org.junit.Test;

import com.fatec.sce.model.ConfiguraDB;
import com.fatec.sce.model.FabricaDeConexoes;

public class TestaDB {
	/**
	 * Objetivo - verificar o comportamento do sistema na conexao ao DB com
	 * configuracao valida Pré-condição - a configuracao default da fabrica de
	 * conexoes é valida
	 */
	@Test
	public void quandoConectaComOBancoRetornaOK() {
		// cenario
		Connection c = null;
		try {
			// acao
			c = new FabricaDeConexoes().getConnection();
			// verificacao
			assertNotNull(c);
		} catch (Exception e) {
			fail("nao deveria falhar");
		}
	}

	/**
	 * Objetivo - verificar o comportamento do sistema na conexao ao DB com senha de
	 * acesso invalida Pré-condição - a senha cadastrada é "alunofatec"
	 */
	@Test
	public void quandoConectaComSenhaInvalida_SQLException() {

		// cenario
		String url = "jdbc:mysql://localhost:3306/biblioteca";
		String driver = "com.mysql.jdbc.Driver";
		String usuario = "root";
		String senha = "alunofatec1"; // senha invalida
		FabricaDeConexoes fabricaDeConexoes = null;

		ConfiguraDB configuraDB = new ConfiguraDB(url, driver, usuario, senha);
		fabricaDeConexoes = new FabricaDeConexoes(configuraDB);
		try {
			// acao
			fabricaDeConexoes.getConnection();
			fail("deveria falhar");
		} catch (Exception e) {
			// verificacao
			assertEquals(e.getMessage(),
					"java.sql.SQLException: Access denied for user 'root'@'localhost' (using password: YES)");

		}
	}

}
