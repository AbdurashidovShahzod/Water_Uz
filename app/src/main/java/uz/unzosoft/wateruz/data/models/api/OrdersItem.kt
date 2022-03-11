package uz.unzosoft.wateruz.data.models.api

import com.google.gson.annotations.SerializedName


/**
 * Created by Abdurashidov Shahzod on 11/03/22 16:05.
 * company QQBank
 * shahzod9933@gmail.com
 */
class OrdersResponse(var ordersList: List<OrdersItem>)


data class OrdersItem(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("organization_id") var organizationId: Int? = null,
    @SerializedName("client_id") var clientId: Int? = null,
    @SerializedName("product_id") var productId: Int? = null,
    @SerializedName("user_id") var userId: Int? = null,
    @SerializedName("product_count") var productCount: Int? = null,
    @SerializedName("container_status") var containerStatus: Int? = null,
    @SerializedName("price") var price: Int? = null,
    @SerializedName("comment") var comment: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("product") var product: Product? = Product(),
    @SerializedName("client") var client: Client? = Client()

)

data class Product(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("organization_id") var organizationId: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("product_count") var productCount: Int? = null,
    @SerializedName("container") var container: Int? = null,
    @SerializedName("container_status") var containerStatus: Int? = null,
    @SerializedName("photo") var photo: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null

)

data class City(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("organization_id") var organizationId: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null

)

data class Area(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("organization_id") var organizationId: Int? = null,
    @SerializedName("city_id") var cityId: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null

)

data class Client(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("organization_id") var organizationId: Int? = null,
    @SerializedName("user_id") var userId: Int? = null,
    @SerializedName("city_id") var cityId: Int? = null,
    @SerializedName("area_id") var areaId: Int? = null,
    @SerializedName("fullname") var fullname: String? = null,
    @SerializedName("street") var street: String? = null,
    @SerializedName("home_number") var homeNumber: String? = null,
    @SerializedName("entrance") var entrance: String? = null,
    @SerializedName("floor") var floor: String? = null,
    @SerializedName("apartment_number") var apartmentNumber: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("location") var location: String? = null,
    @SerializedName("login") var login: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("balance") var balance: String? = null,
    @SerializedName("container") var container: Int? = null,
    @SerializedName("bonus") var bonus: Int? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("phone2") var phone2: String? = null,
    @SerializedName("activated_at") var activatedAt: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("city") var city: City? = City(),
    @SerializedName("area") var area: Area? = Area()

)