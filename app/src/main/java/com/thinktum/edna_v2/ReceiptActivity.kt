package com.thinktum.edna_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_receipt.*

class ReceiptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)

        //get all of the incoming data from the order page
        val intent = intent

        var stopLoop = 0
        var count = 1
        var finalPrice = 0.00

        while(stopLoop == 0){
            if(intent.hasExtra("ItemSelected" + count.toString()) == true){
                val itemName = intent.getStringExtra("ItemSelected" + count.toString())
                val itemPrice = intent.getStringExtra("ItemPrice" + count.toString())
                val itemQuantity = intent.getStringExtra("ItemQuantity" + count.toString())

                // Create TextView programmatically.
                val textView = TextView(this)
                textView.setId(R.id.IDtextViewItem1)
                textView.layoutParams = ConstraintLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                val params = textView.layoutParams as ConstraintLayout.LayoutParams
                params.leftToRight = guideline10.id

                textView.setText(itemName)
                receiptConstraint ?.addView(textView)

                val textView2 = TextView(this)
                textView2.setId(R.id.IDtextViewItem1Price)
                textView2.layoutParams = ConstraintLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                val params2 = textView2.layoutParams as ConstraintLayout.LayoutParams
                params2.leftToRight = guideline10.id
                params2.topToBottom = textView.id

                textView2.setText(itemPrice)
                receiptConstraint ?.addView(textView2)

                val textView3 = TextView(this)
                textView.setId(R.id.IDtextViewItem1Quantity)
                textView3.layoutParams = ConstraintLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                val params3 = textView3.layoutParams as ConstraintLayout.LayoutParams
                params3.leftToRight = guideline11.id
                params3.rightToRight = receiptConstraint.id

                textView3.setText(itemQuantity)
                receiptConstraint ?.addView(textView3)

                //add this number to the prices
                val subTotalHolder = textViewSubtotalNum.text.toString()
                var subTotalNum = subTotalHolder.toDouble()

                val taxHolder = textViewTaxNum.text.toString()
                var taxNum = taxHolder.toDouble()

                val totalHolder = textViewTotalNum.text.toString()
                var totalNum = totalHolder.toDouble()

                var itemTotalPrice = itemPrice!!.toDouble() * itemQuantity!!.toDouble()

                subTotalNum += itemTotalPrice

                taxNum = subTotalNum * 0.1

                totalNum = taxNum + subTotalNum

                textViewSubtotalNum.text = subTotalNum.toString()
                textViewTaxNum.text = taxNum.toString()
                textViewTotalNum.text = totalNum.toString()

                count += 1

            } else {
                //end loop
                stopLoop = 1
            }
        }
        val UserID = intent.getStringExtra("UserID")


    }
}