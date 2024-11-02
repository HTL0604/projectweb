package ute.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_item")
@NamedQuery(name = "CartItem.findAll", query = "SELECT ci FROM CartItem ci")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int _id;

	@ManyToOne
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart; // Tham chiếu đến Cart

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "_id", nullable = false, insertable = false, updatable = false)
	private Product product; // Tham chiếu đến Product

	@ElementCollection
	@Column(nullable = false)
	private List<String> styleValueIds; // List of selected style value IDs

	@Column(nullable = false)
	private int count; // Quantity of the product

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
		updatedAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}

	// Mối quan hệ one-to-one với StyleValue
	@OneToOne(mappedBy = "cartItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private StyleValue styleValue; // Tham chiếu đến StyleValue
}