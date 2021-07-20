package com.CLOPEWOPSoft.Facturas.Api.ProductService.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CLOPEWOPSoft.Facturas.Api.ExceptionHandling.ApiException;
import com.CLOPEWOPSoft.Facturas.Api.ProductService.entity.Producto;
import com.CLOPEWOPSoft.Facturas.Api.ProductService.service.ServiceProducto;

@RestController
@RequestMapping("/producto")
public class ControllerProducto {

	@Autowired
	ServiceProducto serviceProducto;
	
	@GetMapping
	public ResponseEntity<List<Producto>> getProductos(){
		return new ResponseEntity<>(serviceProducto.getProductos(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProducto(@PathVariable("id") Integer id){
		return new ResponseEntity<>(serviceProducto.getProducto(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<HttpStatus> createProducot(@Valid @RequestBody Producto producto, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			throw new ApiException(HttpStatus.BAD_REQUEST,bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		serviceProducto.createProducto(producto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<HttpStatus> updateProduct(@Valid @RequestBody Producto producto, @PathVariable("id") Integer id, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			throw new ApiException(HttpStatus.BAD_REQUEST,bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		serviceProducto.updateProducto(producto, id);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteProducto(@PathVariable("id") Integer id){
		serviceProducto.deleteProducto(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}