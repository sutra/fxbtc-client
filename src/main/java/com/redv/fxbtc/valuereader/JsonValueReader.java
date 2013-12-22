package com.redv.fxbtc.valuereader;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonValueReader<T> implements ValueReader<T> {

	private final Logger log = LoggerFactory.getLogger(JsonValueReader.class);

	private final ObjectMapper objectMapper;

	private final Class<T> valueType;

	public JsonValueReader(ObjectMapper objectMapper, Class<T> valueType) {
		this.objectMapper = objectMapper;
		this.valueType = valueType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T read(InputStream inputStream) throws IOException {

		if (log.isDebugEnabled()) {
			String content = IOUtils.toString(inputStream);
			log.debug("Reading {} from {}.", valueType, content);

			inputStream = IOUtils.toInputStream(content);
		}

		return objectMapper.readValue(inputStream, valueType);
	}

}