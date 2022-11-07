package com.supermarket.clientservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Client")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "El campo nombre no puede ser nulo")
	private String name;

	@NotNull(message = "El campo apellido no puede ser nulo")
	private String surname;

	@Min(value = 18, message = "La edad no puede ser inferior a 18")
	@Max(value = 99, message = "La edad no puede ser superior a 99")
	private int age;

	@NotNull(message = "El campo dirección no puede ser nulo")
	private String address;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private ClientDetail clientDetail;
}
