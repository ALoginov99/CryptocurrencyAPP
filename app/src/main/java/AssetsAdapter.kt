import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.R
import models.Asset
import java.util.*

class AssetsAdapter(private val list: List<Asset>)
    : RecyclerView.Adapter<AssetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AssetViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        val movie: Asset = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size

}

class AssetViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.asset_item, parent, false)) {
    private var mNameView: TextView? = null
    private var mSymbolView: TextView? = null
    private var mPriceUsdView: TextView? = null
    private var mChangePercent24HrView: TextView? = null

    init {
        mNameView = itemView.findViewById(R.id.name)
        mSymbolView = itemView.findViewById(R.id.symbol)
        mPriceUsdView = itemView.findViewById(R.id.priceUsd)
        mChangePercent24HrView = itemView.findViewById(R.id.changePercent24Hr)
    }

    fun bind(asset: Asset) {
        mNameView?.text = asset.name
        mSymbolView?.text = asset.symbol
        mPriceUsdView?.text = String.format(Locale.US,"%.4f", asset.priceUsd)
        mChangePercent24HrView?.text = String.format(Locale.US,"%.4f", asset.changePercent24Hr)
        if(asset.changePercent24Hr != null && asset.changePercent24Hr < 0){
            mChangePercent24HrView?.setTextColor(Color.parseColor("#FF0000"))
        }

        itemView.setOnClickListener {view->
            val bundle = bundleOf(ASSET_ID to asset.id)
            view.findNavController().navigate(R.id.assetInfoFragment,bundle)
//            Toast.makeText(it.context, "нажал на ${mNameView?.text}", Toast.LENGTH_SHORT).show()
        }
    }

}