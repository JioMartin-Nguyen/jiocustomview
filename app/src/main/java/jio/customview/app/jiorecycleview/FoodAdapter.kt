package jio.customview.app.jiorecycleview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindString
import butterknife.BindView
import butterknife.ButterKnife
import jio.customview.app.R
import jio.customview.app.model.Food

class FoodAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mFoods: ArrayList<Food> = arrayListOf()
    private val layoutInflater = LayoutInflater.from(context)

    companion object {
        const val ITEM_1 = 0
        const val ITEM_2 = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return when (viewType) {
            ITEM_1 -> {
                val view = layoutInflater.inflate(R.layout.item_food, parent, false)
                return ViewHolder.ItemFoodViewHolder(view)
            }
            ITEM_2 -> {
                val view = layoutInflater.inflate(R.layout.item_food_bottom, parent, false)
                return ViewHolder.BottomFoodViewHolder(view)
            }
            else -> {
                null
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_1) {
            (holder as? ViewHolder.ItemFoodViewHolder)?.bindItemFood(mFoods[position])
        } else if (getItemViewType(position) == ITEM_2) {
            (holder as? ViewHolder.BottomFoodViewHolder)?.bindItemBottom()
        }
    }

    override fun getItemCount(): Int {
        return if (mFoods.size > 0) {
            mFoods.size + 1
        } else {
            0
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == mFoods.size) {
            ITEM_2
        } else {
            ITEM_1
        }
    }

    fun setData(foods: ArrayList<Food>) {
        mFoods.clear()
        mFoods.addAll(foods)
        notifyDataSetChanged()
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            ButterKnife.bind(this, itemView)
        }

        class ItemFoodViewHolder(itemView: View) : ViewHolder(itemView) {

            @BindView(R.id.tv_food_name)
            lateinit var tvFoodName: TextView

            @BindView(R.id.tv_amount)
            lateinit var tvAmount: TextView

            @BindString(R.string.food_price)
            lateinit var priceTemplate: String

            @BindView(R.id.tv_price)
            lateinit var tvPrice: TextView

            fun bindItemFood(food: Food) {
                tvFoodName.text = food.nameFood
                tvAmount.text = food.amount.toString()
                tvPrice.text = String.format(priceTemplate, food.price)
            }
        }

        class BottomFoodViewHolder(itemView: View) : ViewHolder(itemView) {

            fun bindItemBottom() {

            }

        }


    }
}