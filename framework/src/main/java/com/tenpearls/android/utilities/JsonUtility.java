package com.tenpearls.android.utilities;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;

/**
 * <p>
 * This class is responsible for conversion of Json strings into Json objects
 * and vice versa. It internally uses <a
 * href="https://code.google.com/p/google-gson/">Google's GSON library</a> to
 * parse Json strings into Json objects.
 *
 * 
 */
public class JsonUtility {

	/**
	 * Parses a Json string into Json object.
	 * 
	 * @param jsonString The Json string
	 * @return {@link JsonObject} instance created from the Json string
	 */
	public static JsonObject parseToJsonObject (String jsonString) {
		JsonParser parser = new JsonParser ();
		return (JsonObject) parser.parse (jsonString);
	}

	/**
	 * Parses a Json string into Json array.
	 * 
	 * @param jsonString The Json string
	 * @return {@link JsonArray} instance created from the Json string
	 */

	public static JsonArray parseToJsonArray (String jsonString) {

		JsonParser parser = new JsonParser ();
		return (JsonArray) parser.parse (jsonString);
	}


	/**
	 * Get string value for a key on a Json object.
	 * 
	 * @param jsonObject The Json object which has the provided key
	 * @param key Json key, whose values needs to be returned
	 * @return String value of the Json key
	 */
	public static String getString (JsonObject jsonObject, String key) {

		return getString(jsonObject, key, "");
	}

	/**
	 * Get string value for a key on a Json object.
	 * 
	 * @param jsonObject The Json object which has the provided key
	 * @param key Json key, whose values needs to be returned
	 * @param defaultValue Default value
	 * @return String value of the Json key. Returns defaultValue if Json object
	 *         is null OR the provided Json key does not exist
	 */
	public static String getString (JsonObject jsonObject, String key, String defaultValue) {

		JsonPrimitive jsonPrimitive = getJsonPrimitive(jsonObject, key);
		if(jsonPrimitive == null
				|| !jsonPrimitive.isString()) {
			return defaultValue;
		}

		return StringUtility.validateEmptyString(jsonPrimitive.getAsString(), defaultValue);
	}

	public static String getString (JsonArray jsonArray, int index, String defaultValue) {

		JsonPrimitive jsonPrimitive = getJsonPrimitive(jsonArray, index);
		if(jsonPrimitive == null
				|| !jsonPrimitive.isString()) {
			return defaultValue;
		}

		return StringUtility.validateEmptyString(jsonPrimitive.getAsString(), defaultValue);
	}

	/**
	 * Get integer value for a key on a Json object.
	 * 
	 * @param jsonObject The Json object which has the provided key
	 * @param key Json key, whose values needs to be returned
	 * @return Integer value of the Json key
	 */
	public static int getInt (JsonObject jsonObject, String key) {

		return getInt(jsonObject, key, 0);
	}

	/**
	 * Get integer value for a key on a Json object.
	 * 
	 * @param jsonObject The Json object which has the provided key
	 * @param key Json key, whose values needs to be returned
	 * @param defaultValue Default value
	 * @return Integer value of the Json key. Returns defaultValue if Json
	 *         object is null OR the provided Json key does not exist
	 */
	public static int getInt (JsonObject jsonObject, String key, int defaultValue) {

		JsonPrimitive jsonPrimitive = getJsonPrimitive(jsonObject, key);
		if(jsonPrimitive == null
				|| !jsonPrimitive.isNumber()) {
			return defaultValue;
		}
		return jsonPrimitive.getAsInt();
	}

	public static int getInt (JsonArray jsonArray, int index, int defaultValue) {

		JsonPrimitive jsonPrimitive = getJsonPrimitive(jsonArray, index);
		if(jsonPrimitive == null
				|| !jsonPrimitive.isString()) {
			return defaultValue;
		}
		return jsonPrimitive.getAsInt();
	}

	/**
	 * Get boolean value for a key on a Json object.
	 * 
	 * @param jsonObject The Json object which has the provided key
	 * @param key Json key, whose values needs to be returned
	 * @return Boolean value of the Json key. Returns defaultValue if Json
	 *         object is null OR the provided Json key does not exist
	 */
	public static boolean getBoolean (JsonObject jsonObject, String key) {

		return getBoolean(jsonObject, key, false);
	}

	/**
	 * Get boolean value for a key on a Json object.
	 * 
	 * @param jsonObject The Json object which has the provided key
	 * @param key Json key, whose values needs to be returned
	 * @param defaultValue Default value
	 * @return Boolean value of the Json key. Returns defaultValue if Json
	 *         object is null OR the provided Json key does not exist
	 */
	public static boolean getBoolean (JsonObject jsonObject, String key, boolean defaultValue) {

		JsonPrimitive jsonPrimitive = getJsonPrimitive(jsonObject, key);
		if(jsonPrimitive == null
				|| !jsonPrimitive.isBoolean()) {
			return defaultValue;
		}
		return jsonPrimitive.getAsBoolean();
	}

	public static boolean getBoolean (JsonArray jsonArray, int index, boolean defaultValue) {

		JsonPrimitive jsonPrimitive = getJsonPrimitive(jsonArray, index);
		if(jsonPrimitive == null
				|| !jsonPrimitive.isBoolean()) {
			return defaultValue;
		}
		return jsonPrimitive.getAsBoolean();
	}

