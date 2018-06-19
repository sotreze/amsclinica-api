package com.example.ams.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Receita.class)
public abstract class Receita_ {

	public static volatile SingularAttribute<Receita, Long> codigo;
	public static volatile SingularAttribute<Receita, String> descricao;
	public static volatile SingularAttribute<Receita, Medicacao> medicacao;
}