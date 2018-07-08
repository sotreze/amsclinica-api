package com.example.ams.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lancamento.class)
public abstract class Lancamento_ {

	public static volatile SingularAttribute<Lancamento, Long> codigo;
	public static volatile SingularAttribute<Lancamento, String> descricao;
	public static volatile SingularAttribute<Lancamento, LocalDate> dataConsulta;
	public static volatile SingularAttribute<Lancamento, LocalDate> dataExame;
	public static volatile SingularAttribute<Lancamento, String> observacao;
	public static volatile SingularAttribute<Lancamento, TipoLancamento> tipo;
	public static volatile SingularAttribute<Lancamento, Paciente> paciente;
	public static volatile SingularAttribute<Lancamento, Exame> exame;
	public static volatile SingularAttribute<Lancamento, BigDecimal> valor;

}

