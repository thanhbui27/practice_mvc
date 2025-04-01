package bap.jp.thanhbn.java8_execrise_2;

public class User {
	private String name;
	private int age;
	private ECity city;
	
	
	public User(String name, int age, ECity city) {
		super();
		this.name = name;
		this.age = age;
		this.city = city;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public ECity getCity() {
		return city;
	}
	public void setCity(ECity city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", city=" + city + "]";
	}
	
	
}
