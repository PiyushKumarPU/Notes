package com.learning.patterns.structural.decorator;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class OfferApplicationDemo {

    static void main(String[] args) {

        PaymentProcessor processor = new BasicPaymentProcessor();

        List<OfferType> activeOffers = List.of(
                OfferType.FESTIVAL,      // first
                OfferType.DYNAMIC_PRICING, // then
                OfferType.CASHBACK         // finally
        );
        processor = applyOffers(processor, activeOffers);

        processor.processPayment(2000);
    }


    public static PaymentProcessor applyOffers(PaymentProcessor baseProcessor, List<OfferType> offers) {
        AtomicReference<PaymentProcessor> processor = new AtomicReference<>(baseProcessor);

        offers.stream()
                .sorted(Comparator.comparingInt(OfferType::getPriority))
                .forEach(offer -> {
                    processor.set(switch (offer) {
                        case FESTIVAL -> new FestivalOfferDecorator(processor.get());
                        case DYNAMIC_PRICING -> new DynamicPricingDecorator(processor.get());
                        case CASHBACK -> new CashbackOfferDecorator(processor.get());
                    });
                });

        return processor.get();
    }

}
