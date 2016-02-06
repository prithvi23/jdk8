package core1;

import java.util.HashMap;
import java.util.Map;

/**
 * String literals will return true for both equals and == if the value matches
 * String object will return false for == and true for equals if the value matches
 * When inserting an object into a map the hashcode is associated to the bucket number
 * When inserting another instance with the same hashcode and equals, the key is unchanged and only value is replaced
 * If the property of an inserted key object is modified after insertion, the object is forever lost in the map
 * Adding more keys to a map can either convert it into a list or a binary tree
 * Avoid collisions as they result in a terrible hashcode algorithm. Use an immutable object as a key
 * @author prithvi
 *
 */
public class App {

	public static void main(String[] args) {
		Map<String,Integer> map = new HashMap<String,Integer>(){{
			put("one",1);
			put("two",2);
		}};
		String one = "one";
		String anotherOne = "one";
		String oneObj = new String("one");
		String anotherOneObj = new String("one");
		Test1 test1 = new Test1(1,"one");
		Test2 test2 = new Test2(1,"one");
		Map<kvp,String> kvpMap = new HashMap<kvp,String>(){{
			put(test1, "test1");
			put(test2, "test2");
		}};
		System.out.println("String one:"+one);
		System.out.println("String another one:"+anotherOne);
		System.out.println("String oneObj:"+oneObj);
		System.out.println("String anotherOneObj:"+anotherOneObj);
		System.out.println("Test1 "+test1);
		System.out.println("Test2 "+test2);
		System.out.println("HashCodes:One "+ one.hashCode()+" AnotherOne "+anotherOne.hashCode());
		System.out.println("HashCodes:Test1 "+ test1.hashCode()+" Test2 "+test2.hashCode());
		System.out.println("HashCodes:oneObj "+ oneObj.hashCode()+" anotherOneObj "+anotherOneObj.hashCode());
		System.out.println("String one == anotherOne because they are literals and not objects "+ (one == anotherOne));
		System.out.println("String one.equals(anotherOne) "+ (one.equals(anotherOne)));
		System.out.println("String oneObj == anotherOneObj because they are objects and not literals "+ (oneObj == anotherOneObj));
		System.out.println("String oneObj.equals(anotherOneObj) "+ (oneObj.equals(anotherOneObj)));
		System.out.println("String test1 == test2 - compiler error "+ false);
		System.out.println("String test1.equals(test2) "+ (test1.equals(test2)));
		System.out.println("Equals (test.equals(one)): "+test1.equals(one));
		System.out.println("String equals (one == test.getKey()): "+ (one == test1.getKey()));
		System.out.println("String equals (one.equals(test.getKey()): "+ (one.equals(test1.getKey())));
		System.out.println("map:"+map);
		System.out.println("From string "+map.get(one));
		System.out.println("From class "+map.get(test1.getKey()));
		System.out.println("KvpMap:"+kvpMap);
		System.out.println("From test1 "+kvpMap.get(test1));
		System.out.println("From test2 "+kvpMap.get(test2));
		System.out.println("test2 setkey mod");
		test2.setKey("mod");
		System.out.println("Test1 "+test1);
		System.out.println("Test2 "+test2);
		System.out.println("KvpMap:"+kvpMap);
		System.out.println("HashCodes:Test1 "+ test1.hashCode()+" Test2 "+test2.hashCode());
		for(kvp key : kvpMap.keySet()){
			System.out.println("key: "+key+" key hashcode:"+ key.hashCode());
			System.out.println("key: "+key+" key equals test1 "+ key.equals(test1));
			System.out.println("key: "+key+" key equals test2 "+ key.equals(test2));
			System.out.println("key: "+key+" test1 equals key "+ test1.equals(key));
			System.out.println("key: "+key+" test2 equals key "+ test2.equals(key));
		}
		System.out.println("From test1 "+kvpMap.get(test1));
		System.out.println("From test2 "+kvpMap.get(test2));
		System.out.println("test1 setkey mod");
		test1.setKey("mod");
		System.out.println("Test1 "+test1);
		System.out.println("Test2 "+test2);
		System.out.println("KvpMap:"+kvpMap);
		System.out.println("HashCodes:Test1 "+ test1.hashCode()+" Test2 "+test2.hashCode());
		for(kvp key : kvpMap.keySet()){
			System.out.println("key: "+key+" key hashcode:"+ key.hashCode());
			System.out.println("key: "+key+" key equals test1 "+ key.equals(test1));
			System.out.println("key: "+key+" key equals test2 "+ key.equals(test2));
			System.out.println("key: "+key+" test1 equals key "+ test1.equals(key));
			System.out.println("key: "+key+" test2 equals key "+ test2.equals(key));
		}
		System.out.println("From test1 "+kvpMap.get(test1));
		System.out.println("From test2 "+kvpMap.get(test2));
	}

}
