<p align="center">
 <img src="https://raw.githubusercontent.com/Abedalkareem/AMDots/master/amdots_logo.png"  width="350">  </center>
</p>

<br>
AMDots is a loading indicator with dots moving like google loading, there are three types of the loading: scale, jump, and shake. 
<br>
<br>

## Screenshots

 <img src="https://raw.githubusercontent.com/Abedalkareem/AMDots-Android/master/screenshot.gif"  width="350">  </center>


## Example

To run the example, clone the repo, and run the AMDots project.

## How to use

- Add the view to your ```xml```

```xml
<com.dots.abedalkareem.amdotsview.AMDots
    android:id="@+id/dotsCenter"
    android:layout_width="100dp"
    android:layout_height="50dp"
    android:layout_centerHorizontal="true"
    android:layout_centerVertical="true"
    app:spacing="10" // space between each dot
    app:animationDuration="500" // duration for each round
    app:animationType="scale"/> // type of animation
```

- In you activity just start or stop the animation.

```kotlin
val dot = findViewById(R.id.dotsCenter)
dot.start() 
dot.stop()
```

- To add it programmatically.

```kotlin
val parent = findViewById<ViewGroup>(R.id.parent)
val params = RelativeLayout.LayoutParams(200, 100)
val dotsView = AMDotsView(
      this, listOf(
        Color.parseColor("#3cba54"),
        Color.parseColor("#f4c20d"),
        Color.parseColor("#db3236")
    )
)
parent.addView(dotsView, params)
```  


## Installation

AMDots is available through jitpack. To install it,
simply add the ```maven { url 'https://jitpack.io' }``` to your build.gradle:

```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

and in  ```dependencies```  add this line  ```implementation 'com.github.Abedalkareem:AMDots-Android:1.0.1'```

```
dependencies {
  implementation 'com.github.Abedalkareem:AMDots-Android:1.0.1'
}
```



## Author

Abedalkareem, abedalkareem.omreyh@yahoo.com

## License

AMDots is available under the MIT license. See the LICENSE file for more info.
