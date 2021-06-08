package proposta.core.feignClients.accounts.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import proposta.core.feignClients.accounts.response.CardRes;

@FeignClient(name = "Accounts-Client", url="${api-accounts}")
public interface AccountsClient {

    // consumes e produces por padrao sao application/json
    @GetMapping("/api/cartoes?idProposta={idProposta}")
    CardRes getCardByIdProposal(@PathVariable("idProposta") Long idProposta);

}
