package proposta.core.feignClients.accounts.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import proposta.core.feignClients.accounts.request.NotifyTravelNoticeReq;
import proposta.core.feignClients.accounts.request.ResponsibleSystemReq;
import proposta.core.feignClients.accounts.response.CardRes;
import proposta.core.feignClients.accounts.response.NotifyCardBlockingRes;
import proposta.core.feignClients.accounts.response.NotifyTravelNoticeRes;

@FeignClient(name = "Accounts-Client", url = "${api-accounts}")
public interface AccountsClient {

    // consumes e produces por padrao sao application/json
    @GetMapping("/api/cartoes?idProposta={idProposta}")
    CardRes getCardByIdProposal(@PathVariable("idProposta") Long idProposta);

    @PostMapping("/api/cartoes/{idCard}/bloqueios")
    NotifyCardBlockingRes notifyCardBlocking(@PathVariable String idCard,
                                             ResponsibleSystemReq system);

    @PostMapping("/api/cartoes/{idCard}/avisos")
    NotifyTravelNoticeRes notifyTravelNotice(@PathVariable String idCard,
                                                 NotifyTravelNoticeReq notifyTravelNoticeReq);



}
