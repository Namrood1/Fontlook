package com.aelaf.fontlook

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FontViewerActivity : AppCompatActivity() {

    private lateinit var fontTextView: TextView
    private lateinit var glyphsRecyclerView: RecyclerView
    private lateinit var headerScrollView: HorizontalScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_font_viewer)

        fontTextView = findViewById(R.id.fontTextView)
        glyphsRecyclerView = findViewById(R.id.glyphsRecyclerView)
        headerScrollView = findViewById(R.id.headerScrollView)

        // Load font from assets or resources
        val fontPath = "fonts/your_custom_font.ttf" // Change to your font path
        val typeface = Typeface.createFromAsset(assets, fontPath)
        fontTextView.typeface = typeface

        setupGlyphs()
        setupScrollAnimation()
    }

    private fun setupGlyphs() {
        val glyphs = getGlyphs() // Implement this method to get glyphs based on the font
        glyphsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        glyphsRecyclerView.adapter = GlyphAdapter(glyphs) // Create a GlyphAdapter class to display glyphs
    }

    private fun setupScrollAnimation() {
        headerScrollView.doOnPreDraw {
            ViewCompat.setTranslationY(headerScrollView, -headerScrollView.height.toFloat())
            headerScrollView.animate().translationY(0f).setDuration(300).start()
        }
    }

    private fun getGlyphs(): List<String> {
        // Return a list of glyphs based on the selected font's code points.
        return listOf("A", "B", "C", "D") // Example glyphs; replace with actual code points.
    }
}
