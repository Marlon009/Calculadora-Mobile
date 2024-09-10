package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {
    private lateinit var inputTextView: TextView
    private lateinit var resultTextView: TextView
    private val expression = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputTextView = findViewById(R.id.input_text_view)
        resultTextView = findViewById(R.id.result_text_view)

        val buttonClear: Button = findViewById(R.id.button_clear)
        val buttonBracketLeft: Button = findViewById(R.id.button_bracket_left)
        val buttonBracketRight: Button = findViewById(R.id.button_bracket_right)
        val buttonDivision: Button = findViewById(R.id.division)
        val buttonMultiplication: Button = findViewById(R.id.button_multiplication)
        val buttonSubtraction: Button = findViewById(R.id.button_subtraction)
        val buttonAddition: Button = findViewById(R.id.button_addition)
        val buttonEquals: Button = findViewById(R.id.button_equals)
        val buttonDot: Button = findViewById(R.id.button_dot)

        val buttonNumbers = listOf(
            findViewById<Button>(R.id.button_0),
            findViewById<Button>(R.id.button_1),
            findViewById<Button>(R.id.button_2),
            findViewById<Button>(R.id.button_3),
            findViewById<Button>(R.id.button_4),
            findViewById<Button>(R.id.button_5),
            findViewById<Button>(R.id.button_6),
            findViewById<Button>(R.id.button_7),
            findViewById<Button>(R.id.button_8),
            findViewById<Button>(R.id.button_9),
        )

        buttonNumbers.forEach { button ->
            button.setOnClickListener {
                appendToExpression(button.text.toString())
            }
        }

        buttonClear.setOnClickListener {
            clearExpression()
        }

        buttonEquals.setOnClickListener {
            calculateResult()
        }

        buttonAddition.setOnClickListener {
            appendToExpression("+")
        }

        buttonSubtraction.setOnClickListener {
            appendToExpression("-")
        }

        buttonMultiplication.setOnClickListener {
            appendToExpression("*")
        }

        buttonDivision.setOnClickListener {
            appendToExpression("/")
        }

        buttonDot.setOnClickListener {
            appendToExpression(".")
        }

        buttonBracketLeft.setOnClickListener {
            appendToExpression("(")
        }

        buttonBracketRight.setOnClickListener {
            appendToExpression(")")
        }
    }

    private fun appendToExpression(text: String) {
        expression.append(text)
        inputTextView.text = expression.toString()
    }

    private fun clearExpression() {
        expression.clear()
        inputTextView.text = ""
        resultTextView.text = ""
    }

    private fun calculateResult() {
        try {
            val result = evaluateExpression(expression.toString())
            resultTextView.text = result.toString()
        } catch (e: IllegalArgumentException) {
            resultTextView.text = "Error"
        }
    }

    private fun evaluateExpression(expression: String): Double {
        return try {
            val exp = Expression(expression)
            val result = exp.calculate()
            if (result.isNaN()) throw IllegalArgumentException("Invalid Expression")
            result
        } catch (e: Exception) {
            throw IllegalArgumentException("Invalid Expression")
        }
    }
}
