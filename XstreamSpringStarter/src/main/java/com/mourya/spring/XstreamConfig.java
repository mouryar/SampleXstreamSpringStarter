package com.mourya.spring;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

@Configuration
public class XstreamConfig {
	
	@Bean
	@ConditionalOnMissingBean
	public XStream getXstream(Optional<HierarchicalStreamDriver> driver)
	{
		return new XStream(driver.orElse(new Dom4JDriver()));
	}
	
	@Bean
	public List<Converter> getConverters(List<Converter> converters, XStream xstream)
	{
		converters.forEach(xstream::registerConverter);
		return converters;
	}
}
