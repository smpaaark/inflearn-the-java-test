package me.smpark.inflearnthejavatest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

	@FastTest
	@DisplayName("스터디 만들기 fast")
	void create_new_study() {
		Study actual = new Study(100);
		assertThat(actual.getLimit()).isGreaterThan(0);
	}
	
	@SlowTest
	@DisplayName("스터디 만들기 slow")
	void create_new_study_again() {
		System.out.println("create1");
	}
	
	@DisplayName("스터디 만들기")
	@RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
	void repeatTest(RepetitionInfo repeatitionInfo) {
		System.out.println("test " + repeatitionInfo.getCurrentRepetition() + "/" + repeatitionInfo.getTotalRepetitions());
	}
	
	@DisplayName("스터디 만들기")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요."})
	void parameterizedTest(String message) {
		System.out.println(message);
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
