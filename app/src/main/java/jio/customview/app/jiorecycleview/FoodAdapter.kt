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

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = layoutInflater.inflate(viewType, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == R.layout.item_food) {
            (holder as? ViewHolder.ItemFoodViewHolder)?.bindItemFood(mFoods[position])
        } else if (getItemViewType(position) == R.layout.item_food_bottom) {
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
            R.layout.item_food_bottom
        } else {
            R.layout.item_food
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