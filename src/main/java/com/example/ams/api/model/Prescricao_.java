package com.example.ams.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Prescricao.class)
public abstract class Prescricao_ {

	public static volatile SingularAttribute<Prescricao, Long> codigo;
	public static volatile SingularAttribute<Prescricao, Medico> medico;
	public static volatile SingularAttribute<Prescricao, Paciente> paciente;
	public static volatile SingularAttribute<Prescricao, String> descricao;
	public static volatile SingularAttribute<Prescricao, TipoPrescricao> tipo;
	public static volatile SingularAttribute<Prescricao, Medicacao> medicacao;
	public static volatile SingularAttribute<Prescricao, Exame> exame;
}