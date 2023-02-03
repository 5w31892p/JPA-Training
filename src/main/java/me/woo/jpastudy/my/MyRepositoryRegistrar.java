/*
package me.woo.jpastudy.my;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import lombok.Setter;

// @EnableJpaRepositories 의 JpaRepositoriesRegistrar 를 통해서 등록되는 것을 직접 만들어 보는 클래스

public class MyRepositoryRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry,
		BeanNameGenerator importBeanNameGenerator) {
		// 주입할 빈에 대해 프로그래밍 시작
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(MyRepository.class);
		beanDefinition.getPropertyValues().add("dataTable", Map.of(1L, "data"));
		// 프로그래밍 끝

		registry.registerBeanDefinition("myRepository", beanDefinition);
	}

	// 원래는 MyRepository.java 파일에 따로 있어야함
	@Setter
	public class MyRepository {
		private HashMap<Long, String> dataTable; // db table

		public String find(Long id) {
			return dataTable.getOrDefault(id, "");
		}

		public Long save(String data) {
			var newId = Long.valueOf(dataTable.size());
			this.dataTable.put(newId, data);
			return newId;
		}
	}
}

*/
