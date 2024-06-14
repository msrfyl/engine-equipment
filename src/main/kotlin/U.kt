import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object Equipment {

    val objectMapper: ObjectMapper = jacksonObjectMapper().registerModule(JavaTimeModule())
    fun writeValueAsString(any: Any): String = jacksonObjectMapper().writeValueAsString(any)
    fun <T> readValue(json: String, clazz: Class<T>): T = jacksonObjectMapper().readValue(json, clazz)
    fun <T> readValue(any: Any, clazz: Class<T>): T = jacksonObjectMapper().readValue(writeValueAsString(any), clazz)
    fun datetimeFormatter(pattern: String): DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
    fun toLocalDate(string: String, format: String? = null) : LocalDate {
        return LocalDate.parse(string, DateTimeFormatter.ofPattern(format ?: "yyyy-MM-dd"))
    }
    fun toLocalDateTime(string: String, format: String? = null) : LocalDateTime {
        return LocalDateTime.parse(string, DateTimeFormatter.ofPattern(format ?: "yyyy-MM-dd HH:mm:ss"))
    }
    fun toLocalTime(string: String, format: String? = null) : LocalTime {
        return LocalTime.parse(string, DateTimeFormatter.ofPattern(format ?: "HH:mm:ss"))
    }
    fun toString(dateTime: LocalDateTime, format: String? = null) : String {
        return dateTime.format(DateTimeFormatter.ofPattern(format ?: "yyyy-MM-dd HH:mm:ss"))
    }
    fun toString(date: LocalDate, format: String? = null) : String {
        return date.format(DateTimeFormatter.ofPattern(format ?: "yyyy-MM-dd"))
    }
    fun toString(time: LocalTime, format: String? = null) : String {
        return time.format(DateTimeFormatter.ofPattern(format ?: "HH:mm:ss"))
    }
    fun startAtDay(date: LocalDate) : LocalDateTime {
        return LocalDateTime.of(date, LocalTime.of(0,0,0))
    }
    fun endAtDay(date: LocalDate) : LocalDateTime {
        return LocalDateTime.of(date, LocalTime.of(23,59,59))
    }
}