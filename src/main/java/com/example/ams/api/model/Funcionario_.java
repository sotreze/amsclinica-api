package com.example.ams.api.model;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Funcionario.class)
public abstract class Funcionario_ {

	public static volatile SingularAttribute<Funcionario, Long> codigo;
	public static volatile SingularAttribute<Funcionario, String> cargo;
	public static volatile SingularAttribute<Funcionario, String> setor;
	public static volatile SingularAttribute<Funcionario, LocalDate> dataAdmissao;
	public static volatile SingularAttribute<Funcionario, Pessoa> pessoa;
	public static volatile SingularAttribute<Funcionario, String> cpf;

}