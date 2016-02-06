package jdk8List;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * Demonstrates Option and map compute if lambda
 * @author prithvi
 *
 */
public enum TransactionType {
	GROCERY(0),ENTERTAINMENT(1), OTHER(2);
	public int id = 0;
	private static Map<Integer,TransactionType> entries = new HashMap<>();
	static{
		for(TransactionType transactionType : TransactionType.values()){
			entries.put(transactionType.id, transactionType);
		}
	}
	TransactionType(){}
	TransactionType(int id){
		this.id = id;
	}
	
	/**
	 * Check if present in a map and return it
	 * @param value
	 * @return
	 */
	public static Optional<TransactionType> fromValue(int value){
		return Optional.ofNullable(entries.computeIfPresent(value, (k,v) -> v));
	}
	
}
