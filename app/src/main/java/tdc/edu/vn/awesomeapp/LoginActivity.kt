package tdc.edu.vn.awesomeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private val EMAIL_REGEX: Pattern = Pattern.compile(
        "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,8}", Pattern.CASE_INSENSITIVE
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.edtEmail);
        val password = findViewById<EditText>(R.id.edtPassword);
        val signing = findViewById<Button>(R.id.btnSignIn);

        signing.setOnClickListener {
            val emailStr = email.text.toString();
            val passStr = password.text.toString();
            val shake = AnimationUtils.loadAnimation(this, R.anim.shake);

            when {
                emailStr.isEmpty() -> Snackbar
                    .make(email, getString(R.string.email_required), Snackbar.LENGTH_LONG)
                    .show()
                !EMAIL_REGEX.matcher(emailStr).find() ->  Snackbar
                    .make(email, getString(R.string.invalid_email), Snackbar.LENGTH_LONG)
                    .show()
                !passStr.contains("AwesomeApp") ->
                    signing.startAnimation(shake)
                else -> Snackbar.make(signing, getString(R.string.login_verified), Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}

