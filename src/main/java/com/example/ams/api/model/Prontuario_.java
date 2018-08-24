package com.example.ams.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Prontuario.class)
public abstract class Prontuario_ {

	public static volatile SingularAttribute<Prontuario, Long> codigo;
	public static volatile SingularAttribute<Prontuario, String> relatorio;
	public static volatile SingularAttribute<Prontuario, Paciente> paciente;
	public static volatile SingularAttribute<Prontuario, Medico> medico;
	public static volatile SingularAttribute<Prontuario, String> anexo;
}
