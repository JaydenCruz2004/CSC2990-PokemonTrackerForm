//resources used:
// https://www.geeksforgeeks.org/android/lambda-expressions-in-android-with-example/
//lambda functions are cool
// https://www.geeksforgeeks.org/android/how-to-implement-dark-night-mode-in-android-app/
//how to implement a dark mode

package com.example.csc2990_pokemontrackerform;

import static com.example.csc2990_pokemontrackerform.R.id.textViewName;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Keep track of current layout index (0=linear, 1=table, 2=constraint)
    private static int layoutIndex = 0;

    // edit fields
    private EditText editTextNationalNum, editTextName, editTextSpecies,
            editTextHeight, editTextWeight, editTextHP, editTextAttack, editTextDefense;
    // text fields
    private TextView textViewNationalNum, textViewName, textViewSpecies, textViewGender, textViewHeight, textViewWeight,
            textViewLevel, textViewHP, textViewAttack, textViewDefense;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale, radioButtonUnknown;
    private Spinner spinnerLevel;

    private Switch switchMode;

    // Buttons
    private Button buttonReset, buttonSave, buttonToggleLayout;

    private ImageView pokeballImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load layout based on layoutIndex
        if (layoutIndex == 0) {
            setContentView(R.layout.linear);
        } else if (layoutIndex == 1) {
            setContentView(R.layout.table);
        } else if (layoutIndex == 2) {
            setContentView(R.layout.constraint);
        }

        // Connect views to find by id
        editTextNationalNum = findViewById(R.id.editTextNationalNum);
        editTextName = findViewById(R.id.editTextName);
        editTextSpecies = findViewById(R.id.editTextSpecies);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHP = findViewById(R.id.editTextHP);
        editTextAttack = findViewById(R.id.editTextAttack);
        editTextDefense = findViewById(R.id.editTextDefense);

        textViewNationalNum = findViewById(R.id.textViewNationalNum);
        textViewName = findViewById(R.id.textViewName);
        textViewSpecies = findViewById(R.id.textViewSpecies);
        textViewGender = findViewById(R.id.textViewGender);
        textViewHeight = findViewById(R.id.textViewHeight);
        textViewWeight = findViewById(R.id.textViewWeight);
        textViewLevel = findViewById(R.id.textViewLevel);
        textViewHP = findViewById(R.id.textViewHP);
        textViewAttack = findViewById(R.id.textViewAttack);
        textViewDefense = findViewById(R.id.textViewDefense);


        radioGroupGender = findViewById(R.id.radioGroupGender);

        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonUnknown = findViewById(R.id.radioButtonUnknown);

        // Initialize Spinner
        spinnerLevel = findViewById(R.id.spinnerLevel);

        pokeballImage = findViewById(R.id.pokeballImage);

        /* used to print out 1-50 to add to ArrayList
        for (int i = 0; i < 50; i++) {
            System.out.println("spinnerLevels.add(\"" + i + "\");");
        }
        **/
        //had it print each line but figured you can just put the loop below


        // Add items to Spinner
        List<String> spinnerLevels = new ArrayList<>();
        spinnerLevels.add("N/A");
        for (int i = 1; i < 51; i++) {
            spinnerLevels.add(String.valueOf(i));
        }


        // Create adapter and set to Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerLevels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(adapter);

        // Spinner Listener
        spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLevel = parent.getItemAtPosition(position).toString();
                if (!selectedLevel.equals("Choose a level")) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        buttonReset = findViewById(R.id.buttonReset);
        buttonSave = findViewById(R.id.buttonSave);
        buttonToggleLayout = findViewById(R.id.buttonToggleLayout);

        switchMode = findViewById(R.id.switchMode);

        // Assign listener to buttons
        if (buttonReset != null) buttonReset.setOnClickListener(buttonListener);
        if (buttonSave != null) buttonSave.setOnClickListener(buttonListener);
        if (buttonToggleLayout != null) buttonToggleLayout.setOnClickListener(buttonListener);
        if (switchMode != null) switchMode.setOnClickListener(buttonListener);
    }

    private final View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();

            if (id == R.id.buttonReset) {
                resetForm();
            } else if (id == R.id.buttonSave) {
                if (validateInputs()) {
                    Toast.makeText(MainActivity.this, "Info stored in database!", Toast.LENGTH_SHORT).show();
                    pokeballImage.setVisibility(View.VISIBLE);

                    // Hide it after 3 seconds
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pokeballImage.setVisibility(View.GONE);
                        }
                    }, 3000);
                } else {
                    Toast.makeText(MainActivity.this, "Errors found. Please check highlighted fields.", Toast.LENGTH_SHORT).show();
                }
            } else if (id == R.id.buttonToggleLayout) {
                layoutIndex = (layoutIndex + 1) % 3;
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            } else if (id == R.id.switchMode) {
                int currentMode = AppCompatDelegate.getDefaultNightMode();
                if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
            }
        }
    };

    // Reset form to defaults
    private void resetForm() {
        editTextNationalNum.setText("896");
        editTextName.setText("Glastrier");
        editTextSpecies.setText("Wild Horse Pokemon");
        editTextHeight.setText("2.2 m");
        editTextWeight.setText("800.0 Kg");
        spinnerLevel.setSelection(0);
        editTextHP.setText("0");
        editTextAttack.setText("0");
        editTextDefense.setText("0");
        radioGroupGender.clearCheck();
    }


    // Validate inputs
    private boolean validateInputs() {
        boolean valid = true;
        // National Number
        try {
            int num = Integer.parseInt(editTextNationalNum.getText().toString());
            if (num < 0 || num > 1010) {
                textViewNationalNum.setTextColor(Color.RED);
                Toast.makeText(this, "National Number 0 - 1010 only", Toast.LENGTH_SHORT).show();
                valid = false;
            } else {
                textViewNationalNum.setTextColor(Color.BLACK);
            }
        } catch (Exception e) {
            textViewNationalNum.setTextColor(Color.RED);
            valid = false;
        }

        // Name: 3–12 letters, capitalized
        String name = editTextName.getText().toString().trim();
        if (name.length() < 3 || name.length() > 12 || !name.matches("[a-zA-Z. ]+")) {
            textViewName.setTextColor(Color.RED);
            Toast.makeText(this, "Name must be 3–12 characters, only letters, spaces, or '.'", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            // Check capitalization: each word should start uppercase
            boolean capitalizationValid = true;
            String[] words = name.split("\\s+");
            for (String word : words) {
                if (!word.isEmpty() && !Character.isUpperCase(word.charAt(0))) {
                    capitalizationValid = false;
                    break;
                }
            }
            if (!capitalizationValid) {
                textViewName.setTextColor(Color.RED);
                Toast.makeText(this, "Each word in Name must start with a capital letter", Toast.LENGTH_SHORT).show();
                valid = false;
            } else {
                textViewName.setTextColor(Color.BLACK);
            }
        }


        // Species: only letters and spaces, and must be capitalized correctly
        String species = editTextSpecies.getText().toString().trim();
        if (!species.matches("[a-zA-Z ]+")) {
            // Invalid characters
            textViewSpecies.setTextColor(Color.RED);
            Toast.makeText(this, "Species must only be alphabetical letters and spaces", Toast.LENGTH_SHORT).show();

            valid = false;
        } else {
            // Check capitalization: each word should start with uppercase
            boolean capitalizationValid = true;
            String[] words = species.split("\\s+");
            for (String word : words) {
                if (!word.isEmpty() && !Character.isUpperCase(word.charAt(0))) {
                    capitalizationValid = false;
                    break;
                }
            }

            if (!capitalizationValid) {
                textViewSpecies.setTextColor(Color.RED);
                Toast.makeText(this, "Each word in Species must start with a capital letter", Toast.LENGTH_SHORT).show();

                valid = false;
            } else {
                textViewSpecies.setTextColor(Color.BLACK);
            }
        }

        // Gender
        int selectedId = radioGroupGender.getCheckedRadioButtonId();
        if (selectedId == -1) {
            textViewGender.setTextColor(Color.RED);
            Toast.makeText(this, "Must select a gender", Toast.LENGTH_SHORT).show();

            valid = false;
        } else {
            textViewGender.setTextColor(Color.BLACK);
        }

        // Height
        try {
            double height = Double.parseDouble(editTextHeight.getText().toString().replace("m", "").trim());
            if (height < 0.2 || height > 169.99) {
                textViewHeight.setTextColor(Color.RED);
                Toast.makeText(this, "Height must be between 0.2 - 169.99", Toast.LENGTH_SHORT).show();

                valid = false;
            } else {
                textViewHeight.setTextColor(Color.BLACK);
            }
        } catch (Exception e) {
            textViewHeight.setTextColor(Color.RED);
            valid = false;
        }

        // Weight
        try {
            double weight = Double.parseDouble(editTextWeight.getText().toString().replace("Kg", "").trim());
            if (weight < 0.1 || weight > 992.7) {
                textViewWeight.setTextColor(Color.RED);
                Toast.makeText(this, "Weight must be between 0.1 - 992.7", Toast.LENGTH_SHORT).show();

                valid = false;
            } else {
                textViewWeight.setTextColor(Color.BLACK);
            }
        } catch (Exception e) {
            textViewWeight.setTextColor(Color.RED);
            valid = false;
        }

        // Level
        try {
            if (spinnerLevel.getSelectedItemPosition() == 0) {
                textViewLevel.setTextColor(Color.RED);
                Toast.makeText(this, "Must Select a Level 1 - 50", Toast.LENGTH_SHORT).show();
                valid = false;
            } else {
                textViewLevel.setTextColor(Color.BLACK);
            }
        } catch (Exception e) {
            textViewLevel.setTextColor(Color.RED);
            valid = false;
        }

        // HP: 1–362
        try {
            int hp = Integer.parseInt(editTextHP.getText().toString());
            if (hp < 1 || hp > 362) {
                textViewHP.setTextColor(Color.RED);
                Toast.makeText(this, "HP must be between 1 - 362", Toast.LENGTH_SHORT).show();

                valid = false;
            } else {
                textViewHP.setTextColor(Color.BLACK);
            }
        } catch (Exception e) {
            textViewHP.setTextColor(Color.RED);
            valid = false;
        }

        // Attack: 0–526
        try {
            int atk = Integer.parseInt(editTextAttack.getText().toString());
            if (atk < 0 || atk > 526) {
                textViewAttack.setTextColor(Color.RED);
                Toast.makeText(this, "Attack must be between 0 - 526", Toast.LENGTH_SHORT).show();

                valid = false;
            } else {
                textViewAttack.setTextColor(Color.BLACK);
            }
        } catch (Exception e) {
            textViewAttack.setTextColor(Color.RED);
            valid = false;
        }

        // Defense: 10–614
        try {
            int def = Integer.parseInt(editTextDefense.getText().toString());
            if (def < 10 || def > 614) {
                textViewDefense.setTextColor(Color.RED);
                Toast.makeText(this, "Defense must be between 10 - 614", Toast.LENGTH_SHORT).show();

                valid = false;
            } else {
                textViewDefense.setTextColor(Color.BLACK);
            }
        } catch (Exception e) {
            textViewDefense.setTextColor(Color.RED);
            valid = false;
        }

        return valid;
    }
}
