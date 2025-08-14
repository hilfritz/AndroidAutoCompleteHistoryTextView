# 📝 AndroidAutoCompleteHistoryTextView

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)

A custom `AppCompatAutoCompleteTextView` that **remembers user input history** and provides **smart suggestions** using `SharedPreferences`. Perfect for search bars, forms, or any input field where past entries matter.

> No dependencies. Just copy and use! 💡

📸 *Example: Auto-suggestions from previous inputs*  
<img src="/docs/animation.gif" alt="Demo Animation" width="200">

---

## 🚀 Features

-  Auto-suggestions from real user history
-  Customizable list items
-  Persistent storage via `SharedPreferences`
-  Auto-shows dropdown on focus
-  Auto-refreshes suggestions
-  Reset history anytime
-  Uses View ID as unique key — supports multiple fields
-  AndroidX compatible (`AppCompatAutoCompleteTextView`)

---

## ⬇️ Download

📥 [Download v1.0.0 ZIP](https://github.com/hilfritz/AndroidAutoCompleteHistoryTextView/releases/download/v1.0.0/AutoCompleteHistoryTextView-v1.0.0.zip)

Or clone the repo:
```bash
git clone https://github.com/hilfritz/AndroidAutoCompleteHistoryTextView.git 

```

## 🧩 Usage

### 1. Add to Layout (XML)
⚠️ Important: The view must have an android:id — it uses the ID to save history uniquely. 



```xml
<com.hilfritz.autocompletehistorytextview.view.AutoCompleteHistoryTextView
    android:id="@+id/textInput"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Type something..." />
```

### 2. Use in Activity or Fragment
```java
AutoCompleteHistoryTextView inputView = findViewById(R.id.searchInput);

// Save user input (e.g., after pressing Enter or a button)
inputView.saveToHistory("Paris");

// Reset all saved history
inputView.reset();

// Check if input is empty
if (!inputView.isEmptyInput()) {
    String text = inputView.getText().toString();
    // Process input...
}
```


### 3. API Reference
The dropdown automatically appears on focus if history exists. 
## 📚 API Reference

| Method | Description |
|--------|-------------|
| `saveToHistory(String str)` | Saves a string to persistent history |
| `refreshHistoryList()` | Reloads suggestions from storage |
| `reset()` | Clears all saved history for this view |
| `isEmptyInput()` | Returns `true` if text is null, empty, or `"null"` |
| `getPreferenceSaveKey()` | Gets the internal SharedPreferences key (based on View ID) |

> **Note:** The dropdown automatically appears on focus if history exists.

## 📂 Files You Need

Copy these 3 files into your project:

| File | Location |
|------|----------|
| `AutoCompleteHistoryTextView.java` | `src/main/java/your/package/` |
| `AutoCompleteHistoryTextViewUtil.java` | `src/main/java/your/package/` |
| `listitem_autocompletehistory.xml` | `src/main/res/layout/` |


## 🛠 How It Works

- **Storage:** Uses `SharedPreferences` under the key: `AutoCompleteHistoryTextView.HISTORY`
- **Uniqueness:** Each view uses its Android `android:id` as a key → supports multiple fields
- **Data Type:** Stores history as `Set<String>` → prevents duplicates
- **UI Behavior:** On focus, filters and shows dropdown automatically
- **Sorting:** History is sorted alphabetically

## 📝 Sample Layout: `listitem_autocompletehistory.xml`

Create this file in: `app/src/main/res/layout/`

```xml
<?xml version="1.0" encoding="utf-8"?>
<TextView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="12dp"
    android:textSize="16sp"
    android:textColor="#333"
    android:background="?android:attr/selectableItemBackground" />
```

## 🙌 Credits

- **Author:** Hilfritz V. Camallere
- **Created:** May 7, 2017
- **Location:** Ontario Canada
- **Purpose:** Personal project to minimize code

---

## 📚 Feedback & Contributions

Found a bug? Want a new feature (like max history size, delete item, Kotlin version)?  
I’d love to hear from you!

👉 Open an issue: [GitHub Issues](https://github.com/hilfritz/AndroidAutoCompleteHistoryTextView/issues)  
👉 Submit a PR: [Pull Requests](https://github.com/hilfritz/AndroidAutoCompleteHistoryTextView/pulls)

