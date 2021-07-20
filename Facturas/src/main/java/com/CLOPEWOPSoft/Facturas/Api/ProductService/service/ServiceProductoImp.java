package com.CLOPEWOPSoft.Facturas.Api.ProductService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.CLOPEWOPSoft.Facturas.Api.ExceptionHandling.ApiException;
import com.CLOPEWOPSoft.Facturas.Api.ProductService.entity.Producto;
import com.CLOPEWOPSoft.Facturas.Api.ProductService.repository.ProductoRepository;

@Service
public class ServiceProductoImp implements ServiceProducto {

	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public List<Producto> getProductos() {
		return productoRepository.findAll();
	}

	@Override
	public Producto getProducto(Integer id) {
		try {
			Producto p  = ((Optional<Producto>) productoRepository.findById(id)).get();
			return p;
		}catch(Exception e){
			throw new ApiException(HttpStatus.NOT_FOUND,e.getLocalizedMessage());
		}
	}

	@Override
	public void createProducto(Producto producto) {
		productoRepository.save(producto);
	}

	@Override
	public void updateProducto(Producto producto, Integer id) {
		System.out.println("Hola");
		try {
			productoRepository.updateProducto(
											producto.getCodigo(),
											producto.getNombre(), 
											producto.getDescripcion(), 
											producto.getCantidad(), 
											producto.getPrecio(), 
											producto.getFechaCreacion(), 
											producto.getCategoria().getId(), 
											id);
		}catch(Exception e) {
			throw new ApiException(HttpStatus.NOT_FOUND,e.getLocalizedMessage());
		}
	}

	@Override
	public void deleteProducto(Integer id) {
		try {
			productoRepository.deleteById(id);
		}catch(Exception e) {
			throw new ApiException(HttpStatus.NOT_FOUND,e.getLocalizedMessage());
		}
	}
}
