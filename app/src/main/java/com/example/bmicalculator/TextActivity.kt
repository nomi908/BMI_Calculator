package com.example.bmicalculator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text


class TextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)

        enableEdgeToEdge()
        setContentView(R.layout.activity_text)
        val spinner = findViewById<Spinner>(R.id.Spinner)
        val items = listOf("Sentence Case", "Title Case", "Upper Case", "Lower Case")
        val adapter = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items)
        spinner.adapter = adapter
        val inputxt = findViewById<TextInputEditText>(R.id.inputxt)
        val change = findViewById<CardView>(R.id.change)
        val reset = findViewById<CardView>(R.id.resetxt)
        var selectedtext = items[0]
        val btncopy = findViewById<CardView>(R.id.btncopy)

       spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
           override fun onItemSelected(
               parent: AdapterView<*>?,
               view: View?,
               position: Int,
               id: Long
           ) {
               selectedtext = items[position]
           }

           override fun onNothingSelected(parent: AdapterView<*>?) {
               TODO("Not yet implemented")
           }
       }
        change.setOnClickListener{
            val inputtext = inputxt.text.toString()
            val transformtext = when(selectedtext){
                "Sentence Case" -> inputtext.toLowerCase().replaceFirstChar { it.uppercase()}
                "Upper Case" -> inputtext.uppercase()
                "Lower Case" -> inputtext.lowercase()
                "Title Case" -> inputtext.split(" ").joinToString(" ") { it.lowercase().replaceFirstChar { char -> char.uppercase() } }

                else -> {
                    Toast.makeText(this, "Enter Your Text!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            inputxt.setText(transformtext)
         }

        reset.setOnClickListener{
            inputxt.setText("")
        }

        btncopy.setOnClickListener{
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val getinput = inputxt.text.toString()
            val clipData = ClipData.newPlainText("text", getinput)

            clipboardManager.setPrimaryClip(clipData)

            Toast.makeText(this, "Text Copied", Toast.LENGTH_SHORT).show()





//            val clipboardmanager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//            val getdata = inputxt.text.toString()
//            val clipData = ClipData.newPlainText("text", getdata)
//            clipboardmanager.setPrimaryClip(clipData)
//
//            Toast.makeText(this, "Text Copied", Toast.LENGTH_SHORT).show()
//

        }













//        val spinner = findViewById<Spinner>(R.id.Spinner)
//        val items = listOf("Sentence Case", "Upper Case", "Lower Case", "Title Case")
//        val adapter = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items)
//        spinner.adapter = adapter
//        val inputxt = findViewById<TextInputEditText>(R.id.inputxt)
//        val change = findViewById<CardView>(R.id.change)
//        val reset = findViewById<CardView>(R.id.resetxt)
//        var selectedTransformation = items[0]
//
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//
//                selectedTransformation = items[position]
//
//
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//        }
//        change.setOnClickListener{
//            val inputtext = inputxt.text.toString()
//
//
//            val transformtext = when (selectedTransformation) {
//                "Sentence Case" -> inputtext.toLowerCase().replaceFirstChar { it.uppercase() }
//                "Upper Case" -> inputtext.uppercase()
//                "Lower Case" -> inputtext.lowercase()
//                "Title Case" -> inputtext.split(" ").joinToString(" ") { it.lowercase().replaceFirstChar { char -> char.uppercase() } }
//                else -> {
//                    Toast.makeText(this, "Enter a text!!", Toast.LENGTH_SHORT).show()
//                    return@setOnClickListener
//                }
//            }
//            inputxt.setText(transformtext)
//
//
//        }
//
//        reset.setOnClickListener{
//            if (inputxt.text.toString().isNotEmpty()){
//                inputxt.setText("")
//            }
//
//
//
//        }
//
    }
}
