package sk.kvasnicka.receptar.server.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder




@Configuration
class WebConfig : WebMvcConfigurer {

  override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
    super.configureMessageConverters(converters)
    val builder = Jackson2ObjectMapperBuilder()
    builder.defaultViewInclusion(true)
    converters.add(MappingJackson2HttpMessageConverter(builder.build()))
  }

  override fun extendMessageConverters(converters: List<HttpMessageConverter<*>?>) {
    for (converter in converters) {
      if (converter is MappingJackson2HttpMessageConverter) {
        val objectMapper = converter.objectMapper
//        objectMapper.registerModule(buildContractSerializer())
        JsonJacksonHelper.configure(objectMapper)
        break
      }
    }
  }
}
