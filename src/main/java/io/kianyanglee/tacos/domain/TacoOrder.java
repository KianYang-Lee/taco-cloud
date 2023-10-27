package io.kianyanglee.tacos.domain;

import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Taco_Order")
@AllArgsConstructor
@NoArgsConstructor
public class TacoOrder implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt = new Date();
    
    @Nonnull
    @NotNull
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;
    @Nonnull
    @NotNull
    @NotBlank(message = "Street is required")
    private String deliveryStreet;
    @Nonnull
    @NotNull
    @NotBlank(message = "City is required")
    private String deliveryCity;
    @Nonnull
    @NotNull
    @NotBlank(message = "State is required")
    @Size(max = 2, message = "Maximum character length for state is 2")
    private String deliveryState;
    @NotBlank(message = "Zip code is required")
    private String deliveryZip;
    @Nonnull
    @NotNull
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;
    @Nonnull
    @NotNull
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Nonnull
    @NotNull
    @Pattern(regexp = "^[0-9]{3,4}$", message = "Invalid CVV")
    private String ccCVV;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
