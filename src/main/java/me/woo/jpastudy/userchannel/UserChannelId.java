package me.woo.jpastudy.userchannel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
@Setter
public class UserChannelId implements Serializable {

	@Serial
	private static final long serialVersionUID = 932813899396663626L;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "channel_id")
	private long channelId;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserChannelId userChannelId = (UserChannelId)o;
		return Objects.equals(getUserId(), userChannelId.getUserId()) && Objects.equals(getChannelId(),
			userChannelId.getChannelId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserId(), getChannelId());
	}
}

// @IdClass 방식

// @Getter
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
// public class UserChannelId implements Serializable {
// 	private Long user;   // UserChannel 의 user 필드명과 동일해야함
// 	private Long channel; // UserChannel 의 channel 필드명과 동일해야함
//
// 	@Override
// 	public boolean equals(Object o) {
// 		if (this == o) return true;
// 		if (o == null || getClass() != o.getClass()) return false;
// 		UserChannelId userChannelId = (UserChannelId) o;
// 		return Objects.equals(getUser(), userChannelId.getUser()) && Objects.equals(getChannel(), userChannelId.getChannel());
// 	}
//
// 	@Override
// 	public int hashCode() {
// 		return Objects.hash(getUser(), getChannel());
// 	}
// }
