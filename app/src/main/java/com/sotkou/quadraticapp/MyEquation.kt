package com.sotkou.quadraticapp

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sotkou.quadraticapp.databinding.ActivityMainBinding
import kotlin.math.sqrt

// η μεταβλητη binding στον constructor χρειάζεται για την πρόσβαση στα στοιχεία του layout
class MyEquation(var binding: ActivityMainBinding): BaseObservable() {
    @Bindable // πυροδοτεί αυτόματα updates
    var a: String = ""
    set(value) { field = value // ο defaut setter
        notifyPropertyChanged(BR.a)} // ειδοποίηση για την αλλαγή
    @Bindable
    var b: String = ""
        set(value) {field = value
        notifyPropertyChanged(BR.b)}// BR ειναι η κλάση που δημιουργείται αυτόματα
    // και περιέχει αναφορές για όλα τα bindable στοιχεία
    @Bindable
    var c: String = ""
        set(value) { field = value
        notifyPropertyChanged(BR.c)}

    // χρειαζόμαστε το view για να μπορούμε να καλέσουμε την μέθοδο solveEquation
    // απο το xml αρχείο μέσω του data binding
    fun solveEquation(view: View) {
        val a = a.toDouble()
        val b = b.toDouble()
        val c = c.toDouble()
        val discriminant = b * b - 4 * a * c
        // οι λύσεις
        val x1: Double
        val x2: Double
        if(discriminant> 0.0) {
            x1 = (-b + sqrt(discriminant.toDouble())) / (2 * a)
            x2 = (-b - sqrt(discriminant.toDouble())) / (2 * a)
            // το αποτέλεσμα
            binding.resultText.text = "x1 = $x1, x2 = $x2"
        } else if (discriminant < 0.0)  {
            binding.resultText.text = "Δεν έχει πραγματικές ρίζες.(Μιγαδικές)"
        } else {
            x1 = -b / (2 * a)
            binding.resultText.text = "x1 = x2 = $x1"
        }

    }
}