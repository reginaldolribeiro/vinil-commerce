package com.vinilcommerce.vinilcommerce.repository

import com.vinilcommerce.vinilcommerce.model.Cashback
import com.vinilcommerce.vinilcommerce.model.Genre
import org.springframework.data.jpa.repository.JpaRepository
import java.time.DayOfWeek

interface CashbackRepository : JpaRepository<Cashback, Long>{
    fun findByGenreAndDayOfWeek(genre: Genre, dayOfWeek: DayOfWeek): Cashback
}