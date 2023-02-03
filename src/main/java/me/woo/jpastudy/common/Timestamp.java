package me.woo.jpastudy.common;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Timestamp {

	private LocalDateTime createdAt;

	private LocalDateTime modifiedAt;

	/*
	메소드만 선언
	@PrePersist or @PostPersist 는 여기서 할 수가 없음
	원래는 해당 엔티티에 정의해야 하지만,
	이 경우에는 상속 받는 엔티티에 정의
	*/
	public void updateCreatedAt() {
		this.createdAt = LocalDateTime.now();
	}

	public void updateModifiedAt() {
		this.modifiedAt = LocalDateTime.now();
	}
}