package dev.guedes.hibernatedemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author João Guedes
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "accounts")
@Table(
        name = "accounts",
        uniqueConstraints = {@UniqueConstraint(name = "email_uk", columnNames = "email")},
        indexes = {@Index(name = "email_idx", columnList = "email")}
)
@NamedQueries({
        @NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM accounts a WHERE a.email = :email")
})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 256, unique = true, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

}
