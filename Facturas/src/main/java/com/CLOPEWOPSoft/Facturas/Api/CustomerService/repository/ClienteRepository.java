package com.CLOPEWOPSoft.Facturas.Api.CustomerService.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CLOPEWOPSoft.Facturas.Api.CustomerService.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{

	@Modifying
	@Transactional
	@Query(value = "CALL st_create_cliente(:nombres , "
			+ ":apellidos , "
			+ ":rfc ,"
			+ ":correo ,"
			+ ":idRegion)", nativeQuery = true)
	void createCliente(@Param("nombres") String nombres, 
			@Param("apellidos") String apellidos,
			@Param("rfc") String rfc,
			@Param("correo") String correo,
			@Param("idRegion") Integer idRegion);
	
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Cliente SET Nombres = :nombres ,"
			+ "Apellidos = :apellidos ,"
			+ "RFC = :rfc ,"
			+ "correo = :correo ,"
			+ "ID_Region = :idRegion"
			+ " WHERE ID = :id", nativeQuery = true)
	void updateCliente(@Param("nombres") String nombres, 
			@Param("apellidos") String apellidos,
			@Param("rfc") String rfc,
			@Param("correo") String correo,
			@Param("idRegion") Integer idRegion,
			@Param("id")Integer id);
}
