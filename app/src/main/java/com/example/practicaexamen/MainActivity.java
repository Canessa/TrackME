package com.example.practicaexamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //Atributos para mapear los controles visuales de la pantalla
    private EditText etCorreo;
    private EditText etClave;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCorreo = findViewById(R.id.etCorreo); //enlazo el control visual con el objeto java
        etClave = findViewById(R.id.etClave); //enlazo el control visual con el objeto java

        mAuth = FirebaseAuth.getInstance();  //Obtengo la instancia con mi Firebase Auth
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);
    }

    public void login(View view) {
        String email = etCorreo.getText().toString();
        String password = etClave.getText().toString();
        if (!email.isEmpty() && !password.isEmpty()) {  //Si engreso datos en los controles... entra..
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {  //Cuando la tarea asincrona finaliza...
                            if (task.isSuccessful()) {  //Si el resultado de crear el usuario es satisfaria
                                FirebaseUser user = mAuth.getCurrentUser(); //Si se acaba de registrar... entonces es el usuario actual...
                                updateUI(user);
                            } else {  //por alguna razon no se logra registrar...
                                Toast.makeText(MainActivity.this, "No se logro autenticar", Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Ingrese datos...", Toast.LENGTH_SHORT).show();
        }
    }

    public void registro(View view) {
        String email = etCorreo.getText().toString();
        String password = etClave.getText().toString();
        if (!email.isEmpty() && !password.isEmpty()) {  //Si engreso datos en los controles... entra..
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {  //Cuando la tarea asincrona finaliza...
                            if (task.isSuccessful()) {  //Si el resultado de crear el usuario es satisfaria
                                FirebaseUser user = mAuth.getCurrentUser(); //Si se acaba de registrar... entonces es el usuario actual...
                                updateUI(user);
                            } else {  //por alguna razon no se logra registrar...
                                Toast.makeText(MainActivity.this, "No se logro registrar", Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Ingrese datos...", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {  // si es diferente de null pasarse a la pantalla principal...
            Intent intent = new Intent(this, Principal.class);
            startActivity(intent);
        }
    }
}