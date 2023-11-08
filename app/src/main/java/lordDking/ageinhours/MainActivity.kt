package lordDking.ageinhours

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvHours : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvHours = findViewById(R.id.tvHours)
        val btnDatePicker: Button = findViewById(R.id.btnDate)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }
        fun clickDatePicker(){

            val myCalender = Calendar.getInstance()
            val year = myCalender.get(Calendar.YEAR)
            val month = myCalender.get(Calendar.MONTH)
            val day = myCalender.get(Calendar.DAY_OF_MONTH)

            val dpd =  DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{ view, selectedyear, selectedmonth, selecteddayOfMonth ->


                val selectedDate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"

                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate.time / 3600000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateInMinutes  = currentDate.time / 3600000

                val diffInMinutes = currentDateInMinutes - selectedDateInMinutes

                tvHours?.text = diffInMinutes.toString()


            },

                year,
                month,
                day
            )
            dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
            dpd.show()

        }
    }