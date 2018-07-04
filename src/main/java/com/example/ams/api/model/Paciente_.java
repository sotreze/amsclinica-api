package com.example.ams.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Paciente.class)
public abstract class Paciente_ {

	public static volatile SingularAttribute<Paciente, Long> codigo;
	public static volatile SingularAttribute<Paciente, String> cpf;
	public static volatile SingularAttribute<Paciente, Pessoa> pessoa;
	public static volatile SingularAttribute<Paciente, Categoria> categoria;

}