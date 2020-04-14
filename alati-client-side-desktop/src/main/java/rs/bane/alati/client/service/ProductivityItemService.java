package rs.bane.alati.client.service;

import java.util.Date;
import java.util.List;

import rs.bane.alati.client.model.ProductivityItem;

public interface ProductivityItemService {
	ProductivityItem findOne(Long id);

	List<ProductivityItem> findAll();

	List<ProductivityItem> findByDateBetween(Date date1, Date date2);

	ProductivityItem save(ProductivityItem productivityItem);

	ProductivityItem delete(Long id);

}
