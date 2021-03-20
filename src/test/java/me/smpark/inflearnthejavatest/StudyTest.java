package me.smpark.inflearnthejavatest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {
	
	int value = 1;
	
	@Order(2)
	@FastTest
	@DisplayName("스터디 만들기 fast")
	void create_new_study() {
		System.out.println(this);
		System.out.println(value++);
		Study actual = new Study(1);
		assertThat(actual.getLimit()).isGreaterThan(0);
	}
	
	@Order(1)
	@SlowTest
	@DisplayName("스터디 만들기 slow")
	@Disabled
	void create_new_study_again() {
		System.out.println(this);
		System.out.println("create1 " + value++);
	}
	
	@Order(3)
	@DisplayName("스터디 만들기")
	@RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
	void repeatTest(RepetitionInfo repeatitionInfo) {
		System.out.println("test " + repeatitionInfo.getCurrentRepetition() + "/" + repeatitionInfo.getTotalRepetitions());
	}
	
	@Order(4)
	@DisplayName("스터디 만들기")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@CsvSource({"10, '자바 스터디'", "20, 스프링"})
	void parameterizedTest(@AggregateWith(StudyAggregator.class) Study study) {
		System.out.println(study);
	}
	
	static class StudyAggregator implements ArgumentsAggregator {

		@Override
		public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
				throws ArgumentsAggregationException {
			return new Study(accessor.getInteger(0), accessor.getString(1));
		}
		
	}
	
	static class StudyConverter extends SimpleArgumentConverter {

		@Override
		protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
			assertEquals(Study.class, targetType, "Can only convert to Study");
			return new Study(Integer.parseInt(source.toString()));
		}
		
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
