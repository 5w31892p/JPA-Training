package me.woo.jpastudy.user;

import org.springframework.beans.factory.annotation.Value;

public interface UserProfile {
	// select username, profileImageUrl from user; // 두개의 필드만 나간다는 뜻의 closed projection
	String getUsername();

	String getProfileImageUrl();

	// workersHolder 는 bean 으로 등록한 contextHolder
	@Value("#{target.profileImageUrl != null}")
	boolean hasProfileImage();

	default String getUserInfo() {
		return getUsername() + " " + (hasProfileImage() ? getProfileImageUrl() : "");
	}
}

