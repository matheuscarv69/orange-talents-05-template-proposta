package proposta.entities.card.components;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import proposta.core.feignClients.accounts.client.AccountsClient;
import proposta.core.feignClients.accounts.response.CardRes;
import proposta.entities.card.entities.Card;
import proposta.entities.proposal.entities.Proposal;
import proposta.entities.proposal.repositories.ProposalRepository;

import java.util.List;

@Component
public class CardScheduledComponent {

    private final Logger logger = LoggerFactory.getLogger(CardScheduledComponent.class);

    @Autowired
    private AccountsClient accountsClient;

    @Autowired
    private ProposalRepository proposalRepository;

//    @Scheduled(fixedDelayString = "${delay.scheluded.card.association}")
    protected void scheduledCardAssociation() {
//        logger.info("Envio de requisicao para o sistema de cartoes");
        List<Proposal> elegiblesProposal = proposalRepository.findElegiblesProposals();

        try {
            elegiblesProposal.forEach(proposal -> {
                CardRes cardResponse = accountsClient.getCardByIdProposal(proposal.getId());
                Card card = cardResponse.toModel();

                proposal.setCard(card);

                // salva card e dueDate por meio do cascade MERGE
                proposalRepository.save(proposal);

                //substring para nao expor dados sensiveis
                logger.info("Cartao criado idCard:{}, titular:{}", card.getId().substring(0, 3), card.getOwner().substring(0, 3));
            });

        } catch (FeignException exception) {
            logger.warn("Deu ruim com uma request pro sistema de cartoes, corre aqui!");
        }
    }

}
