<p align="center">
 <img src="https://raw.githubusercontent.com/Abedalkareem/AMDots/master/amdots_logo.png"  width="350">  </center>
</p>

<br>
AMDots is a loading indicator with dots moving like google loading, there is three type of the loading, scale, jump and shake, each one of them gives you a cool loading to show to the user. 
<br>
<br>

## Screenshots

 <img src="https://raw.githubusercontent.com/Abedalkareem/AMDots-Android/master/screenshot.gif"  width="350">  </center>


## Example

To run the example project, clone the repo, and run AMDots project.

## How to use

-Add the view to your ```xml```

```
    <com.dots.abedalkareem.amdotsview.AMDots
        android:id="@+id/dotsCenter"
        android:layout_width="100dp"
        android:layout_height="50dp"
        app:spacing="10" // space between each dot
        android:layout_centerHorizontal="true"
        android:layout_centerVertical="true"
        app:animationDuration="500" // duration for each round
        app:animationType="scale"/> // type of animation, 
```

-In you activity just start or stop the animation.

```
    val dot = findViewById(R.id.dotsCenter)
    dot.start() // to start
    dot.stop() // to stop
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

and in  ```dependencies```  add this line  ```implementation 'com.github.Abedalkareem:AMDots-Android:1.0.0'```

```
dependencies {
  implementation 'com.github.Abedalkareem:AMDots-Android:1.0.0'
}
```



## Author

Abedalkareem, abedalkareem.omreyh@yahoo.com

## License

AMDots is available under the MIT license. See the LICENSE file for more info.
