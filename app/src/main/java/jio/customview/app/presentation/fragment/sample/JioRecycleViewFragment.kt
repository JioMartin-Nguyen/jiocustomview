package jio.customview.app.presentation.fragment.sample

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import jio.customview.app.R
import jio.customview.app.common.utils.DimensionUtill
import jio.customview.app.jiorecycleview.FoodAdapter
import jio.customview.app.model.Food
import jio.customview.app.presentation.fragment.BaseFragment
import jio.customview.app.presentation.widget.itemdecoration.SpaceDividerItemDecoration

class JioRecycleViewFragment : BaseFragment() {

    @BindView(R.id.tv_title)
    lateinit var tvTitle:TextView

    @BindView(R.id.rv_foods)
    lateinit var rvFoods: RecyclerView

    private lateinit var adapter: FoodAdapter
    private var foods: ArrayList<Food> = arrayListOf()


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflateView(R.layout.fragment_jio_recycleview)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        // Khi thay doi noi dung trong item, kich thuoc cua item la khong thay doi
        rvFoods.setHasFixedSize(true)

        adapter = FoodAdapter(context)
        rvFoods.adapter = adapter
        // Them 1 ItemDecoration vao trong recycle de phan chia cho cac Item trong RecycleView
        rvFoods.addItemDecoration(getDividerItemDecoration())
        tvTitle.top = DimensionUtill.dpToPx(120f).toInt()
        tvTitle.left = DimensionUtill.dpToPx(120f).toInt()
        /*tvTitle.layoutParams.apply {

        }*/

        Log.e("===>top",tvTitle.top.toString())
        Log.e("===>bottom",tvTitle.bottom.toString())
        Log.e("===>left",tvTitle.left.toString())
        Log.e("===>right",tvTitle.right.toString())

        Log.e("===>topR",rvFoods.top.toString())
        Log.e("===>bottomR",rvFoods.bottom.toString())
        Log.e("===>leftR",rvFoods.left.toString())
        Log.e("===>rightR",rvFoods.right.toString())
    }

    private fun getDividerItemDecoration(): RecyclerView.ItemDecoration {
        val itemDicoration = SpaceDividerItemDecoration(SpaceDividerItemDecoration.ORIENT_VERTICAL, DimensionUtill.dpToPx(5f).toInt(), false)
        return itemDicoration
    }

    // private val dividerItemDecorationDefault = DividerItemDecoration(context, LinearLayout.VERTICAL)

    private fun initData() {
        for (i in 0..200) {
            foods.add(Food(i.toString(), "Food $i", 900))
        }
        adapter.setData(foods)
    }
}