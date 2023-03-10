package me.woo.jpastudy.userchannel;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.woo.jpastudy.channel.Channel;
import me.woo.jpastudy.user.User;

// lombok
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// JPA
@Entity
@Table(name = "user_channel")
// @IdClass(UserChannelId.class)
public class UserChannel {

	/**
	 * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
	 */
	@EmbeddedId
	private UserChannelId userChannelId = new UserChannelId();

	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "id", nullable = false)
	// private Long id;

	/**
	 * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
	 */
	@Builder
	public UserChannel(User user, Channel channel) {
		this.user = user;
		this.channel = channel;
		this.userChannelId = getUserChannelId(user, channel);
	}

	private UserChannelId getUserChannelId(User user, Channel channel) {
		var id = new UserChannelId();
		id.setUserId(user.getId());
		id.setChannelId(channel.getId());
		return id;
	}

	/**
	 * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
	 */
	// @Id
	// @JoinColumn(name = "user_id")
	@ManyToOne
	@MapsId("user_id")
	User user;

	// @Id
	// @JoinColumn(name = "channel_id")
	@ManyToOne
	@MapsId("channel_id")
	Channel channel;

	/**
	 * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
	 */

	/**
	 * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
	 */
}
