package uz.unzosoft.wateruz.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.View
import uz.unzosoft.wateruz.R
import uz.unzosoft.wateruz.data.models.api.OrdersItem
import uz.unzosoft.wateruz.databinding.ItemOrderBinding
import uz.unzosoft.wateruz.presentation.ui.base.SuperListAdapter


/**
 * Created by Abdurashidov Shahzod on 11/03/22 19:09.
 * company QQBank
 * shahzod9933@gmail.com
 */
class OrdersAdapters : SuperListAdapter<OrdersItem>(
    R.layout.item_order,
    { oldItem, newItem -> oldItem == newItem },
    { oldItem, newItem -> oldItem == newItem },
) {
    @SuppressLint("SetTextI18n")
    override fun bind(t: OrdersItem, view: View, adapterPosition: Int) {
        val binding = ItemOrderBinding.bind(view)
        binding.apply {
            tvName.text = t.product?.name
            tvNumber.text = "${t.client?.phone}\n${t.client?.phone2}"
            litr.text = t.product?.productCount.toString()
        }
    }
}
