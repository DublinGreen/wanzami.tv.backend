package tv.wanzami.service;

import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

public class UploadCoercing implements Coercing<Object, Object> {

    @Override
    public Object serialize(Object dataFetcherResult) throws CoercingSerializeException {
        // Not needed for file uploads
        throw new CoercingSerializeException("File upload serialization is not supported.");
    }

    @Override
    public Object parseValue(Object input) throws CoercingParseValueException {
        // Pass through the raw file
        return input;
    }

    @Override
    public Object parseLiteral(Object input) throws CoercingParseLiteralException {
        throw new CoercingParseLiteralException("Parsing literals for file uploads is not supported.");
    }
}
