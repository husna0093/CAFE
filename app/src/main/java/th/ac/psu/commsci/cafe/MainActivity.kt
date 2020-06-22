package th.ac.psu.commsci.cafe


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //button
        val mShowAlertDialogBtn = findViewById<Button>(R.id.showAlertDialogBtn)
        //textView
        val mTxtView = findViewById<TextView>(R.id.txtView)

        //button click to show alertdialog
        mShowAlertDialogBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            // String array for alert dialog multi choice items
            val OrdersArray = arrayOf("Espresso____________________50", "GreenTea___________________60", "Latte________________________55",
                "Capuchino__________________50", "Ovantine____________________45", "Cocoa______________________65")
            // Boolean array for initial selected items
            val checkedOrdersArray = booleanArrayOf(
                    true, // ESPRESSO checked
                    false, // GREEN TEA
                    false, // LATTE
                    true, // CAPUCHINO
                    false, // OVANTINE
                    false  // COCOA
            )
            // Convert the color array to list
            val colorsList = Arrays.asList(*OrdersArray)
            //setTitle
            builder.setTitle("Select Orders")
            //make alert dialog multi choice
            builder.setMultiChoiceItems(
                    OrdersArray,
                    checkedOrdersArray
            ) { dialog, which, isChecked ->
                // Update the current focused item's checked status
                checkedOrdersArray[which] = isChecked
                // Get the current focused item
                val currentItem = colorsList[which]
                // Notify the current action
                Toast.makeText(applicationContext, currentItem + " " + isChecked, Toast.LENGTH_SHORT
                ).show()
            }
            // Set the positive/yes button click listener
            builder.setPositiveButton("OK") {dialog  , which ->
                // Do something when click positive button
                mTxtView.text = "Orders that customers want ..... \n"
                for (i in checkedOrdersArray.indices) {
                    val checked = checkedOrdersArray[i]
                    if (checked) {
                        mTxtView.text = mTxtView.text.toString() + colorsList[i] + "\n"
                    }
                }
            }
            // Set the neutral/cancel button click listener
            builder.setNeutralButton("Cancel") { dialog, which ->
                // Do something when click the neutral button
            }
            val dialog = builder.create()
            // Display the alert dialog on interface
            dialog.show()
        }

    }
}