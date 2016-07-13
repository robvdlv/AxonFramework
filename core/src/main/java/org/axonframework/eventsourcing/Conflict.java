package org.axonframework.eventsourcing;

import org.axonframework.eventsourcing.eventstore.DomainEventStream;

/**
 * @author Joris van der Kallen
 * @since 3.0
 */
public class Conflict {

    private String aggregateIdentifier;
    private Long aggregateVersion;
    private Long expectedVersion;
    private DomainEventStream conflictingEvents;
    private boolean resolved;

    public Conflict(String aggregateIdentifier, Long aggregateVersion) {
        this(aggregateIdentifier, aggregateVersion, null, DomainEventStream.of());
    }

    public Conflict(String aggregateIdentifier, Long aggregateVersion, Long expectedVersion,
                    DomainEventStream conflictingEvents) {
        this.aggregateIdentifier = aggregateIdentifier;
        this.aggregateVersion = aggregateVersion;
        this.expectedVersion = expectedVersion;
        this.conflictingEvents = conflictingEvents;
        this.resolved =
                expectedVersion == null || aggregateVersion == null || !expectedVersion.equals(aggregateVersion);
    }

    public String getAggregateIdentifier() {
        return aggregateIdentifier;
    }

    public long getAggregateVersion() {
        return aggregateVersion;
    }

    public Long getExpectedVersion() {
        return expectedVersion;
    }

    public DomainEventStream getConflictingEvents() {
        return conflictingEvents;
    }

    public void markResolved() {
        resolved = true;
    }

    public boolean isResolved() {
        return resolved;
    }
}