# Prudhvi-Http-Library
Custom http library
Basic Example with http Request and Response

# Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

      allprojects {
          repositories {
			    ...
	        maven { url 'https://jitpack.io' }
	       }
     }
# Step 2. Add the dependency

    dependencies {
        implementation 'com.github.PrudhviBanki:Prudhvi-Http-Library:1.0.0
    }

# Example code 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Request request = new Request(this, this);
        request.fetchArray(100, Methods.GET, "url", null);
        request.fetchObject(101, Methods.GET, "url", null);

    }

    @Override
    public void successObject(JSONObject obj, int requestId) {
         // check your condition response and display as Object
    }

    @Override
    public void successArray(JSONArray obj, int requestId) {
        // check your condition response and display as JsonArrayList
    }

    @Override
    public void failure(String response, int requestId) {
       //Failure response
    }
