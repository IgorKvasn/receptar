package sk.kvasnicka.receptar.server.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import java.io.IOException
import javax.servlet.http.HttpServletResponse

class JsonJacksonHelper {

    companion object {
        private fun create(): ObjectMapper {
            val mapper = ObjectMapper()
            configure(mapper)
            return mapper
        }

        val objectMapper = create()
        fun configure(objectMapper: ObjectMapper) {
            objectMapper.enable(MapperFeature.DEFAULT_VIEW_INCLUSION)
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }

        @Throws(IOException::class)
        fun writeObject(response: HttpServletResponse, `object`: Any?) {
            writeObject(response, HttpServletResponse.SC_OK, `object`)
        }

        @Throws(IOException::class)
        fun writeObject(response: HttpServletResponse, status: Int, `object`: Any?) {
            response.contentType = "application/json; charset=UTF-8"
            response.status = status
            val out = response.writer
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(out, `object`)
            out.flush()
            response.flushBuffer()
        }

        fun serialize(obj: Any?, canThrowException: Boolean): String? {
            return if (obj == null) null else try {
                objectMapper.writeValueAsString(obj)
            } catch (e: Exception) {
                if (canThrowException) {
                    if (e is RuntimeException) throw e
                    throw IllegalStateException(e)
                }
                null
            }
        }

        fun <T> deserialize(json: String?, cls: Class<T>?, canThrowException: Boolean): T? {
            return if (json == null) null else try {
                objectMapper.readValue(json, cls)
            } catch (e: Exception) {
                if (canThrowException) {
                    if (e is RuntimeException) throw e
                    throw IllegalStateException(e)
                }
                null
            }
        }
    }
}
