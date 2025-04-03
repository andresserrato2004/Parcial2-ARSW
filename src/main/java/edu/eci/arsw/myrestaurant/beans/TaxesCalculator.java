package edu.eci.arsw.myrestaurant.beans;

import org.springframework.stereotype.Service;

import edu.eci.arsw.myrestaurant.model.RestaurantProduct;

@Service
public interface TaxesCalculator {

	public float getProductTaxes(RestaurantProduct p);
	
}
