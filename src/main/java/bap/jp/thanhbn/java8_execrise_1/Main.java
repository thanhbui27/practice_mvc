package bap.jp.thanhbn.java8_execrise_1;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shape circle = new Circle(5);
		circle.area();
		circle.perimeter();
		circle.description();
		Shape rectangle = new Rectangle(3,4, 5);
		rectangle.area();
		rectangle.perimeter();
		rectangle.description();
	}

}
