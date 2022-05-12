package domain;

import domain.enums.AvailableCurrency;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Currency {
    private final AvailableCurrency name;
}
