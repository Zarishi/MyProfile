package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    CheckBox ckbLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName =(EditText)findViewById(R.id.editTextName);
        etGPA =(EditText)findViewById(R.id.editTextGPA);
        rgGender =(RadioGroup) findViewById(R.id.radioGroupGender);
        ckbLike =(CheckBox)findViewById(R.id.checkBoxLikeProgramming);

    }
    @Override
    protected void onPause() {
        super.onPause();
        //step 1a:retrieve data input of the user
        String strName=etName.getText().toString();
        Float floatGPA=Float.parseFloat(etGPA.getText().toString());
        Boolean boolLike=Boolean.getBoolean(ckbLike.getText().toString());
        //step 1b: obtain an instance of the shared preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // Step 1c: Obtain an instance of the Shared Preference Editr for update later
        SharedPreferences.Editor prefEdit = prefs.edit();
        //Step 1d: Add the key-value pair
        prefEdit.putString("name",strName);
        prefEdit.putFloat("gpa",floatGPA);
        prefEdit.putBoolean("like",boolLike);
        //step 1e: Call commit() method to save the changes into the sahred preference
        prefEdit.commit();
        int intGenderId=rgGender.getCheckedRadioButtonId();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //step 2a: Obtain an instance of the Shared Preferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 2b: Retrieve the saved data with the key, name from the SharedPreferences object.
        String strName=prefs.getString("name","John");
        Float floatGPA=prefs.getFloat("gpa",0);
        //Step 2c: Update the UI element with the value.
        etName.setText(strName);
        etGPA.setText(floatGPA + "");
        boolean bLike=prefs.getBoolean("like",false);
        int intGenderID=prefs.getInt("gender",R.id.radioButtonGenderMale);

        ckbLike.setChecked(bLike);
        rgGender.check(intGenderID);
    }
}
