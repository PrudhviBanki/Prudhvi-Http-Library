# Prudhvi-Http-Library
Custom http library
Basic Example with http Request and Response

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

allprojects {
      repositories {
			...
	     maven { url 'https://jitpack.io' }
	}
}
Step 2. Add the dependency

dependencies {
     implementation 'com.github.PrudhviBanki:Prudhvi-Http-Library:1.0.0
}
