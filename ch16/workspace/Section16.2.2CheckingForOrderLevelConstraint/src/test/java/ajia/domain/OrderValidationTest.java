/*
Copyright 2009 Ramnivas Laddad

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at 
    http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and 
limitations under the License. 
*/

package ajia.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ajia.domain.Order;
import ajia.domain.Product;
import ajia.domain.SimplePricingStrategy;


public class OrderValidationTest {
	private Order testOrder;
	private Product mockProduct;
	
	
	@Before
	public void setup() {
		OrderValidationAspect.aspectOf().setOrderValidator(new OrderValidatorTotalPrice());
		testOrder = new Order();
		testOrder.setPricingStrategy(new SimplePricingStrategy());
		
		mockProduct = Mockito.mock(Product.class);
		Mockito.when(mockProduct.getPrice()).thenReturn(20d);
	}
	
	@Test
	public void orderValidationValidOrder() {
		testOrder.addProduct(mockProduct, 1);
		testOrder.addProduct(mockProduct, 2);
		testOrder.addProduct(mockProduct, 2);
	}
	
	@Test(expected=OrderValidationException.class)
	public void orderValidationInvalidOrder() {
		testOrder.addProduct(mockProduct, 1);
		testOrder.addProduct(mockProduct, 2);
		testOrder.addProduct(mockProduct, 2);
		testOrder.addProduct(mockProduct, 2);
	}
	
}
