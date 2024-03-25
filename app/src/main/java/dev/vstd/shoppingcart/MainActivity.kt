package dev.vstd.shoppingcart

import Notification.MyNotificationAdapter
import Notification.NewNotification
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.ActivityMainBinding

@AndroidEntryPoint
open class MainActivity : AppCompatActivity() {

    private  lateinit var binding :ActivityMainBinding
    private  lateinit var newRecyclerView: RecyclerView
    private  lateinit var newArrayList:ArrayList<NewNotification>
    lateinit var imageId:Array<Int>
    lateinit var heading:Array<String>
    lateinit var content:Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_notification)
        binding=ActivityMainBinding.inflate(layoutInflater)
        InitData()
    }
    private  fun InitData(){
        imageId= arrayOf(
            R.drawable.ic_book,
            R.drawable.ic_mes,
            R.drawable.ic_gift,
            R.drawable.ic_price
        )
        heading= arrayOf(
            "Live and Video",
            "Message",
            "Award shop",
            "Promotion"
        )
        content= arrayOf(
            "Live Shop",
            "Not paid full",
            "Spin to win",
            "All products are reduced by 50000"
        )
        newRecyclerView=findViewById(R.id.recycleView)
        newRecyclerView.layoutManager=LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newArrayList= arrayListOf<NewNotification>()
        getData()
    }
    private fun getData(){
        for(i in imageId.indices){
            val news=NewNotification(imageId[i],heading[i],content[i])
        }
        newRecyclerView.adapter=MyNotificationAdapter(newArrayList)
    }
}