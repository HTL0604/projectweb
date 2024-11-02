package ute.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_follow_product")
@NamedQuery(name = "UserFollowProduct.findAll", query = "SELECT ufp FROM UserFollowProduct ufp")
public class UserFollowProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int _id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false) // Foreign key đến User
	private User user;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "_id", nullable = false, insertable = false, updatable = false)
	private Product product; // Tham chiếu đến Product

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
}