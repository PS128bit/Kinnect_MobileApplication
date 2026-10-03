package com.muhammadanas.i230853

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
 SCREEN 16: EDIT PROFILE
*/

class EditProfileActivity : AppCompatActivity() {

    companion object {
        // Keys for the data sent back to the Profile screen
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_BIO = "extra_bio"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_profile)
        // ime() too, so the form moves up above the keyboard
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        val etName = findViewById<EditText>(R.id.et_edit_name)
        val etBio = findViewById<EditText>(R.id.et_edit_bio)

        // Cancel: nothing changes, just go back
        findViewById<TextView>(R.id.tv_edit_cancel).setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        // Save: send the new name (and the bio, if it was changed) back to Profile
        findViewById<TextView>(R.id.tv_edit_save).setOnClickListener {
            val result = Intent()

            val name = etName.text.toString().trim()
            if (name.isNotEmpty()) {
                result.putExtra(EXTRA_NAME, name)
            }

            val bio = etBio.text.toString().trim()
            if (bio != getString(R.string.edit_bio_value)) {
                result.putExtra(EXTRA_BIO, bio)
            }

            setResult(RESULT_OK, result)
            finish()
        }
    }
}