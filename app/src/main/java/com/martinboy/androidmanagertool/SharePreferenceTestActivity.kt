package com.martinboy.androidmanagertool

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.martinboy.managertool.SharePreferenceManager

class SharePreferenceTestActivity : AppCompatActivity() {

    private var btnAddData: Button? = null
    var recycleView: RecyclerView? = null
    var adapter: TestDataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sharepreference)
        recycleView = findViewById(R.id.recycle_view)
        recycleView?.layoutManager = LinearLayoutManager(this)
        adapter = TestDataAdapter()
        recycleView?.adapter = adapter
        getData()
        btnAddData = findViewById(R.id.btn_add_data)
        btnAddData?.setOnClickListener {
            addData()
        }
    }

    private fun addData() {

        val data = TestData()
        data.name = "Martin"
        data.age = 30
        SharePreferenceManager
                .addObjectListToSharePreference(this@SharePreferenceTestActivity
                        , "TestData"
                        , "data", data)

        getData()
    }

    private fun getData() {

        val dataList = SharePreferenceManager.getObjectListToSharePreference(this@SharePreferenceTestActivity
                , "TestData", "data")

        if (dataList != null)
            adapter?.setList(dataList)

    }

    class TestDataAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var mutableList: MutableList<Any> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_testdata, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return mutableList.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            val data = mutableList[holder.adapterPosition]
            val testViewHolder: ViewHolder = holder as ViewHolder

            if (data is TestData) {

                testViewHolder.textName?.text = data.name
                testViewHolder.textAge?.text = data.age.toString()

            }

        }

        fun setList(list: MutableList<Any>) {
            this.mutableList = list
            notifyDataSetChanged()
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var textName: TextView? = null
            var textAge: TextView? = null

            init {
                textName = itemView.findViewById(R.id.btn_check_mobile_status_test)
                textAge = itemView.findViewById(R.id.btn_check_mobile_status_test)
            }


        }

    }

    class TestData {

        var name: String = ""
        var age: Int = 0

    }

}
