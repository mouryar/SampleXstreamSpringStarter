package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

@Configuration
public class HoodConfiguration {
	
	@Bean
	public CommandLineRunner runner(XStream xstream, Converter converter){
		
		return args -> {
			System.err.println(xstream.toXML(new Person("Mourya", "Rajala")));
		};
	}

	@Bean
	public Converter getConvertor() {
		Converter converter = new Converter() {
			
			@Override
			public boolean canConvert(Class arg0) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public Object unmarshal(HierarchicalStreamReader arg0, UnmarshallingContext arg1) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
				// TODO Auto-generated method stub
				Person person = (Person) source;
				writer.startNode("name");
				writer.setValue(person.firstName+" "+person.lastName);
				writer.endNode();
				
			}
		};
		return converter;
	}
	
	@Bean
	public XStream getXstream(){
		return new XStream();
	}

	@Bean
	public HierarchicalStreamDriver getJsonHierarchicalStreamDriver() {
		return new JsonHierarchicalStreamDriver();
	}
	
	public static class Person{
		public final String firstName;
		public final String lastName;
		
		public Person(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
	}

}
