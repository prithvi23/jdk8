package jdk8Optional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Person{
	int id = 1;
	String name = "One";
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Person() {
	}
	@Override
	public String toString() {
		return String.format("Person [id=%s, name=%s]", id, name);
	}
	
}

public class App {
	
	public static void main(String[] args) {
		Person person = new Person();
		Optional<Person> opt = Optional.ofNullable(person);
		opt.ifPresent(x -> printPerson(x));
		opt.ifPresent(System.out::println);
		opt.ifPresent(App::printPerson);
		
		opt.filter(x -> x.name.equals("one")).ifPresent(App::printPerson);
		Optional<String> name = opt.map(x -> x.name);
		name.ifPresent(App::printName);
		
	}


	private static void printPerson(Person x) {
		System.out.println(x);
	}
	

	private static void printName(String x) {
		System.out.println(x);
	}

}
