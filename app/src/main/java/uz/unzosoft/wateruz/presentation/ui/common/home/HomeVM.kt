package uz.unzosoft.wateruz.presentation.ui.common.home

import dagger.hilt.android.lifecycle.HiltViewModel
import uz.unzosoft.wateruz.data.local.LocalStorage
import uz.unzosoft.wateruz.presentation.ui.base.BaseVM
import javax.inject.Inject


/**
 * Created by Abdurashidov Shahzod on 06/03/22 18:07.
 * company QQBank
 * shahzod9933@gmail.com
 */
@HiltViewModel
class HomeVM @Inject constructor(
    private val cache: LocalStorage
) : BaseVM() {

}