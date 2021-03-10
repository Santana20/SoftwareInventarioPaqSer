package com.paqser.inventario.adapters.mysql.entities;

import com.paqser.inventario.domain.models.DetailProduct;
import com.paqser.inventario.domain.models.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Product")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String codProduct;

    @Column(length = 100, nullable = false)
    private String nameProduct;

    @ManyToOne
    @JoinColumn(name = "idBrand")
    private BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "idProductType")
    private ProductTypeEntity productType;

    @OneToMany(mappedBy = "productEntity",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<DetailProductEntity> detailProductEntityList;

    public ProductEntity() {
        // empty for framework
    }
    public ProductEntity(Product product) {
        BeanUtils.copyProperties(product, this);
    }

    public void fromProduct(Product product) {
        BeanUtils.copyProperties(product, this);
    }

    public Product toProduct() {
        Product product = new Product();

        BeanUtils.copyProperties(this, product);

        product.setBrand(this.brand.toBrand());
        product.setProductType(this.productType.toProductType());

        if (this.detailProductEntityList != null && this.detailProductEntityList.size() > 0) {
            List<DetailProduct> list = new ArrayList<>();
            for (DetailProductEntity detailProductEntity : this.detailProductEntityList) {
                DetailProduct toDetailProduct = detailProductEntity.toDetailProductForProductConstruct();
                list.add(toDetailProduct);
            }
            product.setDetailProductsList(list);
        }

        return product;
    }

    public Product toProductForDetailProductConstruct()
    {
        Product product = new Product();

        BeanUtils.copyProperties(this, product);

        product.setBrand(this.brand.toBrand());
        product.setProductType(this.productType.toProductType());

        return product;
    }
}
