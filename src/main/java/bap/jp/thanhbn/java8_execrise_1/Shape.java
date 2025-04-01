package bap.jp.thanhbn.java8_execrise_1;

public interface Shape {
	
	void area();
	
	void perimeter();
	
	default void description() {
		System.out.println("Day la hinh cha");
	}
}
