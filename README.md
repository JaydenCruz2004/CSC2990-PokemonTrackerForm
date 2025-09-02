# 📱 Pokemon Tracker Form

Assignment 1 – **Pokemon Tracker Form**  
CSC Android Development | Due: September 15, 2025 @ 9:59 PM  

This project is an Android app that allows users to register new Pokémon catches. The form is designed to practice Android UI development concepts such as **layouts, views, widgets, input validation, and event handling**.

---

## 🎯 Objectives
- Familiarize with the basics of Android UI development.  
- Gain experience with layouts (**LinearLayout**, **ConstraintLayout**, **TableLayout**).  
- Implement input validation and form handling.  
- Practice using multiple UI widgets like `EditText`, `Spinner`, `Button`, and `TextView`.

---

## 📝 Features

### Required
- **Pokemon Entry Form** with the following fields:
  - National Number (0–1010, default: `896`)
  - Name (letters, spaces, dots; default: `Glastrier`)
  - Species (letters, spaces; default: `Wild Horse Pokémon`)
  - Gender (Male, Female, Unknown)
  - Height (decimal, 2 places, with **m** suffix; default: `2.20 m`)
  - Weight (decimal, 2 places, with **kg** suffix; default: `800.00 kg`)
  - Level (selectable list 1–50)
  - HP (integer, 0–362)
  - Attack (integer, 0–526)
  - Defense (integer, 10–614)

- **Header & Sub-header**
  - App name: *Poke-Tracker* (customizable)
  - Sub-header: *Register new catch*

- **Buttons**
  - **Reset** → resets fields to default values.  
  - **Save/Submit** → validates inputs, shows `Toast` success or error messages, and highlights invalid fields.

- **Scroll support** → ensures usability on smaller screens.

---

## ✅ Validation Rules
- No empty fields.  
- Name: 3–12 alphabetical characters.  
- Gender: Must be *Male* or *Female*.  
- HP: 1–362.  
- Attack: 0–526.  
- Defense: 10–614.  
- Height: 0.2–169.99 m.  
- Weight: 0.1–992.7 kg.  

If validation fails:
- Invalid field labels turn **red**.  
- A `Toast` message notifies the user of errors.

---

## 🖼️ Layouts Implemented
- `res/layout/linear.xml` → LinearLayout  
- `res/layout/constraint.xml` → ConstraintLayout  
- `res/layout/table.xml` → TableLayout  

Switch layouts by editing:
```java
setContentView(R.layout.linear);
// or
setContentView(R.layout.constraint);
// or
setContentView(R.layout.table);
