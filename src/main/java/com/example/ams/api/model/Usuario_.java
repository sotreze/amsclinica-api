package com.example.ams.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile ListAttribute<Usuario, Permissao> permissoes;
	public static volatile SingularAttribute<Usuario, Boolean> ativo;
	public static volatile SingularAttribute<Usuario, Long> codigo;
	public static volatile SingularAttribute<Usuario, String> primeiroNome;
	public static volatile SingularAttribute<Usuario, String> sobrenome;
	public static volatile SingularAttribute<Usuario, String> email;

}

