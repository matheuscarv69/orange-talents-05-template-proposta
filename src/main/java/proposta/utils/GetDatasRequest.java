package proposta.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class GetDatasRequest {

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * Metodo para que recebe duas strings e as seta com
     * os Ip do cliente da requisicao e o user agent usado.
     */
    public void getClientIpAndUserAgent(String clientIp, String userAgent) {
        clientIp = getClientIp();
        userAgent = getUserAgent();
    }

    /**
     * Função para pegar o ip do client que realizou uma request,
     * o header x-forwarded-for é para pegar o ip real do client caso
     * o mesmo utilize algum proxy, caso o seu retorno seja null,
     * é ip do client é buscado com o getRemoteAddr
     */
    public String getClientIp() {
        String clientIp = httpServletRequest.getHeader("X-FORWARDED-FOR");

        if (clientIp == null) {
            clientIp = httpServletRequest.getRemoteAddr();
        }

        return clientIp;
    }

    /**
     * Função para pegar o User-Agent que realizou uma request,
     * o header User-Agent é para pegar esse User-Agent;
     */
    public String getUserAgent() {
        return httpServletRequest.getHeader("User-Agent");
    }

}
