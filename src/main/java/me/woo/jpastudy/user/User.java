package me.woo.jpastudy.user;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//lombok
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 밖에서 빈 생성자 만들지 못하도록
@ToString
// jpa
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;
	@Column(length = 25)
	private String username;
	@Column(length = 25)
	private String password;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "street", column = @Column(name = "home_street"))
	})
	private Address address;
}

