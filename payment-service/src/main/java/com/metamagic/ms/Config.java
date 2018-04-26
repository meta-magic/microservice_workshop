package com.metamagic.ms;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.metamagic.ms.events.integration.PaymentCompletedEvent;
import com.metamagic.ms.events.integration.PaymentDeclinedEvent;
import com.metamagic.ms.events.integration.PaymentInitiatedEvent;
import com.mongodb.MongoClient;

@Configuration
public class Config {
	
	@Value("${mongodb.dbname}")
	private String database;
	
	@Value("${mongodb.hostname}")
	private String hostName;
	
	@Value("${mongodb.port}")
	private int port;
	
	@Value("${mongodb.sagas.collection.name}")
	private String sagasCollectionName;
	
	@Value("${mongodb.events.collection.name}")
	private String mongoEventCollectionName;
	
	@Value("${mongodb.events.snapshot.collection.name}")
	private String mongoEventSnapshotCollectionName;
	
	@Bean
	public MongoClient mongoClient() {
		return new MongoClient(hostName, port);
	}

	@Bean
	public EventBus eventBus() {
		return eventStore();
	}

	@Bean
	public org.axonframework.mongo.eventhandling.saga.repository.MongoTemplate sagaMongoTemplate() {
		return new org.axonframework.mongo.eventhandling.saga.repository.DefaultMongoTemplate(mongoClient(), database, sagasCollectionName);
	}

	@Bean
	public MongoSagaStore mongoSagaStore() {
		return new MongoSagaStore(sagaMongoTemplate());
	}
	
	/*@Bean
	public SagaConfiguration<OrderProcessingSaga> orderProcessingSagaSagaConfiguration() {
		return SagaConfiguration.subscribingSagaManager(OrderProcessingSaga.class, c -> inboundEventMessageChannelAdapter());
	}*/

	@Bean
	public org.axonframework.mongo.eventsourcing.eventstore.DefaultMongoTemplate aggregateMongoTemplate() {
		return new org.axonframework.mongo.eventsourcing.eventstore.DefaultMongoTemplate(mongoClient(), database,
				mongoEventCollectionName, mongoEventSnapshotCollectionName);
	}

	@Bean
	public EventStore eventStore() {
		return new EmbeddedEventStore(mongoEventStorageEngine());
	}

	@Bean
	public EventStorageEngine mongoEventStorageEngine() {
		return new MongoEventStorageEngine(aggregateMongoTemplate());
	}
	
	@Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, PaymentDeclinedEvent> paymentDeclinedEventProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, PaymentDeclinedEvent> paymentDeclinedEventKafkaTemplate() {
        return new KafkaTemplate<>(paymentDeclinedEventProducerFactory());
    }
    
    @Bean
    public ProducerFactory<String, PaymentCompletedEvent> paymentCompletedEventProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, PaymentCompletedEvent> paymentCompletedEventKafkaTemplate() {
        return new KafkaTemplate<>(paymentCompletedEventProducerFactory());
    }
    
    @Bean
	  public Map<String, Object> consumerConfigs() {
	    Map<String, Object> props = new HashMap<>();
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, "paymentservice");

	    return props;
	  }

	  @Bean
	  public ConsumerFactory<String, PaymentInitiatedEvent> consumerFactory() {
	    return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
	            new JsonDeserializer<>(PaymentInitiatedEvent.class));
	  }

	  @Bean
	  public ConcurrentKafkaListenerContainerFactory<String, PaymentInitiatedEvent> kafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, PaymentInitiatedEvent> factory =
	        new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());
	    return factory;
	  }
}
