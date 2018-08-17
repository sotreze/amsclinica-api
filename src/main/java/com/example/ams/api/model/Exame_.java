package com.example.ams.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Exame.class)
public abstract class Exame_ {

	public static volatile SingularAttribute<Exame, Long> codigo;
	public static volatile SingularAttribute<Exame, BigDecimal> quantidade;
	public static volatile SingularAttribute<Exame, Medico> medico;
	public static volatile SingularAttribute<Exame, Paciente> paciente;
	public static volatile SingularAttribute<Exame, TipoExame> tipoExame;
	public static volatile SingularAttribute<Exame, String> descricao;
	public static volatile SingularAttribute<Exame, LocalDate> dataAgendamento;
}