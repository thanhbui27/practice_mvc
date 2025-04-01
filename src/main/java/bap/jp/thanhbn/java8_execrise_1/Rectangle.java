package bap.jp.thanhbn.java8_execrise_1;

public class Rectangle implements Shape {

	private float a;
	private float b;
	private float c;
	
	
	
	public Rectangle(float a, float b, float c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public void area() {
		// TODO Auto-generated method stub
		float cv = a + b + c;
		
		float p = cv/2;
		
		System.out.println("Dien tich tron la : " + (Math.sqrt(p)) );
	}

	@Override
	public void perimeter() {
		// TODO Auto-generated method stub
		System.out.println("Chu vi tron la : " + (a + b + c) );
	}
	
	@Override
	public void description() {
		// TODO Auto-generated method stub
		System.out.println("Day la hinh tam giac");
	}
}
