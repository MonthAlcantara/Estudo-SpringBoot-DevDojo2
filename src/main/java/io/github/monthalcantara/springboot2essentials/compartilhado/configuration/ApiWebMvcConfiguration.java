package io.github.monthalcantara.springboot2essentials.compartilhado.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ApiWebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        PageableHandlerMethodArgumentResolver pageableHandler = new PageableHandlerMethodArgumentResolver();
        /*
         * Setando o modo default de Qual será a primeira página exibida na paginação. Nesse caso a primeira (página zero)
         * Quantos itens por pagina. Nesse Caso 5.
         * Para receber algo diferente o cliente da requisição mandará por parametro .../page=1?size=10
         */
        pageableHandler.setFallbackPageable(PageRequest.of(0, 5));
        resolvers.add(pageableHandler);
    }
}
