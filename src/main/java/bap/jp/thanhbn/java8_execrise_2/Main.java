package bap.jp.thanhbn.java8_execrise_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		List<User> users = Arrays.asList(new User("User A", 24, ECity.DA_NANG),
				new User("User B", 34, ECity.HA_NOI),
				new User("User C", 25, ECity.HUE),
				new User("User D", 70, ECity.QUANG_BINH),
				new User("User E", 14, ECity.QUANG_TRI),
				new User("User F", 44, ECity.TP_HO_CHI_MINH),
				new User("User G", 66, ECity.DA_NANG),
				new User("User H", 20, ECity.QUANG_BINH),
				new User("User Y", 19, ECity.TP_HO_CHI_MINH),
				new User("User K", 41, ECity.DA_NANG),
				new User("User L", 64, ECity.QUANG_BINH),
				new User("User M", 54, ECity.HA_NOI));
		
		System.out.println("-------- Lọc ra những người có độ tuổi từ 20 đến 30 ------------");
		
		users.stream()
		.filter((user) -> user.getAge() >= 20 && user.getAge() <= 30)
		.peek(e -> System.out.print(e.toString())).collect(Collectors.toList());
		
		System.out.println("-------- Nhóm các người dùng theo thành phố của họ. ------------");
		
		Map<Object, List<User>>  groupUserByCity = users.stream()
				.collect(Collectors.groupingBy(u -> u.getCity()));
		System.out.println(groupUserByCity);
		
		System.out.println("-------- Tính số lượng người dùng trong mỗi nhóm (theo thành phố ------------");
		
		Map<Object, Long>  countGroupUserByCity = users.stream()
				.collect(Collectors.groupingBy(u -> u.getCity(), Collectors.counting()));
		
		System.out.println(countGroupUserByCity);
		
		System.out.println("-------- Tính số lượng người dùng trong mỗi nhóm (theo thành phố ------------");
		
		Optional<Object> option = Optional.of(countGroupUserByCity)
				.map(e -> e.entrySet().stream().filter(u -> u.getValue() >= 2) .collect(Collectors.toList()));
		System.out.println(option);
	}
}
