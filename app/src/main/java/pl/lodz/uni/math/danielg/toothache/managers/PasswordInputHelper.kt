package pl.lodz.uni.math.danielg.toothache.managers

import android.text.method.PasswordTransformationMethod
import android.widget.EditText

// TODO: Could mention about DRY in the written part. Source: 'Clean code' R. Martin's book.
//       Doing this take this (and an alternative) code as an example.
fun onClickEyeButton(editText: EditText) {
    if (editText.transformationMethod != null) editText.transformationMethod = null
    else editText.transformationMethod = PasswordTransformationMethod()

    editText.setSelection(editText.text.length)
}