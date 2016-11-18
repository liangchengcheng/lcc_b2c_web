package com.lcc.bean;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
public class Cart {
		private Logger logger = Logger.getLogger(this.getClass());
		private Map< Long, Item > items = new HashMap<Long, Item >( );
		
		public  void addItem( Product product , int number ){
			if( items.containsKey(product.getId())){
				return;
			}
			Item item = new Item( number, product );
			
			items.put(product.getId(),  item );
			
		}
		
		public void modifyNumberBYProductId( long productId, int number ){
			Item item = items.get(productId);
			item.setNumber(number);
		}
		
		public void deleteItemByProductId( long productId ){
			items.remove(productId);
		}

		public void deleteItemByProductId( Long [] productIds ){
			for( Long id : productIds ){
				items.remove(id );
			}
		}
		
		public void clear( ){
			items.clear();
			logger.info("Cart cleared. size="+ items.size() );
		}

		public  Map<Long , Item > getCartItems( ){
			return items;
		}
		
		public int getItemNumber( ){
			return items.size();
		}
		
		public boolean isEmpty( ){
			return items.isEmpty();
		}
		
		public Double getPrice( ){
			double sum = 0;
			for( Long id : items.keySet() ){
				Item item = items.get(id);
				sum+=item.getCost();
			}
			
			return sum;
		}

		public Map<Long, Item> getItems() {
			return items;
		}

		public void setItems(Map<Long, Item> items) {
			this.items = items;
		}
		
}
