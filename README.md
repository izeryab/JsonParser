# JsonParser

### Methods:

![image](https://user-images.githubusercontent.com/51020585/199408667-9e2ea00d-be53-460c-ae1f-3296b97271d7.png)



## How to use this for getting nested value from json

```
public class Main {
	public static void main(String[] args) {

		String json = "{\"data\":[{\"stuff\":[\n" + "    {    \"onetype\":[\n"
				+ "        {\"id\":1,\"name\":\"John Doe\"},\n" + "        {\"id\":2,\"name\":\"Don Joeh\"}\n"
				+ "    ]},\n" + "    {\"othertype\":[\n" + "        {\"id\":2,\"company\":\"ACME\"}\n" + "    ]}]\n"
				+ "},{\"otherstuff\":[\n" + "    {\"thing\":\n" + "        [[1,42],[2,2]]\n" + "    }]\n" + "}]}";

		String name = JsonUtil.getJsonElementFromJsonStringUsingPath("data>0>stuff>0>onetype>0>name", json, ">").getAsString();
		int id= JsonUtil.getJsonElementFromJsonStringUsingPath("data>0>stuff>0>onetype>0>id",json,">").getAsInt();
		
		System.out.println("id : "+id);
		System.out.println("name : "+name);
	}

}
```

## JAVA DOCS
https://izeryab.github.io/JsonParser/JsonUtil.html
