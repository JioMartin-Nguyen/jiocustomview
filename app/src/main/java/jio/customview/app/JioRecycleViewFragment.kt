package jio.customview.app

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import jio.customview.app.jiorecycleview.FoodAdapter
import jio.customview.app.model.Food

class JioRecycleViewFragment : BaseFragment() {

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
    }

    private fun initData() {
        for (i in 0..30) {
            foods.add(Food(i.toString(), "Food $i", 900))
        }
        adapter.setData(foods)
    }
}