package com.atlasvision.tv
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
data class Channel(val number:Int,val name:String,val country:String,val category:String,val type:String,val url:String)
class MainActivity:AppCompatActivity(){
 private val categories=listOf("Chaînes marocaines","Chaînes algériennes","Chaînes égyptiennes","Chaînes françaises","Chaînes anglaises","Chaînes USA","Cinéma arabe et égyptien","Cinéma français","Cinéma anglais","Documentaires arabes","Documentaires français","Documentaires monde","Musique arabe","Musique française","Musique monde","Jeunesse","Information","Sport","Satellites")
 private var all=listOf<Channel>(); private lateinit var channelAdapter:ChannelAdapter
 override fun onCreate(s:Bundle?){super.onCreate(s);setContentView(R.layout.activity_main);all=load();channelAdapter=ChannelAdapter(emptyList()){c->startActivity(Intent(this,PlayerActivity::class.java).putExtra("type",c.type).putExtra("url",c.url))};findViewById<RecyclerView>(R.id.channels).apply{layoutManager=LinearLayoutManager(this@MainActivity);adapter=channelAdapter};findViewById<RecyclerView>(R.id.categories).apply{layoutManager=LinearLayoutManager(this@MainActivity);adapter=TextAdapter(categories){show(it)}};show(categories.first())}
 private fun show(c:String){findViewById<TextView>(R.id.title).text=c;channelAdapter.items=all.filter{it.category==c};channelAdapter.notifyDataSetChanged()}
 private fun load():List<Channel>{val a=JSONArray(assets.open("channels.json").bufferedReader().use{it.readText()});return(0 until a.length()).map{i->a.getJSONObject(i).let{Channel(it.getInt("number"),it.getString("name"),it.getString("country"),it.getString("category"),it.optString("type","hls"),it.getString("url"))}}}
}
class TextAdapter(val items:List<String>,val click:(String)->Unit):RecyclerView.Adapter<TextAdapter.H>(){class H(v:View):RecyclerView.ViewHolder(v);override fun onCreateViewHolder(p:ViewGroup,v:Int)=H(LayoutInflater.from(p.context).inflate(R.layout.item_text,p,false));override fun getItemCount()=items.size;override fun onBindViewHolder(h:H,p:Int){(h.itemView as TextView).text=items[p];h.itemView.setOnClickListener{click(items[p])}}}
class ChannelAdapter(var items:List<Channel>,val click:(Channel)->Unit):RecyclerView.Adapter<ChannelAdapter.H>(){class H(v:View):RecyclerView.ViewHolder(v);override fun onCreateViewHolder(p:ViewGroup,v:Int)=H(LayoutInflater.from(p.context).inflate(R.layout.item_channel,p,false));override fun getItemCount()=items.size;override fun onBindViewHolder(h:H,p:Int){val c=items[p];h.itemView.findViewById<TextView>(R.id.number).text=c.number.toString();h.itemView.findViewById<TextView>(R.id.name).text=c.name;h.itemView.findViewById<TextView>(R.id.info).text=c.country;h.itemView.setOnClickListener{click(c)}}}