	/**
	 * Get double value for a key on a Json object.
	 * 
	 * @param jsonObject The Json object which has the provided key
	 * @param key Json key, whose values needs to be returned
	 * @return Double value of the Json key. Returns defaultValue if Json object
	 *         is null OR the provided Json key does not exist
	 */
	public static double getDouble (JsonObject jsonObject, String key) {

		return getDouble(jsonObject, key, 0.0);
	}


    /**
     * Get double value for a key on a Json object.
     *
     * @param jsonArray The Json Array which has the provided index
     * @param index Json Index, whose values needs to be returned
     * @return Double value of the Json key. Returns defaultValue if Json object
     *         is null OR the provided Json key does not exist
     */


	/**
	 * Get double value for a key on a Json object.
	 * 
	 * @param jsonObject The Json object which has the provided key
	 * @param key Json key, whose values needs to be returned
	 * @param defaultValue Default value
	 * @return Double value of the Json key. Returns defaultValue if Json object
	 *         is null OR the provided Json key does not exist
	 */
	public static double getDouble (JsonObject jsonObject, String key, double defaultValue) {

		JsonPrimitive jsonPrimitive = getJsonPrimitive(jsonObject, key);
		if(jsonPrimitive == null
				|| !jsonPrimitive.isNumber()) {
			return defaultValue;
		}
		return jsonPrimitive.getAsDouble();
	}

	public static double getDouble (JsonArray jsonArray, int index, double defaultValue) {

		JsonPrimitive jsonPrimitive = getJsonPrimitive(jsonArray, index);
		if(jsonPrimitive == null
				|| !jsonPrimitive.isNumber()) {
			return defaultValue;
		}
		return jsonPrimitive.getAsDouble();
	}

	/**
	 * Get nested Json Object.
	 * 
	 * @param jsonObject The Json object which has the provided key
	 * @param key Json key, whose object needs to be returned
	 * @return {@link JsonObject} instance contained by the provided key.
	 *         Returns {@code null} if jsonObject is null OR the provided Json
	 *         key does not exist
	 */
	public static JsonObject getJsonObject (JsonObject jsonObject, String key) {

		JsonElement jsonElement = getJsonElement(jsonObject, key);
		if (jsonElement == null
				|| !jsonElement.isJsonObject()) {
			return null;
		}

		return jsonElement.getAsJsonObject();
	}

	/**
	 * Get nested Json Array.
	 * 
	 * @param jsonObject The Json object which has the provided key
	 * @param key Json key, whose object needs to be returned
	 * @return {@link JsonArray} instance contained by the provided key. Returns
	 *         {@code null} if jsonObject is null OR the provided Json key does
	 *         not exist
	 */
	public static JsonArray getJsonArray (JsonObject jsonObject, String key) {

		JsonElement jsonElement = getJsonElement(jsonObject, key);
		if (jsonElement == null
				|| !jsonElement.isJsonArray()) {
			return null;
		}

		return jsonElement.getAsJsonArray();
	}


	public static JsonObject getJsonObject(JsonArray jsonArray, int index)
	{
		JsonElement jsonElement = getJsonElement(jsonArray, index);
		if (jsonElement == null
				|| !jsonElement.isJsonObject()) {
			return null;
		}

		return jsonElement.getAsJsonObject();
	}

	public static JsonArray getJsonArray(JsonArray jsonArray, int index)
	{
		JsonElement jsonElement = getJsonElement(jsonArray, index);
		if (jsonElement == null
				|| !jsonElement.isJsonArray()) {
			return null;
		}

		return jsonElement.getAsJsonArray();
	}

	private static JsonPrimitive getJsonPrimitive(JsonObject jsonObject, String key) {
		JsonElement jsonElement = getJsonElement(jsonObject, key);
		if(jsonElement == null
				|| !jsonElement.isJsonPrimitive()) {
			return null;
		}

		return jsonElement.getAsJsonPrimitive();
	}

	private static JsonPrimitive getJsonPrimitive(JsonArray jsonArray, int index) {
		JsonElement jsonElement = getJsonElement(jsonArray, index);
		if(jsonElement == null
				|| !jsonElement.isJsonPrimitive()) {
			return null;
		}

		return jsonElement.getAsJsonPrimitive();
	}

	private static JsonElement getJsonElement(JsonObject jsonObject, String key) {
		if (jsonObject == null) {
			return null;
		}
		if (!jsonObject.has (key)) {
			return null;
		}

		JsonElement jsonElement = jsonObject.get(key);
		if (jsonElement.isJsonNull ()) {
			return null;
		}
		return jsonElement;
	}

	private static JsonElement getJsonElement(JsonArray jsonArray, int index) {
		if (jsonArray == null) {
			return null;
		}
		if (jsonArray.size() < index) {
			return null;
		}

		JsonElement jsonElement = jsonArray.get(index);

		if (jsonElement.isJsonNull()) {
			return null;
		}
		return jsonElement;
	}

	public static boolean isJsonElementNull(JsonElement jsonElement) {
		if(jsonElement == null
				|| jsonElement.isJsonNull()) {
			return true;
		}

		return false;
	}

	public static ArrayList<?> getArrayListFromJsonArray(JsonArray jsonArray) {

		try {
			Gson gson = new Gson();
			return gson.fromJson(jsonArray, ArrayList.class);

		} catch (Exception ex){
			return null;
		}

	}



}
