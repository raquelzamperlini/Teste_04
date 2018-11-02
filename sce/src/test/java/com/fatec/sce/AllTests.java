package com.fatec.sce;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TestaDB.class, 
	UC01CadastrarLivro.class, 
	UC02ConsultarLivro.class,
	UC05CadastrarUsuario.class, 
	UC09RegistrarEmprestimoDeLivro.class
	})
public class AllTests {

}