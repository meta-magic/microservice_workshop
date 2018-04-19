package com.metamagic.ms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.AggregateSnapshotter;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.annotation.DefaultParameterResolverFactory;
import org.axonframework.messaging.annotation.MultiParameterResolverFactory;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.axonframework.mongo.eventsourcing.eventstore.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.MongoTemplate;
import org.axonframework.spring.config.annotation.SpringBeanParameterResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.metamagic.ms.aggregate.ShoppingCart;
import com.metamagic.ms.events.integration.OrderPlacedEvent;
import com.metamagic.ms.events.integration.UserCreatedEvent;
import com.mongodb.MongoClient;

/**
 * @author sagar
 * CONFIG OF DATABASE
 */

@Configuration
public class Config {

	@Value("${spring.data.mongodb.database}")
    private String database;
	
	@Value("${mongodb.events.collection.name}")
	private String mongoEventCollectionName;
	
	@Value("${mongodb.events.snapshot.collection.name}")
	private String mongoEventSnapshotCollectionName;
	
	@Autowired 
	private MongoClient mongoClient;
	
	
	@Autowired
	private EventStore eventStore;
	
	@Bean
	public PersistenceManagerFactory persistenceManagerFactory() {
		return JDOHelper.getPersistenceManagerFactory("PersistenceUnit");
	}
	
	@Bean
	public MongoTemplate axonMongoTemplate(){
		return new DefaultMongoTemplate(mongoClient, database, mongoEventCollectionName, mongoEventSnapshotCollectionName);
	}

	@Bean
	public AggregateFactory<ShoppingCart> shoppingCartAggregateFactory(){
		return new GenericAggregateFactory<ShoppingCart>(ShoppingCart.class);
	}
	
	@Bean
	public EventStorageEngine eventStorageEngine(){
		return new MongoEventStorageEngine(axonMongoTemplate());
	}
	
	@Bean
	public EventSourcingRepository<ShoppingCart> shoppingCartRepository(){
		return new EventSourcingRepository<ShoppingCart>(shoppingCartAggregateFactory(), eventStore, multiParameterResolverFactory(), snapshotTriggerDefinition());
	}
	
	/* SNAPSHOTING CONFIG */
	@Bean
	public Snapshotter snapshotter(){
		List<AggregateFactory<?>> aggregateList = new ArrayList<>();
		aggregateList.add(shoppingCartAggregateFactory());
		return new AggregateSnapshotter(eventStore, aggregateList, multiParameterResolverFactory());
	}
	
	@Bean
	public EventCountSnapshotTriggerDefinition snapshotTriggerDefinition(){
		return new EventCountSnapshotTriggerDefinition(snapshotter(), 5);
	}
	
	@Autowired
	public void configure(EventHandlingConfiguration eventHandlingConfiguration){
		eventHandlingConfiguration.registerSubscribingEventProcessor("testprocessor", c -> eventStore);
	}
	
	/* SPRING BEAN INJECTION CONFIG */
	
	@Bean
	public SpringBeanParameterResolverFactory springBeanParameterResolverFactory(){
		return new SpringBeanParameterResolverFactory();
	}
	
	@Bean
	public DefaultParameterResolverFactory defaultParameterResolverFactory(){
		return new DefaultParameterResolverFactory();
	}
	
	@Bean
	public MultiParameterResolverFactory multiParameterResolverFactory(){
		List<ParameterResolverFactory> delegates = new ArrayList<>();
		delegates.add(defaultParameterResolverFactory());
		delegates.add(springBeanParameterResolverFactory());
		MultiParameterResolverFactory multiParameterResolverFactory = new MultiParameterResolverFactory(delegates);
		return multiParameterResolverFactory;
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
    public ProducerFactory<String, OrderPlacedEvent> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
    
    @Bean
	  public Map<String, Object> consumerConfigs() {
	    Map<String, Object> props = new HashMap<>();
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, "string2");

	    return props;
	  }

	  @Bean
	  public ConsumerFactory<String, UserCreatedEvent> consumerFactory() {
	    return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
	            new JsonDeserializer<>(UserCreatedEvent.class));
	  }

	  @Bean
	  public ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent> kafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent> factory =
	        new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());

	    return factory;
	  }
	
}
