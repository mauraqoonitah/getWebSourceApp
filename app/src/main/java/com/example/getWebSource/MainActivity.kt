package com.example.getWebSource

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.databinding.DataBindingUtil
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
//        fun clickHandlerFunction(view: View) {
//        addNickname(view)

        binding.doneButton.setOnClickListener {
            addUrlname(it)
        }

        // access the items of the list
        val choose = resources.getStringArray(R.array.Choose)
        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this, android.R.layout.simple_spinner_item, choose
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :  AdapterView.OnItemClickListener,
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    Toast.makeText(this@MainActivity,
                        getString(R.string.selected_item) + " " +
                                "" + choose[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(this@MainActivity,
                         "nothing selected"
                               , Toast.LENGTH_SHORT).show()                }

                override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                }


            }

        }

        }

    private fun addUrlname(view: View) {
//        val editText = findViewById<EditText>(R.id.nickname_edit)
//        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)

    binding.apply {
//        nicknameTextView.text = editText.text
        // convert the Editable to a String
//        binding.nicknameText.text = binding.nicknameEdit.text.toString()
        myName.urlInput = url_edit.text.toString()

//        editText.visibility = View.GONE
        url_edit.visibility = View.GONE

//        view.visibility = View.GONE
        doneButton.visibility = View.GONE

//        nicknameTextView.visibility = View.VISIBLE
        urlText.visibility = View.VISIBLE
        invalidateAll()

    }
        // Hide the keyboard.
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }

    private fun updateUrlname (view: View) {
        val editText = findViewById<EditText>(R.id.url_edit)
        val doneButton = findViewById<Button>(R.id.done_button)

        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE
        // Set the focus to the edit text.
        editText.requestFocus()
        // Show the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)

    }




}