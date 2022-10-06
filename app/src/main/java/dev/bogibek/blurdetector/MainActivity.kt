package dev.bogibek.blurdetector

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import dev.bogibek.blurdetector.databinding.ActivityMainBinding
import dev.bogibek.blurdetector.utils.BlurrinessDetector
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

    }

    private fun initViews() {
        binding.apply {
            bIsBlurred.setOnClickListener {
                val imageUrl = if (etUrl.text.isNotEmpty()) etUrl.text.toString() else ""
                val imageBitmap = Picasso.get().load(imageUrl).get()
                if (isBlurred(imageBitmap)) tvResult.text = "This image is blurred"
                else tvResult.text = "This image is not blurred"
            }
        }
    }

    private fun isBlurred(imageBitmap: Bitmap): Boolean {
        return runBlocking{ BlurrinessDetector.isBlurredPhoto(imageBitmap) }
    }

}