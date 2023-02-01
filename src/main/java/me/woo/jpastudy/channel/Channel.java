package me.woo.jpastudy.channel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// lombok
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// JPA
@Entity
public class Channel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(length = 30)
	private String name;

	@Enumerated(EnumType.STRING) // 타입을 ORDINAL로 하면 각 0, 1, .. 이렇게 적용하기 때문에 나중에 다른 데이터 들어오면 틀어짐
	private Type type;

	public enum Type {
		PUBLIC, PRIVATE
	}
}
