package com.vinilcommerce.vinilcommerce.service

import com.vinilcommerce.vinilcommerce.model.Genre
import com.vinilcommerce.vinilcommerce.repository.CashbackRepository
import org.springframework.stereotype.Service
import java.time.DayOfWeek

@Service
class CashbackService(val cashbackRepository: CashbackRepository) {
    fun getCashback(genre: Genre, dayOfWeek: DayOfWeek) =
        cashbackRepository.findByGenreAndDayOfWeek(genre, dayOfWeek)
}