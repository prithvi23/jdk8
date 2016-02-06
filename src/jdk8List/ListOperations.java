package jdk8List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.text.StringContent;

public final class ListOperations {
	private ListOperations(){}
	public static final List<Transaction> populateRandomTransactions(int count){
		// if the value is 4 below, the list might have nulls
		// Another way to generated random number stream Stream.generate(random::nextInt)
		return new Random().ints(0,3).limit(10).
		mapToObj(r -> TransactionType.fromValue(r)).filter(Optional::isPresent).
		map(Optional::get).collect(Collectors.toList()).stream().
		map(t -> new Transaction(t,t.id+100)).collect(Collectors.toList());
	}
	
	public static final void print(List<?> transactions){
		transactions.forEach(System.out::println);
	}
	
	public static final List<Transaction> groceryTransactions7(List<Transaction> transactions){
		List<Transaction> groceryTransactions = new ArrayList<>();
		for(Transaction t: transactions){
		  if(t.getType() == TransactionType.GROCERY){
		    groceryTransactions.add(t);
		  }
		}
		return groceryTransactions;
	}
	
	public static final List<Transaction> groceryTransactions8(List<Transaction> transactions){
		return transactions.stream().filter(t -> t.getType().equals(TransactionType.GROCERY)).collect(Collectors.toList());
	}
	
	public static List<String> getSortedList(){
		List<String> list = Arrays.asList("One","Two","Three","Four");
		list.sort((s1,s2)->s1.compareTo(s2));
		return list;
	}
}
