package com.redv.fxbtc.valuereader;

import static com.redv.fxbtc.FXBTCClient.ENCODING;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
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
	public T read(final InputStream inputStream) throws IOException {
		final String content = IOUtils.toString(inputStream, ENCODING);

		log.debug("Reading {} from \"{}\".", valueType, content);

		try (final InputStream in = IOUtils.toInputStream(content, ENCODING)) {
			return objectMapper.readValue(in, valueType);
		} catch (JsonParseException e) {
			String msg = String.format("Parse from \"%1$s\" failed: %2$s",
					content,
					e.getMessage());
			throw new JsonParseException(msg, e.getLocation(), e);
		}
	}

}
