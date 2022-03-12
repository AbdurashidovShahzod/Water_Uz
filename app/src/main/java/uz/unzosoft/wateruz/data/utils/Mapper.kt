package uz.unzosoft.wateruz.data.utils


abstract class Mapper<T, E> {

    abstract fun mapData(dto: E): T

    abstract fun mapEntity(dto: T): E

    fun mapListEntity(list: List<T>?): List<E>? {
        return list?.map {
            mapEntity(it)
        }
    }

    fun mapListData(list: List<E>?): List<T>? {
        return list?.map {
            mapData(it)
        }
    }
}

