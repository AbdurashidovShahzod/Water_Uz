package uz.unzosoft.wateruz.presentation.ui.utils.context

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.VectorDrawable
import android.os.Build
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory


/**
 * Created by Abdurashidov Shahzod on 13/03/22 04:08.
 * company QQBank
 * shahzod9933@gmail.com
 */
 fun Activity.getBitmapDescriptor(id: Int): BitmapDescriptor? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val vectorDrawable = getDrawable(id) as VectorDrawable
        val h = vectorDrawable.intrinsicHeight
        val w = vectorDrawable.intrinsicWidth
        vectorDrawable.setBounds(0, 0, w, h)
        val bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        vectorDrawable.draw(canvas)
        BitmapDescriptorFactory.fromBitmap(bm)
    } else {
        BitmapDescriptorFactory.fromResource(id)
    }
}