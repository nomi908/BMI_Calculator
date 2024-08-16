    package com.example.bmicalculator

    import android.app.AlertDialog
    import android.graphics.Color
    import android.os.Bundle
    import android.provider.CalendarContract.Colors
    import android.util.Log
    import android.widget.Button
    import android.widget.SeekBar
    import android.widget.TextView
    import android.widget.Toast
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.cardview.widget.CardView
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import androidx.core.view.isNotEmpty
    import com.google.android.material.textfield.TextInputEditText

    class bmicalculator : AppCompatActivity() {
        private lateinit var malecard: CardView
        private lateinit var femalecard: CardView
        private lateinit var heighttext:TextView
        private  lateinit var seekbarho:SeekBar
        private  lateinit var weightipt: TextInputEditText
        private lateinit var ageipt: TextInputEditText
        private lateinit var calculatebtn:Button
        var seekbarvalue:Int = 0

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_bmicalculator)

            malecard = findViewById<CardView>(R.id.malecard)
            femalecard = findViewById<CardView>(R.id.femalecard)
            heighttext = findViewById(R.id.heighttextbox)
            seekbarho = findViewById<SeekBar>(R.id.seekbarhold)
            weightipt = findViewById(R.id.weightinput)
            ageipt = findViewById(R.id.ageinput)
            calculatebtn = findViewById(R.id.btncalculation)

            //male and female seletion method
            malecard.setOnClickListener {

                selectcard(malecard)
                Toast.makeText(this, "Male Selected", Toast.LENGTH_SHORT).show()
                malecard.isSelected = true
                femalecard.isSelected = false
            }
            femalecard.setOnClickListener {
                selectcard(femalecard)
                Toast.makeText(this, "Female Selected", Toast.LENGTH_SHORT).show()
                malecard.isSelected = false
                femalecard.isSelected = true
            }


            //seekbar process

            seekbarho.max = 80
            seekbarho.progress = 0

            heighttext.text = "120cm"

            seekbarho.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    seekbarvalue = 120 + progress
                    heighttext.text = seekbarvalue.toString() + "cm"
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {


                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            })

           calculatebtn.setOnClickListener {
              if (malecard.isSelected or femalecard.isSelected){
                 if (ageipt.text!!.isNotEmpty() && weightipt.text!!.isNotEmpty()){
                     if (ageipt.text.toString().toInt() > 20){
                         bmicalculation()
                     }else{
                         Toast.makeText(this, "age should be greate than 20+", Toast.LENGTH_SHORT).show()
                     }
                 }else{
                     Toast.makeText(this, "check weight or age are filled", Toast.LENGTH_SHORT).show()
                 }
              }else{
                  Toast.makeText(this, "Kindly fill all required Fields!!", Toast.LENGTH_SHORT).show()
              }
           }
            


        }

        private fun bmicalculation(){
            val ageStr = ageipt.text.toString()
            val weightStr = weightipt.text.toString()
            if (ageStr.isNotEmpty() || weightStr.isNotEmpty()){
//                val age = ageStr.toDouble()
                val weight = weightStr.toDouble()
                val heightM: Double = seekbarvalue.toDouble() / 100
                val bmi = weight / (heightM * heightM)
                val builder = AlertDialog.Builder(this)

                builder.setTitle("BMI CALCULATION")
                    .setMessage("Your BMI: ${"%.2f".format(bmi)}")
                    .setPositiveButton("OK"){dialog, which ->
                        dialog.dismiss()
                    }
                    .setNegativeButton("Cancel") { dialog, which ->
                        // Negative button click action (optional)
                        dialog.dismiss() // Dismiss the dialog
                    }
                    .show()



//                Toast.makeText(this, "Your BMI: ${"%.2f".format(bmi)}", Toast.LENGTH_SHORT).show()


            }else{
                    Toast.makeText(this, "Fill all Fields!!", Toast.LENGTH_SHORT).show()

                }

        }

        private fun selectcard(cardView: CardView){
            malecard.setCardBackgroundColor(Color.WHITE)
            femalecard.setCardBackgroundColor(Color.WHITE)

    //        set backgroudcolor when selected
            cardView.setCardBackgroundColor(Color.GRAY)
        }
    }