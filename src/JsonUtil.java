import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * class contains methods for json related functionality like
 * getValuefromJson,getValuefromJsonString parseJson from string
 * 
 * @author Zeryab Khan
 */
public class JsonUtil {

	private static final JsonParser jsonParser = new JsonParser();

	/**
	 * 
	 * Method to get Json element from json string using key(like name) or
	 * index(like 1 in case of json array) or path(name.1.firstname)
	 * 
	 * @param path         key or path to get dept like data.input.name where . is
	 *                     the separater for keys
	 * @param jsonString   json in string format
	 * @param keySeparater key separater used to get keys from path
	 * @return JsonElement
	 */
	public static JsonElement getJsonElementFromJsonStringUsingPath(String path, String jsonString,
			String keySeparater) {
		String[] keys = path.split("\\" + keySeparater);
		JsonElement jsonObject = parseJson(jsonString);
		return getJsonElementFromJsonRecursively(keys, jsonObject, 0, keys.length);
	}

	/**
	 * 
	 * Method to get Json element from JsonObject or json array using key or index
	 * 
	 * @param path         key or path to get dept like data.input.name where . is
	 *                     the separater for keys
	 * @param jsonElement  JsonElement
	 * @param keySeparater key separater used to get keys from path
	 * @return JsonElement
	 */
	public static JsonElement getJsonElementFromJsonUsingPath(String path, JsonElement jsonElement,
			String keySeparater) {
		String[] keys = path.split("\\" + keySeparater);
		return getJsonElementFromJsonRecursively(keys, jsonElement, 0, keys.length);
	}

	/**
	 * 
	 * Method to get Json element from JsonObject or json array using key or index
	 * 
	 * @param key               Key of element
	 * @param jsonObjectElement JsonElement
	 * @return JsonElement
	 */
	private static JsonElement getJsonElementFromJsonUsingKeyOrIndex(String key, JsonElement jsonObjectElement) {
		JsonElement jsonElement = new JsonObject();
		if (jsonObjectElement instanceof JsonObject) {
			jsonElement = getJsonElementFromJsonObject(key, (JsonObject) jsonObjectElement);
		} else if (jsonObjectElement instanceof JsonArray) {
			jsonElement = getJsonElementFromJsonArray(key, (JsonArray) jsonObjectElement);
		}

		return jsonElement;
	}

	/**
	 * 
	 * Method to get Json element from Json array using string currentKeyIndex
	 * recusively
	 * 
	 * @param keys            string array of keys
	 * @param jsonElement     JsonElement
	 * @param currentKeyIndex current index of key
	 * @param totalNoOfKeys   total number of keys (this will also be the depth)
	 * @return JsonElement
	 */
	private static JsonElement getJsonElementFromJsonRecursively(String[] keys, JsonElement jsonElement,
			int currentKeyIndex, int totalNoOfKeys) {
		JsonElement value = JsonUtil.getJsonElementFromJsonUsingKeyOrIndex(keys[currentKeyIndex], jsonElement);
		if (currentKeyIndex == totalNoOfKeys - 1) {
			return value;
		} else {
			jsonElement = value;
			currentKeyIndex++;
		}
		return getJsonElementFromJsonRecursively(keys, jsonElement, currentKeyIndex, totalNoOfKeys);
	}

	/**
	 * 
	 * Method to get Json element from JsonObject
	 * 
	 * @param key               Key of element
	 * @param jsonObjectElement JsonObject
	 * @return JsonElement
	 */
	private static JsonElement getJsonElementFromJsonObject(String key, JsonObject jsonObjectElement) {
		JsonElement jsonElement = jsonObjectElement.get(key);
		return jsonElement;
	}

	/**
	 * 
	 * Method to get Json element from Json array using string index
	 * 
	 * @param index     index of element in array in string i.e "1" ,"3"
	 * @param jsonArray JsonArray
	 * @return JsonElement
	 */
	private static JsonElement getJsonElementFromJsonArray(String index, JsonArray jsonArray) {
		int i = 0;
		try {
			i = Integer.parseInt(index);
		} catch (NumberFormatException e) {
			return jsonArray;
		}
		JsonElement jsonElement = getJsonElementFromJsonArray(i, jsonArray);

		return jsonElement;
	}

	/**
	 * Method to get Json Element from Json array
	 * 
	 * @param index     index of item in json array in numeric
	 * @param jsonArray JsonArray
	 * @return JsonElement
	 */
	private static JsonElement getJsonElementFromJsonArray(int index, JsonArray jsonArray) {
		return jsonArray.get(index);
	}

	/**
	 * 
	 * Method to parse Json from Json String
	 * 
	 * @param jsonString json in String format
	 * @return JsonElement
	 */
	public static JsonElement parseJson(String jsonString) {
		if (jsonString.isEmpty()) {
			return new JsonObject();
		}
		JsonElement jsonElement = jsonParser.parse(jsonString);
		return jsonElement;
	}

}
