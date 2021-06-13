package proposta.core.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ProposalMetrics {

    private final MeterRegistry meterRegistry;
    private Counter proposalsCreatedCounter;
    private Timer consultProposalTimer;

    public ProposalMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.initCounters();
    }

    private void initCounters() {
        this.proposalsCreatedCounter = this.meterRegistry.counter("propostas_criadas");
        this.consultProposalTimer = this.meterRegistry.timer("timer_consulta_proposta_por_id");
    }

    public void incrementProposalsCounter() {
        proposalsCreatedCounter.increment();
    }

    public void getTimerConsultProposal(Duration durantion) {
        this.consultProposalTimer.record(durantion);
    }


}
