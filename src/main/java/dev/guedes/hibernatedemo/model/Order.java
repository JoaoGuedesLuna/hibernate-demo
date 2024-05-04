package dev.guedes.hibernatedemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author João Guedes
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "orders")
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "Order.findAllByAccountId", query = "SELECT o FROM orders o WHERE o.account.id = :accountId")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "order_date", nullable = false, columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime orderDate;

    @OneToMany
    @JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(name = "order_fk",
            foreignKeyDefinition = "FOREIGN KEY (order_id) REFERENCES Orders(id) ON UPDATE CASCADE ON DELETE CASCADE")
    )
    @Fetch(FetchMode.JOIN)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<Item> items;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false, foreignKey = @ForeignKey(name = "account_fk",
            foreignKeyDefinition = "FOREIGN KEY (account_id) REFERENCES accounts(id) ON UPDATE CASCADE ON DELETE CASCADE")
    )
    private Account account;

}
