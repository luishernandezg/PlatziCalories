package com.example.platzicalories.Data.remote.repository
import android.util.Log
import com.example.platzicalories.Data.local.dao.TrackerDao
import com.example.platzicalories.Data.local.mapper.toTrackedFood
import com.example.platzicalories.Data.local.mapper.toTrackedFoodEntity
import com.example.platzicalories.Data.remote.api.OpenFoodApi
import com.example.platzicalories.Data.remote.mapper.toTrackableFood
import com.example.platzicalories.domain.tracker.model.TrackableFood
import com.example.platzicalories.domain.tracker.model.TrackedFood
import com.example.platzicalories.domain.tracker.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val api: OpenFoodApi,
    private val dao: TrackerDao
): TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            Log.d("TAG", "Call api.searchFood")
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(
                searchDto.products
                    .filter {
                        val calculatedCalories =
                            (it.nutriments.carbohydrates100g?.times(4f) ?: 0.0) +
                                    (it.nutriments.proteins100g?.times(4f) ?: 0.0) +
                                    (it.nutriments.fat100g?.times(9f) ?: 0.0)
                        val lowerBound = calculatedCalories * 0.99f
                        val upperBound = calculatedCalories * 1.01f
                        if (it.nutriments.energyKcal100g == null) {
                            0.0 in (lowerBound..upperBound)
                        } else {
                            it.nutriments.energyKcal100g in (lowerBound..upperBound)
                        }

                    }
                    .mapNotNull { it.toTrackableFood() }
            )

        }
        catch (e: Exception) {
            Log.d("TAG", "Call api.searchFood ERROR")
            e.printStackTrace()
            Result.failure(e)
        }

    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(day = localDate.dayOfMonth, month = localDate.monthValue, year = localDate.year)
            .map { entities ->
                entities.map {
                    it.toTrackedFood()
                }
            }
    }
}