package store.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @NotEmpty(message = "Данное поле должно быть заполнено!")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Данное поле должно быть заполнено!")
    @Column(name = "author")
    private String author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotEmpty(message = "Данное поле должно быть заполнено!")
    @Column(name = "year")
    private String year;

    @NotNull(message = "Данное поле должно быть заполнено!")
    @Column(name = "price")
    private Integer price;

    @NotEmpty(message = "Данное поле должно быть заполнено!")
    @Column(name = "description")
    private String description;

    private String filename;

    public Product() {
    }

    public Product(String title, String author, Category genre, String year, Integer price,String description) {
        super();
        this.title = title;
        this.author = author;
        this.category = genre;
        this.year = year;
        this.price = price;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
