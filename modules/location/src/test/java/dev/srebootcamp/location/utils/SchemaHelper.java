package dev.srebootcamp.location.utils;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import static dev.srebootcamp.location.utils.StreamsHelper.mapFromClassPath;

public interface SchemaHelper {

    static void validateJsonArrayAgainstSchema(String schemaName, String json) throws Exception {
         validateStringAgainstSchema(schemaName, json, JSONArray::new);

    }
    static void validateJsonObjectAgainstSchema(String schemaName, String json) throws Exception {
        validateStringAgainstSchema(schemaName, json, JSONObject::new);
    }
    static void validateStringAgainstSchema(String schemaName, String json, FunctionWithException<String,Object> parser) throws Exception {
        mapFromClassPath(SchemaHelper.class, schemaName, schemaString -> {
            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaString));
            Schema schema = SchemaLoader.load(rawSchema);
            schema.validate(parser.apply(json)); // throws a ValidationException if this object is invalid
            return null;
        });
    }
}
