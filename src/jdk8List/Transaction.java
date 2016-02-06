package jdk8List;

public final class Transaction {
	@Override
	public String toString() {
		return String.format("Transaction [transactionType=%s, value=%s]", transactionType, value);
	}
	private final TransactionType transactionType;
	private final int value;
	public Transaction(TransactionType transactionType, int value){
		this.transactionType = transactionType;
		this.value = value;
	}
	public TransactionType getType() {
		return transactionType;
	}
	public int getValue() {
		return value;
	}
}
