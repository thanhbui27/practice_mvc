package bap.jp.thanhbn.java8_execrise_1;

public class Circle implements Shape {

	private int r;
	private double PI = 3.14;
	
	public Circle(int r) {
		this.r = r;
	}
	
	@Override
	public void area() {
		// TODO Auto-generated method stub
		System.out.println("Dien tich tron la : " + (PI * r * r) );
	}

	@Override
	public void perimeter() {
		// TODO Auto-generated method stub
		System.out.println("Chu vi tron la : " + (PI * r * 2) );
	}

}
