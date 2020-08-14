package com.thinktum.edna_v2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
//import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity()  {

    private val firebaseRepo: FirebaseRepo = FirebaseRepo()
    private var itemList: List<MenuItem> = ArrayList()

    val item1: String  = "Black Coffee"
    val item2: String = "Double Double"
    val item3: String = "Two Sugar 1 Milk"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val db = FirebaseFirestore.getInstance()

        for(x in 0 until 3){
            if(x == 0){
                //add the db items into the menu
                val docRef = db
                    .collection("Vending Machine").document("thinktum Head Office 001")
                    .collection("Menu").document(item1)

                docRef.get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            Log.d("DB Collection", "DocumentSnapshot data: ${document.data}")

                            //get Image
                            val image = R.drawable.nachos

                            //Get Title
                            val itemName = document.getString("Title")
                            //Get Price
                            val itemPrice = document.getString("price")
                            textviewItem1Order.text = itemName
                            textviewItem1DescOrder.text = itemPrice

                        } else {
                            Log.d("DB Collection", "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d("DB Collection", "get failed with ", exception)
                    }
            } else if (x == 1) {
                //add the db items into the menu
                val docRef = db
                    .collection("Vending Machine").document("thinktum Head Office 001")
                    .collection("Menu").document(item2)

                docRef.get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            Log.d("DB Collection", "DocumentSnapshot data: ${document.data}")

                            //get Image
                            val image = R.drawable.nachos

                            //Get Title
                            val itemName = document.getString("Title")
                            //Get Price
                            val itemPrice = document.getString("price")
                            textviewItem2Order.text = itemName
                            textviewItem2DescOrder.text = itemPrice

                        } else {
                            Log.d("DB Collection", "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d("DB Collection", "get failed with ", exception)
                    }
            } else if (x == 2){
                //add the db items into the menu
                val docRef = db
                    .collection("Vending Machine").document("thinktum Head Office 001")
                    .collection("Menu").document(item3)
                docRef.get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            Log.d("DB Collection", "DocumentSnapshot data: ${document.data}")

                            //get Image
                            val image = R.drawable.nachos

                            //Get Title
                            val itemName = document.getString("Title")
                            //Get Price
                            val itemPrice = document.getString("price")
                            textviewItem3Order.text = itemName
                            textviewItem3DescOrder.text = itemPrice

                        } else {
                            Log.d("DB Collection", "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d("DB Collection", "get failed with ", exception)
                    }
                }
            }






        //val query: Query  = db .collection("Vending Machine")

        //menuList.layoutManager = LinearLayoutManager(this)

        //get data from intent
        val intent = intent
        val VendingMachineName = intent.getStringExtra("VendingMachine")

        textviewVendingNameOrder.text = VendingMachineName

        //val itemList = generateMenuList(1, db)

        //menuList.adapter = MenuItemAdapter(itemList)
        //menuList.layoutManager = LinearLayoutManager(this)
        //menuList.setHasFixedSize(true)


        //---------------------Set up on click listener for the buttons---------------------------
        btnItem1Add.setOnClickListener {
            //add one from the current quantity
            val quantityHolder = textviewItem1QuantNumOrder.text.toString()
            var quantityNum: Int = quantityHolder.toInt()

            quantityNum += 1

            textviewItem1QuantNumOrder.text = quantityNum.toString()

        }

        btnItem1Subtract.setOnClickListener {
            if(textviewItem1QuantNumOrder.text == "0"){
                //print toast saying that this can not go lower then 0
            } else {
                //subtract one from the current quantity
                val quantityHolder = textviewItem1QuantNumOrder.text.toString()
                var quantityNum: Int = quantityHolder.toInt()

                quantityNum -= 1

                textviewItem1QuantNumOrder.text = quantityNum.toString()
            }
        }

        btnItem2Add.setOnClickListener {
            //add one from the current quantity
            val quantityHolder = textviewItem2QuantNumOrder.text.toString()
            var quantityNum: Int = quantityHolder.toInt()

            quantityNum += 1

            textviewItem2QuantNumOrder.text = quantityNum.toString()

        }

        btnItem2Subtract.setOnClickListener {
            if(textviewItem2QuantNumOrder.text == "0"){
                //print toast saying that this can not go lower then 0
            } else {
                //subtract one from the current quantity
                val quantityHolder = textviewItem2QuantNumOrder.text.toString()
                var quantityNum: Int = quantityHolder.toInt()

                quantityNum -= 1

                textviewItem2QuantNumOrder.text = quantityNum.toString()
            }
        }

        btnItem3Add.setOnClickListener {
            //add one from the current quantity
            val quantityHolder = textviewItem3QuantNumOrder.text.toString()
            var quantityNum: Int = quantityHolder.toInt()

            quantityNum += 1

            textviewItem3QuantNumOrder.text = quantityNum.toString()

        }

        btnItem3Subtract.setOnClickListener {
            if(textviewItem3QuantNumOrder.text == "0"){
                //print toast sayinf that this can not go lower then 0
            } else {
                //subtract one from the current quantity
                val quantityHolder = textviewItem3QuantNumOrder.text.toString()
                var quantityNum: Int = quantityHolder.toInt()

                quantityNum -= 1

                textviewItem3QuantNumOrder.text = quantityNum.toString()
            }
        }


        btnSubmitOrder.setOnClickListener {
            //check each of the items to see if they have been added to the order
            if(textviewItem1QuantNumOrder.text != "0"){
                val priceHolder = textviewItem1DescOrder.text.toString()

                val quantityHolder = textviewItem1QuantNumOrder.text.toString()

                val itemName = textviewItem1Order.text.toString().trim()

                val intent = Intent(this, ReceiptActivity::class.java)

                intent.putExtra("ItemSelected1", itemName)
                intent.putExtra("ItemQuantity1", quantityHolder)
                intent.putExtra("ItemPrice1", priceHolder)
                startActivity(intent)
            }

            if(textviewItem2QuantNumOrder.text != "0"){

            }

            if(textviewItem3QuantNumOrder.text != "0"){

            }
        }
    }

    private fun generateMenuList(size: Int, db: FirebaseFirestore): List<MenuItem>{
        val list  =  ArrayList<MenuItem>()

        val docRef = db
            .collection("Vending Machine").document("thinktum Head Office 001")
            .collection("Menu").document("Black Coffee")

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("DB Collection", "DocumentSnapshot data: ${document.data}")

                    //get Image
                    val image = R.drawable.nachos

                    //Get Title
                    val itemName = document.getString("Title")
                    //Get Price
                    val itemPrice = document.getString("price")

                    val item = MenuItem(image, itemName.toString(), itemPrice.toString())
                    list.add(item)

                    /*for (i in 0 until size) {
                        val item = MenuItem(image, itemName.toString(), itemPrice.toString())
                        list.add(item)
                    }*/

                } else {
                    Log.d("DB Collection", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("DB Collection", "get failed with ", exception)
            }

        return list
    }

    private fun loadPostData(){
        firebaseRepo.getItemList().addOnCompleteListener {
            if(it.isSuccessful){
                itemList = it.result!!.toObjects(MenuItem::class.java)
            } else {
                Log.d("DB", "Error: ${it.exception!!.message}")
            }
        }
    }
}