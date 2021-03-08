package me.smpark.inflearnthejavatest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

	@Test
	@DisplayName("스터디 만들기 fast")
	@Tag("fast")
	void create_new_study() {
		Study actual = new Study(100);
		assertThat(actual.getLimit()).isGreaterThan(0);
	}
	
	@Test
	@DisplayName("스터디 만들기 slow")
	@Tag("slow")
	void create_new_study_again() {
		System.out.println("create1");
	}
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("before all");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("after all");
	}
	
	@BeforeEach
	void beforeEach() {
		System.out.println("before each");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("after each");
	}
	
}
