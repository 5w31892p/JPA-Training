package me.woo.jpastudy.thread;

import static me.woo.jpastudy.thread.QThread.*;

import java.util.Objects;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ThreadRepositoryQueryImpl implements ThreadRepositoryQuery {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<Thread> search(ThreadSearchCond cond, Pageable pageable) {
			/* 하단 nullCheck 메소드로 대체
			var query = jpaQueryFactory.select(thread)
			.from(thread)
			.leftJoin(thread.channel, channel).fetchJoin()
			.where(
				thread.mentions.any().user.id.eq(cond.getMentionedUserId())
			)
			.fetch();
			*/
		var query = query(thread, cond) // QThread.thread 에서 QThread static으로 해서 thread만 사용하는 것 가능

			// 조건 넣어주는 것
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize());

		// var countQuery = query(Wildcard.count, cond);
		// var countQuery = jpaQueryFactory.select(Wildcard.count);
		query.orderBy(thread.mentions.any().createdAt.desc());

		var threads = query.fetch();
		long totalSize = countQuery(cond).fetch().get(0);

		threads.stream()
			.map(Thread::getComments)
			.forEach(comments -> comments
				.forEach(comment -> Hibernate.initialize(comment.getEmotions())));
		return PageableExecutionUtils.getPage(threads, pageable,
			() -> totalSize); // PageableExecutionUtils : 페이지로 감싸서 리턴
	}

	private <T> JPAQuery<T> query(Expression<T> expr, ThreadSearchCond cond) {
		return jpaQueryFactory.select(expr)
			.from(thread)
			.leftJoin(thread.channel).fetchJoin()
			.leftJoin(thread.emotions).fetchJoin()
			.leftJoin(thread.comments).fetchJoin()
			.leftJoin(thread.mentions).fetchJoin()
			.where(
				// thread.channel.id.eq(cond.getChannelId()), // 아래 channelIdEq 메소드로 변경
				channelIdEq(cond.getChannelId()),
				// thread.mentions.any().user.id.eq(cond.getMentionedUserId()) // 아래 메소드로 변경
				mentionedUserIdEq(cond.getMentionedUserId())
			);
	}

	private JPAQuery<Long> countQuery(ThreadSearchCond cond) {
		return jpaQueryFactory.select(Wildcard.count)
			.from(thread)
			.where(
				channelIdEq(cond.getChannelId()),
				mentionedUserIdEq(cond.getMentionedUserId())
			);
	}

	/*
	  null check
	*/
	private BooleanExpression channelIdEq(Long channelId) {
		return Objects.nonNull(channelId) ? thread.channel.id.eq(channelId) : null;
	}

	private BooleanExpression mentionedUserIdEq(Long mentionedUserId) {
		return Objects.nonNull(mentionedUserId) ? thread.mentions.any().user.id.eq(mentionedUserId)
			: null;
	}
}
