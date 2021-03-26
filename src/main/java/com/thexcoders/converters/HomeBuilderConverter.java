package com.thexcoders.converters;

import com.thexcoders.classes.HomeStudent;
import com.thexcoders.classes.Student;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Set;

public class HomeBuilderConverter implements Converter<String, HomeStudent>, GenericConverter {
	@Override
	public HomeStudent convert(String source) {
		String[] list = source.split(",");
		System.err.println(source+"\n");
		System.err.println(list);
		return new HomeStudent();
	}

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return null;
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		return null;
	}
}
