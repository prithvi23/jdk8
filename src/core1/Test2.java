package core1;

public class Test2 implements kvp{
	public Test2(int value, String key) {
		this.value = value;
		this.key = key; 
	}
	private int value;
	private String key;
	@Override
	public int hashCode() {
		return getKey().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		if (obj instanceof Test2){
			Test2 other = (Test2) obj;
			return other.key.equals(this.getKey());
		}
		else if (obj instanceof String){
			return this.getKey().equals(obj);
		}else if (obj instanceof Test1){
			Test1 other = (Test1) obj;
			return other.getKey().equals(this.getKey());
		}
		return false;
	}
	@Override
	public String toString() {
		return "Test2:"+key;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}