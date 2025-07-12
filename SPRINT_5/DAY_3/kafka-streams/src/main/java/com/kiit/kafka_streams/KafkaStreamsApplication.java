package com.kiit.kafka_streams;
import com.kiit.kafka_streams.model.LoginEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Properties;


@SpringBootApplication
public class KafkaStreamsApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(KafkaStreamsApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "login-count-app");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

		StreamsBuilder builder = new StreamsBuilder();

		KStream<String, String> stream = builder.stream("user-login-events");

		ObjectMapper mapper = new ObjectMapper();

		KTable<String, Long> loginCounts = stream
				.mapValues(value -> {
					try {
						LoginEvent event = mapper.readValue(value, LoginEvent.class);
						return event.getUserId();
					} catch (Exception e) {
						return null;
					}
				})
				.filter((key, userId) -> userId != null)
				.groupBy((key, userId) -> userId, Grouped.with(Serdes.String(), Serdes.String()))
				.count(Materialized.as("login-counts-store"));

		loginCounts
				.toStream()
				.mapValues(String::valueOf)
				.to("login-counts", Produced.with(Serdes.String(), Serdes.String()));

		KafkaStreams streams = new KafkaStreams(builder.build(), props);
		streams.start();

		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
	}

}
