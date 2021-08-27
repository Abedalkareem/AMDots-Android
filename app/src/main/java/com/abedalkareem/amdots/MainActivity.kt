package com.abedalkareem.amdots

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.dots.abedalkareem.amdotsview.AMDotsView
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

  }

  private fun addDotsView() {
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
  }
}