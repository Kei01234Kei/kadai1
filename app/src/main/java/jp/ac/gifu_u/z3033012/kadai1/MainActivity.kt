package jp.ac.gifu_u.z3033012.kadai1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.NumberFormatException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.edit_text)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            Toast.makeText(this, editText.text.toString(), Toast.LENGTH_SHORT).show()
        }
        val buttonTest = findViewById<Button>(R.id.buttonTest)
        buttonTest.setOnClickListener {
            val text = editText.text.toString()
            getData(text)
        }
    }

    private fun getData(text: String) {
//        lifecycleScope.launch {
//            val url = "https://api.genderize.io/?name=$text"
//            val result = backGroundTask(url)
//            jsonTask(result)
//        }
        val name: TextView = findViewById(R.id.name)
        name.text = text
    }

    private suspend fun backGroundTask(url: String): String {
        var result = ""
        try {
            val urlObj = URL(url)
            val br = BufferedReader(InputStreamReader(urlObj.openStream()))
            result = br.readText()
        } catch(e: NumberFormatException) {
            e.printStackTrace()
        }
        return result
    }

    private fun jsonTask(result: String) {
        val name: TextView = findViewById(R.id.name)
        val gender: TextView = findViewById(R.id.gender)
        val probability: TextView = findViewById(R.id.probability)
        val count: TextView = findViewById(R.id.count)
        val jsonObj = JSONObject(result)
        val nameKey = jsonObj.getString("name")
        val genderKey = jsonObj.getString("gender")
        val probabilityKey = jsonObj.getString("probability")
        val countKey = jsonObj.getString("count")

        name.text = nameKey
        gender.text = genderKey
        probability.text = probabilityKey
        count.text = countKey
    }
}
