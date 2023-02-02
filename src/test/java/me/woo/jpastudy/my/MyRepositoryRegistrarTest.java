package me.woo.jpastudy.my;

/* 테스트 성공 후 주석 처리

// MyRepositoryRegistrar, MyRepository Test
@Import(MyRepositoryRegistrar.class) // bean 주입, component도 아니고 그냥 클래스라서 이렇게 주입해줌
@SpringBootTest // 스프링에서 주입된 bean 들에 대해서 주입받도록 되어 있음
public class MyRepositoryRegistrarTest {
	@Autowired
	MyRepository myRepository;

	@Test
	void myRepositoryTest() {
		// given
		var newData = "new-data";
		var savedId = myRepository.save(newData);

		// when
		var savedData = myRepository.find(savedId);

		// then
		System.out.println(savedData);
	}
}
*/
