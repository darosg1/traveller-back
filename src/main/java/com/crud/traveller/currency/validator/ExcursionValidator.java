package com.crud.traveller.currency.validator;

import com.crud.traveller.entity.Excursion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExcursionValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger ( ExcursionValidator.class );

    public List<Excursion> validateExcursions(final List<Excursion> excursionsList) {
        LOGGER.info ( "Starting filtering lists..." );
        List<Excursion> filteredExcursions = excursionsList.stream ()
                .filter ( excursion -> !excursion.getFlights ().isEmpty ())
                .collect ( Collectors.toList ());
        LOGGER.info ( "Excursion list has been filtered. Current list size: " + filteredExcursions.size () );
        return filteredExcursions;
    }
}
