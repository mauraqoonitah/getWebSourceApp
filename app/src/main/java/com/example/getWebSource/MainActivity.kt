package com.example.getWebSource

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.getWebSource.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myName: Words = Words("Maura Qoonitah")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.words = myName


        url_text.setOnClickListener {
            updateUrlname(it)
        }

        binding.doneButton.setOnClickListener {
            addUrlname(it)
        }

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        // access the items of the list
        val choose = resources.getStringArray(R.array.Choose)
        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this, android.R.layout.simple_spinner_item, choose
            )
            adapter.setDropDownViewResource(R.layout.spinner_item)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :  AdapterView.OnItemClickListener,
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.selected_item) + " " +
                                "" + choose[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(
                        this@MainActivity,
                        "nothing selected", Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                }


            }

        }

        }
//button get websource clicked
    private fun addUrlname(view: View) {
        val urlInput = url_edit.text.toString()
        binding.apply {

        myName.urlInput = url_edit.text.toString()
            url_edit.visibility = View.VISIBLE
            doneButton.visibility = View.VISIBLE
            urlText.visibility = View.VISIBLE
            outputText.visibility = View.VISIBLE

        invalidateAll()

            val textChoosen = spinner.selectedItem.toString()
            url_text.text = textChoosen + urlInput

            val queue = Volley.newRequestQueue(this@MainActivity)

            val outputText = textChoosen + urlInput
            // Request a string response from the provided URL.
            val stringRequest = StringRequest(
                Request.Method.GET, outputText,
                Response.Listener<String> { response ->
                    output_text.text = "${response.substring(0)}"
                },
                Response.ErrorListener { output_text.text = "Enter valid url!" })

            // Add the request to the RequestQueue.
            queue.add(stringRequest)



    }
        // Hide the keyboard.
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }
    private fun updateUrlname(view: View) {
        val editText = findViewById<EditText>(R.id.url_edit)
        val doneButton = findViewById<Button>(R.id.done_button)

        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE
        output_text.visibility = View.GONE

        // Set the focus to the edit text.
        editText.requestFocus()
        // Show the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)

    }




}