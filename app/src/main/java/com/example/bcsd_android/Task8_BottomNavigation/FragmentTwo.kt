package com.example.bcsd_android.Task8_BottomNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bcsd_android.R
import com.example.bcsd_android.databinding.Activity8FragmentTwoBinding

class FragmentTwo:Fragment() {
    private var mBinding:Activity8FragmentTwoBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = Activity8FragmentTwoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonClick()
    }
    private fun buttonClick(){
        var new = "0"
        var old = "0"
        var count = 0
        var sign = 0
        binding.btnAc.setOnClickListener {
            new = "0"
            old = "0"
            count = 0
            sign = 0
            binding.cNumber.setText(R.string.zero)
        }
        binding.btnPlus.setOnClickListener {
            count += 1
            sign = 43
            if (count > 1){
                binding.cNumber.setText(old)
            }
            binding.cNumber.setText(R.string.plus)
            old = (new.toInt() + old.toInt()).toString()
            new = "0"
        }
        binding.btnMinus.setOnClickListener {
            count += 1
            sign = 45
            if (count > 1){
                binding.cNumber.setText(old)
            }
            binding.cNumber.setText(R.string.minus)
            old = (new.toInt() - old.toInt()).toString()
            new = "0"
        }
        binding.btnDivide.setOnClickListener {
            count += 1
            sign = 47
            if (count > 1){
                binding.cNumber.setText(old)
            }
            binding.cNumber.setText(R.string.divide)
            old = ((new.toInt()) / (old.toInt())).toString()
            new = "0"
        }
        binding.btnMulti.setOnClickListener {
            count += 1
            sign = 42
            if (count > 1){
                binding.cNumber.setText(old)
            }
            binding.cNumber.setText(R.string.multi)
            old = ((new.toInt()) * old.toInt()).toString()
            new = "0"
        }
        binding.btnEquals.setOnClickListener {
            if (sign.toChar() == '+'){
                old = (new.toInt() + old.toInt()).toString()
            }
            else if (sign.toChar() == '-'){
                old = (old.toInt() - new.toInt()).toString()
            }
            else if (sign.toChar() == '/'){
                old = (old.toInt() / new.toInt()).toString()
            }
            else if (sign.toChar() == '*'){
                old = (old.toInt() * new.toInt()).toString()
            }
            count = 0
            binding.cNumber.setText(old)
            new = "0"
        }
        binding.btn0.setOnClickListener {
            if (binding.cNumber.text == "0"){
                new = "0"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "+"){
                new = "0"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "-"){
                new = "0"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "/"){
                new = "0"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "*"){
                new = "0"
                binding.cNumber.setText(new)
            }
            else{
                new = new + "0"
                binding.cNumber.setText(new)
            }
        }
        binding.btn1.setOnClickListener {
            if (binding.cNumber.text == "0"){
                new = "1"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "+"){
                new = "1"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "-"){
                new = "1"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "/"){
                new = "1"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "*"){
                new = "1"
                binding.cNumber.setText(new)
            }
            else{
                new = new + "1"
                binding.cNumber.setText(new)
            }
        }
        binding.btn2.setOnClickListener {
            if (binding.cNumber.text == "0"){
                new = "2"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "+"){
                new = "2"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "-"){
                new = "2"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "/"){
                new = "2"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "*"){
                new = "2"
                binding.cNumber.setText(new)
            }
            else{
                new = new + "2"
                binding.cNumber.setText(new)
            }
        }
        binding.btn3.setOnClickListener {
            if (binding.cNumber.text == "0"){
                new = "3"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "+"){
                new = "3"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "-"){
                new = "3"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "/"){
                new = "3"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "*"){
                new = "3"
                binding.cNumber.setText(new)
            }
            else{
                new = new + "3"
                binding.cNumber.setText(new)
            }
        }
        binding.btn4.setOnClickListener {
            if (binding.cNumber.text == "0"){
                new = "4"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "+"){
                new = "4"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "-"){
                new = "4"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "/"){
                new = "4"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "*"){
                new = "4"
                binding.cNumber.setText(new)
            }
            else{
                new = new + "4"
                binding.cNumber.setText(new)
            }
        }
        binding.btn5.setOnClickListener {
            if (binding.cNumber.text == "0"){
                new = "5"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "+"){
                new = "5"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "-"){
                new = "5"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "/"){
                new = "5"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "*"){
                new = "5"
                binding.cNumber.setText(new)
            }
            else{
                new = new + "5"
                binding.cNumber.setText(new)
            }
        }
        binding.btn6.setOnClickListener {
            if (binding.cNumber.text == "0"){
                new = "6"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "+"){
                new = "6"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "-"){
                new = "6"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "/"){
                new = "6"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "*"){
                new = "6"
                binding.cNumber.setText(new)
            }
            else{
                new = new + "6"
                binding.cNumber.setText(new)
            }
        }
        binding.btn7.setOnClickListener {
            if (binding.cNumber.text == "0"){
                new = "7"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "+"){
                new = "7"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "-"){
                new = "7"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "/"){
                new = "7"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "*"){
                new = "7"
                binding.cNumber.setText(new)
            }
            else{
                new = new + "7"
                binding.cNumber.setText(new)
            }
        }
        binding.btn8.setOnClickListener {
            if (binding.cNumber.text == "0"){
                new = "8"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "+"){
                new = "8"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "-"){
                new = "8"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "/"){
                new = "8"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "*"){
                new = "8"
                binding.cNumber.setText(new)
            }
            else{
                new = new + "8"
                binding.cNumber.setText(new)
            }
        }
        binding.btn9.setOnClickListener {
            if (binding.cNumber.text == "0"){
                new = "9"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "+"){
                new = "9"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "-"){
                new = "9"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "/"){
                new = "9"
                binding.cNumber.setText(new)
            }
            else if (binding.cNumber.text == "*"){
                new = "9"
                binding.cNumber.setText(new)
            }
            else{
                new = new + "9"
                binding.cNumber.setText(new)
            }
        }
    }


        override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}