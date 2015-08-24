package com.example.hyes.myrecord_bc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by hyes on 2015. 3. 1..
 */
public class Login extends ActionBarActivity{

    EditText email, password, register_email1, register_email2, register_pwd1, register_pwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button button1 = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.register_button);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        register_email1 = (EditText) findViewById(R.id.register_email1);
        register_email2 = (EditText)findViewById(R.id.register_email2);

        register_pwd1 = (EditText)findViewById(R.id.register_pwd1);
        register_pwd2 = (EditText)findViewById(R.id.register_pwd2);

        button1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   Toast.makeText(getApplicationContext(), "등록된 회원이 맞는지 확인 후 진행~", Toast.LENGTH_SHORT).show();

                    //  Intent intent = new Intent(getApplicationContext(), Record.class);
                       Intent intent = new Intent(getApplicationContext(), List.class);
                       startActivity(intent);
                       Toast.makeText(getApplicationContext(), "등록된 회원이 맞는지 확인 후 진행~", Toast.LENGTH_SHORT).show();

               }
           }
        );

        button2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   String email1, email2, pwd1, pwd2;

                   email1 = register_email1.getText().toString();
                   email2 = register_email2.getText().toString();

                   pwd1 = register_pwd1.getText().toString();
                   pwd2 = register_pwd2.getText().toString();


                   if(email1.equals(email2)){

                       if(pwd1.equals(pwd2)){

                           Toast.makeText(getApplicationContext(), "회원가입처리 후~", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(getApplicationContext(), Ending.class);
                           startActivity(intent);

                       } else {
                           Toast.makeText(getApplicationContext(), "please check password", Toast.LENGTH_SHORT).show();
                       }
                   } else {
                       Toast.makeText(getApplicationContext(), "please check email address", Toast.LENGTH_SHORT).show();
                   }

               }
           }
        );


    }

}
