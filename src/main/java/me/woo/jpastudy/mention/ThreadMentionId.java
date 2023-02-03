package me.woo.jpastudy.mention;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ThreadMentionId implements Serializable {

	@Column(name = "user_id")
	private long userId;

	@Column(name = "thread_id")
	private long threadId;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ThreadMentionId threadMentionId = (ThreadMentionId)o;
		return Objects.equals(getUserId(), threadMentionId.getUserId()) && Objects.equals(getThreadId(),
			threadMentionId.getThreadId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserId(), getThreadId());
	}
}

